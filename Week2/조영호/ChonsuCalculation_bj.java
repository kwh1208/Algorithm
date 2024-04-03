// 우리 나라는 가족 혹은 친척들 사이의 관계를 촌수라는 단위로 표현하는 독특한 문화를 가지고 있다. 
// 이러한 촌수는 다음과 같은 방식으로 계산된다. 
// 기본적으로 부모와 자식 사이를 1촌으로 정의하고 이로부터 사람들 간의 촌수를 계산한다. 
// 예를 들면 나와 아버지, 아버지와 할아버지는 각각 1촌으로 나와 할아버지는 2촌이 되고, 아버지 형제들과 할아버지는 1촌, 나와 아버지 형제들과는 3촌이 된다.
// 여러 사람들에 대한 부모 자식들 간의 관계가 주어졌을 때, 주어진 두 사람의 촌수를 계산하는 프로그램을 작성하시오.
// 알고리즘 분류 상 BFS였지만, 저는 인접 리스트와 DFS를 사용하여 문제를 해결
// 문제에서 촌수 계산을 어떤 메커니즘으로 작동하였는지 파악하는 것이 중요했습니다.
// 나와 아버지는 1촌이고, 나와 형제는 2촌, 나의 할아버지는 2촌... 과 같이 
// 어떤 특정 사람 A, B가 있다고 가정하면, A에서 부모를 각각의 부모를 따라서 B로 이동하는 횟수가 바로 촌수입니다.

// 1. 사람들의 관계를 양방향 인접리스트로 구현한다.
// 2. 촌수를 계산할 사람 2명 중 1명을 시작점으로 두고, 나머지 1명을 끝점으로 설정한 후, DFS를 통해 끝점에 도달할 때까지 반복한다. 이 때, cnt를 1씩 증가시키면서 반복한다.
// 2-1. 끝점에 도달할 경우, cnt의 값을 출력하고 종료한다.
// 2-2. 끝점에 도달할 수 없는 경우 -1을 출력하고 종료한다.

import java.io.*;
import java.util.*;

public class ChonsuCalculation_bj {
    static int ans = -1;

    // DFS를 이용하여 촌수 계산.
    public static void DFS(ArrayList<ArrayList<Integer>> a, boolean[] visited, int pos, int find, int cnt) {
        visited[pos] = true;

        for (int i : a.get(pos)) {
            if (!visited[i]) {
                // 찾으려는 사람의 도달.
                if (i == find) {
                    ans = cnt + 1;
                    return;
                }

                DFS(a, visited, i, find, cnt + 1);
            }
        }
    }

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
        int N = Integer.parseInt(br.readLine());
        // 일반적으로 출력을 할 때, System.out.println(""); 을 사용
        // 적은 양의 출력에서는 편리하고, 그렇게 큰 성능 차이 없이 사용할 수 있다.
        // 하지만 우리가 늘 고려해야하는 경우는 양이 많을 경우
        // 많은 양의 출력을 할 때는, 입력과 동일하게 버퍼를 사용
        // BufferedWriter는 System.out.println(""); 처럼 출력과 개행을 동시해 해주지 않는다
        // 개행을 위해선 따로 newLine(); 혹은 bw.write("\n");을 사용
        // 그리고 BufferedWriter의 경우 버퍼를 잡아 놓았기 때문에 반드시 사용한 후에, flush()/ close()를 해주어야 한다.
        // close()를 하게되면, 출력 스트림을 아예 닫아버리기 때문에 한번 출력후, 다른 것도 출력하고자 한다면 flush()를 사용하면 된다.
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        // StringTokenizer 클래스는 문자열을 구분자를 이용하여 분리할 때 사용
        // 만일 BufferedReader 클래스의 메서드로 입력을 읽어들인다면 라인 단위로 읽어들일 수 밖에 없습니다
        // 꼭 BufferedReader 클래스만이 아니더라도, 스페이스 기준으로 혹은 컴마로 혹은 공백을 기준으로 문자열들을 분리한다던가,
        // 특정 문자에 따라 문자열을 나누고 싶을 때 StringTokenizer를 사용
        // 즉, 토큰은 분리된 문자열 조각으로, StringTokenizer 클래스는 하나의 문자열을 여러 개의 토큰으로 분리하는 클래스
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int findX = Integer.parseInt(st.nextToken());
        int findY = Integer.parseInt(st.nextToken());

        // ArrayList는 자바에서 기본적으로 많이 사용되는 클래스
        // ArrayList는 자바의 List 인터페이스를 상속받은 여러 클래스 중 하나
        // 일반 배열과 동일하게 연속된 메모리 공간을 사용하며 인덱스는 0부터 시작
        ArrayList<ArrayList<Integer>> a = new ArrayList<>();
        // ArrayList를 생성한 후 add() 메소드로 엘레멘트를 추가
        for (int i = 0; i <= N; i++) {
            a.add(new ArrayList<>());
        }

        int M = Integer.parseInt(br.readLine());
        // 양방향 인접리스트 구현.
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            a.get(x).add(y);
            a.get(y).add(x);
        }

        boolean[] visited = new boolean[N + 1];

        DFS(a, visited, findX, findY, 0);

        // 출력을 위해서는 out.write() 후 flush() 와 close() 를 모두 사용해야함
        // flush 는 write 에 저장된 값을 출력함과 동시에 비워주는 역할이고, close() 를 끝 마무리해주는 역할
        bw.write(ans + "\n");
        bw.flush();
        bw.close();
        br.close();
    }

}
