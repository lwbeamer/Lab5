package Commands;

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
            System.out.println(Help.description());
            System.out.println(Info.description());
            System.out.println(Show.description());
            System.out.println(Add.description());
            System.out.println(Update.description());
            System.out.println(RemoveById.description());
            System.out.println(Clear.description());
            System.out.println(Save.description());
            System.out.println(ExecuteScript.description());
            System.out.println(Exit.description());
            System.out.println(RemoveHead.description());
            System.out.println(AddIfMax.description());
            System.out.println(AddIfMin.description());
            System.out.println(RemoveAllByPerson.description());
            System.out.println(FilterByStatus.description());
            System.out.println(PrintFieldDescendingStatus.description());
            return true;
        } catch (WrongArgumentException e) {
            System.out.println("Для этой комманды не нужен аргумент, попробуйте ещё раз");
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
