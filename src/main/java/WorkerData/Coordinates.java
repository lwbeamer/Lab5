package WorkerData;

/**
 * Класс для хранения координат работника
 */
public class Coordinates {
    private final long x;
    private final long y;

    public Coordinates(long x, long y) {
        this.x = x;
        this.y = y;
    }

    /**
     * @return возвращает описание координат в строковом представлении
     */
    public String description() {
        return "X:" + x + " Y:" + y;
    }

    /**
     * @return координата Х
     */
    public long getX() {
        return x;
    }

    /**
     * @return координата Y
     */
    public long getY() {
        return y;
    }
}
