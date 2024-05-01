import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj_17276 {
	static int n,d;
	static int[][]arr;
	static StringBuilder sb = new StringBuilder(); // 정답저장
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc=0;tc<T;tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			// n * n 배열을 d도 만큼 돌리기
			n = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			
			//배열입력받기
			arr = new int[n][n];
			for (int i = 0; i < n; i++) {
				st= new StringTokenizer(br.readLine());
				for(int j=0;j<n;j++) {
					arr[i][j]=Integer.parseInt(st.nextToken());
				}
			}

			// 360도는 회전할 필요 없으므로 %360 한다 
			// 45도만큼 돌리기
			for(int i=0;i<(Math.abs(d)%360)/45;i++) {
				int jump = n/2; //처음에 이동하는 칸 수
				end = n-1; // 끝점

				for(int j=0;j<n/2;j++) {					
					start=j; // 시작점
					
					if(d<0) { //반시계방향
						antiClock(jump);
					}
					else { //시계방향
						clock(jump);
					}
					 
					jump--; // 이동하는 칸수가 줄어든다
					end--; // 배열의 안쪽으로 들어온다
					start++; // 배열의 안쪽으로 들어온다
				}
			}
			makeAnswer(); // 정답저장
		}
		System.out.println(sb);
	}

	// stringbuilder에 출력할 배열을 넣어놓은 메소드
	private static void makeAnswer() {
		for (int i = 0; i < n; i++) {
			for(int j=0;j<n;j++) {
				sb.append(arr[i][j]+" ");
			}
			sb.append("\n");
		}
	}

	static int x,y; //배열위치
	static int start,end; //시작점, 끝점
	
	//반시계방향으로 회전하는 메소드
	private static void antiClock(int jump) {		
		x=y=start;
		int tmp = arr[x][y];
		for(int j=y;j<=end-jump;j+=jump) {
			arr[x][j]=arr[x][j+jump];
		}
		y=end;
		for(int i=x;i<=end-jump;i+=jump) {
			arr[i][y]=arr[i+jump][y];
		}
		x=end;
		for(int j=y;j>=start+jump;j-=jump) {
			arr[x][j]=arr[x][j-jump];
		}
		y=start;
		for(int i=x;i>start+jump;i-=jump) {
			arr[i][y]=arr[i-jump][y];
		}
		x=start+jump;
		arr[x][y]=tmp;
		
	}

	//시계방향으로 회전하는 메소드
	private static void clock(int jump) {
		x=y=start;
		int tmp = arr[x][y];
		for(int i=x;i<=end-jump;i+=jump) {
			arr[i][y] = arr[i+jump][y];
		}
		x = end;
		for(int j=y;j<=end-jump;j+=jump) {
			arr[x][j] = arr[x][j+jump];
		}
		y = end;
		for(int i=x;i>=start+jump;i-=jump) {
			arr[i][y] = arr[i-jump][y];
		}
		x = start;
		for(int j=y;j>start+jump;j-=jump) {
			arr[x][j] = arr[x][j-jump];
		}
		y = start+jump;
		arr[x][y]=tmp;
	}

}