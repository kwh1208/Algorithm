import java.io.*;
import java.util.*;
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // N개의 막대 기둥이 일렬로 세워져 있다.

        // 가장 작은 창고 다각형의 면적을 구하자.

        // 1. 정렬을 할 필요가 있을듯 -> 입력이 뒤죽박죽으로 들어옴
        // 2. 고점을 기준으로
        // 3. 왼쪽에서 오른쪽으로 상승 찾기
        // 4. 오른쪽에서 왼쪽으로 상승 찾기

        int n = Integer.parseInt(br.readLine());

        int[][] arr = new int[n][2];

        int maxIndex = 0;
        int maxH = 0;

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());

            arr[i][0] = l;
            arr[i][1] = h;
        }

        // 배열 정렬하기
        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        for (int i = 0; i < n; i++) {
            if (maxH < arr[i][1]) {
                maxH = arr[i][1];
                maxIndex = i;
            }
        }

//        int startL = arr[0][0];
        int startH = arr[0][1];

//        int endL = arr[n-1][0];
        int endH = arr[n-1][1];

        int sum = 0;
//        Deque<Integer> stack = new ArrayDeque<>();

        int heightStandard = startH;
        // 2부터 7까지
        // 왼쪽 4 -> 6 -> 10 기둥이 커지는 구간 계산
        for (int i = 0; i < maxIndex; i++) {
            sum += heightStandard * (arr[i+1][0] - arr[i][0]);

            if (heightStandard < arr[i+1][1]) {
                heightStandard = arr[i+1][1];
            }

        }


        // 오른쪽 10 -> 8 기둥이 작아지는 구간 계산
        heightStandard = endH;
        for (int i = n-1; i > maxIndex; i--) {
            sum += heightStandard * (arr[i][0] - arr[i-1][0]);

            if (heightStandard < arr[i-1][1]) {
                heightStandard = arr[i-1][1];
            }

        }

        sum += maxH;

        bw.write(sum+"");

        bw.flush();
        br.close();
        bw.close();
    }

}