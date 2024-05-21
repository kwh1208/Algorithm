수식 최대화
import java.util.*;
class Solution {
    public long solution(String expression) {
        long answer = 0;
        // 라이언
        // 숫자들과 3가지의 연산문자(+,-,*)만으로 이루어진 연산 수식이 전달되며, 참가자의 미션은 전달받은 수식에 포함된 연산자의
        // 우선순위를 자유롭게 재정의하여 만들 수 있는 가장 큰 숫자를 제출

        // 100 - 200 * 300 - 500 +20

        // 우선순위 조합은 3! = 6가지 그 중 + > - >
        // + > - > *
        // + > * > -
        // * > + > -
        // * > - > +
        // - > + > *
        // - > * > +

        // 100 - ((200 * 300)) - (500 + 20)
        // 50*(6-3)*2
        String op[][] = {{"+","-","*"},{"+","*","-"},{"-","*","+"},{"-","+","*"},{"*","+","-"},{"*","-","+"}};

        List<String> list = new ArrayList<>();

        // 문자열 파싱 처리를 위한 시작
        int start = 0;

        for(int i = 0; i < expression.length(); i++){
            if(expression.charAt(i) == '+' || expression.charAt(i) == '-' || expression.charAt(i) == '*'){
                list.add(expression.substring(start, i));
                list.add(expression.charAt(i)+"");
                start = i + 1;
            }
        }

        list.add(expression.substring(start));

        for(int i = 0; i < op.length; i++){
            ArrayList<String> sub_list = new ArrayList<String>(list);
            for(int k = 0; k < 3; k++){
                for(int j = 0; j< sub_list.size(); j++){
                    // 만약 해당 연산자가 연산자 리스트에 있는 연산자와 일치할 경우
                    if(op[i][k].equals(sub_list.get(j))){

                        sub_list.set(j - 1, calc(sub_list.get(j - 1), sub_list.get(j), sub_list.get(j+1)));
                        sub_list.remove(j);
                        sub_list.remove(j);
                        j--;
                    }
                }
            }
            answer = Math.max(answer, Math.abs(Long.parseLong(sub_list.get(0))));
        }


        // for(int i = 0; i < list.size(); i++){
        //     System.out.println(list.get(i));
        // }

        return answer;
    }

    private static String calc(String num1, String op, String num2){
        long n1 = Long.parseLong(num1);
        long n2 = Long.parseLong(num2);

        if(op.equals("+")){
            return n1 + n2 + "";
        }
        else if(op.equals("-")){
            return n1 - n2 + "";
        }
        else{
            return n1 * n2 +"";
        }
    }
}