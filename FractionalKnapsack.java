import java.util.Scanner;

public class FractionalKnapsack {

    public static double knapsack(int[] weights, int[] profits, int n, int capacity) {

        // [Product Number, Weight, Profit, Profit/Weight Ratio]
        double[][] items = new double[n][4];

        for (int i = 0; i < n; i++) {
            items[i][0] = i + 1;  // Product number
            items[i][1] = weights[i];
            items[i][2] = profits[i];
            items[i][3] = (double) profits[i] / weights[i];
        }

        // Sort in descending order of profit/weight ratio
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {

                if (items[j][3] < items[j + 1][3]) {

                    double[] temp = items[j];
                    items[j] = items[j + 1];
                    items[j + 1] = temp;
                }
            }
        }

        double totalProfit = 0;
        int remainingCapacity = capacity;

        System.out.println("\nSelected Items:");

        for (int i = 0; i < n; i++) {

            if (remainingCapacity == 0) {
                break;
            }

            if (items[i][1] <= remainingCapacity) {

                // Take complete item
                remainingCapacity -= (int) items[i][1];
                totalProfit += items[i][2];

                System.out.println(
                        "Product " + (int) items[i][0] +
                                " | Weight: " + (int) items[i][1] +
                                " | Profit: " + (int) items[i][2] +
                                " | Fraction: 1.0"
                );

            } else {

                // Take fraction of item
                double fraction = remainingCapacity / items[i][1];

                totalProfit += items[i][2] * fraction;

                System.out.println(
                        "Product " + (int) items[i][0] +
                                " | Weight: " + (int) items[i][1] +
                                " | Profit: " + (int) items[i][2] +
                                " | Fraction: " + fraction
                );

                remainingCapacity = 0;
            }
        }

        return totalProfit;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of products: ");
        int n = sc.nextInt();

        int[] weights = new int[n];
        int[] profits = new int[n];

        System.out.println("\nEnter weight and profit of each product:");

        for (int i = 0; i < n; i++) {

            System.out.print("Product " + (i + 1) + " weight: ");
            weights[i] = sc.nextInt();

            System.out.print("Product " + (i + 1) + " profit: ");
            profits[i] = sc.nextInt();
        }


        System.out.print("\nEnter capacity: ");
        int capacity = sc.nextInt();

        long startTime = System.nanoTime();

        double maximumProfit = knapsack(weights, profits, n, capacity);

        long endTime = System.nanoTime();

        long executionTime = endTime - startTime;

        System.out.println("\nMaximum Profit: " + maximumProfit);
        System.out.println("Execution Time: " + executionTime + " nanoseconds");

        sc.close();
    }
}