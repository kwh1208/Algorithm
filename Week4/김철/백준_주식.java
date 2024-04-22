import java.util.*;
import java.io.*;

class 백준_주식{
    static int T, N;
    static int[] stock;

    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token;

        T = Integer.parseInt(br.readLine());

        // 1. 주식 하나 구매
        // 2. 보유 주식 판매
        // 3. X
        for(int i=0; i<T; i++){
            N = Integer.parseInt(br.readLine());
            stock = new int[N];
            token = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                stock[j] = Integer.parseInt(token.nextToken());
            }

            int beneSum = 0;
            int benePoint = stock[N-1];
            List<Integer> buyList = new ArrayList<>();
            for(int j=N-2; j>=0; j--){
                if(stock[j] <= benePoint){
                    buyList.add(stock[j]);
                }

                if(stock[j] > benePoint || j == 0){
                    for(int temp : buyList){
                        beneSum += benePoint - temp;
                    }
                    benePoint = stock[j];
                    buyList.clear();
                }
            }
            bw.write(beneSum + "\n");
        }
        bw.close();
    }
}