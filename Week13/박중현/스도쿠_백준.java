import java.io.*;
import java.util.*;
public class Main {

    static int[][] arr;
    static boolean[][] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        arr = new int[9][9];

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
            }
        }

        backtracking(0,0);

        bw.flush();
        bw.close();
    }

    static void backtracking(int depth, int col) {

        if (col == 9) {
            backtracking(depth + 1, 0);
            return;
        }

        if (depth == 9) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(arr[i][j]).append(' ');
                }
                sb.append('\n');
            }
            System.out.println(sb);

            System.exit(0);
        }

        if (arr[depth][col] == 0) {
            for (int i = 1; i <= 9; i++) {
                if (check(depth, col, i)) {
                    arr[depth][col] = i;
                    backtracking(depth, col+1);
                }
            }
            arr[depth][col]=0;
            return;
        }

        backtracking(depth, col+1);
    }

    static boolean check(int row, int column, int num) {
        for (int i = 0; i < 9; i++) {
            if (arr[row][i] == num) {
                return false;
            }
        }

        for (int i = 0; i < 9; i++) {
            if (arr[i][column] == num) {
                return false;
            }
        }

        int ind_row = (row/3) * 3;
        int ind_col = (column/3) * 3;

        for (int i = ind_row; i < ind_row + 3; i++) {
            for (int j = ind_col; j < ind_col + 3; j++) {
                if (arr[i][j] == num) {
                    return false;
                }
            }
        }

        return true;
    }

}
