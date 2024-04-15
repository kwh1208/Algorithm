
import java.io.*;
import java.util.*;
public class Main {

    static int[] arr;
    static int[] operation;
    static int maxNum = Integer.MIN_VALUE;
    static int minNum = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 연산자 끼워넣기
        // N개의 수로 이루어진 수열 A1 ~ AN이 주어진다.
        // 또 수와 수 사이에 끼워넣을 수 있는 N-1개의 연산자가 주어진다.
        // 연산자는 덧셈, 뺄셈, 곱셈, 나눗셈으로만 이루어졌다.

        // 1, 2, 3, 4, 5, 6이이고, 주어진 연산자가 덧셈 2개 , 뺄셈 1개, 곰셈 1개, 나눗셈 1개인 경우에
        // 총 60가지의 식을 만들 수 있다.
        // 1 + 2 + 3 - 4 * 5 / 6
        // 식의 계산은 연산자 우선 순위를 무시하고 앞에서부터!!!!


        int N = Integer.parseInt(br.readLine());

        arr = new int[N];
        operation = new int[4];

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 배열에 값 넣기
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        // operation 덧셈, 뺄셈, 곱셈, 나눗셈
        for (int i = 0; i < 4; i++) {
            operation[i] = Integer.parseInt(st.nextToken());
        }

        // 백트래킹은 해당 노드의 유망성을 판단한다고 했다.
        // 이 말은 즉, 해당 범위 내에서 조건을 추가하여 값의 유망성을 판단한다는 의미
        // 백트래킹의 방법 중 하나가 DFS

        dfs(arr[0], 1);

        bw.write(maxNum+"\n");
        bw.write(minNum+"\n");

        bw.flush();
        br.close();
        bw.close();
    }

    public static void dfs(int start, int idx) {
        if (idx == arr.length) {
            maxNum = Math.max(maxNum, start);
            minNum = Math.min(minNum, start);
            return;
        }

        for (int i = 0; i < 4; i++) {
            if (operation[i] > 0) {

                operation[i] = operation[i] - 1;

                switch (i) {
                    case 0:
                        dfs(start + arr[idx], idx + 1);
                        break;
                    case 1:
                        dfs(start - arr[idx], idx + 1);
                        break;
                    case 2:
                        dfs(start * arr[idx], idx + 1);
                        break;
                    case 3:
                        dfs(start / arr[idx], idx + 1);
                        break;
                }

                operation[i] = operation[i] + 1;
            }
        }
    }

}