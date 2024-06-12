package programmers;

import java.util.*;

public class Solution배달 {
    public static void main(String[] args){
        int[][] road = new int[][]{
                {1,2,1},
                {2,3,3},
                {5,2,2},
                {1,4,2},
                {5,3,1},
                {5,4,2}};
//        int[][] road = new int[][]{
//                {1,2,1},
//                {1,3,2},
//                {2,3,2},
//                {3,4,3},
//                {3,5,2},
//                {3,5,3},
//                {5,6,1}
//        };
        int N = 5;
        int K = 3;
        System.out.println(solution(N, road, K));
    }

    static void BFS(int n, int k, int[] visited){

        Queue<Node> q = new ArrayDeque<>();

        // visited[i] = 1번 마을에서 i번째 마을까지 가는 데에 필요한 최소 시간
        // 그래서 더 적게 걸리는 시간을 찾으면 갱신.
        // 방문 안 한 것 = 최댓값으로 초기화
        for(int i=2; i<=n; i++){
            visited[i] = Integer.MAX_VALUE;
        }

        // 1번에서 갈 수 있는 마을 정보를 Node 형식으로 모두 추가
        q.addAll(townList[1]);

        // q에 있는 것은 모두 1번 마을에서 갈 수 있는 것.
        while(!q.isEmpty()){
            Node node = q.poll();
            // visited[node.end] = 1번 마을 ~ 다음 마을 걸리는 시간
            //
            // visited[node.start] = 1번 마을 ~ 현재 마을 걸리는 시간
            // node.val = 현재 마을 ~ 다음 마을 걸리는 시간
            if(visited[node.end] > visited[node.start] + node.val) {
                visited[node.end] = visited[node.start] + node.val;
                // 다음 방문 예정 마을에서 갈 수 있는 모든 마을 추가
                q.addAll(townList[node.end]);
            }
        }

        // 1번 마을에서 i번째 마을까지 k시간 이하로 걸리는 것을 판단
        for (int i = 2; i < n + 1; i++) {
            if(visited[i] <= k) {
                answer++;
            }
        }
    }
    static class Node{
        int start, end, val;
        Node(int start, int end, int val){
            this.start = start;
            this.end = end;
            this.val = val;
        }
    }
    static List<Node>[] townList;
    static int answer = 1;

    private static int solution(int N, int[][] road, int K) {

        // townList[i] = i번째 마을에 있는 도로들
        townList = new ArrayList[N+1];

        int[] visited = new int[N+1];

        for(int i=1; i<=N; i++){
            townList[i] = new ArrayList<>();
        }

        for(int i=0; i<road.length; i++){
            int a = road[i][0];
            int b = road[i][1];
            int c = road[i][2];

            townList[a].add(new Node(a, b, c));
            townList[b].add(new Node(b, a, c));
        }

        // 1번 노드에서 다음 도로까지 걸리는 시간이 K값 이하면 바로 채용
        // K - 다음 도로까지 걸리는 시간 계산하며 계속 반복.

        BFS(N, K, visited);

        return answer;
    }
//    https://rovictory.tistory.com/122


    // 혼자 풀이한 것 87.5
//    static void searchTown(int start, int tempK){
//        for(Node node : townList[start]){
//            if(node.end != 1 && node.val <= tempK){
//                set.add(node.end);
//
//                if(node.val < tempK)
//                    searchTown(node.end, tempK - node.val);
//            }
//        }
//    }
//    static class Node{
//        int end, val;
//        Node(int end, int val){
//            this.end = end;
//            this.val = val;
//        }
//    }
//    static List<Node>[] townList;
//    static Set<Integer> set;
//
//    private static int solution(int N, int[][] road, int K) {
//        int answer = 0;
//
//        // townList[i] = i번째 마을에 있는 도로들
//        townList = new ArrayList[N+1];
//
//        // 배달 가능한 마을 번호
//        set = new HashSet<>();
//
//        for(int i=1; i<=N; i++){
//            townList[i] = new ArrayList<>();
//        }
//
//        for(int i=0; i<road.length; i++){
//            int a = road[i][0];
//            int b = road[i][1];
//            int c = road[i][2];
//
//            townList[a].add(new Node(b, c));
//            townList[b].add(new Node(a, c));
//        }
//
//        // 1번 노드에서 다음 도로까지 걸리는 시간이 K값 이하면 바로 채용
//        // K - 다음 도로까지 걸리는 시간 계산하며 계속 반복.
//
//        for (Node node : townList[1]) {
//            if (node.val <= K) {
//                set.add(node.end);
//
//                // townList[node.end] 를 탐색하기
//                if (node.val < K) searchTown(node.end, K - node.val);
//            }
//        }
//
//        answer = set.size() + 1;
//        return answer;
//    }
}
