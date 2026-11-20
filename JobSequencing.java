import java.util.*;

public class JobSequencing {

    static class Job {
        String id;
        int deadline;
        int profit;

        Job(String id, int deadline, int profit) {
            this.id = id;
            this.deadline = deadline;
            this.profit = profit;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of jobs: ");
        int n = sc.nextInt();

        Job[] jobs = new Job[n];

        System.out.println("\nEnter Job ID, Deadline and Profit:");

        for (int i = 0; i < n; i++) {

            System.out.print("Job " + (i + 1) + ": ");

            String id = sc.next();
            int deadline = sc.nextInt();
            int profit = sc.nextInt();

            jobs[i] = new Job(id, deadline, profit);
        }

        // Start execution time
        long startTime = System.nanoTime();

        // Sort jobs in descending order of profit
        Arrays.sort(jobs, (a, b) -> b.profit - a.profit);

        // Find maximum deadline
        int maxDeadline = 0;

        for (Job job : jobs) {
            maxDeadline = Math.max(maxDeadline, job.deadline);
        }

        // Create slots
        Job[] slots = new Job[maxDeadline];

        int totalProfit = 0;

        // Job Sequencing Greedy Algorithm
        for (Job job : jobs) {

            // Find latest available slot before deadline
            for (int j = job.deadline - 1; j >= 0; j--) {

                if (slots[j] == null) {

                    slots[j] = job;
                    totalProfit += job.profit;

                    break;
                }
            }
        }

        // End execution time
        long endTime = System.nanoTime();

        // Display output
        System.out.println("\nScheduled Jobs:");

        for (int i = 0; i < slots.length; i++) {

            if (slots[i] != null) {

                System.out.println(
                        "Time Slot " + (i + 1)
                                + " -> " + i + " - " + (i + 1)
                                + " -> " + slots[i].id
                                + " | Profit = " + slots[i].profit
                );
            }
        }

        System.out.println("\nMaximum Profit = " + totalProfit);

        System.out.println(
                "\nExecution Time = "
                        + (endTime - startTime)
                        + " nanoseconds"
        );

        sc.close();
    }
}

// inputs
// 1)no of jobs = 5
// 2)Job id = 1, 2, 3, 4, 5 
// 2)Profits : 100, 19, 27, 25, 15
// 3)Deadline: 2, 1, 2, 1, 3

// TIME COMPLEXITY:
// Sorting Jobs: O(n log n)
// Finding Maximum Deadline: O(n)
// Job Sequencing: O(n * d), where d = Maximum Deadline
// Overall Time Complexity: O(n log n + n * d)

// SPACE COMPLEXITY:
// Jobs Array: O(n)
// Slots Array: O(d), where d = Maximum Deadline
// Overall Space Complexity: O(n + d)

// Note: If maximum deadline d is at most n, the overall worst-case complexity becomes:
// - Time: O(n²)
// - Space: O(n)