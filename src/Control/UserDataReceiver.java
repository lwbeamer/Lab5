package Control;

import WorkerData.*;
import Exceptions.ScriptErrorException;
import Exceptions.ValueIsEmptyException;
import Exceptions.ValueOutOfRangeException;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;


/**
 * Класс нужен для обработки данных пользователя при вводе информации об элементах коллекции. Например для работы команды add, update и т.д.
 */
public class UserDataReceiver {

    private Scanner inputScanner;
    private boolean scriptMode;

    public UserDataReceiver(Scanner inputScanner) {
        this.inputScanner = inputScanner;
        scriptMode = false;
    }

    /**
     * Установка режима работы скрипта
     */
    public void setScriptMode() {
        scriptMode = true;
    }

    /**
     * Установка нормального режима работы
     */
    public void setNormalMode() {
        scriptMode = false;
    }

    /**
     * Метод спрашивает у пользователя имя работника
     * @return возвращает введённое имя
     * @throws ScriptErrorException - если что-то пошло не так, то наверх пробрасывается исключение скрипта. Это нужно для того,
     * чтобы используемая команда в скриптовом режиме вернула код ошибки
     */
    public String askName() throws ScriptErrorException {
        String name;
        while (true) {
            try {
                Console.println("Введите имя рабочего:");
                name = inputScanner.nextLine().trim();
                if (scriptMode) Console.println(name);
                if (name.equals("")) throw new ValueIsEmptyException();
                break;
            } catch (ValueIsEmptyException e) {
                Console.printerror("Имя не может быть пустым!");
                if (scriptMode) throw new ScriptErrorException();

            }
        }
        return name;
    }

    /**
     * Метод спрашивает у пользователя координаты
     * @return возвращает введённые координаты
     * @throws ScriptErrorException - если что-то пошло не так, то наверх пробрасывается исключение скрипта. Это нужно для того,
     * чтобы используемая команда в скриптовом режиме вернула код ошибки
     */
    public Coordinates askCoordinates() throws ScriptErrorException {
        long x;
        long y;
        x = askX();
        y = askY();
        return new Coordinates(x, y);
    }

    /**
     * @return возвращает координату X
     * @throws ScriptErrorException - если что-то пошло не так, то наверх пробрасывается исключение скрипта. Это нужно для того,
     * чтобы используемая команда в скриптовом режиме вернула код ошибки
     */
    public long askX() throws ScriptErrorException {
        String strX;
        long x;
        while (true) {
            try {
                Console.println("Введите координату X:");
                strX = inputScanner.nextLine().trim();
                if (strX.equals("")) throw new ValueIsEmptyException();
                if (scriptMode) Console.println(strX);
                x = Long.parseLong(strX);
                break;
            } catch (NumberFormatException e) {
                Console.printerror("Координата X должна быть представлена целым числом!");
                if (scriptMode) throw new ScriptErrorException();
            } catch (ValueIsEmptyException e){
                Console.printerror("Поле не может быть пустым");
                if (scriptMode) throw new ScriptErrorException();
            }
        }
        return x;
    }

    /**
     * @return возвращает координату Y
     * @throws ScriptErrorException - если что-то пошло не так, то наверх пробрасывается исключение скрипта. Это нужно для того,
     * чтобы используемая команда в скриптовом режиме вернула код ошибки
     */
    public long askY() throws ScriptErrorException {
        String strY;
        long y;
        while (true) {
            try {
                Console.println("Введите координату Y:");
                strY = inputScanner.nextLine().trim();
                if (strY.equals("")) throw new ValueIsEmptyException();
                if (scriptMode) Console.println(strY);
                y = Long.parseLong(strY);
                break;
            } catch (NumberFormatException e) {
                Console.printerror("Координата Y должна быть представлена целым числом!");
                if (scriptMode) throw new ScriptErrorException();
            } catch (ValueIsEmptyException e){
                Console.printerror("Поле не может быть пустым");
                if (scriptMode) throw new ScriptErrorException();
            }
        }
        return y;
    }

    /**
     * Метод спрашивает зарплату работника
     * @return возвращает зарплату
     * @throws ScriptErrorException - если что-то пошло не так, то наверх пробрасывается исключение скрипта. Это нужно для того,
     * чтобы используемая команда в скриптовом режиме вернула код ошибки
     */
    public double askSalary() throws ScriptErrorException {
        String strSalary;
        double salary;
        while (true) {
            try {
                Console.println("Введите зарплату:");
                strSalary = inputScanner.nextLine().trim();
                if (strSalary.equals("")) throw new ValueIsEmptyException();
                if (scriptMode) Console.println(strSalary);
                salary = Double.parseDouble(strSalary);
                if (salary <= 0) throw new ValueOutOfRangeException();
                break;
            } catch (ValueOutOfRangeException e) {
                Console.printerror("Зарплата должна быть больше нуля!");
                if (scriptMode) throw new ScriptErrorException();
            } catch (NumberFormatException e) {
                Console.printerror("Зарплата должна быть представлена числом!");
                if (scriptMode) throw new ScriptErrorException();
            } catch (ValueIsEmptyException e){
                Console.printerror("Поле не может быть пустым");
                if (scriptMode) throw new ScriptErrorException();
            }
        }
        return salary;
    }

    /**
     * Метод спрашивает должность работника
     * @return возвращает должность
     * @throws ScriptErrorException - если что-то пошло не так, то наверх пробрасывается исключение скрипта. Это нужно для того,
     * чтобы используемая команда в скриптовом режиме вернула код ошибки
     */
    public Position askPosition() throws ScriptErrorException {
        String strPosition;
        Position position;
        while (true) {
            try {
                Console.println("Список должностей - " + Position.getValues());
                Console.println("Введите должность:");
                strPosition = inputScanner.nextLine().trim();
                if (strPosition.equals("")) throw new ValueIsEmptyException();
                if (scriptMode) Console.println(strPosition);
                position = Position.valueOf(strPosition.toUpperCase());
                break;
            } catch (IllegalArgumentException e) {
                Console.printerror("Такой должности нет в списке!");
                if (scriptMode) throw new ScriptErrorException();
            } catch (ValueIsEmptyException e){
                Console.printerror("Поле не может быть пустым");
                if (scriptMode) throw new ScriptErrorException();
            }
        }
        return position;
    }

    /**
     * Метод спрашивает статус работника
     * @return возвращает статус
     * @throws ScriptErrorException - если что-то пошло не так, то наверх пробрасывается исключение скрипта. Это нужно для того,
     * чтобы используемая команда в скриптовом режиме вернула код ошибки
     */
    public Status askStatus() throws ScriptErrorException {
        String strStatus;
        Status status;
        while (true) {
            try {
                Console.println("Список статусов - " + Status.getValues());
                Console.println("Введите статус:");
                strStatus = inputScanner.nextLine().trim();
                if (strStatus.equals("")) throw new ValueIsEmptyException();
                if (scriptMode) Console.println(strStatus);
                status = Status.valueOf(strStatus.toUpperCase());
                break;
            } catch (IllegalArgumentException e) {
                Console.printerror("Такого статуса нет в списке!");
                if (scriptMode) throw new ScriptErrorException();
            } catch (ValueIsEmptyException e){
                Console.printerror("Поле не может быть пустым");
                if (scriptMode) throw new ScriptErrorException();
            }
        }
        return status;
    }

    /**
     * Метод возвращает личные данные работника. Этот составной класс, поэтому вызываются другие методы, для входящих в него данных
     * @return Person работника
     * @throws ScriptErrorException - если что-то пошло не так, то наверх пробрасывается исключение скрипта. Это нужно для того,
     * чтобы используемая команда в скриптовом режиме вернула код ошибки
     */
    public Person askPerson() throws ScriptErrorException {
        LocalDateTime birthday;
        Float weight;
        String passportID;
        Location location;

        birthday = askBirthday();
        weight = askWeight();
        passportID = askPassportID();
        location = askLocation();

        if (location != null) return new Person(birthday,weight,passportID,location);
        return new Person(birthday,weight,passportID);
    }

    /**
     * @return возвращается дата рождения работника
     * @throws ScriptErrorException - если что-то пошло не так, то наверх пробрасывается исключение скрипта. Это нужно для того,
     * чтобы используемая команда в скриптовом режиме вернула код ошибки
     */
    public LocalDateTime askBirthday() throws ScriptErrorException {
        return LocalDateTime.of(askDate(),askTime());
    }

    /**
     * @return возвращается дата
     * @throws ScriptErrorException - если что-то пошло не так, то наверх пробрасывается исключение скрипта. Это нужно для того,
     * чтобы используемая команда в скриптовом режиме вернула код ошибки
     */
    public LocalDate askDate() throws ScriptErrorException {
        LocalDate localDate;
        String strDate;
        String [] ArrDate;
        while (true){
            try{
                Console.println("Введите дату рождения в формате YYYY-MM-DD:");
                strDate = inputScanner.nextLine().trim();
                if (strDate.equals("")) throw new ValueIsEmptyException();
                if (scriptMode) Console.println(strDate);
                ArrDate = strDate.split("-");
                localDate = LocalDate.of(Integer.parseInt(ArrDate[0]), Integer.parseInt(ArrDate[1]), Integer.parseInt(ArrDate[2]));
                break;
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException exception){
                Console.printerror("Дата должна быть представлена числом в нужном формате");
                if (scriptMode) throw new ScriptErrorException();
            } catch (DateTimeException e){
                Console.printerror("Похоже, что такой даты не существует в природе, попробуйте ещё раз!");
                if (scriptMode) throw new ScriptErrorException();
            } catch (ValueIsEmptyException e){
                Console.printerror("Поле не может быть пустым");
                if (scriptMode) throw new ScriptErrorException();
            }
        }
        return localDate;
    }

    /**
     * @return возвращается время
     * @throws ScriptErrorException - если что-то пошло не так, то наверх пробрасывается исключение скрипта. Это нужно для того,
     * чтобы используемая команда в скриптовом режиме вернула код ошибки
     */
    public LocalTime askTime() throws ScriptErrorException {
        LocalTime localTime;
        String strTime;
        String [] ArrTime;
        while (true){
            try{
                Console.println("Введите время рождения в формате HH-MM:");
                strTime  = inputScanner.nextLine().trim();
                if (strTime.equals("")) throw new ValueIsEmptyException();
                if (scriptMode) Console.println(strTime);
                ArrTime = strTime.split("-");
                localTime = LocalTime.of(Integer.parseInt(ArrTime[0]), Integer.parseInt(ArrTime[1]));
                break;
            } catch (NumberFormatException | ArrayIndexOutOfBoundsException exception){
                Console.printerror("Время должно быть представлено числом в нужном формате");
                if (scriptMode) throw new ScriptErrorException();
            } catch (DateTimeException e){
                Console.printerror("Похоже, что такого времени не существует в природе, попробуйте ещё раз!");
                if (scriptMode) throw new ScriptErrorException();
            } catch (ValueIsEmptyException e){
                Console.printerror("Поле не может быть пустым");
                if (scriptMode) throw new ScriptErrorException();
            }
        }
        return localTime;
    }

    /**
     * @return возвращается вес работника
     * @throws ScriptErrorException - если что-то пошло не так, то наверх пробрасывается исключение скрипта. Это нужно для того,
     * чтобы используемая команда в скриптовом режиме вернула код ошибки
     */
    public Float askWeight() throws ScriptErrorException {
        String strWeight;
        float weight;
        while (true) {
            try {
                Console.println("Введите вес:");
                strWeight = inputScanner.nextLine().trim();
                if (scriptMode) Console.println(strWeight);
                if (strWeight.equals("")) return null;
                weight = Float.parseFloat(strWeight);
                if (weight <= 0) throw new ValueOutOfRangeException();
                break;
            } catch (ValueOutOfRangeException e) {
                Console.printerror("Вес должен быть больше нуля!");
                if (scriptMode) throw new ScriptErrorException();
            } catch (NumberFormatException e) {
                Console.printerror("Вес должен быть представлен числом!");
                if (scriptMode) throw new ScriptErrorException();
            }
        }
        return weight;
    }

    /**
     * @return возвращается номер паспорта работника
     * @throws ScriptErrorException - если что-то пошло не так, то наверх пробрасывается исключение скрипта. Это нужно для того,
     * чтобы используемая команда в скриптовом режиме вернула код ошибки
     */
    public String askPassportID() throws ScriptErrorException {
        String passportID;
        while (true) {
            try {
                Console.println("Введите номер паспорта:");
                passportID = inputScanner.nextLine().trim();
                if (scriptMode) Console.println(passportID);
                if (passportID.equals("")) throw new ValueIsEmptyException();
                break;
            } catch (ValueIsEmptyException e) {
                Console.printerror("Номер паспорта не может быть пустым!");
                if (scriptMode) throw new ScriptErrorException();
            }
        }
        return passportID;
    }

    /**
     * Метод спрашивает место жительства работника. Оно может отсутсвовать
     * @return Location работника
     * @throws ScriptErrorException - если что-то пошло не так, то наверх пробрасывается исключение скрипта. Это нужно для того,
     * чтобы используемая команда в скриптовом режиме вернула код ошибки
     */
    public Location askLocation() throws ScriptErrorException {
        if (!askQuestion("Хотите указать местоположение рабочего?")) return null;
        double x;
        Float y;
        double z;
        String name;
        x = askLocationX();
        y = askLocationY();
        z = askLocationZ();
        name = askLocationName();
        return new Location(x,y,z,name);
    }

    /**
     * @return вовзращает координату X места жительства
     * @throws ScriptErrorException - если что-то пошло не так, то наверх пробрасывается исключение скрипта. Это нужно для того,
     * чтобы используемая команда в скриптовом режиме вернула код ошибки
     */
    public double askLocationX() throws ScriptErrorException {
        String strX;
        double x;
        while (true) {
            try {
                Console.println("Введите координату X:");
                strX = inputScanner.nextLine().trim();
                if (strX.equals("")) throw new ValueIsEmptyException();
                if (scriptMode) Console.println(strX);
                x = Double.parseDouble(strX);
                break;
            } catch (NumberFormatException e) {
                Console.printerror("Координата X должна быть представлена числом!");
                if (scriptMode) throw new ScriptErrorException();
            } catch (ValueIsEmptyException e){
                Console.printerror("Поле не может быть пустым");
                if (scriptMode) throw new ScriptErrorException();
            }
        }
        return x;
    }

    /**
     * @return вовзращает координату Y места жительства
     * @throws ScriptErrorException - если что-то пошло не так, то наверх пробрасывается исключение скрипта. Это нужно для того,
     * чтобы используемая команда в скриптовом режиме вернула код ошибки
     */
    public Float askLocationY() throws ScriptErrorException {
        String strY;
        float y;
        while (true) {
            try {
                Console.println("Введите координату Y:");
                strY = inputScanner.nextLine().trim();
                if (strY.equals("")) throw new ValueIsEmptyException();
                if (scriptMode) Console.println(strY);
                y = Float.parseFloat(strY);
                break;
            } catch (NumberFormatException e) {
                Console.printerror("Координата Y должна быть представлена числом!");
                if (scriptMode) throw new ScriptErrorException();
            } catch (ValueIsEmptyException e){
                Console.printerror("Поле не может быть пустым");
                if (scriptMode) throw new ScriptErrorException();
            }
        }
        return y;
    }

    /**
     * @return вовзращает координату Z места жительства
     * @throws ScriptErrorException - если что-то пошло не так, то наверх пробрасывается исключение скрипта. Это нужно для того,
     * чтобы используемая команда в скриптовом режиме вернула код ошибки
     */
    public double askLocationZ() throws ScriptErrorException {
        String strZ;
        double z;
        while (true) {
            try {
                Console.println("Введите координату Z:");
                strZ = inputScanner.nextLine().trim();
                if (strZ.equals("")) throw new ValueIsEmptyException();
                if (scriptMode) Console.println(strZ);
                z = Double.parseDouble(strZ);
                break;
            } catch (NumberFormatException e) {
                Console.printerror("Координата Z должна быть представлена числом!");
                if (scriptMode) throw new ScriptErrorException();
            } catch (ValueIsEmptyException e){
                Console.printerror("Поле не может быть пустым");
                if (scriptMode) throw new ScriptErrorException();
            }
        }
        return z;
    }

    /**
     * @return возвращет название локации
     */
    public String askLocationName(){
        String LocationName;
        Console.println("Введите название локации:");
        LocationName = inputScanner.nextLine().trim();
        if (scriptMode) Console.println(LocationName);
        if (LocationName.equals("")) return null;
        return LocationName;
    }

    /**
     * Метод задаёт вопрос, на который пользователь отвечает да или нет.
     * @param question - параметр строка, содержащая сам вопрос
     * @return возрващается true или false, в зависимости от ответа
     * @throws ScriptErrorException - если что-то пошло не так, то наверх пробрасывается исключение скрипта. Это нужно для того,
     * чтобы используемая команда в скриптовом режиме вернула код ошибки
     */
    public boolean askQuestion(String question) throws ScriptErrorException {
        String finalQuestion = question + " (+/-):";
        String answer;
        while (true) {
            try {
                Console.println(finalQuestion);
                answer = inputScanner.nextLine().trim();
                if (scriptMode) Console.println(answer);
                if (!answer.equals("+") && !answer.equals("-")) throw new ValueOutOfRangeException();
                break;
            }
            catch (ValueOutOfRangeException e) {
                Console.printerror("Напишите в ответ только '+' или '-'!");
                if (scriptMode) throw new ScriptErrorException();
            }
        }
        return answer.equals("+");
    }

    /**
     * @return возвращает сканнер
     */
    public Scanner getInputScanner() {
        return inputScanner;
    }

    /**
     * устанавливает сканнер
     * @param inputScanner - передаётся объект класса Scanner
     */
    public void setInputScanner(Scanner inputScanner) {
        this.inputScanner = inputScanner;
    }
}
