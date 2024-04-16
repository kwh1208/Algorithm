import java.util.*;

public class Solution게임맵최단거리 {
    public static void main(String args[]) throws Exception {
        int[][] maps = {
                {1,0,1,1,1},
                {1,0,1,0,1},
                {1,0,1,1,1},
                {1,1,1,0,1},
                {0,0,0,0,1}};

        System.out.println(solution(maps));
    }

    // 풀이1
//    static class Point{
//        int x, y;
//        Point(int x, int y){
//            this.x = x;
//            this.y = y;
//        }
//    }
//
//    static int solution(int[][] maps) {
//        int answer = 0;
//
//        // 0 = 벽 있음, 1 = 벽 없음
//        int[][] visited = new int[maps.length][maps[0].length];
//        bfs(maps, visited);
//
//        answer = visited[maps.length-1][maps[0].length-1];
//        return answer == 0 ? -1 : answer;
//    }
//
//    static void bfs(int[][] maps, int[][] visited){
//
//        int[] dx = {0, 0, -1, 1};
//        int[] dy = {-1, 1, 0, 0};
//        Queue<Point> q = new ArrayDeque<>();
//        q.offer(new Point(0, 0));
//        visited[0][0] = 1;
//
//        while(!q.isEmpty()){
//            Point now = q.poll();
//
//            for(int i=0; i<4; i++){
//                int nx = now.x + dx[i];
//                int ny = now.y + dy[i];
//
//                if(nx < 0 || nx >= maps.length || ny < 0 || ny >= maps[0].length) continue;
//                if(maps[nx][ny] == 1 && visited[nx][ny] == 0){
//                    q.offer(new Point(nx, ny));
//                    visited[nx][ny] = visited[now.x][now.y] + 1;
//                }
//            }
//        }
//    }

    // 풀이2
    static class Point{
        int x, y, count;
        Point(int x, int y, int count){
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int answer = 0;

    static int solution(int[][] maps) {

        BFS(maps);

        return answer;
    }

    static void BFS(int[][] maps){
        int n = maps.length;
        int m = maps[0].length;
        Queue<Point> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][m];
        q.offer(new Point(0, 0, 1));
        visited[0][0] = true;

        while(!q.isEmpty()){
            Point now = q.poll();

            if(now.x == n-1 && now.y == m-1) {
                answer = now.count;
                return;
            }

            for(int i=0; i<4; i++){
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx < 0 || nx >= n || ny < 0 || ny >= m) continue;
                if(visited[nx][ny]) continue;
                if(maps[nx][ny] == 0) continue;
                q.offer(new Point(nx, ny, now.count + 1));
                visited[nx][ny] = true;
            }
        }
        answer = -1;
    }
}