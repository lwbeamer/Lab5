import Commands.*;
import Control.*;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Класс Main. Создаёт нужные объекты и запускает программу.
 */
public class Main {
    public static void main(String[] args) throws FileNotFoundException {

        CollectionOperator collectionOperator = new CollectionOperator(new Downloader(System.getenv("LabFive")), new Uploader(System.getenv("LabFive")));

        Scanner scanner = new Scanner(System.in);

        UserDataReceiver userDataReceiver = new UserDataReceiver(scanner);

        collectionOperator.sortCollection();

        CommandInvoker commandInvoker = new CommandInvoker(
                new Help(),
                new Add(collectionOperator, userDataReceiver),
                new AddIfMax(collectionOperator, userDataReceiver),
                new AddIfMin(collectionOperator, userDataReceiver),
                new Clear(collectionOperator),
                new ExecuteScript(),
                new Exit(),
                new FilterByStatus(collectionOperator),
                new Info(collectionOperator),
                new PrintFieldDescendingStatus(collectionOperator),
                new RemoveAllByPerson(collectionOperator, userDataReceiver),
                new RemoveById(collectionOperator),
                new RemoveHead(collectionOperator),
                new Save(collectionOperator),
                new Show(collectionOperator),
                new Update(collectionOperator, userDataReceiver)
        );

        Console console = new Console(scanner, commandInvoker, userDataReceiver);
        console.commandReader();
    }


}

