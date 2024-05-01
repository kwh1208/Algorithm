
import java.io.*;
import java.util.*;
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 매일 세 가지 중에 한 행동을 한다.
        // 1. 주식 하나를 산다.
        // 2. 원하는 만큼 가지고 있는 주식을 판다.
        // 3. 아무것도 안 한다.

        // 날 수가 3일
        // 10 , 7 , 6 일 때, 주가가 계속 감소하므로 최대 이익은 0이 된다.
        // 그러나 만약 날 별로 주가가 3, 5, 9일때는 처음 두 날에 주식을 하나씩 사고
        // 마지막 날 다 팔아버리면 이익이 10이 된다.

        // ex. 3, 5, 4, 9
        // ex. 3, 5, 4

        // 각 테스트케이스 별로 첫 줄에는 날의 수를 나타내는 자연수 N
        // 둘째 줄에는 날 별 주가를 나타내는 N개의 자연수들이 공백으로 구분되어 순서대로 주어진다.

        // 테스트 케이스 수 T
        int T = Integer.parseInt(br.readLine());

        // 각 테스트케이스 별로 첫 줄에는 날의 수를 나타내는 자연수 N
        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());

            int[] arr = new int[N];
            for (int j = 0; j < N; j++) {
                arr[j] = Integer.parseInt(st.nextToken());

            }

            long maxNum = 0; // 현재 최소 가격
            long maxProfit = 0;

            // 3, 5, 4, 9

            // 역순으로 반복문
            // 현재 최고 주가보다 큰 주가를 만나면 주가를 갱신하자
            for (int j = N - 1; j >=0; j--) {
                if (maxNum < arr[j]) {
                    maxNum = arr[j];
                } else { // 현재 주가가 작거나 같다는것은 오늘 가장 비싹 ㅔ팔 수 있다
                    maxProfit += maxNum - arr[j];
                }

            }

            bw.write(maxProfit+"\n");

        }

        bw.flush();
        br.close();
        bw.close();
    }

}