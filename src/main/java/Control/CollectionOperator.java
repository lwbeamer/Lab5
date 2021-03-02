package Control;

import WorkerData.Status;
import WorkerData.Worker;

import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Collections;

/**
 * Класс для управления коллекцией. Выполняет все операции с коллекцией.
 */
public class CollectionOperator {
    private ArrayDeque<Worker> workersCollection = new ArrayDeque<>();
    private final Downloader downloader;
    private final Uploader uploader;
    private LocalDateTime lastInitialisationTime;


    public CollectionOperator(Downloader downloader, Uploader uploader) {
        this.downloader = downloader;
        this.uploader = uploader;
        System.out.println("Программа запущена");
        loadCollection();
    }


    /**
     * @return возвращает время инициализации коллекции
     */
    public LocalDateTime getLastInitialisationTime() {
        return lastInitialisationTime;
    }

    /**
     * Метод запускает парсер, тот вовращает прочитанную из файла коллекцию, время инициализации обновляется.
     */
    private void loadCollection() {
        workersCollection = downloader.parse();
        lastInitialisationTime = LocalDateTime.now();
    }

    /**
     * Метод запускает загрузчик, который записывает коллекцию в файл
     */
    public void saveCollection() {
        uploader.upload(workersCollection);
    }

    /**
     * Метод для прямой сортировки по умолчанию
     */
    public void sortCollection() {
        //Сортировка по возрастанию зарплаты:
        Worker[] a = workersCollection.toArray(new Worker[0]);
        Arrays.sort(a);
        workersCollection.clear();
        Collections.addAll(workersCollection, a);
    }

    /**
     * Метод для обратной сортировки по умолчанию
     */
    public void sortReverseCollection() {
        //Сортировка по убыванию зарплаты:
        Worker[] a = workersCollection.toArray(new Worker[0]);
        Arrays.sort(a);
        workersCollection.clear();
        for (int i = a.length-1; i>=0; i--){
            workersCollection.addLast(a[i]);
        }
    }

    /**
     * @return возвращает тип коллекции
     */
    public String collectionType() {
        return workersCollection.getClass().getName();
    }

    /**
     * @return возвращает количество элементов коллекции
     */
    public int collectionSize() {
        return workersCollection.size();
    }

    /**
     * @return возвращет коллекцию
     */
    public ArrayDeque<Worker> getWorkersCollection() {
        return workersCollection;
    }

    /**
     * @return возвращает строку, содержащую информацию обо всех элементах коллекции
     */
    public String workersDesc(){
        if (workersCollection.isEmpty()) return "Коллекция пуста!";

        StringBuilder deskString = new StringBuilder();
        for (Worker worker : workersCollection) {
            deskString.append(worker.description());
            if (worker != workersCollection.getLast()) deskString.append("\n\n");
        }
        return deskString.toString();
    }

    /**
     * Добавляет элемент в коллекцию
     * @param worker элемент для добавления
     */
    public void addToCollection(Worker worker) {
        workersCollection.add(worker);
    }

    /**
     * @return возвращает id элемента. Если коллекция пуста, то первый id будет равен 1.
     */
    public Long generateId() {
        if (workersCollection.isEmpty()) return 1L;
        return workersCollection.getLast().getId() + 1;
    }

    /**
     * @param id ID рабочего
     * @return вовзращает работника по id
     */
    public Worker getById(long id) {
        for (Worker worker : workersCollection) {
            if (worker.getId() == id) return worker;
        }
        return null;
    }

    /**
     * удаляет рабочего из коллекции
     * @param worker работник для удаления
     */
    public void removeFromCollection(Worker worker) {
        workersCollection.remove(worker);
    }

    /**
     * Удаляет все элементы коллекции
     */
    public void clearCollection() {
        workersCollection.clear();
    }

    /**
     * @return возвращает последний элемент коллекции, если он есть
     */
    public Worker getLast() {
        if (workersCollection.isEmpty()) return null;
        return workersCollection.getLast();
    }

    /**
     * @return возвращает первый элемент коллекции, если он есть
     */
    public Worker getFirst() {
        if (workersCollection.isEmpty()) return null;
        return workersCollection.getFirst();
    }

    /**
     * @param statusToFilter статус, по которому будет идти отбор
     * @return возвращает описание элемента с нужным статусом
     */
    public String statusFilteredInfo(Status statusToFilter) {
        StringBuilder deskStr = new StringBuilder();
        for (Worker worker : workersCollection) {
            if (worker.getStatus().equals(statusToFilter)) {
                deskStr.append(worker.description() + "\n\n");
            }
        }
        return deskStr.toString().trim();
    }


}



