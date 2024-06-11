import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class Main {
	static int[][] partyRoad; //입력받은 길의 거리 넣는 배열
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken()); // 파티장의 크기
		int M = Integer.parseInt(st.nextToken()); // 인원수

		// 거리 초기값 셋팅
		partyRoad = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 1; j <= N; j++) {
				partyRoad[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// logic - 파티장의 크기 만큼 반복해서 최소 거리 구하기
		for(int mid=1; mid<=N;mid++){ // mid가 for문의 가장 바깥에 있어야하고, 작은수부터 늘려나가야 올바른 값이 나온다.
            for(int start=1;start<=N;start++){
                for(int end=1;end<=N;end++){
                    if(end==mid) continue; // end==mid일때의 값은 갱신할 필요가 없음.
                    partyRoad[start][end] = Math.min(partyRoad[start][end],partyRoad[start][mid]+partyRoad[mid][end]);
                }
            }
        }

		// 인원 셋팅
		StringJoiner str = new StringJoiner("\n");
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			int time = Integer.parseInt(st.nextToken());

			if (partyRoad[from][to] <= time) {
				str.add("Enjoy other party");
			} else {
				str.add("Stay here");
			}
		}
		
		bw.write(str.toString());		
		bw.flush();
		
		br.close();
		bw.close();
		
	}
}