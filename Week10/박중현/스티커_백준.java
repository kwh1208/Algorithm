import java.io.*;
import java.util.*;
public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 문방구 스티커 2n개 구매
        // 스티커는 그램 (a)와 같이 2행 n열로 배치

        // 모든 스티커를 붙일 수 없게된 상냥이는 각 스티커에 점수를 매기고 점수의 합이 최대가 되게


        int T = Integer.parseInt(br.readLine());

        for (int a = 0; a < T; a++) {
            int n = Integer.parseInt(br.readLine());

            int[][] arr = new int[2][n];
            int[][] dp = new int[2][n];

            // 다음 두 줄에는 n개의 정수가 주어지며, 각 정수는 그 위치에 해당하는 스티커의 점수
            for (int i = 0; i < 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                for (int j = 0; j < n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            // 50 10 100 20 40
            // 30 50 70 10 60

            // 50 0 100 20 40
            // 0  50 70 10 60

            // 50 0 100 20 40      // 50 0 0   20 40
            // 0 50 0   10 60      // 0 0  70  0 60

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < 2; j++) {
                    if (i == 0) {
                        dp[j][i] = arr[j][i];
                    } else if (i == 1){
                        // 첫번째 행일 경우
                        if (j == 0) {
                            dp[j][i] = dp[j+1][i-1] + arr[j][i];
                        } else { // 두번째 행일 경우
                            dp[j][i] = dp[j-1][i-1] + arr[j][i];
                        }
                    } else {
                        // 첫번째 행일 경우
                        if (j == 0) {
                            dp[j][i] = Math.max(dp[j+1][i-1] + arr[j][i], dp[j+1][i-2] + arr[j][i]);
                        } else { // 두번째 행일 경우
                            dp[j][i] = Math.max(dp[j-1][i-1] + arr[j][i], dp[j-1][i-2] + arr[j][i]);
                        }
                    }
                }
            }

//            for (int i = 0; i < 2; i++) {
//                for (int j = 0; j < n; j++) {
//                    System.out.print(dp[i][j] + " ");
//                }
//                System.out.println();
//            }

            bw.write(Math.max(dp[0][n-1], dp[1][n-1]) + "\n");
        }


        bw.flush();
        bw.close();
    }
}