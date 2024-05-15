package Week7.조영호;

//문제 설명
// 0 또는 양의 정수가 주어졌을 때, 정수를 이어 붙여 만들 수 있는 가장 큰 수를 알아내 주세요.
// 예를 들어, 주어진 정수가 [6, 10, 2]라면 [6102, 6210, 1062, 1026, 2610, 2106]를 만들 수 있고, 이중 가장 큰 수는 6210입니다.
// 0 또는 양의 정수가 담긴 배열 numbers가 매개변수로 주어질 때, 순서를 재배치하여 만들 수 있는 가장 큰 수를 문자열로 바꾸어 return 하도록 solution 함수를 작성해주세요.

// 해결방법
// 문자열로 변환 후 내림차순으로 정렬하는 방법인데
// 일반적으로 내림차순 정렬을 시켰을때 2번째 예에서 3과 30이 "330"이 아닌 "303"이 되어버린다.
// 따라서 Comparator를 사용하여 문자열을 붙여서 판단 후 내림차순 정렬해야한다.
// a.compareTo(b)
// 앞에서부터 비교하다 다른 문자열이 나오면 'a-b' 순서로 해당 문자의 아스키코드 값을 뺀 결과(int)를 리턴 
// 내림차순 : (o2+o1).compareTo(o1+o2);
// 오름차순 : (o1+o2).compareTo(o1+o2);
import java.util.*;
public class TheBiggestNumber_pg {
    public String solution(int[] numbers) {
        String answer = "";
        
        //문자열 리턴을 해줄 String 배열 생성
        String[] str =  new String[numbers.length];

        // int 배열 -> String 배열 전환
        for(int i = 0 ; i < numbers.length; i++){
            str[i] = String.valueOf(numbers[i]);
        }

        // 내림차순 정렬
        Arrays.sort(str, new Comparator<String>() {
            @Override
            public int compare(String a, String b) {
                return (b+a).compareTo(a+b);
            }
        });

        // 0 값이 중복일 경우 ex {0,0,0}
        // 답이 000이 나오면 안되므로 첫번째 값이 0이면 0을 리턴
        if(str[0].equals("0")) return "0";

        // 0이 아니면 문자열을 더해준다
        for(String s:str) answer += s;
        
        return answer;
    }
}