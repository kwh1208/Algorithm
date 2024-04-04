// 우리 나라는 가족 혹은 친척들 사이의 관계를 촌수라는 단위로 표현하는 독특한 문화를 가지고 있다. 
// 이러한 촌수는 다음과 같은 방식으로 계산된다. 
// 기본적으로 부모와 자식 사이를 1촌으로 정의하고 이로부터 사람들 간의 촌수를 계산한다. 
// 예를 들면 나와 아버지, 아버지와 할아버지는 각각 1촌으로 나와 할아버지는 2촌이 되고, 아버지 형제들과 할아버지는 1촌, 나와 아버지 형제들과는 3촌이 된다.
// 여러 사람들에 대한 부모 자식들 간의 관계가 주어졌을 때, 주어진 두 사람의 촌수를 계산하는 프로그램을 작성하시오.
// 알고리즘 분류 상 BFS였지만, 저는 인접 리스트와 DFS를 사용하여 문제를 해결
// 문제에서 촌수 계산을 어떤 메커니즘으로 작동하였는지 파악하는 것이 중요했습니다.
// 나와 아버지는 1촌이고, 나와 형제는 2촌, 나의 할아버지는 2촌... 과 같이 
// 어떤 특정 사람 A, B가 있다고 가정하면, A에서 부모를 각각의 부모를 따라서 B로 이동하는 횟수가 바로 촌수입니다.

// 1. 사람들의 관계를 양방향 인접리스트가 아닌 인접행렬로 구현한다.
// 2. 관계를 찾아야 하는 두 사람 중 한 명으로부터 DFS 탐색을 시작하여 몇 개의 노드를 지나가는지 개수를 카운팅
// 3. 탐색 중 다른 한 사람까지 도달하면 카운팅한 값을 결과값에 넣어주고 탐색을 멈춘다.
// 4. 예를 들어 나>부모>형제 순으로 찾아갈 때, 형제와의 촌수는 부모>형제의 단계를 합쳐 2가 되는 것이다.
// 5. 모든 연결 노드를 탐색해도 목표인 사람을 찾지 못했다면 촌수관계가 없다는 뜻이므로 결과값의 초기화값인 -1이 출력

import java.io.*;
import java.util.*;

public class ChonsuCalculation_bj {
    static int N, from, to, result = -1;
    static boolean[][] map;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        // Scanner와 달리 BufferedReader는 개행문자만 경계로 인식
        // 입력받은 데이터가 String으로 고정
        // Scanner보다 속도가 빠르다
        // 동기화 되기 때문에 멀티 쓰레드 환경에서 안전
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 입력은 readLine();이라는 메소드를 사용
        // String으로 리턴 값이 고정되어 있기 때문에, 다른 타입으로 입력을 받고자 한다면 반드시 형변환이 필요
        // 그리고, 예외처리를 반드시 필요로 한다.
        // readLine()시 마다 try/catch문으로 감싸주어도 되고, throws IOException 을 통한 예외처리를 해도 된다
        // 사람의 수
        int N = Integer.parseInt(br.readLine());
        // StringTokenizer 클래스는 문자열을 구분자를 이용하여 분리할 때 사용
        // 만일 BufferedReader 클래스의 메서드로 입력을 읽어들인다면 라인 단위로 읽어들일 수 밖에 없습니다
        // 꼭 BufferedReader 클래스만이 아니더라도, 스페이스 기준으로 혹은 컴마로 혹은 공백을 기준으로 문자열들을 분리한다던가,
        // 특정 문자에 따라 문자열을 나누고 싶을 때 StringTokenizer를 사용
        // 즉, 토큰은 분리된 문자열 조각으로, StringTokenizer 클래스는 하나의 문자열을 여러 개의 토큰으로 분리하는 클래스
        StringTokenizer st = new StringTokenizer(br.readLine());
        int from = Integer.parseInt(st.nextToken());
        int to = Integer.parseInt(st.nextToken());

        // boolean[N+1][N+1] map: i번 사람과 j번 사람의 연결유무를 저장하는 배열
        boolean[][] map = new boolean[N + 1][N + 1];

        int M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            // 부모
            int v1 = Integer.parseInt(st.nextToken());
            // 자식
            int v2 = Integer.parseInt(st.nextToken());
            map[v1][v2] = map[v2][v1] = true;
        }

        visited = new boolean[N + 1];

        // 관계를 찾아야 하는 두 사람 중 한 명으로부터 DFS 탐색을 시작
        dfs(from, 0);

        System.out.println(result);
    }

    // 몇 개의 노드를 지나가는지 개수를 카운팅
    private static void dfs(int p, int d) {

        // 방문처리
        visited[p] = true;

        // 목표
        // 탐색 중 다른 한 사람까지 도달하면 카운팅한 값을 결과값에 넣어주고 탐색을 멈춘다.
        if (p == to) {
            result = d;
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (map[p][i] && !visited[i])
                dfs(i, d + 1);
        }
    }

}
