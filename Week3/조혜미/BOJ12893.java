/*
<pre>
@Title: 백준: 적의적 12893
@Link: https://www.acmicpc.net/problem/12893
@Author: hey._.mi
@State : 20240412 retry
*/

import java.io.*;
import java.util.*;

public class BOJ12893 {
    public static int n;
    public static int m;
    public static int[] rels;
    public static List<Integer>[] graph;

    public static int dfs(int idx, int isEnemy) {
        if(rels[idx] > -1) {
            return rels[idx] == isEnemy ? 1 : 0;
        }

        rels[idx] = isEnemy;

        for(int i=0; i < graph[idx].size(); i++) {
            if(dfs(graph[idx].get(i), 1-isEnemy) == 0) {
                return 0;
            }
        }

        return 1;
    }

    public static void main(String[]args) throws IOException {
        int answer = 1;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 사람수
        m = Integer.parseInt(st.nextToken()); // 적대관계수

        rels = new int[n+1]; // 적대관계
        Arrays.fill(rels, -1);
        graph = new List[n+1];

        for(int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b= Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a);
        }

        for(int i=1; i <= n; i++) {
            if(rels[i] != -1) continue;
            if(dfs(i, 0) == 0) {
                answer = 0;
                break;
            }
        }

        System.out.println(answer);


       // bw.write(answer);

        bw.flush();
        br.close();
        bw.close();

    }
}
