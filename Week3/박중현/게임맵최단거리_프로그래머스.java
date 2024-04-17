import java.util.*;
class Solution {
    static boolean[][] visit;
    static Queue<Dot> queue = new LinkedList<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public int solution(int[][] maps) {
        int answer = 0;

        // 한 팀의 팀원 게임
        // 5 * 5

        visit = new boolean[maps.length][maps[0].length];

        answer = bfs(maps);

        return answer;
    }

    public int bfs(int[][] maps) {
        queue.add(new Dot(0,0,1));
        visit[0][0] = true;

        while(!queue.isEmpty()) {

            Dot pollDot = queue.poll();

            if(pollDot.x == maps.length -1  && pollDot.y == maps[0].length -1){
                // System.out.println(pollDot.x+"," + pollDot.y);
                return pollDot.cnt;
            }

            for(int i = 0; i < 4; i++) {
                int nx = pollDot.x + dx[i];
                int ny = pollDot.y + dy[i];
                int nCnt = pollDot.cnt;

                if(nx < 0 || ny < 0 || nx >= maps.length || ny >= maps[0].length){
                    continue;
                }

                if(maps[nx][ny] == 1 && !visit[nx][ny]) {

                    queue.add(new Dot(nx,ny, nCnt+1));
                    visit[nx][ny] = true;
                }
            }
        }

        return -1;
    }

}

class Dot {
    int x;
    int y;
    int cnt;

    Dot(int x, int y, int cnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }
}
