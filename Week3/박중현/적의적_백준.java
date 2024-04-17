
import java.io.*;
import java.util.*;
public class Main {

    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static int[] visit;
    static int flag = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 적의 적은 친구
        // A와 적대 관계인 B, B와 적대 관계인 C
        // A와 C는 우호 관계에 있다
        // D와 적대관계인 E가 있다면 E는 A, C와 우호 관계가 된다.
        // 같은 맥락으로, B와 D 역시 우호 관계가 된다.

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N + 1; i++) {
            graph.add(new ArrayList<>());
        }

        visit = new int[N];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph.get(a).add(b);
            graph.get(b).add(a);

        }

        visit = new int[N+1];

        // visit[i] 가 0일 때(방문하지 않았을때) dfs 돌리기
        for (int i = 1; i < N+1; i++) {
            if (visit[i] == 0) {
                dfs(i, 1);
            }
        }

        bw.write(flag+"");

        bw.flush();
        br.close();
        bw.close();
    }

    static void dfs(int a, int value) {

        // 만약 flag = 0이면
        if (flag == 0) {
            return;
        } else { // flag가 0이 아니라면
            visit[a] = value;
            for (int tem : graph.get(a)) {
                if (visit[tem] == 0) { // 만약 해당 아이템 방문을 하지 않았다면
                    visit[tem] = value * -1; // 아이템 방문 후 visit = -1로 설정
                    dfs(tem, value*-1); // value를 -1 곱하여 dfs
                } else if (visit[tem] == value) { // visit의 아이템값이 같은 부호일때
                    flag = 0; // flag를 0으로 하고 return -> 이론이 성립하지 않음
                    return;
                }
            }
        }


    }
}