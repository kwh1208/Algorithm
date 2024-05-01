import java.util.*;
import java.io.*;

class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        for(int t=0;t<T;t++){
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int D = Integer.parseInt(st.nextToken());

            int[][] arr = new int[N][N];
            for(int i=0;i<N;i++){
                st = new StringTokenizer(br.readLine());
                for(int j=0;j<N;j++){
                    arr[i][j]= Integer.parseInt(st.nextToken());
                }
            }

            for(int time=0;time<getTimes(D);time++){
                rotate(arr, N);
            }

            for(int i=0;i<N;i++){
                StringJoiner sj = new StringJoiner(" ");
                for(int j=0;j<N;j++) {
                    sj.add(Integer.toString(arr[i][j]));
                }
                sb.append(sj).append("\n");
            }
        }
        System.out.print(sb);
    }

    private static void rotate(int[][] arr, int N){
        Queue<Integer> queue = new LinkedList<>();

        for(int i=0;i<N;i++){
            queue.add(arr[i][i]);
        }
        for(int i=0;i<N;i++){
            queue.add(arr[i][N/2]);
        }
        for(int i=0;i<N;i++){
            queue.add(arr[N-1-i][i]);
        }
        for(int i=0;i<N;i++){
            queue.add(arr[N/2][i]);
        }

        for(int i=0;i<N;i++){
            arr[i][N/2] = queue.poll();
        }
        for(int i=0;i<N;i++){
            arr[i][N-1-i] = queue.poll();
        }
        for(int i=0;i<N;i++){
            arr[N/2][i] = queue.poll();
        }
        for(int i=0;i<N;i++){
            arr[i][i] = queue.poll();
        }
    }

    private static int getTimes(int d){
        if(d<0){
            d+=360;
        }

        return d/45;
    }

}
