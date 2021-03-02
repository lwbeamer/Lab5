package Commands;

import Control.CollectionOperator;
import Control.UserDataReceiver;
import WorkerData.*;
import Exceptions.EmptyCollectionException;
import Exceptions.ScriptErrorException;
import Exceptions.WorkerNotFoundException;
import Exceptions.WrongArgumentException;

import java.time.ZonedDateTime;

/**
 * Команда "update". Обновляет элемент в коллекции по ID, узнав у пользователя все нужные данные
 */
public class Update implements Executable{

    private final CollectionOperator collectionOperator;
    private final UserDataReceiver userDataReceiver;

    public Update(CollectionOperator collectionOperator, UserDataReceiver userDataReceiver) {
        this.collectionOperator = collectionOperator;
        this.userDataReceiver = userDataReceiver;
    }

    /**
     * Описание команды
     * @return Строка - описание
     */
    public static String description(){
        return "update id {element} : обновить значение элемента коллекции, id которого равен заданному";
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
            long id = Long.parseLong(argument);
            if (collectionOperator.collectionSize() == 0) throw new EmptyCollectionException();
            Worker oldWorker = collectionOperator.getById(id);
            if (oldWorker == null) throw new WorkerNotFoundException();

            String name = oldWorker.getName();
            Coordinates coordinates = oldWorker.getCoordinates();
            ZonedDateTime creationDate = oldWorker.getCreationDate();
            double salary = oldWorker.getSalary();
            Position position = oldWorker.getPosition();
            Status status = oldWorker.getStatus();
            Person person = oldWorker.getPerson();

            collectionOperator.removeFromCollection(oldWorker);

            if (userDataReceiver.askQuestion("Хотите изменить имя рабочего?")) name = userDataReceiver.askName();
            if (userDataReceiver.askQuestion("Хотите изменить координаты рабочего?")) coordinates = userDataReceiver.askCoordinates();
            if (userDataReceiver.askQuestion("Хотите изменить зарплату рабочего?")) salary = userDataReceiver.askSalary();
            if (userDataReceiver.askQuestion("Хотите изменить должность рабочего?")) position = userDataReceiver.askPosition();
            if (userDataReceiver.askQuestion("Хотите изменить статус рабочего?")) status = userDataReceiver.askStatus();
            if (userDataReceiver.askQuestion("Хотите изменить личные данные рабочего?")) person = userDataReceiver.askPerson();

            collectionOperator.addToCollection(new Worker(id, name, coordinates, creationDate, salary, position, status, person));
            System.out.println("Рабочий успешно изменен!");
            return true;
        } catch (WrongArgumentException e) {
            System.err.println("Укажите ID в качестве аргумента!");
        } catch (EmptyCollectionException e) {
            System.err.println("Коллекция пуста!");
        } catch (NumberFormatException e) {
            System.err.println("ID должен быть представлен числом!");
        } catch (WorkerNotFoundException e) {
            System.err.println("Рабочего с таким ID в коллекции нет!");
        } catch (ScriptErrorException e) {}
        return false;
    }
}

