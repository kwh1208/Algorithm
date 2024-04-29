import java.util.*;
class Solution {
    public String solution(String new_id) {
        String answer = "";

        // 카카오 서비스 -> 아이디를 생성하는 업무를 담당
        // 네오에게 주어진 첫 업무는 새로 가입하는 유저들이 카카오 아이디 규칙에 맞지 않는 아이디를 입력했을때
        // 입력된 아이디와 유사하면서 규칙에 맞는 아이디를 추천해주는 프로그램 개발

        // 아이디 길이는 3자 이상 15자 이하
        // 아이디는 알파벳 소문자, 숫자, 빼기, 밑줄 , 마침표 문자만 사용
        // 마침표는 처음과 끝에 사용할 수 없으면 또한 연속으로 사용할 수 없다.

        // 1. 모든 대문자를 대응되는 소문자로 치환한다.
        // 2. 알파벳 소문자, 숫자, 빼기, 밑줄, 마침표를 제외한 모든 문자를 제거
        // 3. 마침표가 2번 이상 연속된 부분을 하나의 마침표로 치환한다.
        // 4. 마침표가 처음이나 끝에 위치한다면 제거한다.
        // 5. 빈 문자열이라면 "a"를 대입한다.
        // 6. 길이가 16자 이상이면, 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거한다.
        //      만약, 제거 후 마침표가 new_id 끝에 위치하면 끝에 위치한 마침표 문자를 제거한다.
        // 7. new_id 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙인다.

        // 대문자를 소문자로 바꾸자
        answer = new_id.toLowerCase();
        // 알파벳 소문자, 숫자, 빼기, 밑줄, 마침표를 제외한 모든 문자 제거
        answer = answer.replaceAll("[^a-z0-9-_.]","");
        // 마침표가 2번 이상 연속된 부분을 하나의 마침표로 치환한다.
        Character prevChar = '!';
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < answer.length(); i++) {
            if(answer.charAt(i) == '.') {
                if(prevChar == '.') {
                    prevChar = answer.charAt(i);
                    continue;
                } else {
                    sb.append(answer.charAt(i));
                    prevChar = answer.charAt(i);
                }
            } else {
                prevChar = answer.charAt(i);
                sb.append(answer.charAt(i));
            }
        }

        answer = sb.toString();

        if(answer.length() > 0) {
            if(answer.substring(0,1).equals(".")) {
                answer = answer.substring(1, answer.length());
            }
        }

        if(answer.length() > 0) {
            if(answer.substring(answer.length() - 1, answer.length()).equals(".")) {
                answer = answer.substring(0, answer.length() - 1);
            }
        }

        if(answer.length() == 0) {
            answer += "a";
        }

        if(answer.length() >= 16) {
            answer = answer.substring(0, 16);
        }

        if(answer.charAt(answer.length()) == '.') {
            answer = answer.substring(0, answer.length() - 1);
        }

        sb = new StringBuilder();

        if (answer.length() <= 2) {
            int len = answer.length();
            Character endChar = answer.charAt(answer.length());
            while (len <= 3) {
                sb.append(endChar);

                len++;
            }
        }

        answer = sb.toString();
        return answer;
    }
}