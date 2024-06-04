package Main;

import java.io.*;

public class Main1904 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N + 1];

        // 이진수 모두 1인 경우 1가지는 무조건 있음
        arr[1] = 1;
        // arr[2] = 모두 1 타일인 경우 + 모두 00 타일인 경우
        if(N >= 2) arr[2] = 2;

        // 길이 N>=2 부터 '00' 타일이 들어가는 경우 생김
        // N 이 짝수면 '00' 타일만으로 이뤄져서 모두 0인 경우 생김
        //1 1  )모두 1 타일 -> 1
        //2 2  )모두 1 타일 -> 1, 00 타일 1개 -> 1
        //3 3  )모두 1 타일 -> 1, 00 타일 1개 -> 2
        //4 5  )모두 1 타일 -> 1, 00 타일 1개 -> 3, 00 타일 2개 -> 1
        //5 8  )모두 1 타일 -> 1, 00 타일 1개 -> 4, 00 타일 2개 -> 3
        //6 13 )모두 1 타일 -> 1, 00 타일 1개 -> 5, 00 타일 2개 -> 6, 00 타일 3개 -> 1
        //7 21 )모두 1 타일 -> 1, 00 타일 1개 -> 6, 00 타일 2개 -> 10, 00 타일 3개 -> 4

        // 시간 제한 맞추기 위해 배열 계산 시에 (% 15746) 를 해준다.
        // 모듈러 연산의 분배법칙에 의해 (A+B)%C = (A%C + B%C)%C 가 성립
        // 하기 때문에 답 도출에 영향 없음. (= 블로그 출처)
        for(int i=3; i<=N; i++){
            arr[i] = (arr[i-2] + arr[i-1]) % 15746;
        }

        // arr[N] % 15746 -> X
        bw.write(arr[N] + "");
        bw.close();
    }
}
//https://st-lab.tistory.com/125