// 영선이는 매우 기쁘기 때문에 효빈이에게 스마일 이뫼콘을 S개 보내려고 한다
// 영선이는 이미 화면에 이모티콘 1개를 입력했다 
// 이제 다음과 같은 3가지 연산만 사용해서 이모티콘을 S개 만들어 보려고 한다 
// 1. 화면에 있는 이모티콘을 모두 복사해서 클립보드에 저장
// 2. 클립보드에 있는 모든 이모티콘을 화면에 붙여넣기 
// 3. 화면에 있는 이모티콘 중 하나를 삭제 
// 모든 연산은 1초가 걸린다
// 클립보드에 이모티콘을 복사하면 이전에 클립보드에 있던 내용은 덮어쓰기가 된다
// 클립보드가 비어있는 상태에는 붙여넣기를 할 수 없다
// 일부만 클립보드에 복사할 수는 없다
// 또한 클립보드에 있는 이모티콘 중 일부를 삭제할 수 없다
// 화면에 이모티콘을 붙여넣기 하면 클립보드에 있는 이모티콘 개수가 화면에 추가
// 영선이가 S개 이모티콘을 화면에 만드는데 걸리는 시간의 최솟값을 구해라 

import java.io.*;
import java.util.*;

class emoticon {
    int clipboard = 0;
    int screen = 0;
    int second = 0;

    emoticon(int clipboard, int screen, int second) {
        this.clipboard = clipboard;
        this.screen = screen;
        this.second = second;
    }
}

public class Emoticon_bj {
    // 걸리는 시간의 최소값을 구하는 문제 -> BFS
    // BFS 탐색을 하면서 조건에 맞게 1,2,3번을 진행
    // 현재 개수가 S와 같아진다면 BFS 탐색 종료 && 현재 시간 출력
    // 탐색을 하면서 계속 같은 구간을 반복하지 않도록 visited 배열 사용
    // 각각의 인덱스가 의미하는 바는 [clipboard에 현재 복사된 이모티콘 개수][현재 화면에 출력된 총 이모티콘 개수]
    public static boolean[][] visited = new boolean[1001][1001];

    public static Queue<emoticon> q = new LinkedList<>();
    public static StringBuilder sb = new StringBuilder();
    // 각각의 조건을 보면
    // 1. 화면에 있는 이모티콘을 모두 복사해서 클립보드에 저장
    // -> 화면에 있는 이모티콘을 모두 클립보드에 복사하기 때문에 시간이 증가
    // -> 현재 total 개수를 clipboard 로 갱신해 큐에 담아주면 된다. && 시간은 현재 시간 +1
    // 2. 클립보드에 있는 모든 이모티콘을 화면에 붙여넣기
    // -> clipboard 개수는 그대로 놔두고 현재 total 개수를 total + clipboard로 갱신해 큐에 담아준다
    // -> 시간은 현재 시간 +1
    // 3. 화면에 있는 이모티콘 중 하나 삭제
    // -> clipboard 개수는 그대로 놔두고 현재 total 개수를 total -1 로 갱신해 큐에 담아준다
    // -> 시간은 현재 시간 + 1

    public static void bfs(int end) {

        // 각 변수는 클립보드에 있는 이모티콘 수 , 화면에 있는 이모티콘 수 , 걸린시간 의미
        q.add(new emoticon(0, 1, 0));
        // 해당문제를 해결하기 위해 방문 여부 중요
        // 행 = 클립보드에 있는 이모티콘 수
        // 열 = 화면에 있는 이모티콘 수
        visited[0][1] = true;

        while (!q.isEmpty()) {
            emoticon current = q.poll();
            if (current.screen == end) {
                sb.append(current.second);
                break;
            }
            // 1. 화면에 있는 이모티콘을 모두 복사해 클립보드에 덮어쓰기
            // 1개를 덮어쓰고 큐에 삽입 -> 또 1개를 덮어쓰고 큐에 삽입 : 반복
            if (!visited[current.screen][current.screen]) {
                visited[current.screen][current.screen] = true;
                q.add(new emoticon(current.screen, current.screen, current.second + 1));
            }

            // 2. 클립보드에서 불러와 화면에 붙여넣기
            // 0개를 붙여 넣는건 의미 없어 시간만 늘어나므로 패스
            // 붙여넣었을 때 문제의 조건인 2000개를 넘어가면 안되므로 패스
            if (current.clipboard != 0 && current.screen + current.clipboard <= 2000) {
                if (!visited[current.screen + current.clipboard][current.clipboard]) {
                    visited[current.screen + current.clipboard][current.clipboard] = true;
                    q.add(new emoticon(current.clipboard, current.screen + current.clipboard, current.second + 1));
                }
            }
            // 3. 화면에 있는 이모티콘 중 하나를 삭제
            // 화면에 이모티콘이 없으면 삭제할 수 없으므로
            if (current.screen > 0) {
                // 삭제하여 만들어진 상태가 이미 있는 상태면 더 오래 걸리는 방법이므로 패스
                if (!visited[current.screen - 1][current.clipboard]) {
                    visited[current.screen - 1][current.clipboard] = true;
                    q.add(new emoticon(current.clipboard, current.screen - 1, current.second - 1));
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int s = Integer.parseInt(br.readLine());
        bfs(s);
        bw.write(sb + "\n");
        bw.flush();
        bw.close();
        br.close();
    }
}
