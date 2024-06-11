class Solution {
    public int solution(int N, int[][] road, int K) {
        int answer = 0;

        // 플로이드 와셜 알고리즘을 위한 2차원 배열
        int[][] arr = new int[N+1][N+1];

        // 최댓값으로 배열 초기화
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                if(i == j) {
                    continue;
                }
                arr[i][j] = 500001;
            }
        }

        // 배열 인덱스를 0부터 하기위해 -1 뺴주고 인접 행렬 만들기
        for (int i = 0; i < road.length; i++) {
            // 새로 입력되는 거리가 더 크면 무시
            if(arr[road[i][0]][road[i][1]] >= road[i][2]) {
                arr[road[i][0]][road[i][1]] = road[i][2];
                arr[road[i][1]][road[i][0]] = road[i][2];
            }
        }

        // 플로이드 와샬
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                for (int k = 1; k <= N; k++) {
                    // jk를 바로 가는것과 i를 거쳐서 가는것중 i를 거쳐서 가는게 더 빠를 경우
                    if(arr[j][k] > arr[j][i] + arr[i][k]) {
                        arr[j][k] = arr[j][i] + arr[i][k];
                    }
                }
            }
        }

        // 출력
        for (int i = 1; i <= N; i++) {
            if(arr[1][i] <= K) {
                answer++;
            }
        }
        return answer;
    }
}