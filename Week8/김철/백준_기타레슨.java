import java.io.*;
import java.util.*;

public class Main2343 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token;

        token = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(token.nextToken());
        int M = Integer.parseInt(token.nextToken());

        int[] lecture = new int[N];
        // 모든 강의 시간 합
        int max = 0;
        // 강의 중 가장 긴 강의 시간
        int min = 1;

        token = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            lecture[i] = Integer.parseInt(token.nextToken());
            if(lecture[i] > min) min = lecture[i];
            max += lecture[i];
        }

        int mid = 0;

        // max = 45
        // min = 9
        while(min <= max){
            // 27
            mid = (min + max) / 2;

            int sum = 0;
            int count = 0;

            for(int i=0; i<N; i++){
                if(sum + lecture[i] > mid){
                    sum = 0;
                    count++;
                }
                sum += lecture[i];
            }

            if(sum != 0) count++;

            if(count > M) min = mid + 1;
            else max = mid - 1;
        }
        bw.write(min + "");
        bw.close();
    }
}
//https://velog.io/@chosj1526/%EB%B0%B1%EC%A4%80-2343-%EA%B8%B0%ED%83%80-%EB%A0%88%EC%8A%A8-JAVA