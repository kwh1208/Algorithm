import java.io.*;

class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        long dp[] = new long[N+1];
        dp[1] = 1;

        if(N>1){
            dp[2] = 2;
        }

        for(int i=3;i<=N;i++){
            dp[i] = (dp[i-2]+dp[i-1]) % 15746;
        }

        System.out.print(dp[N]);
    }
}
