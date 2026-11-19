import java.util.Scanner;

public class LCS {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first string: ");
        String str1 = sc.nextLine();

        System.out.print("Enter second string: ");
        String str2 = sc.nextLine();

        int m = str1.length();
        int n = str2.length();

        // Measure execution time
        long startTime = System.nanoTime();

        // DP table to store LCS lengths
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {

                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(
                            dp[i - 1][j],
                            dp[i][j - 1]
                    );
                }
            }
        }

        // Reconstruct the LCS string
        int i = m;
        int j = n;
        StringBuilder lcs = new StringBuilder();

        while (i > 0 && j > 0) {
            if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                lcs.append(str1.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] >= dp[i][j - 1]) {
                i--;
            } else {
                j--;
            }
        }

        lcs.reverse();

        long endTime = System.nanoTime();
        long executionTime = endTime - startTime;

        System.out.println("\nLongest Common Subsequence: " + lcs);
        System.out.println("Length of LCS: " + dp[m][n]);
        System.out.println("Execution Time: " + executionTime + " nanoseconds");
        System.out.println("Execution Time: " + (executionTime / 1_000_000.0) + " milliseconds");

        sc.close();
    }
}