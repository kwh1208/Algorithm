
import java.io.*;
import java.util.*;
public class Main {

    static int n;
    static int[][] arr;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 송도에 사는 상근이와 친구들은 송도에서 열리는 펜타포트 락 페스티벌에 가려고한다.
        // 올해는 맥주를 마시면서 걸어가기로 함
        // 출발은 상근이네 집에서 하고, 맥주 한 박스를 들고 출발
        // 맥주 한 박스에는 맥주 20개가 들어있음
        // 상근이의 집에서 페스티벌이 열리는 곳은 매우 먼 거리

        // 편의점에 들렸을때 빈 병을 버리고 새 맥주 병을 살 수 있다.

        // 하지만 박스에 들어있는 맥주병은 20병을 넘을 수 없음


        // 첫째 줄에 테스트 케이스의 개수 t
        // 각 테스트 케이스의 첫째 줄에는 맥주를 파는 편의점의 개수 n
        // 다음 n+2개 줄에는 상근이네 집, 편의점, 락페 좌표
        // 송도는 직사각형 모양

        // 테스트 케이스의 수
        int t = Integer.parseInt(br.readLine());

        for(int q = 0; q < t; q++) {
            // 편의점의 개수 n
            n = Integer.parseInt(br.readLine());

            arr = new int[n+2][2];
            visit = new boolean[n+2];

            for(int i = 0 ; i < n+2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                arr[i][0] = Integer.parseInt(st.nextToken());
                arr[i][1] = Integer.parseInt(st.nextToken());
            }

            // arr[0] - > 상근이 집

            bw.write(bfs(arr[0][0], arr[0][1]) + "\n");

        }


        bw.flush(); // 남이있는 데이터 모두 출력
        bw.close();
    }

    public static String bfs(int x, int y) {
        Queue<Dot> queue = new LinkedList<>();

        queue.add(new Dot(x,y));
        visit[0] = true;

        while(!queue.isEmpty()) {
            Dot dot = queue.poll();

            for(int i = 0; i < arr.length; i++) {
                // 거리 연산(빼기 연산)에는 절대값을 꼭 넣어주자
                if(!visit[i] && (Math.abs(arr[i][0] - dot.x) + Math.abs(arr[i][1] - dot.y)) <= 1000) {
                    queue.add(new Dot(arr[i][0], arr[i][1]));
                    visit[i] = true;
                }
            }
        }

        // 맨끝 visit 즉, festival 도착 여부만 확인하면 된다
        if(visit[n+1]) {
            return "happy";
        } else {
            return "sad";
        }
    }
}

class Dot {
    int x;
    int y;

    public Dot(int x, int y) {
        this.x = x;
        this.y = y;
    }
}