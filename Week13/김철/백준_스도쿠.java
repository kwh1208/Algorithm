package Main;

import java.io.*;
import java.util.*;

class Main2580{
    static int[][] sudokuArr;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer token;
        sudokuArr = new int[9][9];

        for(int i=0; i<9; i++){
            token = new StringTokenizer(br.readLine());
            for(int j=0; j<9; j++){
                sudokuArr[i][j] = Integer.parseInt(token.nextToken());
            }
        }

        sudoku(0,0);
        bw.close();
    }

    static void sudoku(int row, int col) throws IOException{

        // 해당 행의 모든 열이 다 정해졌다면
        // 다음 행으로
        if (col > 8) {
            sudoku(row + 1, 0);
            return;
        }

        // 행과 열이 모두 채워졌을 경우 출력 후 종료
        if (row > 8) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(sudokuArr[i][j]).append(' ');
                }
                sb.append('\n');
            }
            bw.write(sb + "");
            bw.close();
            // 출력 뒤 시스템을 종료한다.
            System.exit(0);
        }

        // 만약 해당 위치의 값이 0 이라면 = 정해지지 않았다면
        // 1부터 9까지 중 가능한 수 탐색
        if (sudokuArr[row][col] == 0) {
            for (int i = 1; i <= 9; i++) {
                // i 값이 중복되지 않는지 검사
                if (numCheck(row, col, i)) {
                    sudokuArr[row][col] = i;
                    sudoku(row, col + 1);
                }
            }
            sudokuArr[row][col] = 0;
            return;
        }

        // 당장은 여기 값을 못찾겠으면 건너뛰기
        sudoku(row, col + 1);
    }

    static boolean numCheck(int x, int y, int val){
        int tempRow = (x / 3) * 3;
        int tempCol = (y / 3) * 3;

        //3X3 체크
        for(int i = tempRow; i < tempRow + 3; i++){
            for(int j = tempCol; j < tempCol + 3; j++){
                if(sudokuArr[i][j] == val) return false;
            }
        }

        // 열 체크
        for(int i = 0; i < 9; i++){
            if(sudokuArr[x][i] == val) return false;
        }

        // 행 체크
        for(int i = 0; i < 9; i++){
            if(sudokuArr[i][y] == val) return false;
        }
        return true;
    }
}
//https://st-lab.tistory.com/119