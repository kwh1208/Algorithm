import java.util.Scanner;

public class BOJ_3980 {

    static int[][] position = new int[11][11];
    static boolean[] visited;
    static int max;

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int t = scan.nextInt();
        for(int i = 0; i < t; i++) {
            for(int j = 0; j < 11; j++) {
                for(int k = 0; k < 11; k++) {
                    position[j][k] = scan.nextInt();
                }
            }

            max = 0;
            visited = new boolean[11];
            backtracking(0, 0);
            System.out.println(max);
        }
    }

    public static void backtracking(int pos, int total) {
        if(pos == 11) {
            max = Math.max(max, total);
            return;
        }

        for(int i = 0; i < 11; i++) {
            if(!visited[i] && position[pos][i] != 0) {
                visited[i] = true;
                backtracking(pos + 1, total + position[pos][i]);
                visited[i] = false;
            }
        }
    }
}

//https://moonsbeen.tistory.com/275