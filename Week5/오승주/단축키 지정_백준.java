import java.io.*;
import java.util.*;

public class Solution_for_1283 {
    static Set<Character> shortCutKey = new HashSet<>();
    static ArrayList<ArrayList<Character>> options;

    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        options = new ArrayList<> (N);

        for (int n = 0; n < N; n++) {
            options.add(new ArrayList<>());
        }

        for (int n = 0; n < N; n++) {
            String option = br.readLine();
            ArrayList<Character> curr = options.get(n);

            for (int i = 0; i < option.length(); i++) {
                curr.add(option.charAt(i));
            }
        }
        
        outerLoop:
        for (int n = 0; n < N; n++) {
            ArrayList<Character> curr = options.get(n);

            /*
            첫번째 단어의 첫글자 검색해서 단축키로 등록 가능하다면
            단축키를 등록하고
            단축키 앞 뒤로 대괄호 삽입함
            */
            if (!shortCutKey.contains(Character.toUpperCase(curr.get(0)))) {

                shortCutKey.add(Character.toUpperCase(curr.get(0)));

                curr.add(0, '[');
                curr.add(2, ']');
                continue;
            }

            else {
                /*
                 * 단어들의 첫글자들을 공백을 이용하여 탐색함
                 * 단축키로 등록 가능하다면
                 * 단축키로 등록하고
                 * 단축키 앞 뒤로 대괄호를 삽입함
                 */
                for (int i = 0; i < curr.size(); i++) {
                    if (curr.get(i) == ' ') {
                        if (!shortCutKey.contains(Character.toUpperCase(curr.get(i+1)))) {

                            shortCutKey.add(Character.toUpperCase(curr.get(i+1)));

                            curr.add(i+1, '[');
                            curr.add(i+3, ']');
                            
                            continue outerLoop;
                        }
                    }
                }
                /*
                 * 왼쪽에서부터 차례대로 "공백이 아닌!" 모든 알파벳을 탐색함
                 * 단축키로 등록 가능하다면 
                 * 단축키로 등록하고
                 * 단축키 앞 뒤로 대괄호를 삽입함
                 */
                for (int i = 0; i < curr.size(); i++) {
                    if (!shortCutKey.contains(Character.toUpperCase(curr.get(i))) && curr.get(i) != ' ') {
                        
                        shortCutKey.add(Character.toUpperCase(curr.get(i)));

                        curr.add(i, '[');
                        curr.add(i+2, ']');
                        
                        break;
                    }
                }
            }
        }
        for (ArrayList<Character> option : options) {
            for (Character word : option) {
                System.out.print(word);
            }
            System.out.println();
        }
    }
}