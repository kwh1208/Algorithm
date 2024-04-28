
import java.io.*;
import java.util.*;
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 크기가 N * N 인 2차원 정수 배열 X가 있다.
        // X를 45도의 배수만큼 시계방향 혹은 반시계 방향으로 돌리려고 한다.
        // X를 시계 방향으로 45도 돌리면 아래와 같은 연산이 동시에 X에 적용되어야

        // X의 주 대각선을 (1,1), (2,2), ... , (n, n)
        // X의 가운데 열을 X의 부대각선으로 옮긴다

        int T = Integer.parseInt(br.readLine());

        for (int k = 0; k < T; k++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            int[][] arr = new int[n][n];

            // 3(0,0)  2(0,1)  5(0,2)  4(0,3)  15(0,4) 1(0,5) 10(0,6)
            // 6  8  9  14 10 1 10
            // 1(2,0)  7  13 19 25 1 10(2,6)
            // 16 12 17 18 20 1 10
            // 11(4,0) 22 21 24 23 1 10(4,6)
            // 1  1  1  1  1  1 10
            // 1(6,0)  1  1(6,2)  1  1(6,4)  1 10(6,6)

            // 배열 만들기
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());

                for (int j = 0; j < n; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 정중앙 -> 안 움직임
            // 2번째 원 -> 한 칸씩 이동 (좌,우 or 위, 아래)
            // 3번째 원 -> 두 칸씩 이동 (좌,우 or 위, 아래)
            // 4번째 원 -> 세 칸씩 이동 (좌,우 or 위,아래)


            // (0,0) , (0,3) , (0,6) 을 옮길것
            // tempArr[0][3] = arr[0][0]
            // tempArr[0][6] = arr[0][3]
            // tempArr[3][6] = arr[0][6]
            // tempArr[6][6] = arr[3][6]
            // tempArr[6][3] = arr[6][6]
            // tempArr[6][0] = arr[6][3]


            int t = d / 45;
            t = t % 8;

            while (t != 0) {
                int[][] tempArr = new int[n][n];

                for (int i = 0; i < n; i++) {
                    for (int j = 0; j< n; j++) {
                        tempArr[i][j] = arr[i][j];
                    }
                }

                int start = 0;
                int end = n - 1;

                // t가 양수이면 오른쪽으로 회전
                if (t > 0) {

                    while (start < end) {
                        int len = (end - start) / 2; // 5*5 -> (4-0)/2 첫번째 줄은 2칸씩 (3-1)/2 두번째 줄은 1칸씩

                        // 위에줄 오른쪽으로
                        // 0,0 -> 0,2  0,2 -> 0,4
                        tempArr[start][start+len] = arr[start][start];
                        tempArr[start][end] = arr[start][start+len];

                        // 오른쪽줄은 아래로
                        // 2,4 -> 4,4  / 0,4 -> 2,4
                        tempArr[start+len][end] = arr[start][end];
                        tempArr[end][end] = arr[start+len][end];

                        // 아래줄은 왼쪽으로
                        // 4,4 -> 4,2 / 4,2 -> 4,0
                        tempArr[end][start] = arr[end][start+len];
                        tempArr[end][start+len] = arr[end][end];

                        // 왼쪽줄은 위로
                        // 4,0 -> 2,0 / 2,0 -> 0,0
                        tempArr[start+len][start] = arr[end][start];
                        tempArr[start][start] = arr[start+len][start];

                        end--;
                        start++;
                    }

                    t--;
                } else { // t가 음수이면 왼쪽으로 회전
                    while (start < end) {
                        int len = (end - start) / 2; // 5*5 -> (4-0)/2 첫번째 줄은 2칸씩 (3-1)/2 두번째 줄은 1칸씩

                        // 위에줄 왼쪽으로
                        tempArr[start][start] = arr[start][start+len];
                        tempArr[start][start+len] = arr[start][end];

                        // 오른쪽줄은 위로
                        tempArr[start][end] = arr[start+len][end]; // 4,4 <- 2,4 /  2,4 <- 0,4
                        tempArr[start+len][end] = arr[end][end];

                        // 아래줄은 오른쪽으로
                        tempArr[end][end] = arr[end][start+len]; // 4,2 ->
                        tempArr[end][start+len] = arr[end][start];

                        // 왼쪽줄은 아래로
                        tempArr[start+len][start] = arr[start][start];
                        tempArr[end][start] = arr[start+len][start];

                        end--;
                        start++;
                    }

                    t++;
                }

                arr = tempArr;
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    bw.write(arr[i][j]+" ");
                }
                bw.write("\n");
            }
        }


        bw.flush();
        br.close();
        bw.close();
    }

}