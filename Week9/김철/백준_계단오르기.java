package Main;

import java.io.*;

public class Main2579 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N+1];

        // dp[i] = i번째 계단까지 오를 때 얻을 수 있는 점수 최대값
        int[] dp = new int[N+1];

        for(int i=1; i<=N; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        dp[1] = arr[1];
        // 인덱스 예외 처리
        if(N >= 2) dp[2] = arr[2] + arr[1];

        for(int i=3; i<=N; i++){
            // i번째 까지 1칸 올랐을 경우, 2칸 올랐을 경우 중 최댓값 비교
            // 단, 1칸 올랐을 경우의 이전에는 반드시 2칸으로 올랐어야 함.
            // 연속 3개 밟기 금지 라는 조건을 만족시키기 위해

            // 1칸으로 도착할 경우 (dp[i-1] + arr[i])
            // 2칸으로 도착할 경우 (dp[i-2] + arr[i])

            // dp[i-1] -> dp[i-3] + arr[i-1] 로 변환하여
            dp[i] = Math.max(dp[i-2], dp[i-3] + arr[i-1]) + arr[i];
        }

        bw.write(dp[N] + "");
        bw.close();
    }
}
//https://st-lab.tistory.com/132