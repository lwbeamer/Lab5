package Control;

import WorkerData.*;
import Exceptions.ValueIsEmptyException;
import Exceptions.NonUniqueValueException;
import Exceptions.ValueOutOfRangeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Класс для парсинга и загрузки в коллекцию элементов из файла
 */
public class Downloader {

    private Scanner scanner;


    public Downloader(String source) {
        try {
            File file = new File(source);
            scanner = new Scanner(file);
        } catch (FileNotFoundException e){
            Console.printerror("Файл не найдён! Проверьте правильность введённого пути!");
            System.exit(0);
        } catch (NullPointerException e){
            Console.printerror("Невозможно получить файл. Проверьте переменную окружения!");
            System.exit(0);
        }
    }

    /**
     * Метод, выполняющий парсинг и загрузку
     * @return возвращает полученную коллекцию
     */
    public ArrayDeque<Worker> parse() {

        //Локальные временные переменные:

        String tmp = null, p = null, locName = null, zoneID = null, s;
        String idContainer = "";
        Float w = 0F, locY = 0F;
        long x = 0L, y = 0L, id;
        double locX = 0, locZ = 0, salary;
        boolean[] checker = new boolean[8];
        boolean checkIfHasLocation, errStatus, GlobalErrStatus = false;
        int year = 0, month = 0, day = 0, hour = 0, minute = 0, creationYear = 0, creationMonth = 0, creationDay = 0, creationHour = 0, creationMinute = 0;
        ArrayDeque<Worker> workers = new ArrayDeque<>();

        //Пропуск технических строк в файле:

        try {
            scanner.nextLine();
            scanner.nextLine();
        } catch (NoSuchElementException e){
            //System.err.println("Файл некорректен!");
        }
        Pattern pattern = Pattern.compile(">.+<"); //Паттерн для отсеивания информации от тэгов

        //if (!scanner.hasNextLine()) System.err.println("Файл некорректен!");
        while (scanner.hasNextLine()) {
            s = scanner.nextLine();
            if (s.contains("<Worker>")) {

                //Обнуление проверяющих переменных статуса перед каждым новый объектом
                Arrays.fill(checker, false);
                checkIfHasLocation = false;
                errStatus = false;

                int counter = 0;
                try {
                    s = scanner.nextLine();
                } catch (NoSuchElementException e){
                    //System.err.println("Файл некорректен!");
                }
                workers.add(new Worker()); //добавляем объект в коллекцию

                while (!s.contains("</Worker>")) {
                    try {
                        if (s.contains("<id>")) {
                            Matcher matcher = pattern.matcher(s);
                            if (matcher.find()) {
                                tmp = s.substring(matcher.start() + 1, matcher.end() - 1);
                                if (!idContainer.contains(tmp)) {
                                    idContainer = idContainer + tmp + " ";
                                } else throw new NonUniqueValueException();
                            } else throw new ValueIsEmptyException();

                            id = Long.parseLong(tmp);
                            if (id > 0) {
                                workers.getLast().setId(id);
                                counter++;
                            } else throw new ValueOutOfRangeException();
                        }

                        //задаём добавленному объекту поля из файла:
                        if (s.contains("<name>") && checker[7]) {
                            counter++;
                            Matcher matcher = pattern.matcher(s);
                            if (matcher.find()) {
                                tmp = s.substring(matcher.start() + 1, matcher.end() - 1);
                            } else locName = null;
                            locName = tmp;
                        }

                        if (s.contains("<name>") && !checker[7]) {
                            counter++;
                            Matcher matcher = pattern.matcher(s);
                            if (matcher.find()) {
                                tmp = s.substring(matcher.start() + 1, matcher.end() - 1);
                            } else throw new ValueIsEmptyException();
                            workers.getLast().setName(tmp);
                            checker[7] = true;
                        }

                        if (s.contains("<X>") && checker[0]) {
                            counter++;
                            Matcher matcher = pattern.matcher(s);
                            if (matcher.find()) {
                                tmp = s.substring(matcher.start() + 1, matcher.end() - 1);
                            } else throw new ValueIsEmptyException();
                            locX = Double.parseDouble(tmp);
                        }

                        if (s.contains("<X>") && !checker[0]) {
                            counter++;
                            Matcher matcher = pattern.matcher(s);
                            if (matcher.find()) {
                                tmp = s.substring(matcher.start() + 1, matcher.end() - 1);
                            } else throw new ValueIsEmptyException();
                            x = Long.parseLong(tmp);
                            checker[0] = true;
                        }


                        if (s.contains("<Y>") && checker[1]) {
                            counter++;
                            Matcher matcher = pattern.matcher(s);
                            if (matcher.find()) {
                                tmp = s.substring(matcher.start() + 1, matcher.end() - 1);
                            } else throw new ValueIsEmptyException();
                            checkIfHasLocation = true;
                            locY = Float.parseFloat(tmp);
                        }

                        if (s.contains("<Y>") && !checker[1]) {
                            counter++;
                            Matcher matcher = pattern.matcher(s);
                            if (matcher.find()) {
                                tmp = s.substring(matcher.start() + 1, matcher.end() - 1);
                            } else throw new ValueIsEmptyException();
                            y = Long.parseLong(tmp);
                            checker[1] = true;
                        }

                        if (s.contains("<Z>")) {
                            counter++;
                            Matcher matcher = pattern.matcher(s);
                            if (matcher.find()) {
                                tmp = s.substring(matcher.start() + 1, matcher.end() - 1);
                            } else throw new ValueIsEmptyException();
                            locZ = Double.parseDouble(tmp);
                        }

                        if (s.contains("<year>") && checker[2]) {
                            counter++;
                            Matcher matcher = pattern.matcher(s);
                            if (matcher.find()) {
                                tmp = s.substring(matcher.start() + 1, matcher.end() - 1);
                            } else throw new ValueIsEmptyException();
                            year = Integer.parseInt(tmp);
                        }

                        if (s.contains("<year>") && !checker[2]) {
                            counter++;
                            Matcher matcher = pattern.matcher(s);
                            if (matcher.find()) {
                                tmp = s.substring(matcher.start() + 1, matcher.end() - 1);
                            } else throw new ValueIsEmptyException();
                            creationYear = Integer.parseInt(tmp);
                            checker[2] = true;
                        }

                        if (s.contains("<month>") && checker[3]) {
                            counter++;
                            Matcher matcher = pattern.matcher(s);
                            if (matcher.find()) {
                                tmp = s.substring(matcher.start() + 1, matcher.end() - 1);
                            } else throw new ValueIsEmptyException();
                            month = Integer.parseInt(tmp);
                        }

                        if (s.contains("<month>") && !checker[3]) {
                            counter++;
                            Matcher matcher = pattern.matcher(s);
                            if (matcher.find()) {
                                tmp = s.substring(matcher.start() + 1, matcher.end() - 1);
                            } else throw new ValueIsEmptyException();
                            creationMonth = Integer.parseInt(tmp);
                            checker[3] = true;
                        }

                        if (s.contains("<day>") && checker[4]) {
                            counter++;
                            Matcher matcher = pattern.matcher(s);
                            if (matcher.find()) {
                                tmp = s.substring(matcher.start() + 1, matcher.end() - 1);
                            } else throw new ValueIsEmptyException();
                            day = Integer.parseInt(tmp);
                        }

                        if (s.contains("<day>") && !checker[4]) {
                            counter++;
                            Matcher matcher = pattern.matcher(s);
                            if (matcher.find()) {
                                tmp = s.substring(matcher.start() + 1, matcher.end() - 1);
                            } else throw new ValueIsEmptyException();
                            creationDay = Integer.parseInt(tmp);
                            checker[4] = true;
                        }

                        if (s.contains("<hour>") && checker[5]) {
                            counter++;
                            Matcher matcher = pattern.matcher(s);
                            if (matcher.find()) {
                                tmp = s.substring(matcher.start() + 1, matcher.end() - 1);
                            } else throw new ValueIsEmptyException();
                            hour = Integer.parseInt(tmp);
                        }

                        if (s.contains("<hour>") && !checker[5]) {
                            counter++;
                            Matcher matcher = pattern.matcher(s);
                            if (matcher.find()) {
                                tmp = s.substring(matcher.start() + 1, matcher.end() - 1);
                            } else throw new ValueIsEmptyException();
                            creationHour = Integer.parseInt(tmp);
                            checker[5] = true;
                        }

                        if (s.contains("<minute>") && checker[6]) {
                            counter++;
                            Matcher matcher = pattern.matcher(s);
                            if (matcher.find()) {
                                tmp = s.substring(matcher.start() + 1, matcher.end() - 1);
                            } else throw new ValueIsEmptyException();
                            minute = Integer.parseInt(tmp);
                        }

                        if (s.contains("<minute>") && !checker[6]) {
                            counter++;
                            Matcher matcher = pattern.matcher(s);
                            if (matcher.find()) {
                                tmp = s.substring(matcher.start() + 1, matcher.end() - 1);
                            } else throw new ValueIsEmptyException();
                            creationMinute = Integer.parseInt(tmp);
                            checker[6] = true;
                        }

                        if (s.contains("<zoneID>")) {
                            counter++;
                            Matcher matcher = pattern.matcher(s);
                            if (matcher.find()) {
                                tmp = s.substring(matcher.start() + 1, matcher.end() - 1);
                            } else throw new ValueIsEmptyException();
                            zoneID = tmp;
                        }

                        if (s.contains("<salary>")) {
                            Matcher matcher = pattern.matcher(s);
                            if (matcher.find()) {
                                tmp = s.substring(matcher.start() + 1, matcher.end() - 1);

                                salary = Double.parseDouble(tmp);

                                if (salary > 0) {
                                    counter++;
                                    workers.getLast().setSalary(salary);
                                } else throw new ValueOutOfRangeException();

                            } else throw new ValueIsEmptyException();
                        }

                        if (s.contains("<position>")) {
                            counter++;
                            Matcher matcher = pattern.matcher(s);
                            if (matcher.find()) {
                                tmp = s.substring(matcher.start() + 1, matcher.end() - 1);
                            } else throw new ValueIsEmptyException();
                            if (tmp.equalsIgnoreCase("HUMAN_RESOURCES")) {
                                workers.getLast().setPosition(Position.HUMAN_RESOURCES);
                            } else if (tmp.equalsIgnoreCase("HEAD_OF_DIVISION")) {
                                workers.getLast().setPosition(Position.HEAD_OF_DIVISION);
                            } else if (tmp.equalsIgnoreCase("CLEANER")) {
                                workers.getLast().setPosition(Position.CLEANER);
                            }
                        }

                        if (s.contains("<status>")) {
                            counter++;
                            Matcher matcher = pattern.matcher(s);
                            if (matcher.find()) {
                                tmp = s.substring(matcher.start() + 1, matcher.end() - 1);
                            } else throw new ValueIsEmptyException();
                            if (tmp.equalsIgnoreCase("HIRED")) {
                                workers.getLast().setStatus(Status.HIRED);
                            } else if (tmp.equalsIgnoreCase("RECOMMENDED_FOR_PROMOTION")) {
                                workers.getLast().setStatus(Status.RECOMMENDED_FOR_PROMOTION);
                            } else if (tmp.equalsIgnoreCase("PROBATION")) {
                                workers.getLast().setStatus(Status.PROBATION);
                            }
                        }

                        if (s.contains("<weight>")) {
                            Matcher matcher = pattern.matcher(s);
                            if (matcher.find()) {
                                tmp = s.substring(matcher.start() + 1, matcher.end() - 1);

                                w = Float.parseFloat(tmp);

                                if (w <= 0) throw new ValueOutOfRangeException();


                            } else w = null;
                            counter++;
                        }

                        if (s.contains("<passportID>")) {
                            counter++;
                            Matcher matcher = pattern.matcher(s);
                            if (matcher.find()) {
                                p = s.substring(matcher.start() + 1, matcher.end() - 1);
                            } else throw new ValueIsEmptyException();
                        }

                        try {
                            s = scanner.nextLine();
                        } catch (NoSuchElementException e){
                            //System.err.println("Файл некорректен!");
                            break;
                        }
                    } catch (ValueIsEmptyException e) {
                        Console.printerror("Некоторые поля пустые. Перепроверьте содержимое файла!");
                        errStatus = true;
                        GlobalErrStatus = true;
                        workers.removeLast();
                        break;
                    } catch (NonUniqueValueException e) {
                        Console.printerror("ID должен быть уникальным!");
                        errStatus = true;
                        GlobalErrStatus = true;
                        workers.removeLast();
                        break;
                    } catch (ValueOutOfRangeException e) {
                        Console.printerror("Значение поля должно быть больше 0!");
                        errStatus = true;
                        GlobalErrStatus = true;
                        workers.removeLast();
                        break;
                    } catch (NumberFormatException e) {
                        Console.printerror("Числовые поля содержат посторонние символы!");
                        errStatus = true;
                        GlobalErrStatus = true;
                        workers.removeLast();
                        break;
                    }
                }

                try {
                    if (!errStatus) {
                        //Взятую из файла информацию присваиваем объекту коллекции, попутно создавая вложенные объекты:
                        if (counter == 24) {
                            workers.getLast().setCoordinates(new Coordinates(x, y));
                            workers.getLast().setPerson(new Person(LocalDateTime.of(year, month, day, hour, minute), w, p, new Location(locX, locY, locZ, locName)));
                            workers.getLast().setCreationDate(ZonedDateTime.of(LocalDateTime.of(creationYear, creationMonth, creationDay, creationHour, creationMinute), ZoneId.of(zoneID)));
                        } else if ((counter == 20 && !checkIfHasLocation)) {
                            workers.getLast().setCoordinates(new Coordinates(x, y));
                            workers.getLast().setPerson(new Person(LocalDateTime.of(year, month, day, hour, minute), w, p));
                            workers.getLast().setCreationDate(ZonedDateTime.of(LocalDateTime.of(creationYear, creationMonth, creationDay, creationHour, creationMinute), ZoneId.of(zoneID)));
                        } else {
                            Console.printerror("Некоторые поля элемента отсутствуют!");
                            GlobalErrStatus = true;
                            workers.removeLast();
                        }
                    }
                } catch (DateTimeException e) {
                    Console.printerror("Некорректные значения даты и/или времени");
                    GlobalErrStatus = true;
                    workers.removeLast();
                }
            }
        }
        scanner.close();
        if (GlobalErrStatus) Console.println("Файл был повреждён. Загружены только корректные элементы коллекции.");
        if (workers.size() == 0) Console.println("Похоже, что с файлом какая-то проблема, в коллекцию ничего не было загружено");
        return workers;
    }
}
