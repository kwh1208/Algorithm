// 1. L의 시작점과 끝나는 점을 찾아준다.
// 2. 배열을 생성해서 L위치에 높이 값을 넣어준다.
// 3. 스택으로 방향을 왼쪽,오른쪽으로 나누어서 지붕을 메꿔준다.
// 왼쪽:현재 높이가 이전의 높이보다 작으면,스택에 넣어주고 아닌경우,
// 스택에서 pop을 한 뒤,전 배열값에 현재값을 넣어준다.
// 오른쪽:왼쪽과 동일

// 7
// 2 4
// 11 4
// 15 8
// 4 6
// 5 3
// 8 10
// 13 6

import java.io.*;
import java.util.*;

public class Chango_bj {

    public static void main(String[] args) throws IOException {

        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        // arr 문자열 자료 형 배열 생성
        int[] arr = new int[1001];

        int start = Integer.MAX_VALUE;
        int end = 0;

        for (int i = 0; i < N; i++) {
            // StringTokenizer 클래스는 문자열을 구분자를 이용하여 분리할 때 사용
            StringTokenizer st = new StringTokenizer(br.readLine());
            int L = Integer.parseInt(st.nextToken());
            int H = Integer.parseInt(st.nextToken());
            arr[L] = H;
            // 시작점 찾기
            start = Math.min(L, start);
            // 끝점 찾기
            end = Math.max(L, end);
        }
        // stack 자료형의 height 변수 선언
        Stack<Integer> height = new Stack<>();

        // 왼쪽 비교
        int temp = arr[start];
        for (int i = start + 1; i <= end; i++) {
            // 현재 높이가 이전 지점 높이보다 작으면
            if (arr[i] < temp) {
                height.push(i);
                // 현재 높이가 이전 지점 높이보다 크면
            } else {
                // 높이가 비어있지 않으면
                while (!height.isEmpty()) {
                    // stack에서 pop후 해당 인덱스 배열에 현재값 삽입
                    int x = height.pop();
                    arr[x] = temp;
                }
                // 높이가 비어있는 경우
                temp = arr[i];
            }
        }
        height.clear();

        // 오른쪽 비교
        temp = arr[end];
        for (int i = end - 1; i >= start; i--) {
            // 현재 높이가 이전 지점 높이보다 작으면
            if (arr[i] < temp)
                height.push(i);
            else {
                while (!height.isEmpty()) {
                    int x = height.pop();
                    arr[x] = temp;
                }
                temp = arr[i];
            }
        }

        int result = 0;
        for (int i = start; i <= end; i++) {
            result += arr[i];
        }

        sb.append(result).append("\n");
        System.out.print(sb);
    }
}