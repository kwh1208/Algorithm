import java.io.*;
import java.util.*;

public class Main1654 {

    static int K, N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token;

        token = new StringTokenizer(br.readLine());
        // 이미 보유한 길이가 제각각인 랜선 개수
        K = Integer.parseInt(token.nextToken());
        // 필요한 랜선 개수
        N = Integer.parseInt(token.nextToken());

        int[] kLan = new int[K];

        long max = 0;

        for(int i=0; i<K; i++){
            kLan[i] = Integer.parseInt(br.readLine());
            if(kLan[i] > max) max = kLan[i];
        }

        max++;

        long min = 0;
        long mid = 0;

        while(min < max){
            mid = (min + max) / 2;

            long count = 0;

            for(int i=0; i<K; i++){
                count += (kLan[i] / mid);
            }

            if(count < N){
                max = mid;
            }
            else{
                min = mid + 1;
            }
        }
        bw.write(min - 1 + "");
        bw.close();
    }
}
//https://st-lab.tistory.com/269