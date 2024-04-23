
import java.io.*;
import java.util.*;
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 수빈이는 A와 B로만 이루어진 영어 단어가 존재한다는 사실에 놀랐다.
        // 대표적인 예로 AB , BAA , AA , ABBA 가 있다

        // 두 문자열 S와 T가 주어졌을때, S를 T로 바꾸는 게임

        // 문자열의 뒤에 A를 추가한다.
        // 문자열을 뒤집고 뒤에 B를 추가한다.

        // ex. B / ABBA
        // B -> BA
        // BA -> ABB
        // ABB -> ABBA

        String startStr = br.readLine();
        String endStr = br.readLine();
        int startLength = startStr.length();
        int endLength = endStr.length();

        // endStr 기준으로 접근하는 게 좋을듯
        // endStr 이 끝에 B가 있다 ? => 한 번 B 를 빼고 뒤집어보기
        // endStr 끝에 A가 있다? => A를 빼보자

        Deque<Character> deque = new ArrayDeque<>();

        for (int i = 0; i < endLength; i++) {
            deque.offer(endStr.charAt(i));
        }

        while (startLength < endLength) {
            if (deque.peekLast() == 'A') {
                deque.removeLast();
            } else {
                deque.removeLast();
                Stack<Character> tempStack = new Stack<>();
                while (!deque.isEmpty()) {
                    tempStack.push(deque.removeFirst());
                }

                while(!tempStack.isEmpty()) {
                    deque.offer(tempStack.pop());
                }

            }

            endLength--;
        }

        boolean isPossible = true;

        for (int i = 0; i < startLength; i++) {
            if (startStr.charAt(i) != deque.poll()) {
                isPossible = false;
                break;
            }
        }

        if (isPossible) {
            bw.write(1+"");
        } else {
            bw.write(0+"");
        }

        bw.flush();
        br.close();
        bw.close();
    }

}