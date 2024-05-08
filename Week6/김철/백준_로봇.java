import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main13901 {

    static int r, c, k;
    static int answerR;
    static int answerC;
    static int[][] aa;
    static int[] dd = new int[4];

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token;

        token = new StringTokenizer(br.readLine());
        r = Integer.parseInt(token.nextToken());
        c = Integer.parseInt(token.nextToken());
        aa = new int[r][c];

        // 장애물 개수
        k = Integer.parseInt(br.readLine());

        // 장애물 설정
        for(int i=0; i<k; k++){
            token = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(token.nextToken());
            int y = Integer.parseInt(token.nextToken());
            aa[x][y] = 2;
        }

        // 시작위치 설정
        token = new StringTokenizer(br.readLine());
        answerR = Integer.parseInt(token.nextToken());
        answerC = Integer.parseInt(token.nextToken());
        aa[answerR][answerC] = 1;

        // 방향 설정
        token = new StringTokenizer(br.readLine());
        for(int i=0; i<4; i++){
            dd[i] = Integer.parseInt(token.nextToken());
        }

        DFS(0, 0);

        bw.write(answerR + " " + answerC);
        bw.close();
    }

    static void DFS(int idx, int count){
        if(count == 4) return;

        int x = answerR;
        int y = answerC;

        if(dd[idx] == 1) x -= 1;
        if(dd[idx] == 2) x += 1;
        if(dd[idx] == 3) y -= 1;
        if(dd[idx] == 4) y += 1;

        if(x >= 0 && x < r && y >= 0 && y < c){
            if(aa[x][y] == 0){
                answerR = x;
                answerC = y;
                aa[x][y] = 1;
                DFS(idx, 0);
                return;
            }
        }

        idx = idx + 1 == 4 ? 0 : idx + 1;
        DFS(idx, count + 1);
    }
}