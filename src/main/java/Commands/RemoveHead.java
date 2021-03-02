package Commands;

import Control.CollectionOperator;
import Exceptions.EmptyCollectionException;
import Exceptions.WrongArgumentException;

/**
 * Команда "remove_head". Удаляет первый элемент коллекции, перед этим выводит его в консоль.
 */
public class RemoveHead implements Executable{

    private final CollectionOperator collectionOperator;

    public RemoveHead(CollectionOperator collectionOperator) {
        this.collectionOperator = collectionOperator;
    }

    /**
     * Описание команды
     * @return Строка - описание
     */
    public static String description(){
        return "remove_head : вывести первый элемент коллекции и удалить его";
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
            if (collectionOperator.collectionSize() == 0) throw new EmptyCollectionException();
            System.out.println(collectionOperator.getWorkersCollection().getFirst().description());
            collectionOperator.removeFromCollection(collectionOperator.getWorkersCollection().getFirst());
        } catch (WrongArgumentException e) {
            System.out.println("Для этой комманды не нужен аргумент, попробуйте ещё раз");
        } catch (EmptyCollectionException e){
            System.err.println("Коллекция пуста!");
        }
        return false;
    }
}
