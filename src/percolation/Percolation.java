package percolation;

public class Percolation {

    private final int N;
    private final int startPointIndex;
    private final int endpointIndex;
    private final WeightedQuickUnionUF weightedQuickUnionUF;
    private final boolean[] grid;

    /**
     * Create N-by-N grid, with all sites blocked
     *
     * @param N
     */
    public Percolation(int N) {
        this.N = N;
        startPointIndex = 0;
        endpointIndex = N * N + 1;
        weightedQuickUnionUF = new WeightedQuickUnionUF(endpointIndex + 1);
        grid = new boolean[endpointIndex + 2];
    }

    /**
     * open site (row i, column j) if it is not already
     *
     * @param i row index
     * @param j column index
     */
    public void open(int i, int j) {
        checkPreconditions(i, j);
        //open the element
        int index = convertToIndex(i, j);
        if (!grid[index]) {
            grid[index] = true;
            //link with neighbor's cells
            unionWithNeighbors(i, j, index);
        }
    }

    /**
     * is site (row i, column j) open?
     *
     * @param i row index
     * @param j column index
     * @return {@code true} if grid[i][j] is opened, {@code false} otherwise.
     */
    public boolean isOpen(int i, int j) {
        checkPreconditions(i, j);
        return grid[convertToIndex(i, j)];
    }

    /**
     * is site (row i, column j) full?
     *
     * @param i row index
     * @param j column index
     * @return {@code true} if grid[i][j] is full, {@code false} otherwise.
     */
    public boolean isFull(int i, int j) {
        checkPreconditions(i, j);
        return isOpen(i, j) && weightedQuickUnionUF.connected(startPointIndex, convertToIndex(i, j));
    }

    /**
     * does the system percolate?
     *
     * @return {@code true} if percolates, otherwise returns {@code false}
     */
    public boolean percolates() {
        return weightedQuickUnionUF.connected(startPointIndex, endpointIndex);
    }

    private int convertToIndex(int i, int j) {
        return (i - 1) * N + (j - 1) + 1; // considering that the first (null-index) element is start point
    }

    private void checkPreconditions(int i, int j) {
        if (i < 1 || i > N || j < 1 || j > N)
            throw new IndexOutOfBoundsException("i: " + i + "; j: " + j);
    }

    private void unionWithNeighbors(int i, int j, int globalInd) {
        if (i == 1) {
            weightedQuickUnionUF.union(startPointIndex, globalInd);
        } else if (i > 1) {
            if (isOpen(i - 1, j)) {
                weightedQuickUnionUF.union(globalInd, convertToIndex(i - 1, j)); //link with top-neighbor
            }
        }
        if (i < N) {
            if (isOpen(i + 1, j)) {
                weightedQuickUnionUF.union(globalInd, convertToIndex(i + 1, j)); //link with bottom-neighbor
            }
        } else if (i == N) {
            weightedQuickUnionUF.union(endpointIndex, globalInd);
        }
        if (j > 1) {
            if (isOpen(i, j - 1)) {
                weightedQuickUnionUF.union(globalInd, convertToIndex(i, j - 1)); //link with left-neighbor
            }
        }
        if (j < N) {
            if (isOpen(i, j + 1)) {
                weightedQuickUnionUF.union(globalInd, convertToIndex(i, j + 1)); //link with right-neighbor
            }
        }
    }
}
