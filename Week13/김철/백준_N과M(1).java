package Main;

import java.io.*;
import java.util.*;

class Main15649_1{
    static int N,M;
    static int[] arr;
    static boolean[] visited;
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer token;

        token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        visited = new boolean[N+1];
        arr = new int[M+1];

        DFS(1);

        bw.close();
    }

    static void DFS(int printCount) throws IOException{
        // 출력 개수가 M이 되면 출력 후 리턴.
        if(printCount > M) {
            for(int i=1; i<=M; i++){
                bw.write(arr[i]+ " ");
            }
            bw.write("\n");
            return;
        }

        for(int i=1; i<=N; i++){
            if(!visited[i]){
                visited[i] = true;
                arr[printCount] = i;
                DFS(printCount + 1);
                visited[i] = false;
            }
        }
    }
}
