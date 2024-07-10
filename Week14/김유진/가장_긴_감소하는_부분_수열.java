import java.io.*;
import java.util.*;

//시간복잡도가 크지 않아서 막 해도 괜찮음 
public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[1001];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++){
            int num = Integer.parseInt(st.nextToken());
            
            int maxVal = 0;
            for(int j = 1000;j>num;j--){
                if(dp[j] > maxVal){
                    maxVal = dp[j];
                }
            }
            
            dp[num] = maxVal+1;
        }
        
        int res = 0;
        for(int i=1;i<=1000;i++){
            if(res < dp[i]){
                res = dp[i];
            }
        }
        
        System.out.print(res);
    }
}
