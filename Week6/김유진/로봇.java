import java.util.*;
import java.io.*;

public class Main{

    //상하좌우
    static int[][] direct = {
            {0, 0}, {-1, 0}, {1, 0}, {0, -1}, {0, 1}
    };

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        boolean[][] impossible = new boolean[N][M];

        int K = Integer.parseInt(br.readLine());
        while(K-->0){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            impossible[x][y]=true;
        }

        st = new StringTokenizer(br.readLine());
        int nowX = Integer.parseInt(st.nextToken());
        int nowY = Integer.parseInt(st.nextToken());
        impossible[nowX][nowY] = true;

        st = new StringTokenizer(br.readLine());
        Queue<Integer> directQueue = new LinkedList<>();
        for(int i=0;i<4;i++){
            directQueue.add(Integer.parseInt(st.nextToken()));
        }

        int continuousFail = 0;
        while(true){
            if(continuousFail == 4){
                break;
            }

            int nowDirect = directQueue.peek();

            int newX = nowX + direct[nowDirect][0];
            int newY = nowY + direct[nowDirect][1];

            if(newX < 0 || newX >=N || newY<0 || newY>=M || impossible[newX][newY]){
                continuousFail ++;

                directQueue.poll();
                directQueue.add(nowDirect);
                continue;
            }

            impossible[newX][newY]=true;
            nowX = newX;
            nowY = newY;
            continuousFail = 0;
        }

        System.out.println(nowX+" "+nowY);
    }
}
