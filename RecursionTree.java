import java.util.Scanner;

public class RecursionTree {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter n: ");
        int n = sc.nextInt();

        int level = 0;
        int totalWork = 0;

        System.out.println("\nRecursion Tree Analysis:");
        long startTime = System.nanoTime();
        while (n > 0) {

            int work = n;
            totalWork += work;

            System.out.println("Level " + level +
                    ": Problem Size = " + n +
                    ", Work = " + work);

            n = n / 2;
            level++;
        }
        long endTime = System.nanoTime();
        System.out.println("\nTotal Levels: " + level);
        System.out.println("Total Work: " + totalWork);
        System.out.println("Execution Time: "+(endTime-startTime)+ " nanoseconds");
        System.out.println("Time Complexity: O(n)");

        sc.close();
    }
}

// Time Complexity: O(log n)
// The loop divides n by 2 in every iteration.

// Space Complexity: O(1)
// Only a constant amount of extra variables are used.
// No recursion or additional data structures are used.