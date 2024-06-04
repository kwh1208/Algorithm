package Main;

import java.io.*;
import java.util.*;

public class Main9465 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token;

        int T = Integer.parseInt(br.readLine());
        int[][] sticker;
        int[][] dp;
        int n;

        for(int i=0; i<T; i++){
            n = Integer.parseInt(br.readLine());

            sticker = new int[2][n+1];
            dp = new int[2][n+1];

            token = new StringTokenizer(br.readLine());
            for(int j=1; j<=n; j++){
                sticker[0][j] = Integer.parseInt(token.nextToken());
            }
            token = new StringTokenizer(br.readLine());
            for(int j=1; j<=n; j++){
                sticker[1][j] = Integer.parseInt(token.nextToken());
            }

            Arrays.toString(sticker);
            dp[0][1] = sticker[0][1];
            dp[1][1] = sticker[1][1];

            for(int j=2; j<=n; j++){
                dp[0][j] = Math.max(dp[1][j-1], dp[1][j-2]) + sticker[0][j];
                dp[1][j] = Math.max(dp[0][j-1], dp[0][j-2]) + sticker[1][j];
            }

            if (dp[0][n] > dp[1][n]) {
                bw.write(dp[0][n] + "\n");
            } else {
                bw.write(dp[1][n] + "\n");
            }
        }
        bw.close();
    }
}
//https://velog.io/@yanghl98/%EB%B0%B1%EC%A4%80-9465-%EC%8A%A4%ED%8B%B0%EC%BB%A4-JAVA