import java.io.*;
import java.util.*;

public class Solution_for_17413 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        Deque<Character> stack = new ArrayDeque<>();

        // 문자열 입력받기
        String str = br.readLine();

        int index = 0;
        while (index < str.length()) {
            char word = str.charAt(index);

            // 태그일 경우
            if (word == '<') {
                answer.append(word);
                index++;

                while (str.charAt(index) != '>') {
                    answer.append(str.charAt(index));
                    index++;
                }
                answer.append(str.charAt(index));
                index++;
            }

            // 단어인 경우 -> 공백이 나올 때까지 스택에 쌓음
            else {
                stack.push(str.charAt(index));
                index++;

                // 공백이나 태그가 아닐때까지 스택에 쌓음
                while (index < str.length() && str.charAt(index) != ' ' && str.charAt(index) != '<') {
                    stack.push(str.charAt(index));
                    index++;
                }

                while (!stack.isEmpty()) {
                    answer.append(stack.pop());
                }

                // 공백인 경우
                if (index < str.length() && str.charAt(index) == ' ') {
                    answer.append(str.charAt(index));
                    index++;
                }

                // 태그인 경우 -> x
            }
        }
        System.out.println(answer);
    }
}