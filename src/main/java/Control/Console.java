package Control;

import Exceptions.ScriptRecursionException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Класс - консоль. Отвечает за пользовательский ввод.
 */
public class Console {

    private final Scanner inputScanner;
    private final CommandInvoker commandInvoker;
    private final UserDataReceiver userDataReceiver;
    private final List<String> scriptStack = new ArrayList<>();

    public Console(Scanner inputScanner, CommandInvoker commandInvoker, UserDataReceiver userDataReceiver) {
        this.inputScanner = inputScanner;
        this.commandInvoker = commandInvoker;
        this.userDataReceiver = userDataReceiver;
    }

    /**
     * Обрабатывает команды, введённые пользователем
     */
    public void commandReader() {
        int commandStatus;
        String[] inputCommand;

        do {
            inputCommand = (inputScanner.nextLine().trim() + " ").split(" ", 2);
            commandStatus = launchCommand(inputCommand);
        } while (commandStatus != 2);
    }

    /**
     * Метод запускает выполнение скрипта.
     * @param argument - здесь в качестве аргумента выступает имя файла со скриптом
     * @return код выполнения операции
     */
    public int scriptExecution(String argument) {
        String[] inputCommand;
        int commandStatus;

        scriptStack.add(argument); //добавляем скрипт в список

        //читаем файл сканером, предрекая ошибки:
        try (Scanner scriptScanner = new Scanner(new File(argument))) {
            if (!scriptScanner.hasNext()) throw new NoSuchElementException();

            Scanner tmpScanner = userDataReceiver.getInputScanner(); //Когда с файлом всё ок, передаём консольный сканер в сканер скрипта, чтобы работать с консколью из скрипта
            userDataReceiver.setInputScanner(scriptScanner);

            userDataReceiver.setScriptMode(); //устанавливаем скриптовый режим работы, от этого зависит вывод комманд

            do {
                inputCommand = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                inputCommand[1] = inputCommand[1].trim();
                while (scriptScanner.hasNextLine() && inputCommand[0].isEmpty()) { //цикл, для пропуска пустых строк
                    inputCommand = (scriptScanner.nextLine().trim() + " ").split(" ", 2);
                    inputCommand[1] = inputCommand[1].trim();
                }
                System.out.println(String.join(" ", inputCommand));

                if (inputCommand[0].equals("execute_script")) { //если есть команда вызова скрипта, проверяется, не вызывается ли файл, который уже есть в стэке, если да, то происходит рекурсия (ошибка)
                    for (String script : scriptStack) {
                        if (inputCommand[1].equals(script)) throw new ScriptRecursionException();
                    }
                }
                commandStatus = launchCommand(inputCommand); //запуск команды
            } while (commandStatus == 0 && scriptScanner.hasNextLine()); //цикл работает до тех пор, пока всё идёт без ошибок (код 0), или пока не кончится скрипт.

            userDataReceiver.setInputScanner(tmpScanner); //Отдаём сканер обратно
            userDataReceiver.setNormalMode(); //Возвращаем консольный режим

            if (commandStatus == 1) //Если програма отработала с ошибкой
                System.out.println("Проверьте скрипт на корректность введенных данных!");
            return commandStatus;
        } catch (FileNotFoundException e) {
            System.err.println("Файл со скриптом не найден!");
        } catch (NoSuchElementException e) {
            System.err.println("Файл со скриптом пуст!");
        } catch (ScriptRecursionException e) {
            System.err.println("Скрипты не могут вызываться рекурсивно!");
        }
        return 1;
    }

    /**
     * Метод, запускающий команды. Если код равен 2, значит нужно завершить программу. 0 - всё прошло хорошо. 1 - возникла ошибка. При ошибке пользователь может ввести команду ещё раз.
     * @param userCommand - введённая команда
     * @return возвращает код выполнения операции
     */
    private int launchCommand(String[] userCommand) {
        switch (userCommand[0]) {
            case "":
                break;
            case "help":
                if (!commandInvoker.help(userCommand[1])) return 1;
                break;
            case "info":
                if (!commandInvoker.info(userCommand[1])) return 1;
                break;
            case "show":
                if (!commandInvoker.show(userCommand[1])) return 1;
                break;
            case "add":
                if (!commandInvoker.add(userCommand[1])) return 1;
                break;
            case "update":
                if (!commandInvoker.update(userCommand[1])) return 1;
                break;
            case "remove_by_id":
                if (!commandInvoker.removeById(userCommand[1])) return 1;
                break;
            case "clear":
                if (!commandInvoker.clear(userCommand[1])) return 1;
                break;
            case "save":
                if (!commandInvoker.save(userCommand[1])) return 1;
                break;
            case "execute_script":
                if (!commandInvoker.executeScript(userCommand[1])) return 1;
                else return scriptExecution(userCommand[1]);
            case "add_if_max":
                if (!commandInvoker.addIfMax(userCommand[1])) return 1;
                break;
            case "add_if_min":
                if (!commandInvoker.addIfMin(userCommand[1])) return 1;
                break;
            case "remove_head":
                if (!commandInvoker.removeHead(userCommand[1])) return 1;
                break;
            case "remove_all_by_person":
                if (!commandInvoker.removeAllByPerson(userCommand[1])) return 1;
                break;
            case "filter_by_status":
                if (!commandInvoker.filterByStatusCommand(userCommand[1])) return 1;
                break;
            case "print_field_descending_status":
                if (!commandInvoker.printFieldDescendingStatus(userCommand[1])) return 1;
                break;
            case "exit":
                if (!commandInvoker.exit(userCommand[1])) return 1;
                else return 2;
            default:
                if (!commandInvoker.noSuchCommand(userCommand[0])) return 1;
        }
        return 0;
    }
}
