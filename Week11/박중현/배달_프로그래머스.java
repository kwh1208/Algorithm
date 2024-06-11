import java.util.*;

class Solution {

    static ArrayList<Node>[] graph;

    public int dijkstra(int n, int targetCost) {
        int sum = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] check = new boolean[n+1];
        int[] dist = new int[n+1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;

        pq.offer(new Node(1, 0));

        while(!pq.isEmpty()) {
            int nowVertex = pq.poll().index;

            if(check[nowVertex]) {
                continue;
            }

            check[nowVertex] = true;

            // graph[i]에 있는 인접한 노드들 모두 가져오기
            for(Node next : graph[nowVertex]) {
                if(dist[next.index] > dist[nowVertex] + next.cost) {
                    dist[next.index] = dist[nowVertex] + next.cost;

                    pq.offer(new Node(next.index, dist[next.index]));
                }
            }
        }

        for(int i = 1; i <= n; i++) {
            if(dist[i] <= targetCost) {
                sum++;
            }
        }

        return sum;
    }

    public int solution(int N, int[][] road, int K) {
        int answer = 0;

        graph = new ArrayList[N + 1];

        for(int i = 0; i <= N; i++) {
            graph[i] = new ArrayList<Node>();
        }

        for(int i = 0; i < road.length; i++) {
            int a = road[i][0];
            int b = road[i][1];
            int c = road[i][2];

            graph[a].add(new Node(b,c));
            graph[b].add(new Node(a,c));
        }

        answer = dijkstra(N,K);

        return answer;
    }
}

class Node implements Comparable<Node>{
    int index;
    int cost;

    public Node(int index, int cost) {
        this.index = index;
        this.cost = cost;
    }

    // Node.cost를 기준으로 정렬
    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.cost, o.cost);
    }
}