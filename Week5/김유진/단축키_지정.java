import java.util.*;
import java.io.*;

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        boolean[] assign = new boolean['Z'+1];
        StringJoiner sj = new StringJoiner("\n");
        for(int i=0;i<N;i++){
            String option = br.readLine();
            String[] words = option.split(" ");

            //단어 앞 확인 로직
            boolean assignCheck = false;
            for(int w=0;w<words.length;w++){
                char firstAlphaUpper = words[w].toUpperCase().toCharArray()[0];

                if(assign[firstAlphaUpper]){
                    continue;
                }

                StringBuilder sb = new StringBuilder();
                sb.append('[').append(words[w].toCharArray()[0]).append(']').append(words[w].substring(1));
                words[w] = sb.toString();

                assign[firstAlphaUpper] = true;
                assignCheck = true;

                break;
            }

            //왼 -> 오
            if(!assignCheck){
                for(int w=0;w<words.length;w++){
                    String word = words[w];
                    char[] wordArr = word.toCharArray();

                    boolean wordAssign = false;
                    for(int c=0;c<wordArr.length;c++){
                        char upperAlpha = Character.toUpperCase(wordArr[c]);

                        if(assign[upperAlpha]){
                            continue;
                        }

                        assign[upperAlpha] = true;
                        String newWord = word.substring(0, c)+'['+wordArr[c]+']'+word.substring(c+1);
                        words[w] = newWord;
                        wordAssign = true;

                        break;
                    }

                    if(wordAssign){
                        break;
                    }
                }
            }

            StringJoiner subSj = new StringJoiner(" ");
            for(String word:words){
                subSj.add(word);
            }

            sj.add(subSj.toString());
        }

        System.out.print(sj);
    }
}
