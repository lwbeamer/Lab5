package Commands;

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
            if (argument.isEmpty()) throw new WrongArgumentException();
            System.out.println("Выполняю скрипт '" + argument + "'...");
            return true;
        } catch (WrongArgumentException e) {
            System.out.println("Укажите файл со скриптом в качестве аргумента.");
        }
        return false;
    }
}
