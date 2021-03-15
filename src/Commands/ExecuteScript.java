package Commands;

import Control.Console;
import Exceptions.WrongArgumentException;

/**
 * Команда "execute_script". Класс отвечает за запуск скрипта. Смена режима происходит в классе Console.
 */
public class ExecuteScript implements Executable{


    /**
     * Описание команды
     * @return Строка - описание
     */
    public static String description() {
        return "execute_script file_name : считать и исполнить скрипт из указанного файла. В скрипте содержатся команды в таком же виде, в котором их вводит пользователь в интерактивном режиме.";
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
            Console.println("Выполняю скрипт '" + argument + "'...");
            return true;
        } catch (WrongArgumentException e) {
            Console.println("Укажите файл со скриптом в качестве аргумента.");
        }
        return false;
    }
}
