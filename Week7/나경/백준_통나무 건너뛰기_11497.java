import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringJoiner;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringJoiner sj = new StringJoiner("\n");

        int T = Integer.parseInt(br.readLine());

        for(int i=0; i<T; i++) {
            int N = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine());
            int[] woods = new int[N];

            for(int j=0; j<N; j++) {
                woods[j] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(woods); //오름차순으로 정렬

            //정렬된 배열에서 한칸을 건너뛰어 가는게 가장 단차를 줄일 수 있음
            int diff = 0;
            for(int k=woods.length-1; k >=2; k--){
                diff = Math.max(diff, woods[k]-woods[k-2]);
            }

            sj.add(String.valueOf(diff));
        }

        System.out.println(sj);
    }
}
