import java.io.*;
import java.util.*;

public class Solution_for_2644 {
    static int N, M;

    static boolean[][] relationship;
    static boolean[] visited;
    static int person1;
    static int person2;
    
    public static void DFS() {
        Queue<int[]> queue = new LinkedList<>();

        queue.add(new int[] {person1, 0});
        visited[person1] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();

            // person2인 경우
            if (curr[0] == person2) {
                System.out.println(curr[1]);
                return;
            }
            
            for (int i = 1; i < N+1; i++) {
                // 연결돤 촌수가 존재하고 방문하지 않은 경우 -> 탐색
                if (relationship[curr[0]][i] && !visited[i]) {
                    queue.add(new int[] {i, curr[1]+1});
                    visited[i] = true;
                }
            }
        }
        System.out.println(-1);
    }

    public static void main(String[] arg) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        
        st = new StringTokenizer(br.readLine());

        person1 = Integer.parseInt(st.nextToken());
        person2 = Integer.parseInt(st.nextToken());

        relationship = new boolean[N+1][N+1];
        visited = new boolean[N+1];

        M = Integer.parseInt(br.readLine());

        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());

            int p1 = Integer.parseInt(st.nextToken());
            int p2 = Integer.parseInt(st.nextToken());

            relationship[p1][p2] = true;
            relationship[p2][p1] = true;
        }
        DFS();
    }
}
