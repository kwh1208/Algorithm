import java.util.*;
import java.io.*;

class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] values = br.readLine().split(" ");
        int K = Integer.parseInt(values[0]);
        int N = Integer.parseInt(values[1]);

        List<Integer> ks = new ArrayList<>();
        int maxK = Integer.MIN_VALUE;
        while(K-->0){
            int k = Integer.parseInt(br.readLine());
            if(maxK<k){
                maxK = k;
            }

            ks.add(k);
        }

        long min = 1; long max = maxK;
        long res = -1;
        while(min<=max){
            long mid = (min+max)/2;

            int count = count(ks, mid);

            if(count < N){
                max = mid -1;
                continue;
            }

            res = mid;
            min = mid+1;
        }

        System.out.print(res);
    }

    private static int count(List<Integer> ks, long mid){
        int count =0;
        for(int k:ks){
            count+=(k/mid);
        }

        return count;
    }
}
