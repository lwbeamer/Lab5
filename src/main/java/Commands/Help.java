package Commands;

import Control.Console;
import Exceptions.WrongArgumentException;


/**
 * Команда "help". Класс отвечает за вывод всех описаний команд в консоль.
 */
public class Help implements Executable {

    /**
     * Выполнение команды
     * @param argument - аргумент команды
     * @return Статус выполнения команды
     */
    @Override
    public boolean execute(String argument) {

        try {
            if (!argument.isEmpty()) throw new WrongArgumentException();
            Console.println(Help.description());
            Console.println(Info.description());
            Console.println(Show.description());
            Console.println(Add.description());
            Console.println(Update.description());
            Console.println(RemoveById.description());
            Console.println(Clear.description());
            Console.println(Save.description());
            Console.println(ExecuteScript.description());
            Console.println(Exit.description());
            Console.println(RemoveHead.description());
            Console.println(AddIfMax.description());
            Console.println(AddIfMin.description());
            Console.println(RemoveAllByPerson.description());
            Console.println(FilterByStatus.description());
            Console.println(PrintFieldDescendingStatus.description());
            return true;
        } catch (WrongArgumentException e) {
            Console.println("Для этой комманды не нужен аргумент, попробуйте ещё раз");
        }
        return false;
    }

    /**
     * Описание команды
     * @return Строка - описание
     */
    public static String description() {
        return "help : вывести справку по доступным командам";
    }
}
