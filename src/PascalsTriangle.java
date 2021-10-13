import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PascalsTriangle {

    static void displayTriangle() {
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

                number = number * (i - j) / (j + 1);
            }

            // next line
            System.out.println();
        }
    }
}
