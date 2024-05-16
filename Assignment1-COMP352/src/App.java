import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;

public class App {
    public static void main(String[] args) {

        PrintWriter writer = null;

        try {
            writer = new PrintWriter(new FileOutputStream("OddoOut.txt"), true);
            for (int i = 1; i <= 40; i++) {
                int index = i * 5;
                long startTime = System.nanoTime();
                long[] resultLinear = linearOddonacci(index);
                long endTime = System.nanoTime();
                double timeElapsedLinear = (endTime - startTime) / 1000000.0; // Time in milliseconds

                startTime = System.nanoTime();
                long result = multipleOddonacci(index);
                endTime = System.nanoTime();
                double timeElapsedMultiple = (endTime - startTime) / 1000000.0; // Time in milliseconds

                writer.println(
                        "linearOddonacci(" + index + ") = " + resultLinear[0] + ", Time: " + timeElapsedLinear + " ms");
                writer.println(
                        "multipleOddonacci(" + index + ") = " + result + ", Time: " + timeElapsedMultiple + " ms");
                writer.println();

            }
            writer.close();
        } catch (FileNotFoundException e) {
            e.getMessage();
        }

    }

    // Linear Recursion
    public static long[] linearOddonacci(int n) {
        long[] A = new long[3]; // Array to store the Oddonacci numbers
        long i = 0, j = 0, k = 0;

        if (n <= 3) { // Base case
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
    public static long multipleOddonacci(int n) {
        if (n <= 3) // Base case
            return 1;
        else {
            return multipleOddonacci(n - 1) + multipleOddonacci(n - 2) + multipleOddonacci(n - 3);
        }
    }

    // Tail Recursion
    public static long tailOddonacci(int n) { // Wrapper function to call the helper function
        return tOddonacci(n, 1, 1, 1);
    }

    public static long tOddonacci(int n, long a, long b, long c) { //Helper function to calculate the Oddonacci number
        if (n <= 3) { // Base case
            return a;
        } else {
            return tOddonacci(n - 1, a + b + c, a, b);
        }
    }
}
