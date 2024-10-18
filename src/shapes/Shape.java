package shapes;

import java.util.Comparator;

public abstract class Shape implements Comparable<Shape> {
    protected double height;

    public Shape(double height) {
        this.height = height;
    }

    public double getHeight() {
        return height;
    }

    // Abstract methods for subclasses to implement
    public abstract double calcVolume();
    public abstract double calcBaseArea();

    // Implementation of compareTo() method from Comparable interface
    @Override
    public int compareTo(Shape s) {
        if (this.height > s.height) return 1;
        else if (this.height < s.height) return -1;
        else return 0;
    }

    // Comparator for comparing shapes by volume
    public static Comparator<Shape> volumeComparator = new Comparator<Shape>() {
        @Override
        public int compare(Shape s1, Shape s2) {
            return Double.compare(s1.calcVolume(), s2.calcVolume());
        }
    };

    // Comparator for comparing shapes by base area
    public static Comparator<Shape> baseAreaComparator = new Comparator<Shape>() {
        @Override
        public int compare(Shape s1, Shape s2) {
            return Double.compare(s1.calcBaseArea(), s2.calcBaseArea());
        }
    };
}
