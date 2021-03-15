package Commands;

import Control.CollectionOperator;
import Control.Console;
import Control.UserDataReceiver;
import WorkerData.Worker;
import Exceptions.ScriptErrorException;
import Exceptions.WrongArgumentException;

import java.time.ZonedDateTime;

/**
 * Команда "add". Добавляет элемент в коллекцию, узнав у пользователя все нужные данные
 */
public class AddIfMax implements Executable{

    private final CollectionOperator collectionOperator;
    private final UserDataReceiver userDataReceiver;

    public AddIfMax(CollectionOperator collectionOperator, UserDataReceiver userDataReceiver) {
        this.collectionOperator = collectionOperator;
        this.userDataReceiver = userDataReceiver;
    }

    /**
     * Описание команды
     * @return Строка - описание
     */
    public static String description() {
        return "add_if_max {element} : добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции";
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
            Worker workerToAdd = new Worker(collectionOperator.generateId(), userDataReceiver.askName(), userDataReceiver.askCoordinates(), ZonedDateTime.now(), userDataReceiver.askSalary(), userDataReceiver.askPosition(), userDataReceiver.askStatus(), userDataReceiver.askPerson());
            if (collectionOperator.collectionSize() == 0 || workerToAdd.compareTo(collectionOperator.getLast()) > 0) {
                collectionOperator.addToCollection(workerToAdd);
                Console.println("Рабочий успешно добавлен!");
                return true;
            } else Console.printerror("Значение рабочего меньше, чем значение наибольшего из рабочих!");
        } catch (WrongArgumentException e) {
            Console.println("Для этой комманды не нужен аргумент, введите данные после ввода команды");
        } catch (ScriptErrorException e) {}
        return false;
    }
}
