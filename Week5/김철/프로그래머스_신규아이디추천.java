package programmers;

import java.util.*;

public class Solution신규아이디추천 {
    public static void main(String[] args) {
        String new_id = "";
        System.out.println(solution("...!@BaT#*..y.abcdefghijklm"));
    }
    static String solution(String new_id) {
        String answer = "";

        // new_id가
        // 대문자는 소문자로,
        // 알파벳소문자 , - , _ , . 이외의 문자를 제거.
        // 마침표가 2번 이상 연속되는 구간은 하나로 치환.
        // 마침표가 처음 or 끝에 위치하면 제거
        // 빈 문자열이면 "a" 대입
        // 16자 이상이면 처음~15자만 남긴다. -> 제거하고나서 .가 끝에 위치하면 그것도 제거(substring사용)
        // 길이가 2자 이하라면 마지막 문자를 길이가 3이 될 때까지 반복해서 채우기

        //처음
        System.out.println("처음 "+new_id);

        //1단계
        new_id = new_id.toLowerCase();
        System.out.println("1단계 " + new_id);

        //2단계 : 소문자, 숫자, - _ .
        char[] step2Arr = new_id.toCharArray();
        StringBuilder sb = new StringBuilder();
        for(char c : step2Arr){
            if(('a' <= c && c <= 'z') || ('0' <= c && c <= '9') || c == '-' || c == '_' || c == '.'){
                sb.append(c);
            }
        }
        String step2 = sb.toString();
        System.out.println("2단계 " + step2);

        //3단계
        String step3 = step2;
        while(step3.contains("..")){
            step3 = step3.replace("..", ".");
        }
        System.out.println("3단계 " + step3);

        //4단계 마침표가 처음이나 끝에 위치하면 제거.
        String step4 = step3;
        if(step4.length() > 0){
            if(step4.charAt(0) == '.'){
                step4 = step4.substring(1, step4.length());
            }
        }
        if(step4.length() > 0){
            if(step4.charAt(step4.length() - 1) == '.'){
                // 마지막 마침표 제거
                step4 = step4.substring(0, step4.length() - 1);
            }
        }

        System.out.println("4단계 " + step4);


        //5단계
        String step5 = step4;
        if(step5.equals("")){
            step5 = "a";
        }
        System.out.println("5단계 " + step5);

        //6단계
        String step6 = step5;
        if(step6.length() >= 16){
            step6 = step6.substring(0, 15);
            if(step6.charAt(step6.length() - 1) == '.'){
                // 마지막 마침표 제거
                step6 = step6.substring(0, step6.length() - 1);
            }
        }
        System.out.println("6단계 " + step6);

        //7단계
        String step7 = step6;

        if(step7.length() <= 2){
            sb = new StringBuilder();
            sb.append(step7);
            String s = step7.substring(step7.length() - 1);
            for(int i=0; i < 3 - step7.length(); i++){

                sb.append(s);
            }
            step7 = sb.toString();
        }

        System.out.println("7단계 " + step7);

        answer = step7;
        System.out.println("답 " + answer);

        return answer;
    }
}
//https://velog.io/@hammii/%ED%94%84%EB%A1%9C%EA%B7%B8%EB%9E%98%EB%A8%B8%EC%8A%A4-%EC%8B%A0%EA%B7%9C-%EC%95%84%EC%9D%B4%EB%94%94-%EC%B6%94%EC%B2%9C-java-2021-KAKAO-BLIND-RECRUITMENT