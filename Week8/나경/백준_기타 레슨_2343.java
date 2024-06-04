import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2343 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력
        int N = Integer.parseInt(st.nextToken()); // 강의의 수
        int M = Integer.parseInt(st.nextToken()); // 준비된 블루레이 개수

        int[] num = new int[N];
        st = new StringTokenizer(br.readLine());
        int sum = 0;

        long start = 1;
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
            sum += num[i];
            start = Math.max(start, num[i]);
        }

        // 로직
        long end = sum;
        long answer = 0;

        while (start <= end) {
            long mid = (start + end) / 2;
            int count = 1;
            int tempSum = 0;

            for (int i = 0; i < N; i++) {
                tempSum += num[i];
                if (tempSum > mid) {
                    tempSum = num[i];
                    count++;
                }
            }

            if (count <= M) {
                answer = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        System.out.println(answer);
    }
}
