import java.io.*;
/*
<pre>
@Title: 백준: 촌수계산 2644번
@Link: https://www.acmicpc.net/problem/2644
@Author: hey._.mi
</pre>
*/
public class BOJ2664 {
    static int dfs(int[][] relations, boolean[] visited, int p1, int p2, int cnt) {

        if(cnt >= relations.length || p1 == p2 ) {
            return cnt;
        }

        for(int i=0; i< relations.length; i++) {
            int[] relation = relations[i];

            if(!visited[i] && (relation[0] == p1 || relation[0] == p2)){
                System.out.println("부모 :: "+relation[0]);
                System.out.println("자식 :: "+relation[1]);
                visited[i] = true;
                dfs(relations, visited, relation[1], p2, cnt+1);
            } else if(!visited[i] && (relation[1] == p1 || relation[1] == p2)) {
                visited[i] = true;
                System.out.println("부모 :: "+relation[0]);
                System.out.println("자식 :: "+relation[1]);
                dfs(relations, visited, relation[0], p2, cnt+1);
            }
                dfs(relations, visited, p1, p2, cnt+1);
        }

        return cnt;
    }

    static int solution(int n, int p1, int p2, int m ,int[][] relations) {
        boolean[] visited = new boolean[m];
        int answer = dfs(relations, visited, p1, p2, 0);

        return answer;
    }

    public static void main(String[] args) throws IOException {

        System.out.println(solution(9, 7, 3, 7, new int[][]{{1,2}, {1,3}, {2,7}, {2,8}, {2,9}, {4,5}, {4,6}}));
//
//        int answer = 0;
//
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//
//        int n = Integer.parseInt(br.readLine()); // 사람 수
//        StringTokenizer st = new StringTokenizer(br.readLine());
//        int t1 = Integer.parseInt(st.nextToken()); // 대상1
//        int t2 = Integer.parseInt(st.nextToken()); // 대상2
//        int p1 = Integer.max(t1, t2);
//        int p2 = Integer.min(t1, t2);
//        int m = Integer.parseInt(br.readLine()); // 관계수
//
//        int[][] relations = new int[m][2];
//        boolean[] visited = new boolean[m];
//
//        for (int i = 0; i < m; i++) {
//            st = new StringTokenizer(br.readLine());
//            relations[i][0] = Integer.parseInt(st.nextToken()); // 부모
//            relations[i][1] = Integer.parseInt(st.nextToken()); // 자식
//        }
//
//
//        answer = dfs(relations, visited, p1, p2, 0);
//        System.out.println(answer);
//
//        bw.write(answer);
//
//        bw.flush();
//        br.close();
//        bw.close();
    }

}

/*

[ 4월 4일 (목) 문제 ]
1. 백준: 파이프 옮기기1 17070번 https://www.acmicpc.net/problem/17070
2. 백준: 촌수계산 2644번 https://www.acmicpc.net/problem/2644
3. 프로그래머스: 타겟넘버 https://school.programmers.co.kr/learn/courses/30/lessons/43165
 */