package Commands;

import Control.CollectionOperator;
import Exceptions.WrongArgumentException;

/**
 * Команда "add". Добавляет элемент в коллекцию, узнав у пользователя все нужные данные
 */
public class Clear implements Executable{

    private final CollectionOperator collectionOperator;

    public Clear(CollectionOperator collectionOperator) {
        this.collectionOperator = collectionOperator;
    }

    /**
     * Описание команды
     * @return Строка - описание
     */
    public static String description() {
        return "clear : очистить коллекцию";
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
            collectionOperator.clearCollection();
            System.out.println("Коллекция очищена!");
            return true;
        } catch (WrongArgumentException e) {
            System.out.println("Для этой комманды не нужен аргумент, попробуйте ещё раз");
        }
        return false;
    }
}
