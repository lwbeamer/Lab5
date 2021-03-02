package Commands;

import Control.CollectionOperator;
import WorkerData.Status;
import Exceptions.EmptyCollectionException;
import Exceptions.WrongArgumentException;

/**
 * Команда "filter_by_status". Выводит все элементы, у которых status равен введённому с помощью аргумента значению.
 */
public class FilterByStatus implements Executable{

    private final CollectionOperator collectionOperator;

    public FilterByStatus(CollectionOperator collectionOperator) {
        this.collectionOperator = collectionOperator;
    }

    /**
     * Описание команды
     * @return Строка - описание
     */
    public static String description() {
        return "filter_by_status status : вывести элементы, значение поля status которых равно заданному";
    }

    /**
     * Выполнение команды
     * @param argument - аргумент команды
     * @return Статус выполнения команды
     */
    @Override
    public boolean execute(String argument) {
        try {
            argument = argument.trim();
            if (argument.isEmpty()) throw new WrongArgumentException();
            if (collectionOperator.collectionSize() == 0) throw new EmptyCollectionException();
            Status status = Status.valueOf(argument.toUpperCase());
            String filteredInfo = collectionOperator.statusFilteredInfo(status);
            if (!filteredInfo.isEmpty()) {
                System.out.println(filteredInfo);
                return true;
            } else System.out.println("В коллекции нет рабочих с выбранным статусом!");
        } catch (WrongArgumentException e) {
            System.out.println("Укажите аргумент!");
        } catch (EmptyCollectionException e) {
            System.err.println("Коллекция пуста!");
        } catch (IllegalArgumentException e) {
            System.err.println("Статуса нет в списке!");
            System.err.println("Список возможных статусов - " + Status.getValues());
        }
        return false;
    }
}
