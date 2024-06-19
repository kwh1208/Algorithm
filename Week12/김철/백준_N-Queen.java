package Main;

import java.io.*;
import java.util.*;

public class Main9663 {
    static int N;
    static int[] chess;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());

        // 체스에서 퀸은 상하좌우 대각선까지 이동 가능하다.

        // 인덱스가 열, 값이 행.
        // chess[0] = 1; => 1행 0열 이라는 뜻.
        chess = new int[N];

        DFS(0);
        bw.write(count + "");
        bw.close();
    }

    static void DFS(int depth){
        // N번
        if(depth == N){
            count++;
            return;
        }
        // i 행에서 depth 열에 놓을 수 있는지 파악하기 위한 반복문
        // i행에서 depth 열에 놓을 수 있다면 다시 DFS로 들어가서 depth + 1 열에서는 어떤 행에 놓을 수 있는지 판단한다.
        for(int i=0; i<N; i++){
            chess[depth] = i;

            if(check(depth)){
                // depth 열 i 행에 퀸을 놓게 되었으니 다음 열인 depth + 1 부터 다시 판단 시작
                DFS(depth + 1);
            }
        }
    }

    // 본 DFS 메소드는 열을 나타내는 depth 값을 증가하며 판단하기 때문에
    // check 메소드는 행을 기준으로 겹치는 퀸이 있는지 판단한다.
    static boolean check(int col){

        for(int i=0; i<col; i++){
            // col 열에 위치한 퀸과 , 0~i 열까지 위치한 퀸 중 행이 겹치는 것이 있는지
            if(chess[col] == chess[i]) return false;
                // 서로 대각선 관계에 있는지 판단하고
            else if(Math.abs(col - i) == Math.abs(chess[col] - chess[i])) return false;
        }
        // 여태 퀸을 놓았던 곳과 아무도 겹치지 않으면
        // DFS 속 if 문으로 돌아가서,
        // depth 열 i 행에 퀸을 놓아도 겹치지 않으니
        // chess[depth] = i 로 값을 기입한다.
        return true;
    }
}
//https://st-lab.tistory.com/118