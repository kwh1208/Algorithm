import java.io.*;
import java.util.*;
public class Main {

    static int[][] arr;
    static int[] positions;
    static int answer;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int C = Integer.parseInt(br.readLine());

        for (int k = 0; k < C; k++) {
            arr = new int[11][11];
            positions = new int[11];
            answer = 0;

            for (int i = 0; i < 11; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < 11; j++) {
                    arr[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            solveNQueen(0);

            bw.write(answer+"\n");
        }
        bw.flush();
        bw.close();
    }

    static void solveNQueen(int row) {

        if (row == 11) {
            int tempSum = 0;
            for (int i = 0; i < 11; i++) {
                tempSum+=positions[i];
            }

            if (tempSum > answer) {
                answer = tempSum;
            }

            return;
        }

        for (int i = 0; i < 11; i++) {
            if (isValid(i) && arr[row][i] != 0) {
                positions[i] = arr[row][i];
                solveNQueen(row+1);
            }
        }
    }

    static boolean isValid(int col) {
        for (int i = 0; i < 11; i++) {
            if (positions[col] > 0) {
                return false;
            }
        }

        return true;
    }
}
