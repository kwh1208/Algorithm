
import java.io.*;
import java.util.*;
public class Main {

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 기타 레슨
        // 강토는 자신의 기타 강의 동영상을 블루레이로 만들어 판매하려고 한다.
        // 블루레이는 총 N개의 강의가 들어가는데 블루레이를 녹화할때 강의 순서가 바뀌면 안 된다

        // 순서가 뒤바뀌는 경우에는 강의의 흐름이 끊겨 학생들이 대혼란 에 빠질수 있음

        // 강토는 M개의 블루레이에 모든 기타 강의 동영상을 녹화하기로

        // 첫째 줄에 가으이의수 N, M이 주어진다.

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());

        for(int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 이진탐색 -=> 정렬된 배열에서 특정 값을 찾는 탐색 알고리즘

        // 강의의 순서가 뒤바뀌면 안된다...


        int maxNum = 0;
        int sum = 0;
        for(int i = 0; i < arr.length; i++) {
            maxNum = Math.max(maxNum, arr[i]);
            sum = sum + arr[i];
        }

        int left = maxNum;

        int right = sum;

        bw.write(binarySearch(M, left, right, arr)+"");

        bw.flush();
        bw.close();
    }

    // key -> 나눠지는 그룹 개수
    static int binarySearch(int key, int left, int right, int[] arr) {

        int mid = (left + right) / 2;

        int count = getCount(arr, mid);

        if (left <= right) {

            // if문 순서 중요
            // if문 count < key or count <=key로 해야할까?
            // count <= key로 해야 한다.
            // 왜냐하면 그래야 count==3인채로 right 값을 줄여가면서
            // 블루레이 최소값을 구할수가 있다.

            if (count <= key) {
                return binarySearch(key, left, mid -1, arr);
            } else  {
                return binarySearch(key, mid+1, right, arr);
            }

//            if (count > key) {
//                return binarySearch(key, mid + 1, right, arr);
//            }  else {
//                return binarySearch(key, left, mid -1, arr);
//            }
        }

        return left;
    }

    private static int getCount(int[] arr, int mid) {
        int sum = 0;
        int count = 1;

        // 만약 arr = 1,2,3,4,5,6,7,8,9 / mid = 17
        // 1,2,3,4,5 / 6,7 / 8,9 -> count = 3
        for (int i = 0; i < arr.length; i++) {
            if (sum + arr[i] > mid) {
                count++;
                sum = 0;
            }

            sum = sum + arr[i];
        }


        return count;
    }
}