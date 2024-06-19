import java.util.Scanner;

public class BOJ_9663 {
    static int answer = 0;
    static int N;
    static int[] arr;
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        N = scan.nextInt();
        arr = new int[N];

        nQueen(0);
        System.out.println(answer);
    }

    static void nQueen(int row) {
        if(row == N) {
            answer++;
            return;
        }

        for(int i=0; i<N; i++) {
            arr[row] = i;

            if(isPossible(row)) {
                nQueen(row+1);
            }
        }
    }

    public static boolean isPossible(int col) {
        for(int i=0; i<col; i++) {
            if(arr[col] == arr[i]) {
                return false;
            }else if(Math.abs(col-i) == Math.abs(arr[col] - arr[i])) {
                return false;
            }
        }
        return true;
    }
}

//https://st-lab.tistory.com/118