import java.io.*;
import java.util.*;

class Location {
    int row;
    int col;

    public Location(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

public class Solution_for_9205 {
    static int T, N;
    static int MAX_DISTANCE = 1000;

    static ArrayList<Location> stores;

    static int houseRow, houseCol;                  // start
    static int festivalRow, festivalCol;            // end


    // BFS 탐색할때 필요한 변수
    static Queue<Location> queue = new ArrayDeque<>();
    static boolean[] visited;

    public static boolean BFS() {
        queue.clear();
        visited = new boolean[N];

        queue.add(new Location(houseRow, houseCol));

        while (!queue.isEmpty()) {
            Location curr = queue.poll();

            int currRow = curr.row;
            int currCol = curr.col;
            
            // 큐에서 꺼낸 좌표가 목적지에 도달할 수 있는 거리내에 있는 좌표라면 
            if (isReachable(currRow, currCol, festivalRow, festivalCol)) {
                return true;
            }
            
            // 목적지에 도달할 수 없다면 방문할 수 있는 편의점 방문
            else {
                for (int i = 0; i < stores.size(); i++) {
                    int storeRow = stores.get(i).row;
                    int storeCol = stores.get(i).col;

                    // 방문할 수 있는 거리(1000m)내에 존재하고, 방문하지 않은 편의점이라면 
                    if (!visited[i] && isReachable(currRow, currCol, storeRow, storeCol)) {
                        visited[i] = true;
                        queue.add(stores.get(i));
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            
            stores = new ArrayList<>();
            N = Integer.parseInt(br.readLine());

            int row, col;
            for (int n = 0; n < N+2; n++) {
                st = new StringTokenizer(br.readLine());

                row = Integer.parseInt(st.nextToken());
                col = Integer.parseInt(st.nextToken());

                // 출발지점의 좌표인 경우
                if (n == 0) {
                    houseRow = row;
                    houseCol = col;
                }
                
                // 페스티벌의 좌표인 경우
                else if (n == N+1) {
                    festivalRow = row;
                    festivalCol = col;
                }

                // 편의점의 좌표인 경우
                else {
                    stores.add(new Location(row, col));
                }
            }
            answer.append((BFS() ? "happy" : "sad")).append("\n");
        }
        System.out.println(answer);
    }

    public static boolean isReachable(int currRow, int currCol, int destRow, int destCol) {
        if (Math.abs(currRow - destRow) + Math.abs(currCol - destCol) <= MAX_DISTANCE) return true;
        return false;
    }
}
