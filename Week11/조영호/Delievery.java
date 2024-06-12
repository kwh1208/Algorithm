package Week11.조영호;

import java.util.Arrays;

public class Delievery {
    int[] d;
    boolean[] v;
    int[][] map;

    public int solution(int N, int[][] road, int K) {
        d = new int[N + 1];
        v = new boolean[N + 1];
        map = new int[N + 1][N + 1];

        // 간선 배열 채우기 (간선이 없다면 int 최대값)
        for (int i = 0; i <= N; ++i)
            Arrays.fill(map[i], Integer.MAX_VALUE);
        for (int[] i : road) {
            if (i[2] < map[i[0]][i[1]])
                map[i[0]][i[1]] = map[i[1]][i[0]] = i[2];
        }

        dijkstra(1);

        // 방문할 수 있었던 마을의 개수 반환
        return (int) Arrays.stream(d).boxed()
                .filter(i -> i <= K)
                .count() - 1;
    }

    /* 다익스트라 알고리즘 구현 함수 */
    private void dijkstra(int start) {
        d[start] = 0; //
        v[start] = true;
        for (int i = 2; i < d.length; ++i)
            d[i] = map[start][i];

        for (int i = 0; i < d.length - 2; ++i) {
            int minIdx = getMinIdx();
            v[minIdx] = true;
            for (int j = 2; j < d.length; ++j) {
                if (map[minIdx][j] != Integer.MAX_VALUE && d[minIdx] + map[minIdx][j] < d[j])
                    d[j] = d[minIdx] + map[minIdx][j];
            }
        }
    }

    /* 시작점에서 방문할 수 있는 간선 중 최소값 찾기 */
    private int getMinIdx() {
        int minValue = Integer.MAX_VALUE;
        int minidx = 0;
        for (int i = 2; i < d.length; ++i) {
            if (!v[i] && d[i] < minValue) {
                minValue = d[i];
                minidx = i;
            }
        }
        return minidx;
    }
}
