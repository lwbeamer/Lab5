package Commands;

import Control.CollectionOperator;
import Control.UserDataReceiver;
import WorkerData.Worker;
import Exceptions.ScriptErrorException;
import Exceptions.WrongArgumentException;

import java.time.ZonedDateTime;

/**
 * Команда "add". Добавляет элемент в коллекцию, узнав у пользователя все нужные данные
 */
public class AddIfMin implements Executable{

    private final CollectionOperator collectionOperator;
    private final UserDataReceiver userDataReceiver;

    public AddIfMin(CollectionOperator collectionOperator, UserDataReceiver userDataReceiver) {
        this.collectionOperator = collectionOperator;
        this.userDataReceiver = userDataReceiver;
    }

    /**
     * Описание команды
     * @return Строка - описание
     */
    public static String description() {
        return "add_if_min {element} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции";
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
            if (collectionOperator.collectionSize() == 0 || workerToAdd.compareTo(collectionOperator.getFirst()) < 0) {
                collectionOperator.addToCollection(workerToAdd);
                System.out.println("Рабочий успешно добавлен!");
                collectionOperator.sortCollection();
                return true;
            } else System.err.println("Значение рабочего больше, чем значение наименьшего из рабочих!");
        } catch (WrongArgumentException e) {
            System.out.println("Для этой комманды не нужен аргумент, введите данные после ввода команды");
        } catch (ScriptErrorException e) {}
        return false;
    }
}
