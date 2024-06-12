package Main;

import java.io.*;
import java.util.*;

public class Main11265 {

    static int N, M;
    static int[][] party;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token;

        token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        party = new int[N+1][N+1];

        for(int i=1; i<=N; i++){
            token = new StringTokenizer(br.readLine());
            for(int j=1; j<=N; j++){
                party[i][j] = Integer.parseInt(token.nextToken());
            }
        }

        floyd();

        for(int i=0; i<M; i++){
            token = new StringTokenizer(br.readLine());
            // a = 현재 위치, b = 가려는 파티, c = 해당 파티 시작까지 소요 시간
            int a = Integer.parseInt(token.nextToken());
            int b = Integer.parseInt(token.nextToken());
            int c = Integer.parseInt(token.nextToken());

            // 못가면
            if(party[a][b] > c) bw.write("Stay here\n");
            // 갈 수 있으면
            else bw.write("Enjoy other party\n");
        }

        bw.close();
    }

    static void floyd(){

        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                for(int k=1; k<=N; k++){
                    if(party[j][k] > party[j][i] + party[i][k]){
                        party[j][k] = party[j][i] + party[i][k];
                    }
                }
            }
        }
    }
}
//https://code-lab1.tistory.com/191