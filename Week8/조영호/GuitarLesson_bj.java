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
// 이때,블루레이의 크기(녹화 가능한 길이)를 최소로 하려고 한다.
// 단,M개의 블루레이는 모두 같은 크기이어야 한다.
// 강토의 각 강의의 길이가 분 단위(자연수)로 주어진다.
// 이때,가능한 블루레이의 크기 중 최소를 구하는 프로그램을 작성하시오.

// 이 문제는 이진 탐색을 이용하여 풀 수 있습니다.
// 완전 탐색으로 문제를 풀 경우 N=100,000 이어서 O(N^2)=약 10,000,000,000 으로 시간 초과가 발생하기 때문에 
// 이진 탐색으로 진행하게 되면 O(log(N^2))으로 시간을 단축시킬 수 있습니다.
// 이진 탐색의 경우 문제 조건을 만족시킬 수 있는 max값과 min값을 설정하여 두 값의 중앙값을 정답이라고 가정하여 
// 로직에 대입한 후 조건에 따라 max와 min 값을 수정하여 범위를 좁혀나가면서 값을 찾아가는 방식입니다.
// 이 문제에서 가능한 정답의 범위는 모든 수의 합이 최대,단일 수의 최댓값이 최소로의 범위를 가지게 됩니다.
// 따라서 해당 범위 내에서 중앙값을 구한 후 로직에 대입하여 처리
// 블루레이의 크기가 모두 같고, 녹화순서가 뒤바뀌지 않아야 한다는 조건에서 이진탐색 알고리즘을 선택
// 보통 이분탐색은 정렬된 곳에서 찾는게 보통인데 해당 문제에서는 강의의 순서가 뒤바뀌면 안된다라고 적혀있다. 
// 따라서 정렬을 하지 않고 블루레이의 길이에 따라 만들어지는 강의의 수를 체크하여 
// 가장 최소의 길이가 되는 블루레이 크기를 구해야한다.
// 지정한 블루레이 크기로 모든 레슨을 저장할 수 있는지 판단하여, 모두 저장할 수 있다면 크기를 줄이고, 
// 모두 저장할 수 없다면 블루레이 크기를 늘리는 방식으로 최솟값을 알 수 있다.
// 이진탐색의 시작 인덱스 = 최대 길이의 레슨
// 이진탐색의 종료 인덱스 = 모든 레슨 길이의 합 ('시작인덱스 > 종료인덱스'일 때까지 수행)
// 중앙값 크기로 모든 레슨 저장 가능: 종료 인덱스=중앙값-1 
// 중앙값 크기로 모든 레슨 저장 불가능:시작 인덱스=중앙값+1

public class GuitarLesson_bj {
    public static int[] videoSize;
    public static int sum = 0;
    public static int count = 0;
    public static int start = 0;
    public static int end = 0;

    public static void main(String[] args) throws IOException {
        // 버퍼를 사용하겠다고 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 스트링토큰을 사용하겠다고 선언
        // readLine()을 통해 입력받은 값을 공백 단위로 구분
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 잘라낸 데이터들은 st에 담겨져 있어서 nextToken()으로 순서대로 가져와서 사용 가능
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 이진 탐색 사용
        // 완전탐색으로 진행할 경우 시간 초과

        // // N = 100,000 * 10,000 하면 int 범위 초과하므로 long으로 설정
        // long max = 0;
        // long min = 0;

        // for (int i = 0; i < N; i++) {
        // req.add(Integer.parseInt(st.nextToken()));

        // // max = 모든 수의 합
        // max += req.get(i);

        // // min = 단일 수의 최댓값
        // if (min < req.get(i))
        // min = req.get(i);
        // }

        for (int i = 0; i < N; i++) {
            videoSize[i] = Integer.parseInt(st.nextToken());

            // 종료 인덱스 저장 (A배열의 총합) = 모든 레슨 길이 합
            end += videoSize[i];

            if (start < videoSize[i])
                // 시작 인덱스 저장 (A배열 중 최댓값) = 최대 길이의 레슨
                start = videoSize[i];

        }

        // 이진 탐색 시작
        // min과 max의 격차를 줄여나가면서 탐색
        // while (min <= max) {
        // // 중앙값 : min과 max의 평균
        // long mid = (min + max) / 2;
        // sum = 0;
        // cnt = 0;

        // for (int i = 0; i < N; i++) {
        // // 만약 기존 합에서 현재 값을 더한 값이 mid보다 크다면 cnt++ 후 sum 초기화
        // if (sum + req.get(i) > mid) {
        // cnt++;
        // sum = 0;
        // }
        // // sum에 값들을 지속적으로 더해주기
        // sum += req.get(i);
        // }

        // // 만약 sum이 0이 아니라면 cnt++
        // if (sum != 0)
        // cnt++;

        // // 만약 cnt가 M보다 작거나 같다면 max를 줄여 다음 mid 값을 줄이기
        // if (cnt <= M)
        // max = mid - 1;
        // // 만약 cnt가 M보다 크다면 min을 늘려 다음 mid 값을 늘리기
        // else
        // min = mid + 1;
        // }

        // // min에 최종 결과 저장되므로 min 출력
        // System.out.println(min);
        // }

        // 이진 탐색 시작
        // start와 end의 격차를 줄여나가면서 탐색
        while (start <= end) {
            int middle = (start + end) / 2;

            for (int i = 0; i < N; i++) {
                if (sum + videoSize[i] > middle) {
                    count++;
                    sum = 0;
                } else {
                    // sum에 값들을 지속적으로 더해주기
                    sum += videoSize[i];
                }
            }
            if (sum != 0)
                count++;
            // 지정한 블루레이 크기로 모든 레슨을 저장할 수 있는지 판단
            // 모두 저장 가능 = 중앙값 크기로 모든 레슨 저장 가능 : 종료 인덱스 = 중앙값 -1
            if (count > M)
                start = middle + 1;
            // 모두 저장할 수 없다 = 중앙값 크기로 모든 레슨 저장 불가능 : 시작 인덱스 = 중앙값 + 1
            else
                end = middle - 1;
        }
        System.out.println(start);
    }
}
