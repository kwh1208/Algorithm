package Week12.조영호;

public class Nqueen {
    private static int[] board;
    private static int answer;

    // depth는 행을 의미
    private static void backTracking(int depth, int n) {
        if (depth == n) {
            answer++;
            return;
        }
        for (int i = 0; i < n; i++) {
            board[depth] = i;
            // 해당 depth와 i에 퀸을 놓을수 있는지 확인
            if (valid(depth)) {
                backTracking(depth + 1, n);
            }
        }
    }

    public static boolean valid(int i) {
        // 마지막으로 놓여진 것과 이전의 것들을 비교
        for (int j = 0; j < i; j++) {
            if (board[i] == board[j]) {
                return false;
            }
            if (Math.abs(i - j) == Math.abs(board[i] - board[j]))
                return false;
        }
        return true;
    }

    public static int solution(int n) {
        // 배열의 값은 해당 행의 queen이 있는 열을 의미
        board = new int[n];
        backTracking(0, n);
        return answer;
    }
}
