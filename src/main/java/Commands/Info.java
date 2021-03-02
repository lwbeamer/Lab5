package Commands;

import Control.CollectionOperator;
import Exceptions.WrongArgumentException;
import java.time.LocalDateTime;

/**
 * Команда "info". Выводит информацию о коллекции.
 */
public class Info implements Executable{

    private final CollectionOperator collectionOperator;

    public Info(CollectionOperator collectionOperator) {
        this.collectionOperator = collectionOperator;
    }


    /**
     * Описание команды
     * @return Строка - описание
     */
    public static String description(){
        return "info : вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)";
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
            LocalDateTime lastInitTime = collectionOperator.getLastInitialisationTime();
            String lastInitTimeString;

            if (lastInitTime == null)
                lastInitTimeString = "Инициализации ещё не было";
            else lastInitTimeString = lastInitTime.toLocalDate().toString() + " " + lastInitTime.toLocalTime().toString();

            System.out.println(" Тип коллекции: " + collectionOperator.collectionType());
            System.out.println(" Количество элементов: " + collectionOperator.collectionSize());
            System.out.println(" Дата последней инициализации: " + lastInitTimeString);
            return true;
        } catch (WrongArgumentException e) {
            System.out.println("Для этой комманды не нужен аргумент, попробуйте ещё раз");
        }
        return false;
    }
}
