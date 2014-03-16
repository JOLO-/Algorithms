package percolation;

import java.util.Random;

public class PercolationStats {

    private final int N;
    private final int experimentsNumber;
    private final double[] thresholds;
    private Random random = new Random();

    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0)
            throw new IllegalArgumentException();
        this.N = N;
        experimentsNumber = T;
        thresholds = new double[T];

        for (int i = 0; i < T; i++) {
            Percolation percolation = new Percolation(N);
            while (!percolation.percolates()) {
                openRandom(percolation);
            }
            int opendAmount = getOpenedAmount(percolation);
            thresholds[i] = ((double) opendAmount) / ((double) N * N);
            System.out.println("threshold = " + thresholds[i]);
        }
    }

    public double mean() {
        int sum = 0;
        for (int i = 0; i < thresholds.length; i++)
            sum += thresholds[i];
        double mean = ((double) sum) / thresholds.length;
        return mean;
    }

    public double stddev() {
        double mean = mean();
        double sum = 0;
        for (int i = 0; i < thresholds.length; i++) {
            sum += Math.pow(thresholds[i] - mean, 2);
        }
        return sum / (thresholds.length - 1);
    }

    public double confidenceLo() {
        double mu = mean();
        double dev = stddev();
        return mu - 1.96 * Math.sqrt(dev) / Math.sqrt(experimentsNumber);
    }

    public double confidenceHi() {
        double mu = mean();
        double dev = stddev();
        return mu + 1.96 * Math.sqrt(dev) / Math.sqrt(experimentsNumber);
    }

    public static void main(String[] args) {
    }

    private void openRandom(Percolation p) {
        int i = random.nextInt(N) + 1;
        int j = random.nextInt(N) + 1;
        p.open(i, j);
    }

    private int getOpenedAmount(Percolation percolation) {
        int counter = 0;
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (percolation.isOpen(i, j)) {
                    counter++;
                }
            }
        }
        return counter;
    }
}
