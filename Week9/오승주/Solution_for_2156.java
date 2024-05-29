import java.io.*;
import java.util.*;

public class Solution_for_2156 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] amount = new int[N+1];

        for (int i = 1; i < N+1; i++) {
            amount[i] = Integer.parseInt(br.readLine());
        }

        int[] dp = new int[10001];
        if (N == 1) {
            System.out.println(amount[1]);
            return;
        }

        dp[1] = amount[1];
        dp[2] = dp[1] + amount[2];

        for (int i = 3; i < N+1; i++) {
            dp[i] = Math.max(dp[i-3]+amount[i-1]+amount[i],(Math.max(dp[i-2]+amount[i],(Math.max(dp[i-3]+amount[i],dp[i-1])))));
        }

        System.out.println(dp[N]);
    }
}