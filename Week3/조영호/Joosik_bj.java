import java.util.*;
import java.io.*;
// 문제에서 주어진 행동은 총 3가지
// 1. 주식 하나를 산다
// 2. 원하는 만큼 주식을 판매
// 3. 아무것도 하지 않는다 
// 이문제를 틀리는 대부분의 경우는 그리디적인 접근을 해서 틀림
// 그리디적인 접근 방법은 주가의 최대치에서 다음날 떨어진다면 현재 날짜에 기존에 구입했던 주식을 판매 
// 그러나 이는 최대 이익을 볼 수 있는 방법 아님 
// 올바른 방법은 주가가 떨어지는 시점이 아니라 오늘 산 주식을 이후 가장 비싸게 팔 수 있는 날에 판매하는것
// 즉 가장 비싸게 팔 수 있는 날까지 주식을 계속 사야한다 
// 이를 앞에서부터 계산하면 주식을 판 다음날부터 다시 최대값을 구하는 방식
// 뒤에서부터 계산한다면 가장 비쌀 때의 가격을 쉽게 찾을 수 있으므로 비교적 간단하게 구현 가능 
// 날짜를 거꾸로 탐색하면 최대 주기를 바로바로 갱신 가능 
// 이떄 갱신이 가능하다는 건 지금까지 주가보다 오늘 주가가 비싸다는 것이므로 최대 주가를 갱신하면 된다
// 갱신이 안된다는 것은 오늘 산 주가를 지금까지 갱신한 최대 주가날에는 최대 이익이라는 것이므로 이익을 계산

public class Joosik_bj {

    // 다음날 주가가 오늘 주가보다 떨어질 때 판매 -> X
    // 오늘 이후 최대 이익을 낼 수 있는 날에 판매 -> 뒤에서부터 봐야 한다
    public static void main(String[] args) throws Exception {
        // 입력된 데이터가 바로 전달되지 않고 버퍼를 거쳐 전달되므로 데이터 처리 효율성을 높임
        // BufferedReader는 Scanner와 유사
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // BufferedWriter는 System.out.println();과 유사
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());
        for (int i = 1; i < T; i++) {
            // 주식의 수
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[j] = Integer.parseInt(st.nextToken());
            }
            // 현재까지 가장 비싼 주가
            long max = 0;
            // 최대이익
            long result = 0;

            int length = arr.length - 1;

            for (int j = length; j >= 0; j--) {
                // 현재 최고 주가보다 큰 주가를 만난다면
                if (max < arr[j]) {
                    // 주가 갱신
                    max = arr[j];
                    // 현재 주가가 작거나 크다는 것은 오늘이 가장 비싸게 팔 수 있는 날
                } else {
                    result += max - arr[i];
                }

            }
            bw.write(Long.toString(result) + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
