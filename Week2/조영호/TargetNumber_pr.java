//깊이 우선 탐색 알고리즘을 이용
// 마지막 노드까지 탐색했을 때 타겟 넘버와 결과값이 같으면 정답 카운트 증가

public class TargetNumber_pr {
    // 정답 카운트를 저장할 전역변수 answer 선언 및 초기화
    int answer = 0;

    // 깊이 우선 탐색
    public void dfs(int[] numbers, int depth, int target, int sum) {
        // 마지막 노드까지 탐색했을 경우, 즉 depth와 numbers 배열의 길이가 같을 때
        if (depth == numbers.length) {
            // target과 sum값을 비교하여 일치하면 answer 카운트 증가
            if (target == sum)
                answer++;
        } else {
            // 해당 노드의 값을 더하고 다음 깊이 탐색
            dfs(numbers, depth + 1, target, sum + numbers[depth]);
            // 해당 노드의 값을 빼고 다음 깊이 탐색
            dfs(numbers, depth + 1, target, sum - numbers[depth]);
        }
    }

    // 초기 depth, sum값을 0으로 세팅하여 알고리즘 실행후 answer 값 return
    public int solution(int[] numbers, int target) {
        dfs(numbers, 0, target, 0);

        return answer;
    }
}
