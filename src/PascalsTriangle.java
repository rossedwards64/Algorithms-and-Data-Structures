import java.util.Scanner;

public class PascalsTriangle {

    public static void main(String[] args) {
        displayTriangleSolution1();
        displayTriangleSolution2();
    }

    static void displayTriangleSolution1() {
        Scanner scan = new Scanner(System.in);
        System.out.println("How many rows would you like to display?: ");

        int rowNum = scan.nextInt();

        if(rowNum == 0) {
            return;
        }

        for(int i = 0; i < rowNum; i++) {
            int number = 1;

            for(int j = rowNum - i; j > 1; j--) {
                System.out.print(" ");
            }

            for(int j = 0; j <= i; j++) {
                System.out.print(number + " ");
                number = calculateBinomial(number, i, j);
            }

            // next line
            System.out.println();
        }
    }

    static int calculateBinomial(int num, int n, int k) {
        return num * (n - k) / (k + 1);
    }

    static void displayTriangleSolution2() {
        Scanner scan = new Scanner(System.in);
        System.out.println("How many lines would you like to print?: ");
        int rowNum = scan.nextInt();

        if(rowNum == 0) {
            return;
        }

        for(int i = 0; i <= rowNum; i++) {
            for(int j = 0; j <= rowNum - i; j++) {
                System.out.print(" ");
            }

            for(int j = 0; j <= i; j++) {
                System.out.print(" " + factorial(i) / (factorial(i - j) * factorial(j)));
            }
            System.out.println();
        }
    }

    static int factorial(int i) {
        if(i == 0) {
            return 1;
        }

        return i * factorial(i - 1);
    }
}
