package Control;

import Commands.*;

/**
 * Класс, который вызывает нужные команды
 */
public class CommandInvoker {
    private final Executable helpCommand;
    private final Executable addCommand;
    private final Executable addIfMaxCommand;
    private final Executable addIfMinCommand;
    private final Executable clearCommand;
    private final Executable executeScriptCommand;
    private final Executable exitCommand;
    private final Executable filterByStatusCommand;
    private final Executable infoCommand;
    private final Executable printFieldDescendingStatusCommand;
    private final Executable removeAllByPersonCommand;
    private final Executable removeByIdCommand;
    private final Executable removeHeadCommand;
    private final Executable saveCommand;
    private final Executable showCommand;
    private final Executable updateCommand;

    public CommandInvoker(Executable helpCommand, Executable addCommand, Executable addIfMaxCommand, Executable addIfMinCommand,
                          Executable clearCommand, Executable executeScriptCommand, Executable exitCommand, Executable filterByStatusCommand,
                          Executable infoCommand, Executable printFieldDescendingStatusCommand, Executable removeAllByPersonCommand, Executable removeByIdCommand,
                          Executable removeHeadCommand, Executable saveCommand, Executable showCommand, Executable updateCommand) {
        this.helpCommand = helpCommand;
        this.addCommand = addCommand;
        this.addIfMaxCommand = addIfMaxCommand;
        this.addIfMinCommand = addIfMinCommand;
        this.clearCommand = clearCommand;
        this.executeScriptCommand = executeScriptCommand;
        this.exitCommand = exitCommand;
        this.filterByStatusCommand = filterByStatusCommand;
        this.infoCommand = infoCommand;
        this.printFieldDescendingStatusCommand = printFieldDescendingStatusCommand;
        this.removeAllByPersonCommand = removeAllByPersonCommand;
        this.removeByIdCommand = removeByIdCommand;
        this.removeHeadCommand = removeHeadCommand;
        this.saveCommand = saveCommand;
        this.showCommand = showCommand;
        this.updateCommand = updateCommand;
    }


    /**
     * Метод выводит сообщение, если желаемая команда не найдена
     * @param command введённая команда
     * @return возвращает статус выполнения команды
     */
    public boolean noSuchCommand(String command) {
        System.out.println("Команда '" + command + "' не найдена. Наберите 'help' для справки.");
        return false;

    }

    /**
     * Запускает выполнение команды
     * @param argument аргумент команды
     * @return статус выполнения команды
     */
    public boolean help(String argument) {
        return helpCommand.execute(argument);
    }

    /**
     * Запускает выполнение команды
     * @param argument аргумент команды
     * @return статус выполнения команды
     */
    public boolean add(String argument) {
        return addCommand.execute(argument);
    }

    /**
     * Запускает выполнение команды
     * @param argument аргумент команды
     * @return статус выполнения команды
     */
    public boolean addIfMax(String argument) {
        return addIfMaxCommand.execute(argument);
    }

    /**
     * Запускает выполнение команды
     * @param argument аргумент команды
     * @return статус выполнения команды
     */
    public boolean addIfMin(String argument) {
        return addIfMinCommand.execute(argument);
    }

    /**
     * Запускает выполнение команды
     * @param argument аргумент команды
     * @return статус выполнения команды
     */
    public boolean clear(String argument) {
        return clearCommand.execute(argument);
    }

    /**
     * Запускает выполнение команды
     * @param argument аргумент команды
     * @return статус выполнения команды
     */
    public boolean executeScript(String argument) {
        return executeScriptCommand.execute(argument);
    }

    /**
     * Запускает выполнение команды
     * @param argument аргумент команды
     * @return статус выполнения команды
     */
    public boolean exit(String argument) {
        return exitCommand.execute(argument);
    }

    /**
     * Запускает выполнение команды
     * @param argument аргумент команды
     * @return статус выполнения команды
     */
    public boolean info(String argument) {
        return infoCommand.execute(argument);
    }

    /**
     * Запускает выполнение команды
     * @param argument аргумент команды
     * @return статус выполнения команды
     */
    public boolean printFieldDescendingStatus(String argument) {
        return printFieldDescendingStatusCommand.execute(argument);
    }

    /**
     * Запускает выполнение команды
     * @param argument аргумент команды
     * @return статус выполнения команды
     */
    public boolean removeAllByPerson(String argument) {
        return removeAllByPersonCommand.execute(argument);
    }

    /**
     * Запускает выполнение команды
     * @param argument аргумент команды
     * @return статус выполнения команды
     */
    public boolean removeById(String argument) {
        return removeByIdCommand.execute(argument);
    }

    /**
     * Запускает выполнение команды
     * @param argument аргумент команды
     * @return статус выполнения команды
     */
    public boolean removeHead(String argument) {
        return removeHeadCommand.execute(argument);
    }

    /**
     * Запускает выполнение команды
     * @param argument аргумент команды
     * @return статус выполнения команды
     */
    public boolean save(String argument) {
        return saveCommand.execute(argument);
    }

    /**
     * Запускает выполнение команды
     * @param argument аргумент команды
     * @return статус выполнения команды
     */
    public boolean show(String argument) {
        return showCommand.execute(argument);
    }

    /**
     * Запускает выполнение команды
     * @param argument аргумент команды
     * @return статус выполнения команды
     */
    public boolean update(String argument) {
        return updateCommand.execute(argument);
    }

    /**
     * Запускает выполнение команды
     * @param argument аргумент команды
     * @return статус выполнения команды
     */
    public boolean filterByStatusCommand(String argument) {
        return filterByStatusCommand.execute(argument);
    }

}
