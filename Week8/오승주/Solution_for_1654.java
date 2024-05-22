import java.io.*;
import java.util.*;

public class Solution_for_1654 {
    static int K, N;
    static long max;
    static long[] length;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        length = new long[K];
        for (int k = 0; k < K; k++) {
            length[k] = Integer.parseInt(br.readLine());
        }

        max = Arrays.stream(length).max().getAsLong();

        /*
         * 해당 문제에서의 답의 분포는 T~F이기 때문에
         * low는 답의 조건이 되는 값으로 설정해주고
         * high는 답의 조건이 될 수 없는 값으로 설정해주어야 함.
         * high = max; 로 설정을 하게 된다면 max는 답의 조건이 되는 값이기 때문에 초기의 low와 high의 값에 적절하지 않음(이분탐색 범위 적절 X)
         */
        long low = 1;
        long high = max + 1;                // high = max; 로 지정 -> "틀렸습니다" 출력
        long mid = (low + high) / 2;

        while (low + 1 < high) {
            if (N <= getCount(mid)) {
                low = mid;
            }
            else high = mid;

            mid = (low + high) / 2;
        }

        System.out.println(low);

        /*
        do {

            if (N <= getCount(mid)) {
                low = mid + 1;
            }
            else high = mid - 1;

            mid = (low + high) / 2;

        } while(low <= high);

        System.out.println(mid);
        */
        
    }

    public static int getCount(long mid) {
        int count = 0;
        for (int i = 0; i < K; i++) {
            count += (length[i] / mid);
        }

        return count;
    }
}