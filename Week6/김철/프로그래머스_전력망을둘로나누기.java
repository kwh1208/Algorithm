import java.util.ArrayList;
import java.util.Arrays;

class Solution전력망을둘로나누기 {
    public static void main(String[] args){

        int n = 9;
        int[][] wires = {{1,3},{2,3},{3,4},{4,5},{4,6},{4,7},{7,8},{7,9}};;

        System.out.println(solution(n, wires));
    }

    static ArrayList<Integer>[] graph;
    static int min;

    private static int solution(int n, int[][] wires) {
        graph = new ArrayList[n + 1];
        min = Integer.MAX_VALUE;

        // 그래프 ArrayList 초기화. 송전탑 개수만큼 ArrayList 생성
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        // 각 송전탑마다 연결되어 있는 전선들
        for (int i = 0; i < wires.length; i++) {
            int v1 = wires[i][0];
            int v2 = wires[i][1];
            graph[v1].add(v2);
            graph[v2].add(v1);
        }

        System.out.println(Arrays.toString(graph));

        // 모든 전선을 하나씩 끊어가며 완전탐색한다.
        for (int i = 0; i < wires.length; i++) {
            int v1 = wires[i][0];
            int v2 = wires[i][1];

            //
            boolean[] visited = new boolean[n + 1];

            // 해당 전선을 그래프에서 제거
            graph[v1].remove(Integer.valueOf(v2));
            graph[v2].remove(Integer.valueOf(v1));

            // 제거한 상태에서,
            // 1번 송전탑부터 연결되어 있는 모든 전선 탐색하기
            int cnt = dfs(1, visited);

            // 하나의 네트워크를 검사했으니 반대편 트리는 n에서 빼면 됨
            // dfs를 통해 탐색한 네트워크와 다른 네트워크 상 송전탑 개수 차이
            int diff = Math.abs(cnt - (n - cnt));
            min = Math.min(min, diff);

            // 다음 전선 제거하기 위해 그래프에 다시 추가
            graph[v1].add(v2);
            graph[v2].add(v1);
        }

        return min;
    }

    static int dfs(int v, boolean[] visited) {
        // 인접리스트에서 v번째 리스트를 방문하겠다.
        visited[v] = true;
        int cnt = 1; // 송전탑 1개

        // 연결된 모든 전선들을 검사
        for (int next : graph[v]) {
            if (!visited[next]) {
                cnt += dfs(next, visited);
            }
        }

        return cnt;
    }
}