
import java.io.*;
import java.util.*;
public class Main {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<ArrayList<Integer>>();
    static boolean[] visitedFlag;
    static int answer = -1;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 부모 - 자식 1촌
        // 나 - 할아버지 2촌
        // 나 - 아버지 형제 - 3촌

        // 나를 기준으로 촌수 정하기
        //    할아버지(2촌)
        //    아버지(1촌)   아버지 형제(3촌)
        //    나

        // 입력 파일의 첫째 줄에는 전체 사람의 수 n이 주어지고,
        // 둘째 줄에는 촌수를 계산해야 하는 서로 다른 두사람의 번호가 주어진다
        // 셋째 줄에는 부모 자식들 간의 관계수 m이 주어진다.
        // 넷째 줄부터는 부모 자식간의 관계를 나타내는 두 번호 x, y
        // 이때 앞에 나오는 번호 x는 뒤에 나오는 정수 y의 부모 번호를 나타낸다.

        int n = Integer.parseInt(br.readLine()); // 전체 사람의 수
        StringTokenizer st = new StringTokenizer(br.readLine());
        int person1 = Integer.parseInt(st.nextToken());
        int person2 = Integer.parseInt(st.nextToken()); // 촌수를 계산해야 하는 서로 다른 두 사람의 번호
        int m = Integer.parseInt(br.readLine()); // 부모 자식들 간의 관계 수 m

        visitedFlag = new boolean[n+1];

        // 리스트 초기화
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<Integer>());
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()); // 부모
            int y = Integer.parseInt(st.nextToken()); // 자식

            graph.get(x).add(y);
            graph.get(y).add(x);
        }

        dfs(person1,person2,0);

        bw.write(answer+"");

        bw.flush();
        br.close();
        bw.close();
    }

    static void dfs(int start, int end, int count) {

        if (start == end) {
            answer = count;
        }

        // 현재 노드 방문 처리
        visitedFlag[start] = true;

        for (int node : graph.get(start)) {
            // 방문을 안 한
            if (!visitedFlag[node]) {
                dfs(node, end, count+1);
            }
        }
    }

}