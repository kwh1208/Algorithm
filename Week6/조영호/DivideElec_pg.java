package Week6.조영호;
/* 문제 설명 */

// n개의 송전탑이 전선을 통해 하나의 트리 형태로 연결되어 있습니다. 
// 당신은 이 전선들 중 하나를 끊어서 현재의 전력망 네트워크를 2개로 분할하려고 합니다. 
// 이때, 두 전력망이 갖게 되는 송전탑의 개수를 최대한 비슷하게 맞추고자 합니다.
// 송전탑의 개수 n, 그리고 전선 정보 wires가 매개변수로 주어집니다. 
// 전선들 중 하나를 끊어서 송전탑 개수가 가능한 비슷하도록 두 전력망으로 나누었을 때, 
// 두 전력망이 가지고 있는 송전탑 개수의 차이(절대값)를 return 하도록 solution 함수를 완성해주세요.
import java.util.*;

public class DivideElec_pg {
    // 전력망을 나타낸 ArrayList 배열
    static List<Integer>[] arrayLists;
    // 두 전력망이 갖고 있는 송전탑 개수의 차이 최소값 (정답)
    static int min;

    public static int bfs(int start, int end, int n) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];

        // 연결된 송전탑 개수
        int LinkedNodeCount = 0;
        // 처음 하나 넣고 시작
        queue.add(start);
        // 방문체크
        visited[start] = true;

        // bfs 알고리즘
        while (!queue.isEmpty()) {
            int current = queue.poll();
            LinkedNodeCount++;

            for (int next : arrayLists[current]) {
                // start가 포함된 전력망과 end가 포함된 전력망은 연결되어 있지 않으므로
                // 다음 node가 end면 안됨
                if (next != end && !visited[next]) {
                    queue.add(next);
                    visited[next] = true;
                }
            }
        }
        return LinkedNodeCount;
    }

    public int solution(int n, int[][] wires) {
        min = Integer.MAX_VALUE;
        arrayLists = new ArrayList[n + 1];

        for (int i = 0; i < n; i++) {
            arrayLists[i] = new ArrayList<>();
        }
        for (int[] wire : wires) {
            arrayLists[wire[0]].add(wire[1]);
            arrayLists[wire[1]].add(wire[0]);
        }
        for (int[] wire : wires) {
            int n1 = bfs(wire[0], wire[1], n);
            int n2 = bfs(wire[1], wire[0], n);

            // bfs로 연결되어 있는 송전탑 개수의 차이 구하고 min 갱신하기
            min = Math.min(min, Math.abs(n1 - n2));
        }
        return min;
    }
}
