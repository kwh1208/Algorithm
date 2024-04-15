/*
<pre>
@Title: 백준: 연산자 끼워넣기
@Link: https://www.acmicpc.net/problem/14888
@Author: hey._.mi
@State : 20240414
*/

import java.io.*;
import java.util.StringTokenizer;

public class BOJ14888 {
    public static int N;
    public static int[] arr; // 숫자
    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;

    static void dfs(int[] arr, int[] opp, int idx, int res) {
        if(idx >= arr.length) {
            min = Math.min(res, min);
            max = Math.max(res, max);
            return;
        }

        if(opp[0] > 0) {
            int[] temp = opp.clone();
            temp[0]--;
            dfs(arr, temp, idx+1, res + arr[idx]);
        }
        if(opp[1] > 0) {
            int[] temp = opp.clone();
            temp[1]--;
            dfs(arr, temp, idx+1, res - arr[idx]);
        }
        if(opp[2] > 0) {
            int[] temp = opp.clone();
            temp[2]--;
            dfs(arr, temp, idx+1, res * arr[idx]);
        }
        if(opp[3] > 0) {
            int[] temp = opp.clone();
            temp[3]--;
            dfs(arr, temp, idx+1, res / arr[idx]);
        }
    }

    public static void main(String[]args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] opp = new int[4];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<4; i++) {
            opp[i] = Integer.parseInt(st.nextToken());
        }

        dfs(arr, opp, 1, arr[0]);
        System.out.println(max);
        System.out.println(min);


       // bw.write(answer);

        bw.flush();
        br.close();
        bw.close();

    }
}
