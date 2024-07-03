package Week13.조영호;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class NandM {
    public static int N; // 정적변수로 변경
    public static int M; // 정적변수로 변경
    public static int[] arr;
    public static boolean[] visit;
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        // 정적변수 N과 M을 초기화해준다.
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // m크기의 탐색 과정에서의 값을 담을 int 배열(arr)
        arr = new int[M];
        // 재귀를 하면서 이미 방문한 노드라면
        // 다음 노드를 탐색하도록 하기 위해(유망한 노드인지 검사하기 위해)
        // n크기의 boolean배열(visit)
        visit = new boolean[N];

        // 정적변수를 쓰면 되기 때문에 굳이 N과 M을 넘겨줄 필요 없다.
        dfs(0);
        System.out.println(sb);

    }

    public static void dfs(int depth) {
        if (depth == M) {
            for (int val : arr) {
                sb.append(val).append(' ');
            }
            sb.append('\n');
            return;
        }

        for (int i = 0; i < N; i++) {
            // 해당 노드를 방문하지 않았다면
            if (!visit[i]) {
                // 해당 노드 방문상태 변경
                visit[i] = true;
                // 해당 깊이를 index로 하여 i+1 값 저장
                arr[depth] = i + 1;
                // 다음 자식 노드 방문 위해 depth 1 증가시키며 재귀 호출
                dfs(depth + 1);
                // 자식노드 방문이 끝나고 돌아오면 방문노드를 방문하지 않은 상태로 변경
                visit[i] = false;
            }
        }
    }
}
