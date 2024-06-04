import java.io.*;
import java.util.*;

class Main{
    public static void main(String [] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        while(T-->0){

            int N = Integer.parseInt(br.readLine());
            int dp[][] = new int[2+1][N+1];
            int sticker[][] = new int[2+1][N+1];
            for(int i=1;i<=2;i++){
                StringTokenizer st = new StringTokenizer(br.readLine());

                for(int j=1;j<=N;j++){
                    sticker[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dp[1][1] = sticker[1][1];
            dp[2][1] = sticker[2][1];

            for(int j=2;j<=N;j++){
                dp[1][j] = Math.max(dp[2][j-1], dp[2][j-2])+sticker[1][j];
                dp[2][j] = Math.max(dp[1][j-1], dp[1][j-2])+sticker[2][j];
            }

            int result = Math.max(dp[1][N], dp[2][N]);

            System.out.println(result);
        }
    }
}
