package view;

public class ConsoleLogger implements MyLogger{
    private static ConsoleLogger consoleLogger;

    private ConsoleLogger() {}

    public static ConsoleLogger getInstance() {
        if (consoleLogger == null) {
            consoleLogger = new ConsoleLogger();
        }
        return consoleLogger;
    }

    @Override
    public void logAction(String text) {
        System.out.println("Действие: " + text);
    }
}
