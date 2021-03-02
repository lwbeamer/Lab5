package Commands;

import Control.CollectionOperator;
import WorkerData.Worker;
import Exceptions.EmptyCollectionException;
import Exceptions.WorkerNotFoundException;
import Exceptions.WrongArgumentException;

/**
 * Команда "remove_by_id". Удаляет выбранный по ID элемент коллекции
 */
public class RemoveById implements Executable{

    private final CollectionOperator collectionOperator;

    public RemoveById(CollectionOperator collectionOperator) {
        this.collectionOperator = collectionOperator;
    }

    /**
     * Описание команды
     * @return Строка - описание
     */
    public static String description(){
        return "remove_by_id id : удалить элемент из коллекции по его id";
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
            long id = Long.parseLong(argument);
            Worker workerToRemove = collectionOperator.getById(id);
            if (workerToRemove == null) throw new WorkerNotFoundException();
            collectionOperator.removeFromCollection(workerToRemove);
            System.out.println("Рабочий успешно удален!");
            return true;
        } catch (WrongArgumentException e) {
            System.out.println("Использование: '" + "remove" + "'");
        } catch (EmptyCollectionException e) {
            System.err.println("Коллекция пуста!");
        } catch (NumberFormatException e) {
            System.err.println("ID должен быть представлен числом!");
        } catch (WorkerNotFoundException e) {
            System.err.println("Рабочего с таким ID в коллекции нет!");
        }
        return false;
    }
}
