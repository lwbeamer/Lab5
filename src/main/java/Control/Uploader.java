package Control;

import WorkerData.Worker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;

/**
 * Класс для загрузки информации из коллекции в файл
 */
public class Uploader {

    private final String filePath;

    public Uploader(String filePath){
        this.filePath = filePath;
    }

    /**
     * Загружает коллекцию в файл, записывая все нужные поля построчно
     * @param workersCollection - метод принимает коллекцию в качестве параметра
     */
    public void upload(ArrayDeque<Worker> workersCollection){
        try{
            BufferedWriter br = new BufferedWriter(new FileWriter(filePath));

            br.write("<?xml version=\"1.0\"?>\n");
            br.write("<Workers>\n");

            for (Worker worker : workersCollection){
                br.write("\t<Worker>\n");
                br.write("\t\t<id>"+worker.getId()+"</id>\n");
                br.write("\t\t<name>"+worker.getName()+"</name>\n");
                br.write("\t\t\t<coordinates>\n");
                br.write("\t\t\t\t<X>"+worker.getCoordinates().getX()+"</X>\n");
                br.write("\t\t\t\t<Y>"+worker.getCoordinates().getY()+"</Y>\n");
                br.write("\t\t\t</coordinates>\n");
                br.write("\t\t\t<creationDate>\n");
                br.write("\t\t\t\t<year>"+worker.getCreationDate().getYear()+"</year>\n");
                br.write("\t\t\t\t<month>"+worker.getCreationDate().getMonthValue()+"</month>\n");
                br.write("\t\t\t\t<day>"+worker.getCreationDate().getDayOfMonth()+"</day>\n");
                br.write("\t\t\t\t<hour>"+worker.getCreationDate().getHour()+"</hour>\n");
                br.write("\t\t\t\t<minute>"+worker.getCreationDate().getMinute()+"</minute>\n");
                br.write("\t\t\t\t<zoneID>"+worker.getCreationDate().getZone()+"</zoneID>\n");
                br.write("\t\t\t</creationDate>\n");
                br.write("\t\t<salary>"+worker.getSalary()+"</salary>\n");
                br.write("\t\t<position>"+worker.getPosition()+"</position>\n");
                br.write("\t\t<status>"+worker.getStatus()+"</status>\n");
                br.write("\t\t\t<person>\n");
                br.write("\t\t\t\t<birthday>\n");
                br.write("\t\t\t\t\t<year>"+worker.getPerson().getBirthday().getYear()+"</year>\n");
                br.write("\t\t\t\t\t<month>"+worker.getPerson().getBirthday().getMonthValue()+"</month>\n");
                br.write("\t\t\t\t\t<day>"+worker.getPerson().getBirthday().getDayOfMonth()+"</day>\n");
                br.write("\t\t\t\t\t<hour>"+worker.getPerson().getBirthday().getHour()+"</hour>\n");
                br.write("\t\t\t\t\t<minute>"+worker.getPerson().getBirthday().getMinute()+"</minute>\n");
                br.write("\t\t\t\t</birthday>\n");
                if (worker.getPerson().getWeight() != null)
                br.write("\t\t\t\t<weight>"+worker.getPerson().getWeight()+"</weight>\n");
                else br.write("\t\t\t\t<weight></weight>\n");
                br.write("\t\t\t\t<passportID>"+worker.getPerson().getPassportID()+"</passportID>\n");
                if (worker.getPerson().getLocation() != null) {
                    br.write("\t\t\t\t<location>\n");
                    br.write("\t\t\t\t\t<X>" + worker.getPerson().getLocation().getX() + "</X>\n");
                    br.write("\t\t\t\t\t<Y>" + worker.getPerson().getLocation().getY() + "</Y>\n");
                    br.write("\t\t\t\t\t<Z>" + worker.getPerson().getLocation().getZ() + "</Z>\n");
                    if (worker.getPerson().getLocation().getName() != null)
                    br.write("\t\t\t\t\t<name>" + worker.getPerson().getLocation().getName() + "</name>\n");
                    else br.write("\t\t\t\t\t<name></name>\n");
                    br.write("\t\t\t\t</location>\n");
                }
                br.write("\t\t\t</person>\n");
                br.write("\t</Worker>\n");
            }
            br.write("</Workers>");

            br.close();
            Console.println("Сохранено!");
        } catch (IOException e){
            Console.printerror("Проверьте файл");
        }
    }
}
