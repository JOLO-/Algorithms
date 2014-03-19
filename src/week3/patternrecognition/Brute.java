package week3.patternrecognition;

public class Brute {

    public static void main(String[] args) {
//        String fileName = args[0];
//        int pointNumber = StdIn.readInt();
//        Point[] points = new Point[pointNumber];
//        for (int i = 0; i < pointNumber; i++) {
//            int x = StdIn.readInt();
//            int y = StdIn.readInt();
//            points[i] = new Point(x, y);
//        }
//        StdDraw.setXscale(0, 32768);
//        StdDraw.setYscale(0, 32768);
//        for (int i = 0; i < pointNumber; i++)
//            for (int j = i + 1; j < pointNumber; j++)
//                for (int k = j + 1; k < pointNumber; k++)
//                    for (int l = k + 1; l < pointNumber; l++)
//                        if (isCollinear(points[i], points[j], points[k], points[l]))
//                            handleCollinear(points[i], points[j], points[k], points[l]);
    }

    private static boolean isCollinear(Point a, Point b, Point c, Point d) {
        double first = a.slopeTo(b);
        double second = b.slopeTo(c);
        double third = c.slopeTo(d);
        return first == second && second == third;
    }

    private static void handleCollinear(Point a, Point b, Point c, Point d) {
        a.draw();
        b.draw();
        c.draw();
        d.draw();
        a.drawTo(b);
        b.drawTo(c);
        c.drawTo(d);
//        StdOut.print(a);
//        StdOut.print(" -> ");
//        StdOut.print(b);
//        StdOut.print(" -> ");
//        StdOut.print(c);
//        StdOut.print(" -> ");
//        StdOut.println(d);
    }
}
