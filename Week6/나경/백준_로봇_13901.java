import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


class Room {
	int width, height; //방의 크기
	int[][] room;
	boolean[][] visited;
	
	Room(int width, int height) {
		this.width = width;
		this.height = height;
		
		room = new int[width][height];
		visited = new boolean[width][height];
	}
	
	void setBlocks(int x, int y) { //방에 장애물 셋팅
		room[x][y] = 1;
	}
}

class Robot {
	int x, y; //로봇이 위치한 좌표
	int[] arrowArr = new int[4]; //사용자가 정의한 이동 방향
	int arrowIndex = 0;
	
	Robot(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	void setArrow(String line) {
		int i = 0;
		for(String str : line.split(" ")) {
			this.arrowArr[i++] = Integer.parseInt(str);
		}		
	}
	
}

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		//입력
		Room room = new Room(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		
		int k = Integer.parseInt(br.readLine()); //장애물의 개수		
		for(int i = 0; i<k; i++) {
			st = new StringTokenizer(br.readLine());
			room.setBlocks(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		st = new StringTokenizer(br.readLine());
		Robot robot = new Robot(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		robot.setArrow(br.readLine());
		
		//구현
		room.visited[robot.x][robot.y] = true; //처음 로봇이 있는 자리를 방문 배열에 넣기
		int cantgo = 0;
		while(cantgo < 4) {
			boolean flag = false;
			int arrow = robot.arrowArr[robot.arrowIndex]; //1은 위 방향, 2은 아래 방향, 3은 왼쪽 방향, 4는 오른쪽 방향
			
			int x = robot.x;
			int y = robot.y;
			
			switch(arrow) {
				case 1: x = x-1; break;
				case 2: x = x+1; break;
				case 3: y = y-1; break;
				case 4: y = y+1; break;
			}
			
			if(x >= 0 && y >= 0 && x < room.width && y < room.height) {
				if(room.room[x][y] == 0 && room.visited[x][y] == false) {
					flag = true;
					room.visited[x][y] = true;
					robot.x = x;
					robot.y = y;
					cantgo = 0;
				}
			}
			
			if(!flag) {
				cantgo++;
				robot.arrowIndex = (robot.arrowIndex+1)%4;
			}
		}

		System.out.println(robot.x + " " + robot.y);
		
	}
}
