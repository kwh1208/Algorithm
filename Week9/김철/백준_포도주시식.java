package Main;

import java.io.*;

public class Main2156 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());

        int[] wine = new int[n+1];
        int[] dp = new int[n+1];

        for(int i=1; i<=n; i++){
            wine[i] = Integer.parseInt(br.readLine());
        }

        dp[1] = wine[1];
        if(n >= 2) {
            dp[2] = wine[1] + wine[2];
        }

        for(int i=3; i<=n; i++){
            // 현재 와인을 마시지 않는 경우
            // 현재 와인을 마시는 경우로 나누어서 비교
            dp[i] = Math.max(dp[i-1], Math.max(dp[i-2], dp[i-3] + wine[i-1]) + wine[i]);
        }

        bw.write(dp[n] + "");
        bw.close();
    }
}
//https://st-lab.tistory.com/135