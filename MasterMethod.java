
import java.util.Scanner;

public class MasterMethod {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter value of a: ");
        int a = sc.nextInt();

        System.out.print("Enter value of b: ");
        int b = sc.nextInt();

        System.out.print("Enter value of k in f(n) = n^k: ");
        int k = sc.nextInt();
        long startTime = System.nanoTime();
        double logValue = Math.log(a) / Math.log(b);

        System.out.println("\nRecurrence Relation:");
        System.out.println("T(n) = " + a + "T(n/" + b + ") + n^" + k);

        System.out.println("\nlog_b(a) = " + logValue);

        if (k < logValue) {
            System.out.println("Case 1: ");
            System.out.println("Time Complexity: O(n^" +
                    String.format("%.2f", logValue) + ")");
        }
        else if (Math.abs(k - logValue) < 0.000001) {
            System.out.println("Case 2: ");
            System.out.println("Time Complexity: O(n^" + k +
                    " log n)");
        }
        else {
            System.out.println("Case 3: ");
            System.out.println("Time Complexity: O(n^" + k + ")");
        }
        long endTime = System.nanoTime();
        System.out.println("Execution Time: "+(endTime - startTime)+ " nanoseconds");
        sc.close();
    }
}
// TIME COMPLEXITY:
// Calculating log_b(a): O(1)
// Comparing Master Theorem cases: O(1)
// Overall Time Complexity: O(1)

// SPACE COMPLEXITY:
// Uses only a fixed number of variables
// Overall Space Complexity: O(1)