package 조영호;

import java.io.*;
import java.util.*;

// 문제 
// 강토는 자신의 기타 강의 동영상을 블루레이로 만들어 판매하려고 한다. 
// 블루레이에는 총 N개의 강의가 들어가는데, 블루레이를 녹화할 때, 강의의 순서가 바뀌면 안 된다. 
// 순서가 뒤바뀌는 경우에는 강의의 흐름이 끊겨, 학생들이 대혼란에 빠질 수 있기 때문이다. 
// 즉, i번 강의와 j번 강의를 같은 블루레이에 녹화하려면 i와 j 사이의 모든 강의도 같은 블루레이에 녹화해야 한다.
// 강토는 이 블루레이가 얼마나 팔릴지 아직 알 수 없기 때문에,블루레이의 개수를 가급적 줄이려고 한다.
// 오랜 고민 끝에 강토는 M개의 블루레이에 모든 기타 강의 동영상을 녹화하기로 했다.
// 이때,블루레이의 크기(녹화 가능한 길이)를 최소로 하려고 한다.단,M개의 블루레이는 모두 같은 크기이어야 한다.
// 강토의 각 강의의 길이가 분 단위(자연수)로 주어진다.이때,가능한 블루레이의 크기 중 최소를 구하는 프로그램을 작성하시오.

// 이 문제는 이진 탐색을 이용하여 풀 수 있습니다.
// 완전 탐색으로 문제를 풀 경우 N=100,000 이어서 O(N^2)=약 10,000,000,000 으로 시간 초과가 발생하기 때문에 
// 이진 탐색으로 진행하게 되면 O(log(N^2))으로 시간을 단축시킬 수 있습니다.
// 이진 탐색의 경우 문제 조건을 만족시킬 수 있는 max값과 min값을 설정하여 두 값의 중앙값을 정답이라고 가정하여 
// 로직에 대입한 후 조건에 따라 max와 min 값을 수정하여 범위를 좁혀나가면서 값을 찾아가는 방식입니다.
// 이 문제에서 가능한 정답의 범위는 모든 수의 합이 최대,단일 수의 최댓값이 최소로의 범위를 가지게 됩니다.
// 따라서 해당 범위 내에서 중앙값을 구한 후 로직에 대입하여 처리하시면 되겠습니다.

public class GuitarLesson_bj {
    static int N, M;
    static long sum, cnt;
    static ArrayList<Integer> req = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 이진 탐색 사용
        // 완전탐색으로 진행할 경우 시간 초과

        // N = 100,000 * 10,000 하면 int 범위 초과하므로 long으로 설정
        long max = 0;
        long min = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            req.add(Integer.parseInt(st.nextToken()));

            // max = 모든 수의 합
            max += req.get(i);

            // min = 단일 수의 최댓값
            if (min < req.get(i))
                min = req.get(i);
        }
        // 이진 탐색 시작
        // min과 max의 격차를 줄여나가면서 탐색
        while (min <= max) {
            // 중앙값 : min과 max의 평균
            long mid = (min + max) / 2;
            sum = 0;
            cnt = 0;

            for (int i = 0; i < N; i++) {
                // 만약 기존 합에서 현재 값을 더한 값이 mid보다 크다면 cnt++ 후 sum 초기화
                if (sum + req.get(i) > mid) {
                    cnt++;
                    sum = 0;
                }
                // sum에 값들을 지속적으로 더해주기
                sum += req.get(i);
            }

            // 만약 sum이 0이 아니라면 cnt++
            if (sum != 0)
                cnt++;

            // 만약 cnt가 M보다 작거나 같다면 max를 줄여 다음 mid 값을 줄이기
            if (cnt <= M)
                max = mid - 1;
            // 만약 cnt가 M보다 크다면 min을 늘려 다음 mid 값을 늘리기
            else
                min = mid + 1;
        }

        // min에 최종 결과 저장되므로 min 출력
        System.out.println(min);
    }
}
