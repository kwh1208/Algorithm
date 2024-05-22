import java.io.*;
import java.util.*;

public class Solution_for_2343 {
    static int N, M;
    static long[] videos;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        videos = new long[N];

        st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; n++) {
            videos[n] = Integer.parseInt(st.nextToken());
        }

        // stream(long[])의 max()메서드는 OptionalLong 타입을 반환하므로 getAsLong()을 실행시켜주어야 함
        long low = Arrays.stream(videos).max().getAsLong()-1;    // Arrays.stream(videos).max().getAasLong(); -> 틀렸습니다.
        long high = Arrays.stream(videos).sum();                 // stream(long[])의 sum()메서드는 long타입을 반환
        long mid;

        while (low + 1 < high) {
            
            mid = (low + high) / 2;
            // mid크기를 갖는 M개의 블루레이로 나눠서 저장할 수 있는 경우

            if (getDiskCount(mid) <= M) {
                high = mid;
            }
            else {
                low = mid;
            }
        }
        System.out.println(high);
    }

    public static int getDiskCount(long size) {
        int count = 1;
        long sum = 0;

        for (int n = 0; n < N; n++) {
            if (size < sum + videos[n]) {
                sum = 0;
                count++;
            }
            sum += videos[n];
        }

        return count;
    }
}