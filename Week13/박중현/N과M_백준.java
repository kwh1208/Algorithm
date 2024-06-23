import java.io.*;
import java.util.*;
public class Main {

    static int[] arr;
    static boolean[] check;
    static int N, M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 1부터 N까지 자연수 중에 중복 없이 M개를 고른 수열
        // 1부터 3까지 자연수 중에 중복 없이 1개를 고른 수열
        // 1
        // 2
        // 3

        // 1부터 3까지 자연수 중에 중복 없이 2개를 고른 수열
        // 1 2
        // 1 3
        // 2 1
        // 2 3
        // 3 1
        // 3 2

        // 1 2 3 4
        // 1 2 3 4

        arr = new int[M];

        check = new boolean[N];
        // arr = {1, 2, 3}
        // check = {false, false, false}

        backTracking(0);

        bw.flush();
        bw.close();
    }

    static void backTracking(int index) {
        if (index == M) {
            for (int i = 0; i < M; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
            return;
        }

        for (int i = 0; i < N; i++) {
            if (isValid(i)) {
                check[i] = true;
                arr[index] = i+1;
                // 다음 자식 노드 방문을 위해 index 추가 / 재귀호출
                backTracking(index+1);

                // 방문 끝나고 돌아왔을때 방문노드를 방문하지 않은 상태로 변경
                check[i] = false;
            }
        }
    }

    static boolean isValid(int node) {
        if (check[node]) {
            return false;
        }

        return true;
    }
}
