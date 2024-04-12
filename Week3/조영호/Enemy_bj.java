import java.io.*;
import java.util.*;

public class Enemy_bj {

    static List<List<Integer>> graph;
    static int[] visited;
    static int flag = 1;

    public static void dfs(int a, int value) {

        if (flag == 0) {
            return;
            // 처음 flag = 1
        } else {
            // 입력으로 적대관계(a,b)가 주어짐
            visited[a] = value;
            for (int tem : graph.get(a)) {
                // 방문하지 않았다면
                if (visited[tem] == 0) {
                    visited[tem] = value * -1;
                    // 방문했다면
                } else if (visited[tem] == value) {
                    flag = 0;
                    return;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            graph.get(A).add(B);
            bw.write(String.valueOf(graph));
            graph.get(B).add(A);
            bw.write(String.valueOf(graph));
        }
        visited = new int[N + 1];

        for (int i = 1; i < N + 1; i++) {
            if (visited[i] == 0) {
                dfs(i, 1);
            }
        }
        bw.write(String.valueOf(flag));

        bw.flush();
        bw.close();
        br.close();
    }

}
