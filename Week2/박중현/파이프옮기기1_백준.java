
import java.io.*;
import java.util.*;
public class Main {

    static int[][] arr;

//    static boolean[][] check;

    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 새 집의 크기는 N * N 의 격자판으로 나타냄
        // 파이프는 회전시킬 수 있으며, 아래와 같이 3가지 방향이 가능하다.

        // 파이프는 매우 무겁기 때문에 파이프를 밀어서 이동시킨다.
        // 파이프는 오른쪽(x+1, y), 45도 방향(x+1, y+1), 밑쪽(x,y+1)

        int N = Integer.parseInt(br.readLine());

        // 시작 막대의 끝은 1,1
        // 오른쪽으로 갈 수 있는 방법 : 가로 파이프이거나 대각선 파이프일때
        // 대각선으로 갈 수 있는 방법 : 가로, 세로, 대각선 파이프 다 가능 하지만, 4칸이 확보가 되어야 함
        // 세로로 갈 수 있는 방법 : 세로 파이프이거나 대각선 파이프일때

        arr = new int[N][N];
//        check = new boolean[N][Nj;

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0,1, Direction.HORIZONTAL);


        bw.write(count+"");

        bw.flush();
        br.close();
        bw.close();
    }

    public static void dfs(int x, int y, Direction direction) {

        if (x == arr.length -1 && y == arr.length -1) {
            count += 1;
            return;
        }

//        check[x][y] = true;

        // 수평인 파이프일 때 오른쪽 , 대각선 가능
        if (direction.equals(Direction.HORIZONTAL)) {
            if (isRightMovePossible(x,y)) {
                dfs(x, y+1, Direction.HORIZONTAL);
            }

            if (isDiagonalMovePossible(x,y)) {
                dfs(x+1, y+1, Direction.DIAGONAL);
            }
        } else if (direction.equals(Direction.VERTICAL)) { // 파이프 방향이 수직일때 아래, 대각선 가능
            if (isDownMovePossible(x,y)) {
                dfs(x+1, y, Direction.VERTICAL);
            }

            if (isDiagonalMovePossible(x,y)) {
                dfs(x+1, y+1, Direction.DIAGONAL);
            }
        } else { // 파이프 방향이 대각선일때 오른쪽, 아래, 대각선 가능
            if (isRightMovePossible(x,y)) {
                dfs(x, y+1, Direction.HORIZONTAL);
            }

            if (isDownMovePossible(x,y)) {
                dfs(x+1, y, Direction.VERTICAL);
            }

            if (isDiagonalMovePossible(x,y)) {
                dfs(x+1, y+1, Direction.DIAGONAL);
            }
        }

    }

    // 파이프가 배열 밖으로 벗어나는지 확인하는 메서드
    public static boolean isArrLengthOver(int x, int y) {
        if (x >= arr.length || y >= arr.length || x < 0 || y < 0) {
            return true;
        }

        return false;
    }

    // 파이프가 오른쪽으로 이동할 수 있는지 확인하는 메서드
    public static boolean isRightMovePossible(int x, int y) {
        if (!isArrLengthOver(x, y+1) && arr[x][y+1] == 0) {
            return true;
        }

        return false;
    }

    // 파이프가 아래로 이동할 수 있는지 확인하는 메서드
    public static boolean isDownMovePossible(int x, int y) {
        if (!isArrLengthOver(x+1,y) && arr[x+1][y] == 0) {
            return true;
        }

        return false;
    }

    // 파이프가 대각선으로 이동할 수 있는지 확인하는 메서드
    public static boolean isDiagonalMovePossible(int x, int y) {
        if (!isArrLengthOver(x+1, y+1) && arr[x+1][y] == 0 && arr[x+1][y+1] == 0 && arr[x][y+1] == 0) {
            return true;
        }

        return false;
    }
}

enum Direction {
    // 수직, 수평, 대각선
    VERTICAL, HORIZONTAL, DIAGONAL
}