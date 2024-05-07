import java.util.*;
class Solution {

    static int[][] arr;
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;

        // n개의 송전탑이 전선을 통해 하나의 트리 형태로 연결
        // 당신은 이 전선들 중 하나를 끊어서 현재의 전력망 네트워크를 2개로 분할하려고 한다.
        // 이때 두 전력망이 갖게 되는 송전탑의 개수를 최대한 비슷하게 맞추고자 한다.

        // 송전탑의 개수 n
        // 전선 정보 wires가 매개변수
        // 전선들 중 하나를 끊어서 송전탑 개수가 가능한 비슷하도록 두 전력망으로 나누었을 때
        // 두 전력망이 가지고 있는 송전탑 개수의 차이?

        arr = new int[n+1][n+1];


        // 배열 만들기
        for(int i = 0; i < wires.length; i++) {
            arr[wires[i][0]][wires[i][1]] = 1;
            arr[wires[i][1]][wires[i][0]] = 1;
        }

        int count1 = Integer.MAX_VALUE;
        int count2 = Integer.MAX_VALUE;

        // 전제 : 모든 노드들은 현재 엮여있다.
        // 1. 선을 끊으면 구역이 2군데로 나눠진다.
        // 2. 각 구역에 있는 노드 개수들을 구하자
        // 3. 노드 개수들의 차이의 최소값을 계속 저장하자
        // 4. 선을 다시 붙인뒤에 다른 선을 끊어서 다시 2~3 을 반복

        for(int i = 1; i < arr.length; i++) {
            for(int j = 1; j < arr.length; j++) {
                if(arr[i][j] == 1) {
                    // 선 끊기
                    arr[i][j] = 0;
                    arr[j][i] = 0;

                    count1 = bfs(i);
                    count2 = bfs(j);

                    answer = Math.min(Math.abs(count2 - count1), answer);

                    // 선 다시 붙이기
                    arr[i][j] = 1;
                    arr[j][i] = 1;
                }
            }
        }

        return answer;
    }

    static int bfs(int x) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visit = new boolean[arr.length];

        queue.add(x);
        visit[x] = true;

        int count = 1;

        while(!queue.isEmpty()) {

            int element = queue.poll();

            for(int i = 1; i < arr.length; i++) {
                if(arr[element][i] == 1 && !visit[i]) {
                    queue.offer(i);
                    visit[i] = true;
                    count++;
                }

            }
        }

        return count;
    }
}
