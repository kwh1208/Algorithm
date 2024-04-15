/*
<pre>
@Title: 백준: 이모티콘
@Link: https://www.acmicpc.net/problem/14226
@Author: hey._.mi
@State : 20240414
*/

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ14226 {
    public static int S;
    public static boolean[][] visited;
    static int answer = Integer.MAX_VALUE;

    static class State {
        int clipboard;
        int window;
        int time;

        public State(int clipboard, int window, int time) {
            this.clipboard = clipboard;
            this.window = window;
            this.time = time;
        }
    }

    public static void main(String[]args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        S = Integer.parseInt(st.nextToken());
        visited = new boolean[1001][1001];

        Queue<State> que = new LinkedList<>();
        que.offer(new State(0, 1, 0));

        while (!que.isEmpty()) {
            State cur = que.poll();
            int clipboard = cur.clipboard;
            int window = cur.window;
            int time = cur.time;

            if(cur.window == S) {
                answer = Math.min(answer, time);
                break;
            }

            if(clipboard >= 0 && clipboard < 1001 & window >= 0 & window < 1001) {
                if(window+clipboard < 1001 && !visited[clipboard][window+clipboard]) {
                    visited[clipboard][window+clipboard] = true;
                    que.offer(new State(clipboard, window+clipboard, time+1));
                }

                if(window-1 >= 0 && !visited[clipboard][window-1]) {
                    visited[clipboard][window-1] = true;
                    que.offer(new State(clipboard, window-1, time+1));
                }

                if(!visited[window][window]) {
                    visited[window][window] = true;
                    que.offer(new State(window, window, time+1));
                }
            }
        }

        System.out.println(answer);

        bw.flush();
        br.close();
        bw.close();

    }
}
