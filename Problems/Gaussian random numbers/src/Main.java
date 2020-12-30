import java.util.*;

public class Main {
    public static void main(String[] args) {

        final Scanner scanner = new Scanner(System.in);

        int k = scanner.nextInt();
        int n = scanner.nextInt();
        double m = Double.parseDouble(scanner.nextLine());
        Rand rand = new Rand(k, m);
        System.out.println(rand.findSeed(n, m));
    }
}

class Rand {

    long seed;
    double m;

    Rand(int k, double m) {
        this.seed = k;
        this.m = m;
    }

    public long findSeed(int n, double m) {

        Random random = new Random(seed);

        for (int i = 0; i < n; i++) {

            if (m <= random.nextGaussian()) {
                this.seed++;
                findSeed(n, m);
                break;
            }
        }
        return this.seed;

    }

}