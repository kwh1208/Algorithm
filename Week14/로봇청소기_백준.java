import java.io.*;
import java.util.*;
public class Main {

	static int[] dx = {-1, 0, 1, 0};
	static int[] dy = {0, 1, 0, -1};
	static int[][] arr;
	static int count = 1;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		// 문제
		// 로봇 청소기와 방의 상태가 주어졌을때 청소하는 영역의 개수를 구하는 프로그램
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		// 3째줄부터 N개의 줄에 각 장소의 상태를 나타낸다.
		// 값이 0인경우 청소되지 않은 빈 칸, 1인 경우 벽이 있는 것이다.

		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());

		arr = new int[N][M];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		robotCleaning(r, c, d);

		bw.write(count+"");

		bw.flush();
		bw.close();
	}

	public static void robotCleaning(int x, int y, int direction) {
		arr[x][y] = 2;

		for (int i = 0; i < 4; i++) {
			direction = (direction+3) % 4; // 북 -> 서 -> 남 -> 동

			int nx = x + dx[direction];
			int ny = y + dy[direction];

			if (nx < 0 || ny < 0 || nx >= arr.length || ny >= arr[0].length) {
				continue;
			}

			if (arr[nx][ny] == 0) {
				count++;
				robotCleaning(nx,ny, direction);
				return;
			}
		}

		int d = (direction + 2) % 4; // 반대 방향 후진
		int bx = x + dx[d];
		int by = y + dy[d];
		if (bx >= 0 && by >= 0 && bx < arr.length && by < arr[0].length && arr[bx][by] != 1) {
			robotCleaning(bx, by, direction);
		}
	}
}
