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

        long low = 1;
        long high = max;
        long mid = (low + high) / 2;

        do {

            if (N <= getCount(mid)) {
                low = mid+1;
            }
            else high = mid - 1;

            mid = (low + high) / 2;

        } while(low <= high);

        System.out.println(mid);
    }

    public static int getCount(long mid) {
        int count = 0;
        for (int i = 0; i < K; i++) {
            count += (length[i] / mid);
        }

        return count;
    }
}