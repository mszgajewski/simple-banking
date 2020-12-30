import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scn = new Scanner(System.in);
        int width = scn.nextInt();

        int n = (width*2)-1;


        for (int i = 0; i <width; i++) {
            for (int j = 0; j < n; j++) {
                if (j == n / 2 || i == width / 2 && j %2 == 0 || j == i * 2 || j==(n-(i*2))-1 ) {
                    System.out.print("*");
                } else if (j % 2 == 0) {
                    System.out.print(".");
                } else {
                    System.out.print(" ");
                }

            } System.out.println();
        }

    }
}