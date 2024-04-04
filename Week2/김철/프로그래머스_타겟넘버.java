public class 프로그래머스_타겟넘버 {
    public static void main(String args[]) throws Exception {
        int[] numbers = {4, 1, 2, 1};

        System.out.println(solution(numbers, 4));
    }

    static int answer = 0;

    public static int solution(int[] numbers, int target) {
        DFS(0, 0, numbers, target);

        return answer;
    }

    // idx == 숫자를 idx개 만큼 합했다 라는 의미
    // sum == 합한 수
    static void DFS(int idx, int sum, int[] numbers, int target) {

        // 주어진 배열의 모든 숫자를 합했으니 target과 판단
        if (idx == numbers.length) {
            if (sum == target) answer++;
            return;
        }

        // idx + 1 = idx + 1
        DFS(idx + 1, sum + numbers[idx], numbers, target);
        DFS(idx + 1, sum - numbers[idx], numbers, target);
    }
}