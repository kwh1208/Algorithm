package Week6.조영호;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*입력 */
// 첫 번째 줄에는 방의 크기 R, C(3 ≤ R, C ≤ 1,000)가 입력된다. 
// 두 번째 줄에는 장애물의 개수 k(0 ≤ k ≤ 1,000)가 입력된다. 
// 다음 k개의 줄에는 각 장애물 위치 br(0 ≤ br ≤ R – 1), bc(0 ≤ bc ≤ C - 1)가 주어진다. 
// 그 다음 순서대로 로봇의 시작 위치 sr(0 ≤ sr ≤ R – 1), sc(0 ≤ sc ≤ C - 1)와 
// 이동 방향의 순서(총 4개가 입력되는데 1은 위 방향, 2은 아래 방향, 3은 왼쪽 방향, 4는 오른쪽 방향을 나타낸다)가 한 줄씩 입력된다. 
// 로봇의 시작위치에 장애물이 있는 경우는 없다.

/*출력 */
// 로봇의 마지막 위치 r, c를 출력한다.

public class Robot_bj {
	static int R, C, sr, sc, count;
	static int K;
	static int[][] map;
	static int[][] deltas = { { 0, 0 }, { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } }; // 1: 상, 2: 하, 3: 좌, 4: 우
	static int[] dir;

	public static boolean isIn(int nr, int nc) {
		return nr >= 0 && nr < R && nc >= 0 && nc < C;
	}

	public static void loop(int d) {
		while (true) {
			// nr = 로봇의 시작위치 + 이동방향
			int nr = sr + deltas[dir[d]][0];
			// nc = 로봇의 시작위치 + 이동방향
			int nc = sc + deltas[dir[d]][1];
			// 로봇의 위치가 방의 크기를 벗어나지 않고 장애물이 없을때
			if (isIn(nr, nc) && map[nr][nc] == 0) {
				map[nr][nc] = 1;
				sr = nr;
				sc = nc;
				count = 0;
			} else { // 장애물이 있을때 다음 방향
				d = (d + 1) % 4;
				count++;
			}
			// 방향 다 확인하면 break 문으로 나와
			if (count == 4) {
				break;
			}
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		// 방의 크기 R, C(3 ≤ R, C ≤ 1,000) 입력
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];

		// 장애물의 개수 k(0 ≤ k ≤ 1,000) 입력
		K = Integer.parseInt(br.readLine());
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			// 장애물 위치
			map[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
		}

		// 로봇의 시작 위치 sr(0 ≤ sr ≤ R – 1), sc(0 ≤ sc ≤ C - 1)
		st = new StringTokenizer(
				br.readLine());
		sr = Integer.parseInt(st.nextToken());
		sc = Integer.parseInt(st.nextToken());

		// 로봇 초기 위치 방문 표시
		map[sr][sc] = 1;

		// 방향입력
		for (int i = 0; i < 4; i++) {
			st = new StringTokenizer(br.readLine());
			dir[i] = Integer.parseInt(st.nextToken());
		}
		// 입력 완료
		count = 0;

		loop(0);

		// 로봇의 마지막 위치 출력
		System.out.println(sr + " " + sc);
	}
}
