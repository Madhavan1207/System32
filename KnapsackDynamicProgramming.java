import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KnapsackDynamicProgramming {

    public static void solveKnapsack(int[] weights, int[] values, int capacity) {

        int n = weights.length;

        // Start measuring execution time
        long startTime = System.nanoTime();

        int[][] dp = new int[n + 1][capacity + 1];

        // Step 1: Fill the DP table
        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= capacity; w++) {
                if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(
                            dp[i - 1][w],
                            values[i - 1] + dp[i - 1][w - weights[i - 1]]
                    );
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        // Step 2: Backtrack to find selected items
        List<Integer> selectedItems = new ArrayList<>();
        int currentCapacity = capacity;
        int totalWeight = 0;

        for (int i = n; i > 0 && currentCapacity > 0; i--) {
            if (dp[i][currentCapacity] != dp[i - 1][currentCapacity]) {
                selectedItems.add(i);
                totalWeight += weights[i - 1];
                currentCapacity -= weights[i - 1];
            }
        }

        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;

        // Step 3: Display results
        System.out.println("\n==================================================");
        System.out.println("        0/1 KNAPSACK DYNAMIC PROGRAMMING          ");
        System.out.println("==================================================");
        System.out.println("Backpack Capacity     : " + capacity + " kg");
        System.out.println("Total Items Available : " + n);
        System.out.println("--------------------------------------------------");
        System.out.println("Selected Items (ID)   : " + selectedItems);
        System.out.println("Total Weight Used     : " + totalWeight + " kg");
        System.out.println("Maximum Total Value   : " + dp[n][capacity]);
        System.out.println("Capacity Check        : "
                + (totalWeight <= capacity ? "PASSED" : "FAILED"));
        System.out.println("--------------------------------------------------");
        System.out.println("Execution Time        : " + executionTime + " ns");
        System.out.println("Execution Time        : "
                + (executionTime / 1_000_000.0) + " ms");
        System.out.println("Time Complexity       : O(n * W)");
        System.out.println("Space Complexity      : O(n * W)");
        System.out.println("==================================================");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Take backpack capacity as input
        System.out.print("Enter backpack capacity (kg): ");
        int capacity = sc.nextInt();

        // Take number of items as input
        System.out.print("Enter number of items: ");
        int n = sc.nextInt();

        int[] weights = new int[n];
        int[] values = new int[n];

        // Take weight and value of each item
        for (int i = 0; i < n; i++) {
            System.out.println("\nEnter details for Item " + (i + 1) + ":");

            System.out.print("Weight (kg): ");
            weights[i] = sc.nextInt();

            System.out.print("Value: ");
            values[i] = sc.nextInt();
        }

        // Display entered items
        System.out.println("\nGiven Items:");
        System.out.println("Item ID | Weight (kg) | Value");
        System.out.println("-----------------------------");

        for (int i = 0; i < n; i++) {
            System.out.printf("Item %-2d | %-11d | %-5d%n",
                    (i + 1), weights[i], values[i]);
        }

        solveKnapsack(weights, values, capacity);

        sc.close();
    }
}