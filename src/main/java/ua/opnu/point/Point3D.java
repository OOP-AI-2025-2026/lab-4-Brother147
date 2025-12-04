package ua.opnu.point;

public class Point3D extends Point {

    private int z;

    /**
     * Конструктор по умолчанию: (0, 0, 0).
     */
    public Point3D() {
        super();
        this.z = 0;
    }

    /**
     * Конструктор с координатами (x, y, z).
     */
    public Point3D(int x, int y, int z) {
        super(x, y);
        this.z = z;
    }

    /**
     * Устанавливает координаты (x, y, z).
     */
    public void setLocation(int x, int y, int z) {
        super.setLocation(x, y);
        this.z = z;
    }

    /**
     * Требование из задания:
     * при setLocation(x, y) координата z должна стать 0.
     */
    @Override
    public void setLocation(int x, int y) {
        super.setLocation(x, y);
        this.z = 0;
    }

    /**
     * Координата z.
     */
    public int getZ() {
        return z;
    }

    /**
     * Расстояние от точки до начала координат (0,0,0)
     * с учетом z.
     */
    @Override
    public double distanceFromOrigin() {
        int x = getX();
        int y = getY();
        int z = getZ();
        return Math.sqrt(x * x + y * y + z * z);
    }

    /**
     * Расстояние между двумя 3D-точками.
     * sqrt( (x1-x2)^2 + (y1-y2)^2 + (z1-z2)^2 )
     */
    public double distance(Point3D p) {
        int dx = getX() - p.getX();
        int dy = getY() - p.getY();
        int dz = getZ() - p.getZ();
        return Math.sqrt(dx * dx + dy * dy + dz * dz);
    }

    /**
     * Строковое представление с тремя координатами.
     */
    @Override
    public String toString() {
        return "(" + getX() + ", " + getY() + ", " + z + ")";
    }
}
