package Main;

import java.util.*;
import java.io.*;

class Main1283 {
    private static int N;
    private static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    private static Set<Character> shortSet = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        // 핵심.
        // 1. 각 단어의 첫 글자만을 판별하는 반복문
        // 2. 설명의 모든 알파벳들을 판단하는 반복문
        for (int i = 0; i < N; i++) {
            String option = br.readLine();
            shortCutKey(option);
        }
        bw.close();
    }

    private static void shortCutKey(String option) throws IOException{
        String[] wordArray = option.split(" ");
        StringBuilder sb = new StringBuilder();
        boolean check = false;

        // 각 단어의 첫 글자만을 판별하는 반복문
        for (int j = 0; j < wordArray.length; j++) {
            char shortCut = wordArray[j].charAt(0);
            char lower = Character.toLowerCase(shortCut);

            // 첫 글자로 단축키 설정 가능하다면, [] 넣어서 출력
            if (!check && !shortSet.contains(lower) && shortCut != ' ') {
                shortSet.add(lower);

                // [단축키] 지정
                sb.append("[" + shortCut + "]");

                // 예외 사항 발생으로 풀이 지체.
                // ex) 입력 : Cucut tut 의 경우
                // -> 출력 : Cucut [t]ut 가 되어야 하는데
                // 아래 코드는 Cucut [t] tut 로 출력됨.
                // = else 문으로 첫 단어 Cucut 출력하고
                // + 둘째 단어에서 찾은 단축키 [t] 출력하고
                // + [t] 다음부터 출력
//                sb.append(option.substring(option.indexOf(shortCut) + 1));

                // 단축키 지정 이후부터 해당 단어 끝까지
                sb.append(wordArray[j].substring(1) + " ");
                check = true;
//                break;
            }

            // 해당 단어의 첫 글자로 단축키 설정 안된다면, 안된 채로 해당 단어 출력
            else {
                sb.append(wordArray[j] + " ");
            }
        }

        // 첫 글자로 단축키 설정했으면 sb 한 번에 출력
        if (check) {
            bw.write(sb + "\n");
            return;
        }

        sb = new StringBuilder();

        // 설명의 모든 알파벳들을 판단하는 반복문
        for (int j = 0; j < option.length(); j++) {
            char shortCut = option.charAt(j);
            char lower = Character.toLowerCase(shortCut);

            // 단축키 목록에 없고 and 빈 칸이 아니라면
            if (!shortSet.contains(lower) && shortCut != ' ') {
                shortSet.add(lower);
                // [단축키] 지정
                sb.append("[" + shortCut + "]");
                // [단축키] 이후부터 출력
                sb.append(option.substring(option.indexOf(shortCut) + 1));
                break;
            }
            // 단축키 안된다면
            else {
                sb.append(shortCut);
            }
        }
        bw.write(sb + "\n");
    }
}
//예외 판별 입력
//5
//Cu
//Cucu
//Cucut tut
//Cucut ytt utu
//Cucut ytj utu

//https://hansel.tistory.com/143