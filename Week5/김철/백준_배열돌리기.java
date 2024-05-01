package Main;

import java.util.*;
import java.io.*;

class Main17276 {
    private static int T, n, d;
    private static int[][] X;

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token;

        T = Integer.parseInt(br.readLine());
        for(int i=0; i<T; i++){
            token = new StringTokenizer(br.readLine());
            n = Integer.parseInt(token.nextToken());
            d = Integer.parseInt(token.nextToken());
            X = new int[n][n]; // n = 7

            for(int j=0; j<n; j++){
                token = new StringTokenizer(br.readLine());
                for(int k=0; k<n; k++){
                    X[j][k] = Integer.parseInt(token.nextToken());
                }
            }

            // 45도 단위로 360도 까지 배열 회전의 경우의 수는 8 이다.
            // 각도가 음수여도 정방향으로 반복한다.
            // ex) d / 45 = -1 라면 8 + (-1) = 7번 반복하기
            int rotateCount; // 반복할 횟수 지정
            if(d / 45 >= 0) rotateCount = d / 45;
            else rotateCount = 8 + d / 45;

            for(int j=0; j<rotateCount; j++) rotate(X);

            for(int j=0; j<n; j++) {
                for(int k = 0; k < n; k++){
                    bw.write(X[j][k] + " ");
                }
                bw.write("\n");
            }
        }

        bw.close();
    }

    private static void rotate(int[][] X){

        // 덮어쓰기를 방지하기 위해 처음 것을 큐에 넣기
        Deque<Integer> q = new ArrayDeque<>();
        for(int j=0; j<n; j++){
            q.offer(X[j][j]);
        }

        for(int j=0; j<n; j++){
            X[j][j] = X[(n-1)/2][j]; // 주 대각선 <- 가운데 행
            X[(n-1)/2][j] = X[(n-1)-j][j]; // 가운데 행 <- 부 대각선
            X[(n-1) - j][j] = X[(n-1)-j][(n-1)/2]; // 부 대각선 <- 가운데 열
        }

        for(int j=0; j<n; j++){
            X[(n-1) - j][(n-1)/2] = q.pollLast(); // 가운데 열 <- 주 대각선
        }

    }
}