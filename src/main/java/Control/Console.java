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
        int globalErrorStatus = 0;

        argument = argument.trim();

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
                Console.println(String.join(" ", inputCommand));

                if (inputCommand[0].equals("execute_script")) { //если есть команда вызова скрипта, проверяется, не вызывается ли файл, который уже есть в стэке, если да, то происходит рекурсия (ошибка)
                    for (String script : scriptStack) {
                        if (inputCommand[1].equals(script)) throw new ScriptRecursionException();
                    }
                }
                commandStatus = launchCommand(inputCommand); //запуск команды
            } while (commandStatus == 0 && scriptScanner.hasNextLine()); //цикл работает до тех пор, пока всё идёт без ошибок (код 0), или пока не кончится скрипт.

            scriptStack.remove(scriptStack.size()-1); //удаляем завершённый скрипт

            userDataReceiver.setInputScanner(tmpScanner); //Отдаём сканер обратно
            userDataReceiver.setNormalMode(); //Возвращаем консольный режим

            if (commandStatus == 1) { //Если програма отработала с ошибкой
                Console.println("Что-то пошло не так. Проверьте на корректность введённых данных скрипт "+argument);
            }
            return commandStatus;

        } catch (FileNotFoundException e) {
            Console.printerror("Файл со скриптом не найден!");
        } catch (NoSuchElementException e) {
            Console.printerror("Файл со скриптом пуст!");
        } catch (ScriptRecursionException e) {
            Console.printerror("Скрипты не могут вызываться рекурсивно!");
        }
        return 1;
    }

    /**
     * Возвращает мапу с командами
     * @return мапу с командами
     */
    public HashMap<String,Convertable<Boolean,String>> getCommandsMap(){
        return commandInvoker.getCommandsMap();
    }

    /**
     * Метод, запускающий команды. Если код равен 2, значит нужно завершить программу. 0 - всё прошло хорошо. 1 - возникла ошибка. При ошибке пользователь может ввести команду ещё раз.
     * @param userCommand - введённая команда
     * @return возвращает код выполнения операции
     */
    private int launchCommand(String[] userCommand) {
        try {
            if (userCommand[0].equals("exit")) {
                if (!getCommandsMap().get(userCommand[0]).convert(userCommand[1])) return 1;
                else return 2;
            }
            if (userCommand[0].equals("execute_script")){
                if (!getCommandsMap().get(userCommand[0]).convert(userCommand[1])) return 1;
                else return scriptExecution(userCommand[1]);
            }
            if (!getCommandsMap().get(userCommand[0]).convert(userCommand[1])) return 1;
        } catch (NullPointerException e){
            Console.println("Команда '" + userCommand[0] + "' не найдена. Наберите 'help' для справки.");
        }
        return 0;
    }

    /**
     * Вывод в консоль без переноса строки
     * @param toOut Объект для вывода
     */
    public static void print(Object toOut) {
        System.out.print(toOut);
    }

    /**
     * Вывод в консоль с переносом строки
     * @param toOut Ошибка для вывода
     */
    public static void println(Object toOut) {
        System.out.println(toOut);
    }

    /**
     * Вывод ошибки в консоль
     * @param toOut Объект для вывода
     */
    public static void printerror(Object toOut) {
        System.err.println(toOut);
    }
}
