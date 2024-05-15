
import java.io.*;
import java.util.*;
public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][2];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i][0] = Integer.parseInt(st.nextToken());
            arr[i][1] = Integer.parseInt(st.nextToken());
        }

        // 최소의 강의실을 사용해서 모든 수업을 가능하게 해야
        // 수업이 끝난후에 다음 수업을 시작 가능

        Arrays.sort(arr, new Comparator<int[]>() {
            public int compare(int[] o1, int[] o2){
                if(o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            }
        });

        // 1 3 //2
        // 2 4 //2
        // 3 5 //2

        // 강의의 끝나는 시간을 넣는 PQ
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // 맨첫번째 강의 끝나는 시간 넣기
        pq.offer(arr[0][1]);

        for(int i = 1; i < N; i++) {
            if(pq.peek() <= arr[i][0]) {
                pq.poll();
            }

            pq.offer(arr[i][1]);
        }

        bw.write(pq.size() + "");

        bw.flush();
        bw.close();
    }

}