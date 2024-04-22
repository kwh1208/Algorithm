import java.util.*;
import java.io.*;

public class 백준_A와B {
    public static void main(String[] args)throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringBuffer S = new StringBuffer(br.readLine());
        StringBuffer T = new StringBuffer(br.readLine());
        int answer = 0;

        while(S.length() < T.length()){
            if(T.charAt(T.length() - 1) == 'A'){
                T.deleteCharAt(T.length() - 1);
            }
            else if(T.charAt(T.length() - 1) == 'B'){
                T.deleteCharAt(T.length() - 1);
                T.reverse();
            }
        }
        // StringBuffer 는 바로 비교 불가
        if(T.toString().equals(S.toString())) answer = 1;
        else answer = 0;
        bw.write(answer+"");
        bw.close();
    }
}