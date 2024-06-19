import java.io.*;
import java.util.*;
public class Main {

    static int N;
    static int cnt = 0;
    static int[] cols;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        cols = new int[N];

        solveNQueen(0);

        bw.write(cnt + "\n");
        bw.flush();
        bw.close();
    }

    static void solveNQueen(int row) {
        if (row == N) {
            cnt++;
            return;
        }
        // 1 3 0 2 => 0 번째 행의 1번째 열에 Q가 있다. 1번째 행의 3번째 열에 Q가 있다. 2번째 행의 0번째 열에 Q가 있다. 3번째 행의 2번째 열에 Q가 있다.
        // o Q o o
        // o o o Q
        // Q o o o
        // o o Q o
        for (int col = 0; col < N; col++) {
            if (isValid(row, col)) {
                cols[row] = col;
                solveNQueen(row + 1);
            }
        }
    }

    static boolean isValid(int row, int col) { // 만약 2번째 행의 0번째 열이 가능한가?
        for (int i = 0; i < row; i++) {
            if (cols[i] == col) { // 우선 열 체크 먼저
                return false;
            }

            if (Math.abs(cols[i] - col) == Math.abs(i - row)) { // 대각선 체크
                return false;
            }
        }
        return true;
    }
}
