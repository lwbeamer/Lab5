package Commands;

import Control.CollectionOperator;
import Control.Console;
import WorkerData.Worker;
import Exceptions.EmptyCollectionException;
import Exceptions.WrongArgumentException;

/**
 * Команда "print_field_descending_status". Сортирует коллекцию в порядке убывания (по выбранному полю в compareTo) и выводит значения полей Status всех элементов
 */
public class PrintFieldDescendingStatus implements Executable{

    private final CollectionOperator collectionOperator;

    public PrintFieldDescendingStatus(CollectionOperator collectionOperator) {
        this.collectionOperator = collectionOperator;
    }


    /**
     * Описание команды
     * @return Строка - описание
     */
    public static String description(){
        return "print_field_descending_status : вывести значения поля status всех элементов в порядке убывания";
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
            collectionOperator.sortReverseCollection();
            for (Worker worker: collectionOperator.getWorkersCollection()){
                Console.println(worker.getStatus()+"\n");
            }
            collectionOperator.sortCollection();
            return true;
        } catch (WrongArgumentException e) {
            Console.println("Для этой комманды не нужен аргумент, попробуйте ещё раз");
        } catch (EmptyCollectionException e) {
            Console.printerror("Коллекция пуста!");
        }
        return false;
    }
}
