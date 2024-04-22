import java.util.*;

class Solution {
    public int solution(int[] people, int limit) {
        int answer = 0;

        // 구명보트는 한 번에 최대 2명만 탑승 가능 !!!!!!!!!!

        // 몸무게 70kg, 50kg, 80kg, 50kg
        // 구명보트의 무게 제한 100kg이라면
        // 2번째 사람과 4번째 사람은 같이 탈 수 있지만 1번째 사람과 3번째 사람의 무게의 합은 150kg이므로
        // 구명보트의 무게 제한을 초과하여 같이 탈 수 없다

        // 구명보트를 최대한 적게 사용하여 모든 사람을 구출하려고 한다.

        // 정렬하기
        Arrays.sort(people);

        // min
        // max
        int minIdx = 0;
        int maxIdx = people.length - 1;

        // minIdx 가 maxIdx보다 작거나 같을때까지 while문 돌리기
        while (minIdx <= maxIdx) {
            // 만약에 people[0] + people[3]이 100보다 작으면 minIdx = 1, maxIdx = 2
            // 만약에 people[0] + people[3]이 100보다 크면 minIdx = 0, maxIdx = 2
            if((people[minIdx] + people[maxIdx]) <= limit) {
                minIdx++;
            }
            answer++; // 무조건 구명보트는 +가 된다.
            maxIdx--; // maxIdx 는 무조건 줄어든다
        }

        return answer;
    }
}