import java.io.*;
import java.util.StringTokenizer;

/*
<pre>
@Title: 백준: 파이프 옮기기1 17070번
@Link: https://www.acmicpc.net/problem/17070
@Author: hey._.mi
</pre>
*/
public class BOJ17070 {
        public static void main(String[] args) throws IOException {
            int answer = 0;

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            int n = Integer.parseInt(br.readLine()); // 사람 수
            StringTokenizer st = new StringTokenizer(br.readLine());

            int[][] house = new int[n][n];

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                house[i][0] = Integer.parseInt(st.nextToken());
                house[i][1] = Integer.parseInt(st.nextToken());
                house[i][2] = Integer.parseInt(st.nextToken());
            }

            int[][][] map = new int[n][n][3]; // 0 가로 1 세로 2 대각선
            map[0][1][0] = 1;

            for(int i=0; i<n; i++) { // 가로
                for(int j=2; j<n; j++) { // 세로
                    // 가로
                    if(house[i][j] == 0 && j-1 >= 0) {
                        map[i][j][0] = map[i][j-1][0] + map[i][j-1][2];
                    }
                    // 세로
                    if(i-1 >= 0 && house[i][j] == 0) {
                        map[i][j][1] = map[i-1][j][1] + map[i-1][j][2];
                    }
                    // 대각선
                    if(house[i][j] == 0 && i-1 >=0 && j-1 >= 0 && house[i-1][j] == 0 && house[i][j-1] == 0) {
                        map[i][j][2] = map[i-1][j-1][2] + map[i-1][j-1][1] + map[i-1][j-1][0];
                    }
                }
            }

            answer = map[n-1][n-1][0] + map[n-1][n-1][1] + map[n-1][n-1][2];
            System.out.println(answer);

        bw.flush();
        br.close();
        bw.close();
        }

    }