import java.io.*;
import java.util.*;

public class boj_17070 {
    static int N;
    static int[][] house;

    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        // 집의 상태 입력받기
        N = Integer.parseInt(br.readLine());
        house = new int[N+1][N+1];
        for (int r = 1; r < N+1; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c < N+1; c++) {
                house[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        DFS(1, 1, 1, 2);
        System.out.println(count);
    }

    public static void DFS(int firstPipeRow, int firstPipeCol, int secondPipeRow, int secondPipeCol) {
        
        // (N, N)에 도달한 경우
        if (secondPipeRow == N && secondPipeCol == N) {
            count++;
            return;
        }

        // (N, N)에 도달하지 않은 경우
        else {
            int currentPipeDir = getDirection(firstPipeRow, firstPipeCol, secondPipeRow, secondPipeCol);

            switch(currentPipeDir) {
                // 가로로 놓인 경우
                case 1:
                    // 가로로 미는 경우
                    if ( (secondPipeCol != N) && checkHorizontal(secondPipeRow, secondPipeCol)) {
                        DFS(secondPipeRow, secondPipeCol, secondPipeRow, secondPipeCol+1);
                    }
                    // 대각선으로 미는 경우
                    if ( (secondPipeRow != N) && (secondPipeCol != N) && checkDiagonal(secondPipeRow, secondPipeCol)) {
                        DFS(secondPipeRow, secondPipeCol, secondPipeRow+1, secondPipeCol+1);
                    }
                    break;
                
                // 세로로 놓인 경우
                case 2:
                    // 세로로 미는 경우
                    if ((secondPipeRow != N) && checkVertical(secondPipeRow, secondPipeCol)) {
                        DFS(secondPipeRow, secondPipeCol, secondPipeRow+1, secondPipeCol);
                    }
                    // 대각선으로 미는 경우
                    if ((secondPipeRow != N) && (secondPipeCol != N) && checkDiagonal(secondPipeRow, secondPipeCol)) {
                        DFS(secondPipeRow, secondPipeCol, secondPipeRow+1, secondPipeCol+1);
                    }
                    break;
                
                // 대각선으로 놓인 경우
                case 3:
                    // 가로로 미는 경우
                    if ((secondPipeCol != N) && checkHorizontal(secondPipeRow, secondPipeCol)) {
                        DFS(secondPipeRow, secondPipeCol, secondPipeRow, secondPipeCol+1);
                    }

                    // 세로로 미는 경우
                    if ((secondPipeRow != N) && checkVertical(secondPipeRow, secondPipeCol)) {
                        DFS(secondPipeRow, secondPipeCol, secondPipeRow+1, secondPipeCol);
                    }

                    // 대각선으로 미는 경우
                    if ((secondPipeRow != N) && (secondPipeCol != N) && checkDiagonal(secondPipeRow, secondPipeCol)) {
                        DFS(secondPipeRow, secondPipeCol, secondPipeRow+1, secondPipeCol+1);
                    }
                    break;
            }
        }
    }


    public static int getDirection(int firstPipeRow, int firstPipeCol, int secondPipeRow, int secondPipeCol) {
        // 가로로 놓인 경우
        if (firstPipeRow == secondPipeRow && firstPipeCol + 1 == secondPipeCol) {
            return 1;
        }

        // 세로로 놓인 경우
        else if (firstPipeRow + 1 == secondPipeRow && firstPipeCol == secondPipeCol) {
            return 2;
        }

        // 대각선으로 놓인 경우
        else return 3;
    }



    // 가로로 밀 수 있는지 확인하는 메서드
    public static boolean checkHorizontal(int secondPipeRow, int secondPipeCol) {
        if (house[secondPipeRow][secondPipeCol+1] == 0) return true;
        return false;
    }
    // 세로로 밀 수 있는지 확인하는 메서드
    public static boolean checkVertical(int secondPipeRow, int secondPipeCol) {
        if (house[secondPipeRow+1][secondPipeCol] == 0) return true;
        return false;
    }
    // 대각선으로 밀 수 있는지 확인하는 메서드 -> 3칸 확인필요
    public static boolean checkDiagonal(int secondPipeRow, int secondPipeCol) {
        if (
            house[secondPipeRow][secondPipeCol+1] == 0 &&       // 가로
            house[secondPipeRow+1][secondPipeCol+1] == 0 &&     // 대각선
            house[secondPipeRow+1][secondPipeCol] == 0          //세로
            ) return true;
        return false;
    }
}