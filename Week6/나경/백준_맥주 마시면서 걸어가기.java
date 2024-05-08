import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.StringJoiner;
import java.util.StringTokenizer;

class Position {
	int x;
	int y;
	boolean visited; //방문 여부 체크
	
	Position(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	public static boolean cangoDestination(Position start, List<Position> conveniences, Position destination) {
		boolean flag = false;
		
		if(Math.abs(destination.x-start.x) + Math.abs(destination.y-start.y) <=1000) {
			return true;
		}
		
		for(Position con : conveniences) {
			if(con.visited == false && Math.abs(con.x-start.x) + Math.abs(con.y-start.y) <=1000) {
				con.visited = true;
				flag = cangoDestination(con, conveniences, destination);
				
				if(flag == true) {
					break;
				}
			}
		}
		
		
		return flag;
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		StringJoiner answer = new StringJoiner("\n");
		
		int T = Integer.parseInt(st.nextToken());
		
		for(int t=0; t<T; t++) {
			//입력
			int n = Integer.parseInt(br.readLine());
			
			st = new StringTokenizer(br.readLine());		
			Position house = new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())); //상근이 집 좌표
			
			List<Position> conveniences = new ArrayList<>();
			for(int i=0; i<n; i++) { //편의점 좌표
				st = new StringTokenizer(br.readLine());
				Position convenience = new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())); //상근이 집 좌표
				conveniences.add(convenience);
			}
			
			st = new StringTokenizer(br.readLine());		
			Position destination = new Position(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())); //상근이 집 좌표
	
			//로직
			boolean flag = cangoDestination(house, conveniences, destination);
			
			answer.add(flag? "happy" : "sad");
		}
		
		System.out.println(answer);
		
	}
}
