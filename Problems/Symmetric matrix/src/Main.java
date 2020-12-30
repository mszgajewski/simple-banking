import java.util.Scanner;


class Main {

    static boolean isSymmetric(int mat[][], int N) {

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                if (mat[i][j] != mat[j][i]) {
                    return false;
                }
                return true;
            }

    public static void main (String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[][] mat = new int[n][n];
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < n; ++j) {
                    mat[i][j] = scanner.nextInt();
                }
            }

        if (isSymmetric(mat, n))
            System.out.println("YES");
        else
            System.out.println ("NO");

    }
}
