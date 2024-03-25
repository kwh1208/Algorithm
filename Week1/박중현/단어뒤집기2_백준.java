import java.io.*;
import java.util.*;
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 단어뒤집기2
        // 문자열 s 가 주어졌을때 이 문자열에서 단어만 뒤집는다
        // 문자열의 시작과 끝은 공백이 아니다.
        // <와 >가 문자열에 있는 경우 번갈아가면서 등장하며, <이 먼저 등장한다.
        // 태그는 <로 시작해서 >로 끝나는 길이가 3 이상인 부분 문자열
        // <와> 사이에는 알파벳 소문자와 공백만 있다.
        // 단어는 알파벳 소문자와 숫자로 이루어진 부분 문자열이고, 연속하는 두 단어는 공백 하나로 구분한다
        // 태그는 단어가 아니다
        // 문자열 S의 길이는 십만 이하이다.

        // 1. 문자열만 뒤집는다.
        // 2. 태그는 원상태로 둔다.
        // 3. 뒤집기 or 반대로 출력 -> 스택
        // 4. 문자열 길이 십만 이하 -> N제곱까지는 가능 , 백만넘으면 O(NlogN) 시간복잡도 필요
        // 5. 스택(반대출력)과 큐(그대로출력) 같이 사용
        // 6. 스택에 몰아넣었던 문자열 그대로 다시 큐에 넣기 -> 시점은? 태그 or 띄어쓰기
        // 7. <   ==> 큐에 넣기
        // 8. >   ==> 스택에 넣기

        String str = br.readLine();

        // 스택 자료구조 선언
        Deque<Character> stack = new ArrayDeque<>();
        // 큐 자료구조 선언
        Deque<Character> queue = new ArrayDeque<>();

        boolean isReverse = true;

        for (int i = 0; i < str.length(); i++) {
            Character s = str.charAt(i);

            if (s == '<') {
                while (!stack.isEmpty()) {
                    queue.offer(stack.pop());
                }
                queue.offer(s);
                isReverse = false;
                continue;
            }

            if (s == '>') {
                queue.offer(s);
                isReverse = true;
                continue;
            }

            if (isReverse && s == ' ') {
                while (!stack.isEmpty()) {
                    queue.offer(stack.pop());
                }
                queue.offer(s);
                continue;
            }

            if (isReverse) {
                stack.push(s);
            } else {
                queue.offer(s);
            }

        }

        // 마지막에 태그도 띄어쓰기도 없는데 stack 에 값이 남아있는 경우
        // noojkeab enilno egduj
        while (!stack.isEmpty()) {
            queue.offer(stack.pop());
        }

        while (!queue.isEmpty()) {
            System.out.print(queue.poll());
        }

        bw.flush();
        br.close();
        bw.close();
    }

}