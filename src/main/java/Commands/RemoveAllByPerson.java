package Commands;

import Control.CollectionOperator;
import Control.UserDataReceiver;
import WorkerData.Person;
import WorkerData.Worker;
import Exceptions.EmptyCollectionException;
import Exceptions.ScriptErrorException;
import Exceptions.WrongArgumentException;
import java.util.ArrayList;

/**
 * Команда "remove_all_by_person". Удаляет все элементы, у которых поле Person совпадает с введённым.
 */
public class RemoveAllByPerson implements Executable{

    private final CollectionOperator collectionOperator;
    private final UserDataReceiver userDataReceiver;

    public RemoveAllByPerson(CollectionOperator collectionOperator, UserDataReceiver userDataReceiver) {
        this.collectionOperator = collectionOperator;
        this.userDataReceiver = userDataReceiver;
    }

    /**
     * Описание команды
     * @return Строка - описание
     */
    public static String description(){
        return "remove_all_by_person person : удалить из коллекции все элементы, значение поля person которого эквивалентно заданному";
    }

    /**
     * Выполнение команды
     * @param argument - аргумент команды
     * @return Статус выполнения команды
     */
    @Override
    public boolean execute(String argument) {
        boolean check = false;
        ArrayList<Long> listId = new ArrayList<>();
        try {
            if (!argument.isEmpty()) throw new WrongArgumentException();
            if (collectionOperator.collectionSize() == 0) throw new EmptyCollectionException();
            Person personToRemove = userDataReceiver.askPerson();
            for (Worker worker: collectionOperator.getWorkersCollection()){
                if (worker.getPerson().equals(personToRemove)){
                    listId.add(worker.getId());
                    check = true;
                }
            }
            for (Long id: listId){
                collectionOperator.removeFromCollection(collectionOperator.getById(id));
            }
            if (check) System.out.println("Рабочие успешно удалены!");
            else System.out.println("Рабочих с такими личными данными не найдено!");
            return true;
        } catch (WrongArgumentException e) {
            System.out.println("Для этой комманды не нужен аргумент, введите данные после ввода команды");
        } catch (EmptyCollectionException e) {
            System.err.println("Коллекция пуста!");
        } catch (NumberFormatException e) {
            System.err.println("ID должен быть представлен числом!");
        } catch (ScriptErrorException e) {
            e.printStackTrace();
        }
        return false;
        }
}
