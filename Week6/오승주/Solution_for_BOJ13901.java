import java.io.*;
import java.util.*;

public class Solution_for_13901 {
    static int R, C, K;
    static int[] moveDir = new int[4];

    static boolean[][] visited;

    static int xr, xc;
    static int sr, sc;

    static int curRow, curCol;
    static int nextRow, nextCol;

    static int[][] dir = {{}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        visited = new boolean[R][C];

        K = Integer.parseInt(br.readLine());
        for (int k = 0; k < K; k++) {
            st = new StringTokenizer(br.readLine());

            xr = Integer.parseInt(st.nextToken());
            xc = Integer.parseInt(st.nextToken());

            visited[xr][xc] = true;
        }

        st = new StringTokenizer(br.readLine());
        sr = Integer.parseInt(st.nextToken());
        sc = Integer.parseInt(st.nextToken());

        visited[sr][sc] = true;
        curRow = sr;
        curCol = sc;

        // 이동 방향의 순서 입력받기
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            moveDir[i] = Integer.parseInt(st.nextToken());
        }

        int dirCount = 0;
        int pointer = 0;
        while (true) {

            if (pointer == 4) {
                break;
            }
            
            nextRow = curRow + dir[moveDir[dirCount]][0];
            nextCol = curCol + dir[moveDir[dirCount]][1];

            if (isMoveable(nextRow, nextCol)) {
                
                curRow = nextRow;
                curCol = nextCol;

                visited[curRow][curCol] = true;

                // 로봇이 움직이면 이동방향을 가리키는 포인터 초기화시킴
                pointer = 0;
            }

            else {
                dirCount = (dirCount+1) % 4;
                pointer++;
            }

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    sb.append(visited[i][j]+" ");
                }
                sb.append("\n");
            }
            System.out.println(sb);
        }
        System.out.printf("%d %d\n", curRow, curCol);

        /*
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                sb.append(visited[i][j]);
            }
            sb.append("\n");
        }
        System.out.println(sb);
        */
    }

    public static boolean isMoveable(int row, int col) {
        if (row < 0 || col < 0 || R-1 < row || C-1 < col) return false;
        if (visited[row][col]) return false;

        return true;
    }
}