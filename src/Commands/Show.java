package Commands;

import Control.CollectionOperator;
import Control.Console;
import Exceptions.WrongArgumentException;

/**
 * Команда "show". Выводит в консоль информацию обо всех элементах коллекции
 */
public class Show implements Executable{

    private final CollectionOperator collectionOperator;

    public Show(CollectionOperator collectionOperator) {
        this.collectionOperator = collectionOperator;
    }

    /**
     * Описание команды
     * @return Строка - описание
     */
    public static String description(){
        return "show : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
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
            Console.println(collectionOperator.workersDesc());
            return true;
        } catch (WrongArgumentException e) {
            Console.println("Для этой комманды не нужен аргумент, попробуйте ещё раз");
        }
        return false;
    }
}
