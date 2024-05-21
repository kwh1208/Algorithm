import java.io.*;
import java.util.*;

public class Main11000 {

    static class Point implements Comparable<Point>{
        int x, y;
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            return this.y - o.y > 0 ? 1 : -1;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token;

        int N = Integer.parseInt(br.readLine());
        int[][] lesson = new int[N][2];

        // 모든 강의를 판단하며
        // 각 강의실의 마지막 수업의 종료시간을 넣을 것이기 때문에
        // 우선순위 큐의 사이즈가 곧 강의실의 개수가 된다.
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int i=0; i<N; i++) {
            token = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(token.nextToken());
            int t = Integer.parseInt(token.nextToken());
            lesson[i][0] = s;
            lesson[i][1] = t;
        }

        // 시작 시간 정렬 후 -> 종료 시간 정렬
        Arrays.sort(lesson, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0];
            }
        });

        // 종료시간을 담는다.
        pq.offer(lesson[0][1]);

        for(int i=1; i<N; i++) {
            // 다음 수업의 시작 시간(= lesson[i][1])이 이전 수업의 종료 시간보다 크거나 같으면
            if(lesson[i][0] >= pq.peek()) {
                // 해당 강의실에 이어서 수업할 수 있다.
                // 다음 수업 종료 시간을 해당 강의실의 마지막 시간으로 갱신하기 위해
                // 기존 강의 종료 시간을 제거한다.
                pq.poll();
            }
            // 강의실의 마지막 종료 시간을 추가한다.
            pq.offer(lesson[i][1]);
        }

        bw.write(pq.size() + "");
        bw.close();
    }
}
//https://sookr5416.tistory.com/276