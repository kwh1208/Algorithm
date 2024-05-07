package Week6.조영호;
/* 문제 설명 */

// n개의 송전탑이 전선을 통해 하나의 트리 형태로 연결되어 있습니다. 
// 당신은 이 전선들 중 하나를 끊어서 현재의 전력망 네트워크를 2개로 분할하려고 합니다. 
// 이때, 두 전력망이 갖게 되는 송전탑의 개수를 최대한 비슷하게 맞추고자 합니다.
// 송전탑의 개수 n, 그리고 전선 정보 wires가 매개변수로 주어집니다. 
// 전선들 중 하나를 끊어서 송전탑 개수가 가능한 비슷하도록 두 전력망으로 나누었을 때, 
// 두 전력망이 가지고 있는 송전탑 개수의 차이(절대값)를 return 하도록 solution 함수를 완성해주세요.
import java.util.LinkedList;
import java.util.Queue;

public class DivideElec_pg {
     // bfs 메서드
    static int bfs(int left, int n) {
        int cnt = 1;
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(left);

        while (!queue.isEmpty()) {
            int temp = queue.poll();
            visited[temp] = true;
            for (int i = 1; i < n + 1; i++) {
                if (arr[temp][i] == 1 && !visited[i]) {
                    queue.add(i);
                    cnt++;
                }
            }
        }
        // cnt와 n - cnt는 각각 연결된 전력망
        return Math.abs(cnt - (n - cnt));
    }
     static int[][] arr;
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;

        // 인접 행렬 만들기
        arr = new int[n + 1][n + 1];
        for (int i = 0; i < wires.length; i++) {
            arr[wires[i][0]][wires[i][1]] = 1;
            arr[wires[i][1]][wires[i][0]] = 1;
        }

        // 선 하나씩 끊으면서 bfs 탐색
        for (int i = 0; i < wires.length; i++) {
            int left = wires[i][0];
            int right = wires[i][1];

            // 선 끊기
            arr[left][right] = 0;
            arr[right][left] = 0;

            // bfs
            answer = Math.min(answer, bfs(left, n));

            // 끊었던 선 복구
            arr[left][right] = 1;
            arr[right][left] = 1;
        }

        return answer;
    }
}
