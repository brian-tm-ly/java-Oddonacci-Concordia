
/** COMP352 - Assignment 1
 * @author Brian Ly (40028072)
 * @version 1.0
 * 
 * Oddonacci Sequence
 * The Oddonacci sequence is a sequence where the numbers are the sum of the previous three numbers. The first three numbers are 1, 1, and 1.
 * This program calculates the nth number in the Oddonacci sequence using three different methods: Linear Recursion, Multiple Recursion, and Tail Recursion.
 */

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class Oddonacci {
    public static void main(String[] args) {

        PrintWriter writer = null;

        try {
            writer = new PrintWriter(new FileOutputStream("OddoOut2.txt"), true);
            for (int i = 5; i <= 200; i += 5) {
                long startTime = System.nanoTime();
                double[] resultLinear = linearOddonacci(i);
                long endTime = System.nanoTime();
                double timeElapsedLinear = (endTime - startTime) / 1000000.0; // Time in milliseconds

                startTime = System.nanoTime();
                double result = multipleOddonacci(i);
                endTime = System.nanoTime();
                double timeElapsedMultiple = (endTime - startTime) / 1000000.0; // Time in milliseconds

                writer.println(
                        "linearOddonacci(" + i + ") = " + resultLinear[0] + ", Time: " + timeElapsedLinear + " ms");
                writer.println(
                        "multipleOddonacci(" + i + ") = " + result + ", Time: " + timeElapsedMultiple + " ms");
                writer.println();

            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.getMessage();
        }

    }

    // Linear Recursion

    /**
     * Calculate the nth Oddonacci number using linear recursion
     * @param n
     * @return array of long Oddonacci numbers
     */
    public static double[] linearOddonacci(int n) {
        double[] A = new double[3]; // Array to store the Oddonacci numbers
        double i = 0, j = 0, k = 0;
        if (n == 0)
            return null;
        else if (n <= 3) { // Base case
            i = 1;
            j = 1;
            k = 1;
            A[0] = i;
            A[1] = j;
            A[2] = k;
            return A;
        } else { // Recursive case
            A = linearOddonacci(n - 1); // Recursive call to get the previous Oddonacci numbers
            i = A[0]; // Get the previous Oddonacci numbers
            j = A[1]; // Get the previous Oddonacci numbers
            k = A[2]; // Get the previous Oddonacci numbers
            A[0] = i + j + k; // Calculate the Oddonacci number
            A[1] = i; // Update the Oddonacci numbers
            A[2] = j; // Update the Oddonacci numbers
            return A;
        }
    }

    // Multiple Recursion

    /**
     * Calculate the nth Oddonacci number using multiple recursion
     * @param n
     * @return Oddonacci number as a long
     */
    public static long multipleOddonacci(int n) {
        if (n == 0)
            return 0;
        else if (n <= 3) // Base case
            return 1;
        else {
            return multipleOddonacci(n - 1) + multipleOddonacci(n - 2) + multipleOddonacci(n - 3);
        }
    }

    // Tail Recursion (Bonus)

    /**
     * Wrapper function to call the helper function
     * @param n
     * @return Oddonacci number as a long
     */
    public static long tailOddonacci(int n) { // Wrapper function to call the helper function
        return tOddonacci(n, 1, 1, 1);
    }

    /**
     * Helper function to calculate the Oddonacci number
     * @param n
     * @param a first Oddonacci number
     * @param b second Oddonacci number
     * @param c third Oddonacci number
     * @return Oddonacci number as a long
     */
    public static long tOddonacci(int n, long a, long b, long c) { //Helper function to calculate the Oddonacci number
        if (n == 0) {
            return 0;
        } else if (n <= 3) { // Base case
            return a;
        } else {
            return tOddonacci(n - 1, a + b + c, a, b);
        }
    }
}
