package Week12.조영호;

import java.util.Scanner;

public class LineUp {
    static int[][] position = new int[11][11];
    // 헤당 선수를 뽑았는지 아직 뽑지 않았는지 확인해기 위해 visited 배열을 구현
    static boolean[] visited;
    static int max;

    public static void backtracking(int pos, int total) {
        if (pos == 11) {
            // max 값을 total 값과 비교하여 지정
            max = Math.max(max, total);
            return;
        }
        for (int i = 0; i < 11; i++) {
            // 능력치가 0인 선수는 뽑지 않는다
            if (!visited[i] && position[pos][i] != 0) {
                visited[i] = true;
                // 선수를 뽑을때 마다 능력치 합 누적
                backtracking(pos + 1, total + position[pos][i]);
                visited[i] = false;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            for (int j = 0; j < 11; j++) {
                for (int k = 0; k < 11; k++) {
                    position[j][k] = sc.nextInt();
                }
            }

            max = 0;
            visited = new boolean[11];
            backtracking(0, 0);
            System.out.println(max);
        }
        sc.close();
    }
}
