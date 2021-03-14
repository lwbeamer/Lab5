package Control;

import Commands.*;

import java.util.HashMap;

/**
 * Класс, который вызывает нужные команды
 */
public class CommandInvoker {
    private HashMap<String, Convertable<Boolean,String>> commandsMap = new HashMap<>();
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
        commandsMap.put("help",this::help);
        this.addCommand = addCommand;
        commandsMap.put("add",this::add);
        this.addIfMaxCommand = addIfMaxCommand;
        commandsMap.put("add_if_max",this::addIfMax);
        this.addIfMinCommand = addIfMinCommand;
        commandsMap.put("add_if_min",this::addIfMin);
        this.clearCommand = clearCommand;
        commandsMap.put("clear",this::clear);
        this.executeScriptCommand = executeScriptCommand;
        commandsMap.put("execute_script",this::executeScript);
        this.exitCommand = exitCommand;
        commandsMap.put("exit",this::exit);
        this.filterByStatusCommand = filterByStatusCommand;
        commandsMap.put("filter_by_status",this::filterByStatusCommand);
        this.infoCommand = infoCommand;
        commandsMap.put("info",this::info);
        this.printFieldDescendingStatusCommand = printFieldDescendingStatusCommand;
        commandsMap.put("print_field_descending_status",this::printFieldDescendingStatus);
        this.removeAllByPersonCommand = removeAllByPersonCommand;
        commandsMap.put("remove_all_by_person_command",this::removeAllByPerson);
        this.removeByIdCommand = removeByIdCommand;
        commandsMap.put("remove_by_id",this::removeById);
        this.removeHeadCommand = removeHeadCommand;
        commandsMap.put("remove_head",this::removeHead);
        this.saveCommand = saveCommand;
        commandsMap.put("save",this::save);
        this.showCommand = showCommand;
        commandsMap.put("show",this::show);
        this.updateCommand = updateCommand;
        commandsMap.put("update",this::update);
    }

    /**
     * Возвращает мапу с командами
     * @return мапу (ключ - команда, значение - ссылка на метод)
     */
    public HashMap<String, Convertable<Boolean, String>> getCommandsMap() {
        return commandsMap;
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
