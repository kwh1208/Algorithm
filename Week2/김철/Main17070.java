import java.io.*;
import java.util.*;

class Main17070 {
    static int N;
    static int[][] house;
    static int answer = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token;

        N = Integer.parseInt(br.readLine());

        house = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            token = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                house[i][j] = Integer.parseInt(token.nextToken());
            }
        }

        // 끝을 기준으로 시작
        DFS(1, 2, "right");
        bw.write(answer + "");
        bw.close();
    }

    static void DFS(int r, int c, String direction) {
        if (r == N && c == N) {
            answer++;
            return;
        }
        // 1. 방향 오른쪽일 때
        //    1-1. 오른쪽 밀기
        if (direction.equals("right")) {
            if (c + 1 <= N && house[r][c + 1] == 0) {
                DFS(r, c + 1, "right");
            }
        }
        // 2. 방향 대각선일 때
        //    2-1. 오른쪽 꺾기
        //    2-2. 아래쪽 밀기
        else if (direction.equals("diagonal")) {
            if (c + 1 <= N && house[r][c + 1] == 0) {
                DFS(r, c + 1, "right");
            }
            if (r + 1 <= N && house[r + 1][c] == 0) {
                DFS(r + 1, c, "under");
            }
        }
        // 3. 방향 아래일 때
        //    3-1. 아래쪽 밀기
        else if (direction.equals("under")) {
            if (r + 1 <= N && house[r + 1][c] == 0) {
                DFS(r + 1, c, "under");
            }
        }

        // 방향 무관하게 공통 가능성 : 대각선 이동
        if (r + 1 <= N && c + 1 <= N && house[r + 1][c] == 0 && house[r][c + 1] == 0 && house[r + 1][c + 1] == 0) {
            DFS(r + 1, c + 1, "diagonal");
        }
    }
}