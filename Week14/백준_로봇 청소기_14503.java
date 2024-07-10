import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	public static int n;
	public static int m;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int answer = 0;
		
		//데이터 입력
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		int[][] room = new int[n][m];
		int[][] visited = new int[n][m];
		
		st = new StringTokenizer(br.readLine());
		Robot robot;
		{
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int direction = Integer.parseInt(st.nextToken());
			robot = new Robot(x,y,direction);
		}		
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 구현
		while(true) {

			//현재 칸이 청소되지 않은 경우
			if(visited[robot.x][robot.y] == 0) {
				visited[robot.x][robot.y] = 1;
				answer++;
			}
			
			int i = 0;
			for(i=0; i<4; i++) {
				robot.rotate();
				robot.go();
				
				if(visited[robot.x][robot.y] == 0 && room[robot.x][robot.y] == 0 ) { //청소되지 않은 빈칸인 경우
					break;	
				}else {
					robot.back();
				}
			}
			
			if(i == 4) {
				robot.back();
				
				if(room[robot.x][robot.y] == 1) {//벽이면
					break;
				}
			}
		}
		
		System.out.println(answer);
		
	}
}

class Robot {
	int x;
	int y;
	int direction;
	
	Robot(int x, int y, int direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
	}
	
	public void rotate() { //반시계 방향으로 90도 회전
		this.direction = (this.direction+3) % 4;
	}
	
	public void go() { //한 칸 전진
		if(this.direction == 0) {
			this.x = this.x-1;
		}else if(this.direction == 1) {
			this.y = this.y + 1;
		}else if(this.direction == 2) {
			this.x = this.x + 1;
		}else {
			this.y = this.y - 1;
		}
	}
	
	public void back() { //한 칸 후진
		if(this.direction == 0) {
			this.x = this.x+1;
		}else if(this.direction == 1) {
			this.y = this.y - 1;
		}else if(this.direction == 2) {
			this.x = this.x - 1;
		}else {
			this.y = this.y + 1;
		}
	}
}