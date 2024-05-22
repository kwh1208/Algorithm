import java.util.Scanner;

public class BOJ_1654 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int K = scan.nextInt();
        int N = scan.nextInt();

        int[] lines = new int[K];
        int max = 0;
        for (int i = 0; i < K; i++) {
            lines[i] = scan.nextInt();
            max = Math.max(lines[i], max);
        }

        long start= 1;
        long end = max;
        long mid = 0;

        while(start <= end) {
            mid = (start+end) / 2;
            long count = 0;

            for (int i = 0; i < K; i++) {
                count += lines[i]/mid;
            }

            if(count < N) {
                end = mid-1;
            }else {
                start = mid+1;
            }
        }

    System.out.println(end);

    }
}
