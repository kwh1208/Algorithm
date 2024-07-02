import java.io.*;
import java.util.*;
public class Main {

    static int[][] arr;
    static boolean[][] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        arr = new int[9][9];
        visit = new boolean[9][9];

        /*
        0 3 5 4 6 9 2 7 8    F T T T T T T T T
        7 8 2 1 0 5 6 0 9    T T F F T T T T T
        0 6 0 2 7 8 1 3 5
        3 2 1 0 4 6 8 9 7
        8 0 4 9 1 3 5 0 6
        5 9 6 8 2 0 4 1 3
        9 1 7 6 5 2 0 8 0
        6 0 3 7 0 1 9 5 2
        2 5 8 3 9 4 7 6 0
         */

        for(int i = 0; i < 9; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] != 0) {
                    visit[i][arr[i][j] - 1] = true;
                }
            }
        }

        backtracking(0);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                bw.write(arr[i][j]+" ");
            }
            bw.write("\n");
        }

        bw.flush();
        bw.close();
    }

    static void backtracking(int depth) {

        if (depth == 9) {
            return;
        }

        for (int i = 0; i < 9; i++) {
            if (arr[depth][i] == 0) {

                for (int j = 0; j < 9; j++) {
                    if (visit[depth][j] == false && checkIfAvailable(depth, i, j+1)) { // visit[1][2] = false
                        arr[depth][i] = j + 1;
                        visit[depth][j] = true;
                        backtracking(depth+1);
                        visit[depth][j] = false;
//                        arr[depth][i] = 0;
                    }
                }
            }
        }
    }

    static boolean checkIfAvailable(int row, int column, int num) {
        for (int i = 0; i < row; i++) {
            if (num == arr[row][column]) {
                return false;
            }
        }

        return true;
    }
}
