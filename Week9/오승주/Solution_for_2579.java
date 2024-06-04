import java.io.*;
import java.util.*;

public class Solution_for_2579 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] score = new int[N+1];
        
        for (int i = 1; i < N+1; i++) {
            score[i] = Integer.parseInt(br.readLine());
        }

        if (N == 1) {
            System.out.println(score[1]);
            return;
        }
        if (N == 2) {
            System.out.println(score[1] + score[2]);
            return;
        }

        int[][] dp = new int[N+1][3];

        dp[1][1] = score[1];
        dp[2][1] = score[1] + score[2];
        dp[2][2] = score[2];

        for (int i = 3; i < N+1; i++) {
            for (int j = 1; j < 3; j++) {
                dp[i][1] = dp[i-1][2] + score[i];
                dp[i][2] = Math.max(dp[i-2][1], dp[i-2][2]) + score[i];
            }
        }
        System.out.println(Math.max(dp[N][1], dp[N][2]));
    }
}