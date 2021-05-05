import user.Basket;
import user.Order;
import user.User;
import view.ConsoleLogger;
import view.ConsoleViewer;
import view.MyLogger;
import view.Viewer;

public class ShopDelivery {
    private static ShopDelivery delivery;
    private final Viewer viewer;                                          // Отбражение на экране
    private final MyLogger logger;                                        // Логер

    private ShopDelivery() {
        viewer = ConsoleViewer.getInstance();
        logger = ConsoleLogger.getInstance();
    }

    public static ShopDelivery getInstance() {
        if(delivery == null) {
            delivery = new ShopDelivery();
        }
        return delivery;
    }
    /**
     * Процесс сборки заказа и смена его статуса
     * @param user пользователь
     * @param status статус заказа
     */
    public void assemblyUserOrder(User user, OrderStatus status) {
        Order userOrder;
        Basket userBasket;
        logger.logAction("Действия над заказом для - " + user.getUserName());
        userOrder = user.getOrder();
        userBasket = userOrder.getBasket();
        logger.logAction("Список заказа: " + userBasket.getProductList());
        userOrder.setStatus(status.name());
        logger.logAction("Смена статуса заказа на: " + userOrder.getStatus() + "\n");
        viewer.printText("Статус заказа для " + user.getUserName() +
                " изменён на - " + userOrder.getStatus() + "\n");
    }

}
