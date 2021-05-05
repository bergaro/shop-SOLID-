import products.*;
import user.Basket;
import user.Order;
import user.User;
import view.ConsoleViewer;
import view.Viewer;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class ShopManager {
    private final Viewer viewer = ConsoleViewer.getInstance();            // Отобраение на экран
    private List<Product> showcase;                                       // Витрина товаров
    private final ShopDelivery delivery = ShopDelivery.getInstance();     // Сборщик заказов
    /**
     * Создание фиктивной витрины товаров
     * @return витрина товаров магазина
     */
    private List<Product> initProductShowcase() {
        Product cheese = Cheese.build("Сыр российский", new BigDecimal(350))
                .pWeight(0.331)
                .pCountry("Россия")
                .pCheeseBase("Коровье молоко")
                .pQuantity(20);
        cheese.setRating(7);
        Product potato = Potato.build("Картофель белый", new BigDecimal("32.50"))
                .pWeight(10.00)
                .pCountry("Россия")
                .pVariety("Белая роза")
                .pQuantity(30);
        potato.setRating(5);
        potato.setRecommendProduct(true);
        Product strawberry = Strawberry.build("Клубника 'Лорд'", new BigDecimal(269))
                .pCountry("Пекин")
                .pBerriesPerPack(15)
                .pQuantity(40);
        strawberry.setRating(11);
        Product watermelon = Watermelon.build("Арбуз 'Гавриш'",  new BigDecimal(95))
                .pCountry("Дания")
                .pWatermelonVariety("Гавриш")
                .pQuantity(50);
        watermelon.setRecommendProduct(true);
        List<Product> showcase = new ArrayList<>();
        showcase.add(cheese);
        showcase.add(potato);
        showcase.add(strawberry);
        showcase.add(watermelon);
        return showcase;
    }
    /**
     * Создаёт витрину товаров, если есть необходимость
     */
    private void needInitShowcase() {
        if(showcase == null) {
            showcase = initProductShowcase();
        }
    }
    /**
     * Формирование заказа и корзины пользователя
     * и увеличение рейтинга товара
     * @param productsOrder
     * @param user
     */
    public void initOrder(Map<Integer, Integer> productsOrder, User user) {
        Product product;
        int productNumber;
        List<Integer> productIdx = new ArrayList<>(productsOrder.keySet());
        for (int numProd : productIdx) {
            product = showcase.get(numProd - 1);
            productNumber = productsOrder.get(numProd);
            if (productNumber != 0 && product.getProductQuantity() >= productNumber) {
                user.createOrder(product, productNumber);
                product.iterationRating();
            } else {
                viewer.printText("Товар под индексом - " + productIdx + " не найден или не существует.");
            }
        }
        user.viewBasket();
    }
    /**
     * Передача заказа в отдел доставки
     * @param user заказчик
     */
    public void assemblyOrder(User user) {
        delivery.assemblyUserOrder(user, OrderStatus.ASSEMBLY_ORDER);
    }
    /**
     * Отменяет заказ:
     * - Очищает корзину заказов по пользователю
     * - Меняет статус заказа
     * - Обнуляет сумму корзины
     * - Уменьшение рейтинга заказа
     * @param user заказчик
     */
    public void cancelOrder(User user) {
        Order order = user.getOrder();
        Basket basket = order.getBasket();
        Map<Product, Integer> userBasket = basket.getRealBasket();
        List<Product> products = new ArrayList<>(userBasket.keySet());
        for(Product product : products) {
            product.decrementRating();
        }
        userBasket.clear();
        delivery.assemblyUserOrder(user, OrderStatus.CANCELLED_ORDER);
        basket.resetToZero();
    }
    /**
     * Показать доступные товары
     */
    public void showAvailableProducts() {
        int count = 0;
        needInitShowcase();
        for (Product product : showcase) {
            viewer.printText(++count + ") " + product.toString());
        }
        System.out.println("\n");
    }
    /**
     * Фильтр товаров по наименованию или стране производителю
     * @param key Поисковое слово
     */
    public void filterProducts(String key) {
        int count = 0;
        needInitShowcase();
        for(Product product : showcase) {
            String[] pNameComponents = product.getProductName().split(" ");
            if(key.equals(product.getProductName())) {
                viewer.printText(++count + ") " + product.toString());
            } else if(key.equals(product.getCountryOfOrigin())) {
                viewer.printText(++count + ") " + product.toString());
            } else {
                for(String nameComponent : pNameComponents) {
                    if(key.equals(nameComponent)) {
                        viewer.printText(++count + ") " + product.toString());
                    }
                }
            }
        }
        viewer.printText("\n");
    }
    /**
     * Фильрует товары по рейтингу
     */
    public void filterProductsByRating() {
        needInitShowcase();
        getProductsByRating();
    }
    /**
     * Вспомогательный метод для фильтрации товаров по рейтингу
     */
    private void getProductsByRating() {
        List<Product> sortProducts = showcase.stream()
                .sorted(Comparator.comparingInt(Product::getRating).reversed())
                .collect(Collectors.toList());
        viewer.printText("Товары по рейтингу: \n");
        for(Product product : sortProducts) {
            viewer.printText("Рейтинг товара: " + product.getRating() + ", " + product.toString());
        }
        viewer.printText("\n");
    }
    /**
     * Получить список рекомендованных к покупке товаров
     */
    public void getRecommendProducts() {
        needInitShowcase();
        List<Product> recommendShowcase = showcase.stream()
                .filter(Product::getRecommendProduct)
                .sorted(Comparator.comparingInt(Product::getRating))
                .collect(Collectors.toList());
        for(Product product : recommendShowcase) {
            viewer.printText("Рекомендуемые товары: \n - " + product + ", рейтинг товара: " + product.getRating());
        }
        viewer.printText("\n");
    }
}
