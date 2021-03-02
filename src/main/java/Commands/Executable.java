package Commands;

/**
 * Интерфейс для всех команд. Содержит один метод который отвечает за выполнение команды.
 */
public interface Executable {
    boolean execute(String argument);
}
