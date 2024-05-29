import java.util.*;
import java.io.*;

class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int[][] dp = new int[N+1][2];
        
        int dp1 = Integer.parseInt(br.readLine());
        dp[1][0] = dp1;
        
        for(int i=2;i<=N;i++){
            //0: i-1 밟x 1: 밟음
            int value = Integer.parseInt(br.readLine());
            dp[i][0] = Math.max(dp[i-2][0], dp[i-2][1]) + value;
            dp[i][1] = dp[i-1][0] + value;
        }
        
        System.out.print(Math.max(dp[N][0], dp[N][1]));
    }
}
