import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main12893 {

    static int N, M;
    static List<Integer>[] enemyListArray;   // 리스트로 된 배열

    // visited[i] 를 1 또는 -1 로 이분하면서
    // i가 1팀 -1팀 중 어느 팀에 속하는지 판단한다.
    // visited[i] = 0 은 방문하지 않은 상태
    static int visited[];
    static int answer = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token;

        token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());
        enemyListArray = new List[N + 1];
        visited = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            // i 와 적대관계에 있는 사람들을 담을 리스트 객체를
            // 각 enemyListArray[i]에 생성
            enemyListArray[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            token = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(token.nextToken());
            int B = Integer.parseInt(token.nextToken());
            enemyListArray[A].add(B);
            enemyListArray[B].add(A);
        }

        for (int i = 1; i <= N; i++) {
            // 방문하지 않았다면
            if (visited[i] == 0) {
                // 사람 i의 적대관계에 있는 사람들을 검색하면서
                // team 값을 1 또는 -1로 설정하며 visited[i]에 입력한다.
                DFS(i, 1);
            }
            if (answer == 0) break;
        }

        bw.write(answer + "");
        bw.close();
    }

    // 사람 personNum 와 적대관계인 사람들을 판단한다.
    // team는 1 or -1 값으로 이분하여서
    // 어느 팀에 있는지 판단한다.
    static void DFS(int personNum, int team) {

        // 사람 personNum 를 1 팀인지, -1 팀인지 표시
        visited[personNum] = team;

        // 사람 personNum의 적대관계 List 인 enemyListArray[personNum] 방문 시작
        // 방문을 통해 personNum와 적대관계인 사람들 판단
        for (int temp : enemyListArray[personNum]) {
            // 적대관계인 사람 temp 를 처음 방문했다면
            if (visited[temp] == 0) {
                // temp 를 team 의 반대 팀으로 설정
                visited[temp] = team * -1;
                // 사람 temp 의 팀과 상대 팀을 판단
                DFS(temp, team * -1);

                // 여기서 DFS 를 실행하지 않으면 진행할 수 없다.
            }
            // temp 는 personNum 의 상대 편이어야 한다.
            // 같은 편이라면 이론 성립 불가.
            else if (visited[temp] == team) {
                answer = 0;
                return;
            }
        }
    }
}
// 참고
// https://velog.io/@strangehoon/%EB%B0%B1%EC%A4%80-12893-%EC%A0%81%EC%9D%98-%EC%A0%81
