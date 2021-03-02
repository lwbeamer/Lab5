package Commands;

import Exceptions.WrongArgumentException;

/**
 * Команда "exit". Завершает программу, сохранения коллекции не происходит.
 */
public class Exit implements Executable{


    /**
     * Описание команды
     * @return Строка - описание
     */
    public static String description() {
        return "exit : завершить программу (без сохранения в файл)";
    }

    /**
     * Выполнение команды
     * @param argument - аргумент команды
     * @return Статус выполнения команды
     */
    @Override
    public boolean execute(String argument) {
        try {
            if (!argument.isEmpty()) throw new WrongArgumentException();
            return true;
        } catch (WrongArgumentException e) {
            System.out.println("Для этой комманды не нужен аргумент, попробуйте ещё раз");
        }
        return false;
    }
}
