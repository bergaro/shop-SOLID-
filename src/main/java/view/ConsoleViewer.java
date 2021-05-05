package view;

public class ConsoleViewer implements Viewer {
    private static ConsoleViewer consoleViewer;

    private ConsoleViewer() {}

    public static ConsoleViewer getInstance() {
        if(consoleViewer == null) {
            consoleViewer = new ConsoleViewer();
        }
        return consoleViewer;
    }

    @Override
    public void printText(String sthStr) {
        System.out.println(sthStr);
    }

    @Override
    public int inputMainText() {
        String[] menuStr = new String[]{
                "1. Вывод доступных для покупки товаров;",
                "2. Фильтрация товаров по ключевым словам, ценам, производителям;",
                "3. Составление продуктовой корзины пользователя;",
                "4. Трекинг заказа в системе доставки;",
                "5. Отказаться от заказа;",
                "6. Вывод товаров по рейтингу",
                "7. Рекомендуемые продукты",
                "8. Выход из системы."
        };
        for(String menuItem : menuStr) {
            System.out.println(menuItem);
        }
        return menuStr.length;
    }


}
