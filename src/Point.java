import java.util.Comparator;

public class Point implements Comparable<Point> {

    public final Comparator<Point> SLOPE_ORDER = new Comparator<Point>() {
        @Override
        public int compare(Point o1, Point o2) {
            double s1 = slopeTo(o1);
            double s2 = slopeTo(o2);
            return (s1 < s2) ? -1 : (s1 > s2) ? 1 : 0;
        }
    };

    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void draw() {
        StdDraw.point(x, y);
    }

    public void drawTo(Point that) {
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    public double slopeTo(Point that) {
        if (x == that.x) return Double.POSITIVE_INFINITY;
        else return (that.y - y) / (that.x - x);
    }

    public int compareTo(Point that) {
        return (y < that.y) ? -1 : (y > that.y) ? 1 : (x < that.x) ? -1 : (x > that.x) ? 1 : 0;
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }
}