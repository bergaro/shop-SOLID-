package user;

import products.Product;
import view.ConsoleLogger;
import view.ConsoleViewer;
import view.MyLogger;
import view.Viewer;


public class User {
    private String userName;                                        // имя пользователя
    private Order order;                                            // Заказ
    private MyLogger logger;                                        // Логирование действий
    private Viewer viewer;                                          // Вывод на экран

    public User(String userName) {
        logger = ConsoleLogger.getInstance();
        viewer = ConsoleViewer.getInstance();
        logger.logAction("создание пользователя - " + userName);
        order = new Order();
        this.userName = userName;
    }
    /**
     * Получить имя пользователя
     * @return имя пользователя
     */
    public String getUserName() {
        return userName;
    }
    /**
     * Получить заказ пользователя
     * @return заказ пользователя
     */
    public Order getOrder() {
        return order;
    }
    /**
     * Добавляет продукт и необходимое кол-во в корзину заказа
     * @param product
     * @param quantity
     */
    public void createOrder(Product product, int quantity) {
        order.addProductList(this, product, quantity);
    }
    /**
     * Отобразить корзину заказа
     */
    public void viewBasket() {
        viewer.printText(order.myBasket());
    }

    @Override
    public String toString() {
        return "Пользователь: " + userName;
    }
}
