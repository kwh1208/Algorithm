/*
<pre>
@Title: 백준: 적의적 12893
@Link: https://www.acmicpc.net/problem/12893
@Author: hey._.mi

*/

import java.io.*;
import java.util.StringTokenizer;

public class BOJ12893 {
    public static int n;
    public static int m;
    public static int[][] rels;
    public static int[][] map;

    public static int dfs(int from, int to, int isEnemy) {

        if(map[from][to] != isEnemy) {
            return 0;
        }

        for(int i=0; i<=m; i++) {
            if(map[from][i] == 1) {
                dfs(i, 0,0);
            }
        }

        return 1;
    }

/*
* 0000
* 0011
* 0001
* 0100
*
1 2
2 3
1 3 0*/

    public static void main(String[]args)  throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());

            rels = new int[m][2];
            map = new int[n+1][n+1];

            for (int i = 0; i < m; i++) {
                st = new StringTokenizer(br.readLine());
                rels[i][0] = Integer.parseInt(st.nextToken());
                rels[i][1] = Integer.parseInt(st.nextToken());
                map[rels[i][0]][rels[i][1]] = 1;
                map[rels[i][1]][rels[i][0]] = 1;
            }

            for (int i=0; i < m; i++){
                dfs(rels[i][0], rels[i][1], 1);
            }

           // bw.write(answer);

            bw.flush();
            br.close();
            bw.close();

        }
    }
