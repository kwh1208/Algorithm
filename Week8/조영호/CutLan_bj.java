package 조영호;

// 문제
// 집에서 시간을 보내던 오영식은 박성원의 부름을 받고 급히 달려왔다.
// 박성원이 캠프 때 쓸 N개의 랜선을 만들어야 하는데 너무 바빠서 영식이에게 도움을 청했다.
// 이미 오영식은 자체적으로 K개의 랜선을 가지고 있다.
// 그러나 K개의 랜선은 길이가 제각각이다.
// 박성원은 랜선을 모두 N개의 같은 길이의 랜선으로 만들고 싶었기 때문에 K개의 랜선을 잘라서 만들어야 한다.
// 예를 들어 300 cm 짜리 랜선에서 140 cm 짜리 랜선을 두 개 잘라내면 20 cm는 버려야 한다.(이미 자른 랜선은 붙일 수 없다.)
// 편의를 위해 랜선을 자르거나 만들 때 손실되는 길이는 없다고 가정하며,기존의 K개의 랜선으로 N개의 랜선을 만들 수 없는 경우는 없다고 가정하자.
// 그리고 자를 때는 항상 센티미터 단위로 정수길이만큼 자른다고 가정하자.
// N개보다 많이 만드는 것도 N개를 만드는 것에 포함된다.
// 이때 만들 수 있는 최대 랜선의 길이를 구하는 프로그램을 작성하시오.
// 입력 첫째 줄에는 오영식이 이미 가지고 있는 랜선의 개수 K,그리고 필요한 랜선의 개수 N이 입력된다.
// K는 1 이상 10,000 이하의 정수이고,N은 1 이상 1,000,000 이하의 정수이다.
// 그리고 항상 K≦N 이다.그 후 K줄에 걸쳐 이미 가지고 있는 각 랜선의 길이가 센티미터 단위의 정수로 입력된다.랜선의 길이는 231-1 보다 작거나 같은 자연수이다.

import java.io.*;
import java.util.*;

public class CutLan_bj {
    // 이분탐색
    // 이진 탐색 알고리즘은 정렬되어 있는 리스트에서 탐색 범위를 절반씩 좁혀가며 데이터를 탐색
    // 이진 탐색은 정렬된 데이터를 이용해 탐색하는 방법으로 탐색하고자 하는 리스트 ,배열 내부의 데이터가 정렬되어 있어야만 사용할 수 있는
    // 알고리즘
    // 변수 3개(start, end, mid 혹은 min, max, mid 등)을 사용하여 탐색
    // 찾으려는 데이터와 중간점 위치에 있는 데이터를 반복적으로 비교해서 원하는 데이터를 찾는 것

    // 랜선 K개와 랜선의 길이, 그리고 필요한 랜선의 개수 N
    // 이때 N개를 만들 수 있는 랜선의 최대 길이 구하기

    static int[] arr;
    static int k;
    static int n;

    // 특정 랜선 길이로 만들 수 있는 랜선 개수 계산
    public static int caculate(long h) {
        int sum = 0;
        for (int i = 0; i < k; i++) {
            // 배열에 저장해 놓은 모든 랜선의 길이를 h로 나눈 값들의 합 = 바로 h로 잘랐을 때 나오는 랜선의 개수
            sum += arr[i] / h;
        }
        return sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 입력 받는 부분
        // 이미 갖고 있는 랜선 개수
        k = Integer.parseInt(st.nextToken());
        // 필요한 랜선 개수
        n = Integer.parseInt(st.nextToken());

        arr = new int[k];

        long low = 1;
        long high = 0;
        for (int i = 0; i < k; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            // 랜선의 최대 길이를 int 형 변수 high에 저장
            // high 길이 만큼 랜선들을 자르고 N개 이상의 랜선을 만들 수 있으면 이 길이가 정답
            high = Math.max(high, arr[i]);
        }

        // 초기 설정을 통해 이분 탐색 범위 지정
        if (caculate(high) >= n) {
            System.out.println(high);
            return;
        }

        // 이분탐색
        while (low < high - 1) {
            // low와 high의 중간값 mid를 구해준다
            long mid = (low + high) / 2;
            if (caculate(mid) >= n) {
                low = mid;
            } else {
                // N보다 크거나 같으면 mid에서 high로 변경
                // 중간값에서도 N개 이상의 랜선이 나오기 때문에 mid보다 작은 값들을 볼 필요없다
                high = mid;
            }
        }
        System.out.println(low);
    }

}
