import java.io.*;
import java.util.*;

public class Solution_for_17276 {
    static int T, N, d;
    static int[][] curr;
    static int[][] after;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());

            curr = new int[N][N];
            after = new int[N][N];
            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                
                for (int c = 0; c < N; c++) {
                    curr[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            /*
             * 만약 회전해도 배열이 똑같은 경우 
             */
            if (d == 0 || d == 360 || d == -360) {
                printArray();
                continue;
            }

            /*
             * 시계 방향으로 회전하는 경우
             */
            if (0 <= d) {
                clockwise(d/45, N);
            }

            /*
             * 반시계 방향으로 회전하는 경우
             */
            else {
                counterClockwise(-(d/45), N);
            }

            printArray();
        }
    }

    /*
     * 시계방향으로 회전시키는 경우
     * 동시에 원소를 회전시킨 후에 이동하지 않은 원소들도 이동시켜줌
     * 
     * curr = after
     * 한번 이상 회전시키는 경우, curr배열을 업데이트 해주어야 함
     */
    public static void clockwise(int count, int n) {
        for (int i = 0; i < count; i++) {
            after = new int[N][N];

            move1(n);
            move2(n);
            move3(n);
            move4(n);

            moveElement();

            curr = after;
        }
    }

    
    /*
     * move1(n): 주 대각선을 가운데 열로
     * move2(n): 가운데 열을 부 대각선으로
     * move3(n): 가운데 행을 주 대각선으로
     * move4(n): 가운데 행을 주 대각선으로
     */
    public static void move1(int n) {
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (r==c) {
                    after[r][n/2] = curr[r][c];
                }
            }
        }
    }
    public static void move2(int n) {
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (c==(n/2)) {
                    after[r][n-r-1] = curr[r][c];
                }
            }
        }
    }
    public static void move3(int n) {
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if ((r+c) == (n-1)) {
                    after[n/2][c] = curr[r][c];
                }
            }
        }
    }
    public static void move4(int n) {
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (r == (n/2)) {
                    after[c][c] = curr[r][c];
                }
            }
        }
    }




    /*
     * count 매개변수의 수만큼
     * 반시계 방향으로 회전시키고
     * 이동하지 않은 나머지 원소들도 이동시킴
     * 
     * curr = after
     * 한번 이상 회전시키는 경우, curr배열을 업데이트 해주어야 함
     */
    public static void counterClockwise(int count, int n) {
        for (int i = 0; i < count; i++) {
            after = new int[N][N];

            oppositeMove1(n);
            oppositeMove2(n);
            oppositeMove3(n);
            oppositeMove4(n);

            moveElement();

            curr = after;
        }
    }

    /*
     * oppositeMove1(n): 주 대각선을 가운데 행으로 옮김
     * oppositeMove2(n): 가운데 열을 주 대각선으로 옮김
     * oppositeMove3(n): 부 대각선을 가운데 열로 옮김
     * oppositeMove4(n): 가운데 행을 부 대각선으로 옮김
     */
    public static void oppositeMove1(int n) {
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (r == c) {
                    after[n/2][c] = curr[r][c];
                }
            }
        }
    }
    public static void oppositeMove2(int n) {
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (c == (n/2)) {
                    after[r][r] = curr[r][c];
                }
            }
        }
    }
    public static void oppositeMove3(int n) {
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if ((r+c) == (n-1)) {
                    after[r][n/2] = curr[r][c];
                }
            }
        }
    }
    public static void oppositeMove4(int n) {
        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                if (r == (n/2)) {
                    after[n-c-1][c] = curr[r][c];
                }
            }
        }
    }



    /*
     * 원소를 회전시키고 난 뒤 이동되지 않은 요소들을 탐색하여 이동시킴
     */
    public static void moveElement() {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (after[r][c] == 0) {
                    after[r][c] = curr[r][c];
                }
            }
        }
    }



    public static void printArray() {
        StringBuilder answer = new StringBuilder();

        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                answer.append(curr[r][c] + " ");
            }
            answer.append("\n");
        }
        System.out.print(answer);
    }
}
