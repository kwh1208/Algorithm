import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        int answer = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[][] lectures = new int[N][2];

        int[] roomArr = new int[N];
        int roomCnt = 0;

        for(int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            lectures[i][0] = Integer.parseInt(st.nextToken());
            lectures[i][1] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(lectures, (o1, o2) -> (o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]));

        roomArr[0] = lectures[0][1]; //첫번째 강의실에 첫번째 수업이 끝나는 시간 저장
        roomCnt++;
        
        for(int i=1; i<N; i++) {
            int j=0;
            for(j=0; j<roomCnt; j++) {
                if(roomArr[j] <= lectures[i][0]) {
                    break;
                }
            }

            roomArr[j] = lectures[i][1];
            if(j == roomCnt) {
                roomCnt++;
            }
        }

        System.out.println(roomCnt);
    }
}
