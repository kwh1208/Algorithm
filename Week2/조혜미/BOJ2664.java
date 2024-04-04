import jdk.swing.interop.SwingInterOpUtils;

import java.io.*;
import java.util.StringTokenizer;

/*
<pre>
@Title: 백준: 촌수계산 2644번
@Link: https://www.acmicpc.net/problem/2644
@Author: hey._.mi
</pre>
*/
public class BOJ2664 {

    static int n;
    static int m;
    static int p1;
    static int p2;
    static int[][] relations;
    static int[] map;
    static int answer;

    static void dfs(int idx) {
        if(idx == p2) return;

        for(int i=1; i<=n; i++){
            if(relations[idx][i]==1 && map[i]==0){
                map[i] = map[idx]+1;
                dfs(i);
            }
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        p1 = Integer.parseInt(st.nextToken());
        p2 = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(br.readLine()); // 관계수
        relations = new int[n+1][n+1];
        map = new int[n+1];

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            relations[a][b] = 1;
            relations[b][a] = 1;
        }

        dfs(p1);


        answer = map[p2] > 0 ? map[p2] : -1;
        System.out.println(answer);

        bw.write(answer);

        bw.flush();
        br.close();
        bw.close();
    }

}