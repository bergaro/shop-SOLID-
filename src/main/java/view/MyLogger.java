package view;

/**
 * Создано для демонстрации принципа разделения интерейсов.
 * Не хочу, чтобы интерфейс Viewer нёс ответственность за логирование.
 */
public interface MyLogger {
    void logAction(String sthText);
}
