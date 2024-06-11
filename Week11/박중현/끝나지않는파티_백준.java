import java.io.*;
import java.util.*;
public class Main {
    static ArrayList<Node>[] graph;

    // 노드의 크기, 출발지
    public static void Dijkstra(int n, int start, int end, int targetCost) {
        boolean[] check = new boolean[n+1];
        int[] dist = new int[n+1];
        int INF = Integer.MAX_VALUE;

        Arrays.fill(dist, INF); // dist 배열에 모두 INF 값을 넣는다.
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0)); // pq에 처음으로 start 값을 넣어준다.

        while (!pq.isEmpty()) {
            int nowVertex = pq.poll().index;

            // 만약 방문했었다면 패스
            if (check[nowVertex]) {
                continue;
            }

            for (Node next : graph[nowVertex]) {

                // 첫 선언된 dist 배열은 모두 무한대값이 들어가 있기 때문에 이 반복문을 무조건 탄다.
                if (dist[next.index] > dist[nowVertex] + next.cost) {
                    dist[next.index] = dist[nowVertex] + next.cost;

                    pq.offer(new Node(next.index, dist[next.index]));
                }
            }
        }

        // 최소 거리 출력
        if (dist[end] <= targetCost) {
            System.out.println("Enjoy other party");
        } else {
            System.out.println("Stay here");
        }

    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 끝나지 않는 파티
        // 파티를 좋아하는 민호는 끝없이 파티가 열리는 놀이동산 "민호월드"를 세웠다.
        // N개의 파티장을 가진 큰 놀이동산
        // 민호는 파티장을 증축할때마다 편의를 위해 새로운 파티장과 기존의 모든 파티장이 직접적으로 연결될 수 있는 도로

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 파티장의 크기 N
        // 서비스를 요청한 손님의 수 M
        // N개의 줄에 걸쳐 각가 N개의 수가 주어진다.
        // i번째 줄의 j번째 수는 i번 파티장에서 j번 파티장으로 직접적으로 연결된 도로를 통해 이동하는 시간을 의미
        // M개의 줄에 걸쳐 서비스를 요청한 손님이 시간내에 다른 파티장에 도착할 수 있으면 Enjoy other party 없으면 Stay here

        // 0 4 4 8 7 -> 1번 파티장에서 2번 파티장으로 4시간 , 1번 파티장에서 3번 파티장으로 4시간, 1번 파티장에서 4번 파티장으로 8시간, 1번 파티장에서 5번 파티장으로 7시간
        // 7 0 7 7 4 -> 2번 파티장에서 1번 파티장으로 7시간
        // 1 4 0 5 4
        // 5 2 2 0 7
        // 1 4 1 6 0

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        graph = new ArrayList[N+1];

        for (int i = 0; i<=N; i++) {
            graph[i] = new ArrayList<>();
        }

        for(int i = 1; i <= N; i++) {

            st = new StringTokenizer(br.readLine());

            for (int j = 1; j <= N; j++) {
                int iToJCost = Integer.parseInt(st.nextToken());
                graph[i].add(new Node(j, iToJCost));
            }
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());

            Dijkstra(N, A, B, C);
        }

        bw.flush();
        bw.close();
    }
}

class Node implements Comparable<Node> {
    int index;
    int cost;

    // 정점번호, 가중치 저장
    public Node(int index, int cost) {
        this.index = index;
        this.cost = cost;
    }

    // cost = 가중치
    //
    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.cost, o.cost); // this.cost < o.cost -> return (-)  | this.cost > o.cost return (+)
    }
}