import java.io.*;
import java.util.*;

public class Main11497 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token;

        int T = Integer.parseInt(br.readLine());

        for(int i=0; i<T; i++) {
            int N = Integer.parseInt(br.readLine());
            int[] tree = new int[N];

            // 처음과 끝을 직관적으로 인접시키기 위해 배열 길이 +1
            int[] afterTree = new int[N+1];
            // 배열 내 난이도 최댓값
            int maxLevel = Integer.MIN_VALUE;

            token = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++) {
                tree[j] = Integer.parseInt(token.nextToken());
            }

            // 정렬
            Arrays.sort(tree);

            int startIdx = 0;
            int endIdx = N - 1;
            for(int j=0; j<N; j++) {
                if(j % 2 == 0) {
                    afterTree[startIdx] = tree[j];
                    startIdx++;
                }
                else {
                    afterTree[endIdx] = tree[j];
                    endIdx--;
                }
            }

            // 처음과 끝 인접시키기
            afterTree[N] = afterTree[0];

            for(int j=1; j<=N; j++) {
                int diff = Math.abs(afterTree[j-1] - afterTree[j]);
                if(diff >= maxLevel)
                    maxLevel = diff;
            }

            bw.write(maxLevel + "\n");
        }

        bw.close();
    }
}
