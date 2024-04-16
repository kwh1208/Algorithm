import java.io.*;
import java.util.StringTokenizer;

class Main14888 {
    static int N;
    static int MAX = Integer.MIN_VALUE;
    static int MIN = Integer.MAX_VALUE;
    static int[] calCount = new int[4];
    static int[] numArray;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token;

        N = Integer.parseInt(br.readLine());
        numArray = new int[N];

        token = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            numArray[i] = Integer.parseInt(token.nextToken());
        }

        token = new StringTokenizer(br.readLine());
        for(int i=0; i<4; i++){
            calCount[i] = Integer.parseInt(token.nextToken());
        }

        backTracking(numArray[0], 0);

        bw.write(MAX + "\n" + MIN);
        bw.close();
    }

    static void backTracking(int num, int idx){
        // idx = 연산자 사용 개수
        if(idx >= N-1){
            MAX = Math.max(MAX, num);
            MIN = Math.min(MIN, num);
            return;
        }

        for(int i=0; i<4; i++){
            if(calCount[i] >= 1){
                calCount[i]--;
                switch(i){
                    case 0:
                        backTracking(num + numArray[idx + 1], idx + 1);
                        break;
                    case 1:
                        backTracking(num - numArray[idx + 1], idx + 1);
                        break;
                    case 2:
                        backTracking(num * numArray[idx + 1], idx + 1);
                        break;
                    case 3:
                        backTracking(num / numArray[idx + 1], idx + 1);
                        break;
                }
                calCount[i]++;
            }
        }
    }
}
//https://st-lab.tistory.com/121