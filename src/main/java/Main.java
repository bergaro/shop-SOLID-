import user.User;
import view.ConsoleLogger;
import view.ConsoleViewer;
import view.MyLogger;
import view.Viewer;

import java.util.*;

/**
 * Согласно ДЗ:
 * 1) Старался избегать магических чисел, но не везде смог (выбор пунктов меню)
 * 2) Где возможно устранл повторения кода (Например - ShopManager::needInitShowcase())
 * 3) Принцип единственной ответственности (Например - классы user/Basket, user/Order, user/User)
 * 4) Принцип открытости/закрытости (Наверное наследование продуктов в products/* или реализация интерфейсов в user/)
 * 5) Принцип замены Барбары Лисков (Наследование от Product в products/)
 * 6) Принцип сегрегации (разделения) интерфейса (Разделение MyLogger и Viewer в view/)
 * 7) Принцип инверсии зависимостей (Например создание провуктов в ShopManager::initProductShowcase())
 */
public class Main {

    private static User user = new User("Владислав"); // Передаем статично имя пользователя
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String strNumber;
        int menuSize;
        int menuItem;

        Viewer viewer = ConsoleViewer.getInstance();
        MyLogger logger = ConsoleLogger.getInstance();
        ShopManager manager = new ShopManager();

        while (true) {
            menuSize = viewer.inputMainText();
            strNumber = scanner.nextLine();
            menuItem = selectMenuItem(strNumber, menuSize);
            if(menuItem == menuSize) {
                logger.logAction("Выбран пункт - " + menuItem + ". Выход из системы.");
                scanner.close();
                break;
            }
            logger.logAction("Выбран пункт - " + menuItem + ".");
            if(menuItem == 1) {
                manager.showAvailableProducts();
            } else if (menuItem == 2) {
                viewer.printText("Введите полностью/частично: \n" +
                        "Наименование товара или страну производитель.");
                String keyword = scanner.nextLine();
                logger.logAction("Пользователь ввёл: " + keyword);
                manager.filterProducts(keyword);
            } else if (menuItem == 3) {
                constructProductBasket(viewer, manager);
            } else if (menuItem == 4) {
                manager.assemblyOrder(user);
            } else if (menuItem == 5) {
                manager.cancelOrder(user);
            } else if (menuItem == 6) {
                manager.filterProductsByRating();
            } else if(menuItem == 7) {
                manager.getRecommendProducts();
            }
        }
    }

    /**
     * Формирование пользовательской корзины
     * @param viewer
     * @param manager
     */
    static void constructProductBasket( Viewer viewer, ShopManager manager) {
        int pIdx;
        int pQuantity;
        int nextIteration;
        String clearScan;
        Map<Integer, Integer> productsOrder = new HashMap<>();
        while (true){
            viewer.printText("Выберите индекс товара:");
            clearScan = scanner.nextLine();
            pIdx = Integer.parseInt(clearScan);
            viewer.printText("Введите желаемое кол-во товара: ");
            clearScan = scanner.nextLine();
            pQuantity = Integer.parseInt(clearScan);
            productsOrder.put(pIdx, pQuantity);
            viewer.printText("Для выхода введите '0' или любое число для продолжения");
            clearScan = scanner.nextLine();
            nextIteration = Integer.parseInt(clearScan);
            if(nextIteration == 0) {
                break;
            }
        }
        manager.initOrder(productsOrder, user);
    }

    static int selectMenuItem(String strNumber, int menuItem) {
        int action = 0;
        try {
            action = Integer.parseInt(strNumber);
            if(action > 0 && action <= menuItem) {
                System.out.println(action);
            } else {
                System.out.println("Указанного пункта не существует.");
            }
        } catch (Exception ex) {
            System.out.println("Не корректный ввод: " + ex.getMessage());
        }
        return action;
    }

}
