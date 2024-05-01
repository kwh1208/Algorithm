package Week5.조영호;
/* 문제 */
// 크기가 n x n인 2차원 정수 배열 X가 있다. (n은 홀수)
// X를 45° 의 배수만큼 시계방향 혹은 반시계방향으로 돌리려고 한다. 
// X를 시계 방향으로 45° 돌리면 아래와 같은 연산이 동시에 X에 적용되어야 한다:

// X의 주 대각선을 ((1,1), (2,2), …, (n, n)) 가운데 열 ((n+1)/2 번째 열)로 옮긴다.
// X의 가운데 열을 X의 부 대각선으로 ((n, 1), (n-1, 2), …, (1, n)) 옮긴다. 
// X의 부 대각선을 X의 가운데 행 ((n+1)/2번째 행)으로 옮긴다.
// X의 가운데 행을 X의 주 대각선으로 옮긴다.
// 위 네 가지 경우 모두 원소의 기존 순서는 유지 되어야 한다.
// X의 다른 원소의 위치는 변하지 않는다.
// 반시계 방향으로 45° 돌리는 경우도 위와 비슷하게 정의된다.

// 예를 들어, 아래 그림 중앙에 5x5 배열 X가 있고, 이 배열을 시계방향 혹은 반시계방향으로 45° 돌렸을 때의 결과가 우측 그리고 좌측에 있다. 
// 굵은 원소는 주 대각선 / 중간 열 / 부 대각선 / 중간 행에 위치한 원소이다.
// 입력으로 2차원 배열 X와 어느 방향으로 몇 도 회전할지 입력 받아, 그 결과를 출력하는 프로그램을 작성하시오.

/* 입력 */
// 첫 줄에 테스트 케이스의 수 T가 주어진다 (1 ≤ T ≤ 10).
// 각 테스트 케이스에 대해: 첫 줄에 배열의 크기를 나타내는 n (1 ≤ n < 500, n은 홀수) 그리고 각도 d가 주어진다. 
// d는 0 ≤ |d| ≤ 360 을 만족하며 |d| 는 45의 배수이다. 
// d가 양수이면 시계방향으로 d° 돌려야 하고, 음수이면 반시계방향으로 |d|° 돌려야 한다. 
// 다음 n줄에 걸쳐 각 줄에 n개의 정수가 공백으로 구분되어 주어진다 (X의 원소들을 나타낸다). 각 값은 1 이상 1,000,000 이하의 정수이다.

/* 출력 */
// 각 테스트 케이스에 대해 회전 연산을 마친 후 배열의 상태를 출력한다. 
// n줄에 걸쳐 각 줄에 n개의 정수를 공백으로 구분하여 출력한다. 

import java.util.*;
import java.io.*;

public class RotationArray_bj {
    static int n,d;
	static int[][]arr;
	static StringBuilder sb = new StringBuilder(); // 정답저장
	
    // stringbuilder에 출력할 배열을 넣어놓은 메소드
	private static void makeAnswer() {
		for (int i = 0; i < n; i++) {
			for(int j=0;j<n;j++) {
				sb.append(arr[i][j]+" ");
			}
			sb.append("\n");
		}
	}

    //배열위치
    static int x,y; 
    //시작점, 끝점
	static int start,end; 
	
	//반시계방향으로 회전하는 메소드
	private static void antiClock(int jump) {		
		x = y = start;
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
                //처음에 이동하는 칸 수
				int jump = n/2;
                // 끝점 
				end = n-1; 

				for(int j=0;j<n/2;j++) {
                    // 시작점					
					start = j; 
					
                    //반시계방향
					if(d<0) { 
						antiClock(jump);
					}
                    //시계방향
					else { 
						clock(jump);
					}
					 
                    // 이동하는 칸수가 줄어든다
					jump--;
                    // 배열의 안쪽으로 들어온다 
					end--; 
                    // 배열의 안쪽으로 들어온다
					start++; 
				}
			}
            // 정답저장
			makeAnswer(); 
		}
		System.out.println(sb);
	}

	

	
}
