package user;

import products.Product;
import view.ConsoleLogger;
import view.MyLogger;

public class Order {
    private User user;                                              // Пользователь
    private final Basket basket;                                    // Корзина товаров
    private final MyLogger logger;                                  // Логгер
    private String status;                                          // Статус заказа

    public Order() {
        basket = new Basket();
        logger = ConsoleLogger.getInstance();
    }
    /**
     * Установить статус заказа
     * @param status статус заказа
     */
    public void setStatus(String status) {
        this.status = status;
    }
    /**
     * Получить статус заказа
     * @return статус заказа
     */
    public String getStatus() {
        return status;
    }
    /**
     * Получить корзину заказа
     * @return
     */
    public Basket getBasket() {
        return basket;
    }
    /**
     * Добавляет товар в корзину и уменьшеает его кол-во на складе
     * @param user пользователь
     * @param product товар
     * @param quantity кол-во необходимо продукции
     */
    public void addProductList(User user, Product product, int quantity) {
        this.user = user;
        int pQuantity = product.getProductQuantity();
        if(pQuantity != 0 && pQuantity >= quantity) {
            product.setProductQuantity(pQuantity - quantity);
            basket.setProductInBasket(product, quantity);
            logger.logAction(product.getProductName() + " осталось - " + product.getProductQuantity() + " шт.");
            logger.logAction(product.getProductName() + " успешно добавлен в корзину.");
        }
    }
    /**
     * Предоставление содержимого корзины заказа
     * @return содержимое корзины заказа
     */
    public String myBasket() {
        return basket.getProductList();
    }

    @Override
    public String toString() {
        return user + ", " + basket.getProductList();
    }
}
