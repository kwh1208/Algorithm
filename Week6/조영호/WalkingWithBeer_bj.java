package Week6.조영호;
/*문제*/
// 송도에 사는 상근이와 친구들은 송도에서 열리는 펜타포트 락 페스티벌에 가려고 한다. 
// 올해는 맥주를 마시면서 걸어가기로 했다. 출발은 상근이네 집에서 하고, 맥주 한 박스를 들고 출발한다. 
// 맥주 한 박스에는 맥주가 20개 들어있다. 
// 목이 마르면 안되기 때문에 50미터에 한 병씩 마시려고 한다. 
// 즉, 50미터를 가려면 그 직전에 맥주 한 병을 마셔야 한다.

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Queue;
import java.util.StringTokenizer;

// 상근이의 집에서 페스티벌이 열리는 곳은 매우 먼 거리이다. 
// 따라서, 맥주를 더 구매해야 할 수도 있다. 
// 미리 인터넷으로 조사를 해보니 다행히도 맥주를 파는 편의점이 있다. 
// 편의점에 들렸을 때, 빈 병은 버리고 새 맥주 병을 살 수 있다. 
// 하지만, 박스에 들어있는 맥주는 20병을 넘을 수 없다. 편의점을 나선 직후에도 50미터를 가기 전에 맥주 한 병을 마셔야 한다.

// 편의점, 상근이네 집, 펜타포트 락 페스티벌의 좌표가 주어진다. 
// 상근이와 친구들이 행복하게 페스티벌에 도착할 수 있는지 구하는 프로그램을 작성하시오.

/* 입력 */
// 첫째 줄에 테스트 케이스의 개수 t가 주어진다. (t ≤ 50)

// 각 테스트 케이스의 첫째 줄에는 맥주를 파는 편의점의 개수 n이 주어진다. (0 ≤ n ≤ 100).

// 다음 n+2개 줄에는 상근이네 집, 편의점, 펜타포트 락 페스티벌 좌표가 주어진다. 각 좌표는 두 정수 x와 y로 이루어져 있다. (두 값 모두 미터, -32768 ≤ x, y ≤ 32767)

// 송도는 직사각형 모양으로 생긴 도시이다. 두 좌표 사이의 거리는 x 좌표의 차이 + y 좌표의 차이 이다. (맨해튼 거리)

/* 출력 */
// 각 테스트 케이스에 대해서 상근이와 친구들이 행복하게 페스티벌에 갈 수 있으면 "happy", 중간에 맥주가 바닥나서 더 이동할 수 없으면 "sad"를 출력한다. 
public class WalkingWithBeer_bj {
    static class Point {
		int r, c;

		public Point(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static int TC, N;
	static boolean visited[];
	static int dr[] = { -1, 1, 0, 0 };
	static int dc[] = { 0, 0, -1, 1 };
	static String answer;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		TC = Integer.parseInt(br.readLine());

		for (int t = 1; t <= TC; t++) {
			N = Integer.parseInt(br.readLine());
			ArrayList<Point> stores = new ArrayList<Point>();
			Queue<Point> q = new ArrayDeque<Point>();
			visited = new boolean[N];
			answer = "sad";

			st = new StringTokenizer(br.readLine());
			int startC = Integer.parseInt(st.nextToken());
			int startR = Integer.parseInt(st.nextToken());
			// 집 좌표 q에 넣기
			q.add(new Point(startR, startC));

			// 편의점 좌표 리스트에 넣기
			for (int i = 0; i < N; i++) {

				st = new StringTokenizer(br.readLine());
				int c = Integer.parseInt(st.nextToken());
				int r = Integer.parseInt(st.nextToken());
				stores.add(new Point(r, c));
			}
			st = new StringTokenizer(br.readLine());
			int endC = Integer.parseInt(st.nextToken());
			int endR = Integer.parseInt(st.nextToken());

			// bfs
			while (!q.isEmpty()) {
				Point cur = q.poll();
				
				// 현재 지점 (집 or 편의점) 에서 도착지점까지 갈수있으면 answer 설정 후 break 
				if (Math.abs(cur.r - endR) + Math.abs(cur.c - endC) <= 1000) {
					answer = "happy";
					break;
				}
				
				for (int i = 0; i < stores.size(); i++) {
					Point next = stores.get(i);
					
					//이미 방문한 편의점이면 패스 
					if(visited[i]) continue;
					
					//다음 편의점이  현재 지점(집 or 편의점)에서 갈수 있는 거리이면 방문체크하고 queue에 담기 
					if(Math.abs(cur.r-next.r)+Math.abs(cur.c-next.c)<=1000) {
						visited[i]=true;
						q.add(new Point(next.r, next.c));
					}
				}

			}
			System.out.println(answer);

		}
	}
}
