import java.util.Arrays;
import java.util.Scanner;

public class PascalsTriangle {

    static void displayTriangleSolution1() {
        Scanner scan = new Scanner(System.in);
        System.out.println("How many rows would you like to display?: ");

        // how many rows you want to generate
        int rowNum = scan.nextInt();

        // cannot generate a triangle with 0 as input
        if(rowNum == 0) {
            return;
        }

        for(int i = 0; i < rowNum; i++) {
            // reinitialise number to 1 to print first and last entries of row
            int number = 1;

            // shifts the numbers to the right so that they are arranged in a pyramid shape
            for(int j = rowNum - i; j > 1; j--) {
                System.out.print(" ");
            }

            // calculates the binomial coefficients to print to the triangle
            for(int j = 0; j <= i; j++) {
                System.out.print(number + " ");
                number = calculateBinomial(number, i, j);
            }

            // next line
            System.out.println();
        }
    }

    static int calculateBinomial(int num, int i, int j) {
        // pascal's formula

                /*
                   number = 4
                   i and j are equal to n and k in pascal's formula

                   example: 4 * (4 - 1) / (1 + 1)
                                = 3        = 2

                          = 4 * 3 / 2
                               = 1.5

                          = 4 * 1.5
                          = 6
                          6 is then printed
                 */
        return num * (i - j) / (j + 1);
    }
}
