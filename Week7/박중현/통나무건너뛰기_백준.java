
import java.io.*;
import java.util.*;
public class Main {
    static int[] arr;
    static int[] tempArr;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // N개의 통나무를 원형으로 세워놓고 뛰어놀기
        // 원형으로 인접한 옆 통나무로 건너 뛰는데, 이 때 각 인접한 통나무의 높이 차가 최소가 되게 한다

        // 통나무 건너뛰기 난이도는 인접한 두 통나무 간의 높이의 차가 최댓값으로 결정
        // 높이가 2, 9, 7, 4, 5 -> 최대 난이도 : |9 - 2| = 7
        // 높이가 2, 5, 9, 7, 4 -> 최대 난이도 : |9 - 5| = 4

        int T = Integer.parseInt(br.readLine());

        for(int k = 0; k < T; k++) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            arr = new int[N];
            tempArr = new int[N];

            for(int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arr);

//            int minNum = Integer.MAX_VALUE;
//
//            Arrays.sort(arr);
//            // 2, 4, 5, 7, 9
//            // 2, 4, 9, 7, 5
//
//            // 2, 4, 5, 7, 9, 11
//            // 2, 4, 5, 11, 9, 7
//
//            // N = 5 -> arr[2] <-> arr[4]
//
//            // arr 끝값과 중간값 바꾸기
//            int temp = arr[N/2];
//            arr[N/2] = arr[N-1];
//            arr[N-1] = temp;
//
//            int[] tempArr1 = new int[N];
//            int[] tempArr2 = new int[N];
//
//            for(int i = 0; i < N; i++) {
//                tempArr1[i] = arr[i];
//                tempArr2[i] = arr[i];
//            }
//
//            // arr 2, 4, 9, 7, 5
//            // tempArr1 & tempArr2 2, 4, 9, 7, 5
//
//            for(int i = 2/N; i < N; i++) {
//
//                if( i > 2/N) {
//                    int tempNum = tempArr1[2/N];
//                    tempArr1[2/N] = tempArr1[i];
//                    tempArr1[i] = tempNum;
//                }
//
//                int diff;
//                int maxNum = -1;
//                for(int j = 0; j < N; j++) {
//                    if(j == 0) {
//                        diff = tempArr1[N-1] - tempArr1[j];
//                    }
//                    else {
//                        diff = tempArr1[j] - tempArr1[j-1];
//                    }
//
//                    maxNum = Math.max(maxNum, Math.abs(diff));
//                }
//
//                minNum = Math.min(minNum, maxNum);
//            }
//
//            for(int i = 2/N; i >= 0; i--) {
//
//                if( i < 2/N) {
//                    int tempNum = tempArr2[2/N];
//                    tempArr2[2/N] = tempArr2[i];
//                    tempArr2[i] = tempNum;
//                }
//
//                int diff;
//                int maxNum = -1;
//
//                for(int j = 0; j < N; j++) {
//                    if(j == 0) {
//                        diff = tempArr2[N-1] - tempArr2[j];
//                    }
//                    else {
//                        diff = tempArr2[j] - tempArr2[j-1];
//                    }
//
//                    maxNum = Math.max(maxNum, Math.abs(diff));
//                }
//
//                minNum = Math.min(minNum, maxNum);
//            }


            // [] [] [] [] []
            // [2] [] [] [] []
            // [2] [] [] [] [4]
            // [2] [5] [] [] [4]
            // [2] [5] [] [7] [4]
            // [2] [5] [9] [7] [4]

            int start = 0;
            int end = N - 1;

            for(int i = 0; i < N; i=i+2) {
                // i = 0 -> start = 0, end = 4, tempArr[0] = arr[0], tempArr[4] = arr[1]
                // i = 1 -> start = 1, end = 3, tempArr[1] = arr[2], tempArr[3] = arr[3]
                tempArr[start] = arr[i];
                if(start == end) {
                    break;
                }
                tempArr[end] = arr[i+1];
                start++; end--;
            }

            int diff = Integer.MIN_VALUE;

            for(int i = 0; i < N; i++) {
                if(i == 0) {
                    diff = Math.max(diff, Math.abs(tempArr[N-1] - tempArr[0]));
                }
                else {
                    diff = Math.max(diff, Math.abs(tempArr[i] - tempArr[i-1]));
                }
            }

            bw.write(diff+"\n");
        }


        bw.flush();
        bw.close();
    }

}