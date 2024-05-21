import java.io.*;
import java.util.*;
public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 집에서 시간을 보내던 오영식은 박성원의 부름을 받고 달려감
        // N개의 랜선을 만들어야

        // K개의 랜선이 있지만 , 길이가 제각각
        // 박성원은 랜선을 모두 N개의 같은 길이의 랜선으로 만들고 싶음

        // ex. 300cm짜리 랜선에서 140cm짜리 랜선을 두 개 잘라내면 20cm 버려야

        StringTokenizer st = new StringTokenizer(br.readLine());
        // 이미 가지고 있는 랜선의 개수
        int K = Integer.parseInt(st.nextToken());
        //필요한 랜선의 개수
        int N = Integer.parseInt(st.nextToken());

        long[] arr = new long[K];

        long maxNum = Long.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            maxNum = Math.max(maxNum, arr[i]);
        }

        long right = maxNum;

        long answer = binarySearch(N, 1, right, arr);

        bw.write(answer+"");

        bw.flush();
        bw.close();
    }

    static long binarySearch(int targetCount, long left, long right, long[] arr) {

        long mid = (left + right) / 2;

        long count = getCount(arr, mid);

        if (left <= right) {

            // 현재 count가 targetCount보다 작다면
            // 랜선 길이를 줄여서 count를 늘려야

            // 현재 count가 targetCount보다 크다면
            // 랜선 길이를 늘려서 count를 줄여야함
            if (count < targetCount) {
                return binarySearch(targetCount, left, mid-1, arr);
            } else {
                return binarySearch(targetCount, mid+1, right, arr);
            }
        }

        return right;
    }
    // 1 802
    // 1 400
    // 201 400
    // 201 299
    // 201 249
    // 201 224
    // 201 211
    // 201 205 11
    // 201 202 11
    // 201 200 11

    private static long getCount(long[] arr, long mid) {
        long count = 0;
        for (int i = 0; i < arr.length; i++) {
            count += arr[i] / mid;
        }

        return count;
    }
}