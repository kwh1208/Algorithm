// 문자열 S가 주어졌을 때, 이 문자열에서 단어만 뒤집으려고 한다.
// 먼저, 문자열 S는 아래와과 같은 규칙을 지킨다.
// 알파벳 소문자('a'-'z'), 숫자('0'-'9'), 공백(' '), 특수 문자('<', '>')로만 이루어져 있다.
// 문자열의 시작과 끝은 공백이 아니다.
// '<'와 '>'가 문자열에 있는 경우 번갈아가면서 등장하며, '<'이 먼저 등장한다. 또, 두 문자의 개수는 같다.
// 태그는 '<'로 시작해서 '>'로 끝나는 길이가 3 이상인 부분 문자열이고, '<'와 '>' 사이에는 알파벳 소문자와 공백만 있다. 
// 단어는 알파벳 소문자와 숫자로 이루어진 부분 문자열이고, 연속하는 두 단어는 공백 하나로 구분한다. 
// 태그는 단어가 아니며, 태그와 단어 사이에는 공백이 없다.
// 이번 문제의 경우 꺽새 내부는 동일하게 처리하되, 꺽새가 아닌 경우 단어를 뒤집어주어야한다.

import java.io.*;
import java.util.*;

public class Reverse_bj {

    // throws 사용하는 이유: 해당 메서드 내에서 예외처리를 하지 않고 해당 메서드를 사용한 곳에서 예외 처리를 하도록 예외를 위로 던지는것
    public static void main(String args[]) throws Exception {

        // 키보드의 입력이 있을 때마다 한 문자씩 버퍼로 전송
        // Scanner와 달리 BufferedReader는 개행문자만 경계로 인식하고 입력받은 데이터가 String으로 고정
        // Scanner보다 속도가 빠르다
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // String으로 리턴 값이 고정되어 있기 때문에,
        // 다른 타입으로 입력을 받고자 한다면 반드시 형변환이 필요하다.
        // 그리고, 예외처리 반드시 필요
        // 문자열 str 입력
        String str = br.readLine();

        // 결과 값을 출력할 StringBuilder
        StringBuilder sb = new StringBuilder();

        // 열린 꺽새와 닫힌 꺽새를 판별하기 위한 flag
        boolean flag = false;

        // 후입선출 기능을 이용하기 위한 자료구조 Stack
        Stack<Character> stack = new Stack<>();

        // 문자열 str의 길이만큼 반복문 수행
        for (int i = 0; i < str.length(); i++) {

            // 열린 꺽새를 만났을 경우
            // stack이 비어있지 않다면 모든 원소를 꺼내고 flag를 true
            if (str.charAt(i) == '<') {
                while (!stack.isEmpty()) {
                    sb.append(stack.pop());
                }
                flag = true;
            }

            // 닫힌 꺽새를 만났을 경우, flag를 false로 입력 후 '>'저장
            else if (str.charAt(i) == '>') {
                flag = false;
                sb.append(str.charAt(i));
                continue;
            }

            // flag가 true일 경우, '>'를 만나기 전까지 그대로 입력
            if (flag == true) {
                sb.append(str.charAt(i));
            }

            // flag가 false일 경우, 꺽새 이외의 문자를 처리
            else if (flag == false) {

                // 해당 인덱스의 i번째 문자가 공백일 경우, 모든 원소를 POP수행 후 공백 추가
                if (str.charAt(i) == ' ') {
                    while (!stack.isEmpty()) {
                        sb.append(stack.pop());
                    }
                    sb.append(' ');
                    continue;
                } else {
                    // 그외의 경우, Stack에 문자 추가
                    stack.push(str.charAt(i));
                }
            }

            // 반복문이 마지막 횟수일 때, 스택이 비어있지 않다면 원소 추가
            if (i == str.length() - 1) {
                while (!stack.isEmpty()) {
                    sb.append(stack.pop());
                }
            }
        }

        // 결과문 출력
        System.out.println(sb);
    }
}
