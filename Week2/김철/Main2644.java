import java.util.*;
import java.io.*;

class Main2644 {
    static int n, m;
    static int[][] map;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token;

        n = Integer.parseInt(br.readLine());

        // map = 촌수 관계에 1을 부여
        map = new int[n + 1][n + 1];

        // targetA 시점에서 모든 사람과의 촌수 계산
        dist = new int[n + 1];

        token = new StringTokenizer(br.readLine());

        int targetA = Integer.parseInt(token.nextToken());
        int targetB = Integer.parseInt(token.nextToken());

        m = Integer.parseInt(br.readLine());

        for (int i = 0; i < m; i++) {
            token = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(token.nextToken());
            int y = Integer.parseInt(token.nextToken());
            map[x][y] = map[y][x] = 1;
        }

        bw.write(BFS(targetA, targetB) + "");
        bw.close();
    }

    static int BFS(int A, int B) {
        // A에서 촌수 관계로 연결되는 사람이 담긴 큐
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(A);

        while (!q.isEmpty()) {
            // 현재 비교할 사람
            int currPerson = q.poll();

            // 현재 비교할 사람이 targetB 라면 답이 나온 것.
            if (currPerson == B) return dist[currPerson];

            // 현재 사람과 다음 사람을 비교
            for (int i = 1; i <= n; i++) {
                // 촌수 관계 존재하는데도 && 촌수 계산 안됐을 경우
                if (map[currPerson][i] == 1 && dist[i] == 0) {
                    dist[i] = dist[currPerson] + 1;  // 촌수 + 1
                    q.offer(i);                       // 다음 비교할 사람들 큐에 입력
                }
            }
        }

        // 모든 관계를 비교해보아도 A와 B의 친척 관계를 찾을 수 없음.
        return -1;
    }
}
