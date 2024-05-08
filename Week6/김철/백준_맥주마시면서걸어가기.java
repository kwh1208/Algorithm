import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main9205 {
    private static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int T,N;
    static Point startP, finishP;
    static List<Point> list;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token;

        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            N = Integer.parseInt(br.readLine());

            // 편의점 위치를 나타내는 list
            list = new ArrayList<>();

            // 집, 편의점, 페스티벌에 위치를 저장함.
            for (int i = 0; i < N + 2; i++) {
                token = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(token.nextToken());
                int y = Integer.parseInt(token.nextToken());

                // 집
                if(i == 0){
                    startP = new Point(x,y);
                }
                // 페스티벌
                else if(i == N+1){
                    finishP = new Point(x,y);
                }
                // 편의점
                else{
                    list.add(new Point(x,y));
                }
            }
            bw.write(BFS() + "\n");
        }
        bw.close();
    }

    // BFS() == 편의점 방문을 위한 탐색
    public static String BFS() {

        Queue<Point> q = new LinkedList<>();// 이동 좌표
        boolean[] visited = new boolean[N]; // 편의점 방문 처리
        q.offer(startP);                    // 출발 위치 = 초기값

        while (!q.isEmpty()) {
            Point now = q.poll();

            // 문제 제약 조건에 따라 한 번에 1000미터 이동 가능
            // 현재 위치와 도착 위치 거리가 1000이하라면 행복 도착 가능
            if (Math.abs(now.x - finishP.x) + Math.abs(now.y - finishP.y) <= 1000) {
                return "happy";
            }

            // 편의점 방문
            for (int i=0; i<N; i++) {
                // 방문하지 않은 편의점이라면
                if (!visited[i]) {
                    Point next = list.get(i); // 다음 위치
                    // now 에서 다음 편의점과의 거리가 1000 이하라면
                    if(Math.abs(now.x - next.x) + Math.abs(now.y - next.y) <= 1000){
                        visited[i] = true;
                        q.offer(next);
                    }

                }
            }
        }

        return "sad";
    }

}