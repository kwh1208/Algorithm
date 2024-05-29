import java.util.Scanner;

public class BOJ_2579 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int cnt = scan.nextInt();
        int[] stair = new int[cnt+1];
        int[] score = new int[cnt+1];

        for(int i=1; i<=cnt; i++) {
            stair[i] = scan.nextInt();
        }
        score[1] = stair[1];
        if(cnt > 1) score[2] = stair[1] + stair[2];
        if(cnt > 2) score[3] = Math.max(stair[1], stair[2]) + stair[3];

        for(int i=4; i<=cnt; i++) {
            score[i] = Math.max(score[i-3]+stair[i-1],  + score[i-2]) + stair[i];
        }

        System.out.println(score[cnt]);

    }
}
