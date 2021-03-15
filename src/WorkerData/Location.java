package WorkerData;

import java.util.Objects;

/**
 * Класс для хранения Location работника
 */
public class Location {
    private final double x;
    private final Float y; //Поле не может быть null
    private final double z;
    private final String name; //Поле может быть null

    public Location(double x, Float y, double z, String name) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.name = name;
    }

    /**
     * @return возвращает описание координат в строковом представлении
     */
    public String description() {
        if (name != null) return "X:" + x + " Y:" + y + " Z:" + z + " " + name;
        return "X:" + x + " Y:" + y + " Z:" + z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Double.compare(location.x, x) == 0 && Double.compare(location.z, z) == 0 && Objects.equals(y, location.y) && Objects.equals(name, location.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, z, name);
    }

    /**
     * @return возвращает координату X
     */
    public double getX() {
        return x;
    }

    /**
     * @return возвращает координату Y
     */
    public Float getY() {
        return y;
    }

    /**
     * @return возвращает координату Z
     */
    public double getZ() {
        return z;
    }

    /**
     * @return возвращает название локации
     */
    public String getName() {
        return name;
    }
}
