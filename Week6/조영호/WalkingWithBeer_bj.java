package Week6.조영호;
/*문제*/

// 송도에 사는 상근이와 친구들은 송도에서 열리는 펜타포트 락 페스티벌에 가려고 한다. 
// 올해는 맥주를 마시면서 걸어가기로 했다. 출발은 상근이네 집에서 하고, 맥주 한 박스를 들고 출발한다. 
// 맥주 한 박스에는 맥주가 20개 들어있다. 
// 목이 마르면 안되기 때문에 50미터에 한 병씩 마시려고 한다. 
// 즉, 50미터를 가려면 그 직전에 맥주 한 병을 마셔야 한다.
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

import java.util.*;
import java.io.*;

public class WalkingWithBeer_bj {
	// 결과
	public static boolean[] result;
	// 방문 여부 체크
	public static boolean[] visited;

	// 지점 클래스 선언
	public static class Point {
		int x;
		int y;

		public Point(int a, int b) {
			x = a;
			y = b;
		}
	}

	// DFS탐색
	public static void DFS(Point now, ArrayList<Point> storeArr, Point festival, int current) {
		// 페스티벌이 1000미터 내에 있는 지 확인
		// 맥주 한 병당 50미터 이동할 수 있고 20병 있으므로.
		if (Math.abs(now.x - festival.x) + Math.abs(now.y - festival.y) <= 1000) {
			result[current] = true;
			return;
		}

		// 편의점 위치 확인
		for (int i = 0; i < storeArr.size(); i++) {
			Point store = storeArr.get(i);

			// 방문 안 한 편의점인 경우
			if (visited[i] == false) {
				// 1000미터 이내에 있다면 DFS 탐색 실행
				if (Math.abs(now.x - store.x) + Math.abs(now.y - store.y) <= 1000) {
					visited[i] = true;
					DFS(store, storeArr, festival, current);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// t : 테스트 케이스의 갯수
		int t = Integer.parseInt(br.readLine());
		result = new boolean[t];

		StringTokenizer st;

		for (int i = 0; i < t; i++) {
			// n : 맥주를 파는 편의점의 갯수
			int n = Integer.parseInt(br.readLine());

			// home : 상근이 집 위치
			st = new StringTokenizer(br.readLine());
			Point home = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

			// store : 편의점 위치
			ArrayList<Point> store = new ArrayList<>();
			for (int j = 0; j < n; j++) {
				st = new StringTokenizer(br.readLine());

				store.add(new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}

			// festival : 축제 위치
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());

			Point festival = new Point(x, y);

			visited = new boolean[n];

			// DFS탐색 시작
			DFS(home, store, festival, i);
		}

		// 결과 출력
		for (int i = 0; i < t; i++) {
			if (result[i])
				System.out.println("happy");
			else
				System.out.println("sad");
		}
	}
}
