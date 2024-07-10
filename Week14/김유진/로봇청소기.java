import java.util.*;
import java.io.*;

class Coor{
    int x;
    int y;
    int d;

    public Coor(int x, int y, int d){
        this.x=x;
        this.y=y;
        this.d=d;
    }
}

class Direction{
    int dx;
    int dy;

    public Direction(int dx, int dy){
        this.dx=dx;
        this.dy=dy;
    }
}

class Main{
    static int N;
    static int M;
    static int[][] rooms; //0: 청소x 1: 벽 2: 청소o
    static int cleanCnt;
    static Coor nowCoor;

    //북, 동, 남, 서
    static Direction[] directions = {
            new Direction(-1, 0),
            new Direction(0, 1),
            new Direction(1, 0),
            new Direction(0, -1)
    };

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        nowCoor = new Coor(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));

        rooms = new int[N][M];
        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<M;j++){
                rooms[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        clean();
        System.out.print(cleanCnt);
    }

    static void clean(){
        while(true){
            if(rooms[nowCoor.x][nowCoor.y] == 0){
                rooms[nowCoor.x][nowCoor.y] = 2;
                cleanCnt ++;
            }

            if(isNearAllCleaned()){
                int nowD = nowCoor.d;
                int newX = nowCoor.x + directions[nowD].dx*-1;
                int newY = nowCoor.y + directions[nowD].dy*-1;

                if(rooms[newX][newY] == 1){
                    return; //break;
                }

                nowCoor = new Coor(newX, newY, nowCoor.d);
                continue;
            }

            //else
            int newD = nowCoor.d - 1;
            newD = (newD<0)?newD+4:newD;

            int newX = nowCoor.x + directions[newD].dx;
            int newY = nowCoor.y + directions[newD].dy;
            if(rooms[newX][newY] == 0){
                nowCoor = new Coor(newX, newY, newD);
            } else{
                nowCoor = new Coor(nowCoor.x, nowCoor.y, newD);
            }
        }
    }

    static boolean isNearAllCleaned(){
        for(Direction d:directions){
            int newX = nowCoor.x+d.dx;
            int newY = nowCoor.y+d.dy;

            if(rooms[newX][newY] == 0){
                return false;
            }
        }

        return true;
    }
}
