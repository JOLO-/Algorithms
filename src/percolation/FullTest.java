package percolation;

public class FullTest {

    public static void main(String[] args) {
        Percolation p = new Percolation(7);
        p.open(2, 3);
        p.open(5, 5);
        p.open(7, 3);
        p.open(4, 2);
        p.open(3, 3);
        p.open(4, 3);
        p.open(1, 5);
        checkFull(p, 1, 5, true);
        p.open(3, 7);
        p.open(3, 5);
        p.open(6, 1);
        p.open(7, 6);
        p.open(4, 1);
        p.open(5, 7);
        p.open(5, 3);
        p.open(1, 7);
        p.open(1, 2);
        p.open(6, 5);
        p.open(1, 3);
        checkFull(p, 1, 2, true);
        checkFull(p, 1, 3, true);
        checkFull(p, 2, 3, true);
        checkFull(p, 3, 3, true);
        checkFull(p, 4, 1, true);
        checkFull(p, 4, 2, true);
        checkFull(p, 4, 3, true);
        checkFull(p, 5, 3, true);
        for (int i = 1; i < 7; i++)
            if (i != 4)
                checkFull(p, i, 1, false);
        p.open(5, 1);
        checkFull(p, 4, 1, true);
        checkFull(p, 5, 1, true);
        checkFull(p, 6, 1, true);
        checkPerculates(p, false);
        p.open(7, 1);
        checkPerculates(p, true);
    }

    private static void checkFull(Percolation percolation, int i, int j, boolean expectedVal) {
        if (percolation.isFull(i, j) != expectedVal)
            throw new IllegalStateException(i + "," + j);
    }

    private static void checkPerculates(Percolation percolation, boolean expectedVal) {
        if (percolation.percolates() != expectedVal)
            throw new IllegalStateException();
    }
}
