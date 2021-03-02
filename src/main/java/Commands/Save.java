package Commands;

import Control.CollectionOperator;
import Exceptions.WrongArgumentException;

/**
 * Команда "save". Сохраняет текущую коллекцию в файл.
 */
public class Save implements Executable{

    private final CollectionOperator collectionOperator;

    public Save(CollectionOperator collectionOperator) {
        this.collectionOperator = collectionOperator;
    }

    /**
     * Описание команды
     * @return Строка - описание
     */
    public static String description(){
        return "save : сохранить коллекцию в файл";
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
            collectionOperator.saveCollection();
            return true;
        } catch (WrongArgumentException e) {
            System.out.println("Для этой комманды не нужен аргумент, попробуйте ещё раз");
        }
        return false;
    }
}
