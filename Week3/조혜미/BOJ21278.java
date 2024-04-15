/*
<pre>
@Title: 백준: 호석이 두 마리 치킨
@Link: https://www.acmicpc.net/problem/21278
@Author: hey._.mi
@State : 20240415 -> 플루이드 와샬
https://velog.io/@suk13574/%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98Java-%ED%94%8C%EB%A1%9C%EC%9D%B4%EB%93%9C-%EC%99%80%EC%83%ACFloyd-Warshall-%EC%95%8C%EA%B3%A0%EB%A6%AC%EC%A6%98
*/

import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class BOJ21278 {
    public static int N; // 건물의 개수
    public static int M; // 도로의 개수
    public static int[][] roads;

    public static void main(String[]args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        roads = new int[N+1][N+1];
        int time = Integer.MAX_VALUE;
        int b1 = 0;
        int b2 = 0;

        for(int i=0; i<N+1; i++) {
            for(int j=0; j<N+1; j++) {
                roads[i][j] = i == j ? 0 : 100*100;

            }
        }

        for(int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            roads[a][b] = 1;
            roads[b][a] = 1;
        }

        // 최단 거리 map 만들기 -> 플로이드
        for(int i=1; i<=N; i++) { // stopover
            for(int a=1; a<=N; a++) { // from
                for(int b=1; b<=N; b++) { // to
                    roads[a][b] = Math.min(roads[a][b], roads[a][i]+roads[i][b]); // from -> to & from -> stopover -> to 비교
                }
            }
        }

        // 최단 시간 구하기
        for(int a=1; a<=N; a++) {
            for(int b=1; b<=N; b++) {
                int temp = 0;

                for(int i=1; i<=N; i++) {
                    if(a == i || b == i) continue; // stopover과 node 같은 경우 제외

                    temp += Math.min(roads[a][i], roads[b][i]); // stopover를 거치는 것이 최단인가?
                }

                if(time > temp) {
                    time = temp;
                    b1 = Math.min(a, b);
                    b2 = Math.max(a, b);
                }
            }
        }

        System.out.println(b1 + " " + b2 + " " + time*2);

        bw.flush();
        br.close();
        bw.close();

    }
}
