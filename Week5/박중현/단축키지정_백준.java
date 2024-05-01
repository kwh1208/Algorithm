
import java.io.*;
import java.util.*;
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 한글 프로그램의 메뉴에는 총 N 개의 옵션이 있다.
        // 각 옵션들은 한 개 또는 여러 개의 단어로 옵션의 기능을 설명ㅇ
        // 위에서부터 차례대로 각 옵션에 단축키를 의미하는 대표 알파벳 지정

        // 1.
        // 먼저 하나의 옵션에 대해 왼쪽에서부터 오른쪽 순서로 단어의 첫 글자가 이미 단축키로 지정되었는지 살펴본다
        // 만약 단축키로 아직 지정이 안 되어있으면 그 알파벳을 단축키로 지정

        // 2. 만약 모든 단어의 첫 글자가 이미 지정이 되어있다면 왼쪽에서부터 차례대로 알파벳을 보면서 단축키로 지정안된것이 있으면 단축키로 지정

        // 3. 어떠한 것도 단축키로 지ㅓㅇ할 수 없다면 그냥 놔두며 대소문자를 구분치 않는다

        // 4. 위의 규칙을ㄹ 첫 번째 옵션부터 N번째 옵션까지 차례대로 적용한다.

        int N = Integer.parseInt(br.readLine());

        Character[] shortCuts = new Character[N];
        int[] shortCutIdxArr = new int[N];

        String[] arr = new String[N];

        for(int k = 0; k < N ; k++) {
            String[] words = br.readLine().split(" ");

            boolean canShortCut = false;

            int wordIdx = -1;
            int shortCutIdx = -1;

            // 각 단어의 첫 번째 글자를 단축키인지 체크하기
            for (int i = 0; i < words.length; i++) {
                // 소문자로 변환해서 체크하기
                Character c = Character.toLowerCase(words[i].charAt(0));

                for (int j = 0; j < N; j++) {
                    if (c == shortCuts[j]) {
                        canShortCut = false;
                        break;
                    } else {
                        canShortCut = true;
                    }
                }

                // 만약 단축키 지정이 가능하다면 설정을 다 한 뒤에 break;
                if (canShortCut) {
                    // hello world의 경우 h라면
                    // wordIdx = 0;
                    // shortCutIdx = 0;

                    wordIdx = i;
                    shortCutIdx = 0;

                    shortCuts[k] = c;
                    break;
                }
            }

            // 첫번째 단계에서 단축키 지정을 하지 못했다면
            if (!canShortCut) {
                for (int i = 0; i < words.length; i++) {
                    for (int j = 1; j < words[i].length(); j++) {
                        Character c = Character.toLowerCase(words[i].charAt(j));

                        for (int a = 0 ; a < N; a++) {
                            if (c == shortCuts[a]) {
                                canShortCut = false;
                                break;
                            } else {
                                canShortCut = true;
                            }
                        }

                        // 만약 단축키 지정이 가능하다면 break로 for문 빨리 나가기
                        if (canShortCut) {
                            wordIdx = i;
                            shortCutIdx = j;

                            shortCuts[k] = c;
                            break;
                        }
                    }

                    if (canShortCut) {
                        break;
                    }
                }
            }

            // 단축키가 있다면 []를 추가해서 출력하기
            for (int i = 0; i < words.length; i++) {
                for (int j = 0; j < words[i].length(); j++) {
                    if (canShortCut && i == wordIdx && j == shortCutIdx) {
                        bw.write("[" + words[i].charAt(j) + "]");
                    }
                    else {
                        bw.write(words[i].charAt(j)+"");
                    }
                }
                bw.write(" ");
            }
            bw.write("\n");

        }

        bw.flush();
        br.close();
        bw.close();
    }

}