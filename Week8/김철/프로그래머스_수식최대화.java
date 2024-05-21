import java.util.*;
import java.io.*;

public class Solution수식최대화 {
    public static void main(String[] args){
        System.out.println(solution("100-200*300-500+20"));
    }

    private static long solution(String expression) {
        long answer = Long.MIN_VALUE;

        // 연산자 목록 미리 지정
        String[][] op = {
                {"*", "+", "-"},
                {"*", "-", "+"},
                {"+", "*", "-"},
                {"+", "-", "*"},
                {"-", "*", "+"},
                {"-", "+", "*"}
        };

        // 입력된 expression 에서 숫자, 연산자를 함께 차례로 기입할 리스트
        List<String> list = new ArrayList<>();

        // list 안에서 숫자의 시작 인덱스를 표현하기 위함
        int start = 0;

        for(int i=0; i<expression.length(); i++){
            // 뺄셈, 덧셈, 곱셈 중 연산자 발견하면
            if(expression.charAt(i) == '-' || expression.charAt(i) == '+'
                    || expression.charAt(i) == '*'){

                // 그 앞에 숫자를 list에 기입
                // 어차피 중위표기법에 의해서 숫자 -> 연산자 순으로 이어지기 때문에
                // 숫자를 list에 넣기 위해 substring 사용
                list.add(expression.substring(start, i));

                // 연산자도 list에 기입
                list.add(expression.charAt(i) + "");

                // 다음 숫자 기입을 위해
                start = i + 1;
            }
        }
        // 마지막은 숫자일테니
        list.add(expression.substring(start));

        // 연산자 우선순위 모든 경우의 수를 탐색
        for(int i=0; i<op.length; i++){
            // 경우의 수 탐색마다 list 복사
            List<String> tempList = new ArrayList<>(list);

            for(int j=0; j<op[0].length; j++){

                for(int k=0; k<tempList.size(); k++){
                    // 연산자가 일치한다면
                    if(op[i][j].equals(tempList.get(k))){
                        // 숫자를 계산한다
                        // 계산한 숫자의 결과를 j-1 인덱스에 도출한다.
                        tempList.set(k - 1, strCalc(tempList.get(k-1), tempList.get(k), tempList.get(k+1)));
                        // 2번 remove 시행하는 이유 :
                        // 1 + 2 - 3 를
                        // 3 - 3 의 형태로 바꾸기 위해
                        tempList.remove(k);
                        tempList.remove(k);
                        k--;
                    }
                }
            }
            answer = Math.max(answer , Math.abs(Long.parseLong(tempList.get(0))));
        }

        return answer;
    }

    private static String strCalc(String num1, String op, String num2){
        long n1 = Long.parseLong(num1);
        long n2 = Long.parseLong(num2);

        if(op.equals("-")){
            return n1 - n2 + "";
        }
        else if(op.equals("+")){
            return n1 + n2 + "";
        }
        return n1 * n2 + "";
    }
}
//https://jisunshine.tistory.com/150