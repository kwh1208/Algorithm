import java.util.*;

class Solution {
    Stack<Character> operators = new Stack<>();
    Stack<Long> numbers = new Stack<>();
    long maxValue = Long.MIN_VALUE;
    char[] optStd = {'+', '-', '*'};

    public long solution(String expression) {
        String[] numberStrs = expression.split("\\*|\\+|\\-");
        for(String numberStr : numberStrs){
            numbers.push(Long.parseLong(numberStr));
        }

        for(char expChar:expression.toCharArray()){
            if(expChar == '*' || expChar =='+' || expChar =='-'){
                operators.push(expChar);
            }
        }

        getMaxResult(new HashMap<Character, Integer>());
        return maxValue;
    }

    private void getMaxResult(Map<Character, Integer> ranks){
        if(ranks.size() == 3){
            calMaxResult(ranks);
            return;
        }

        for(int i=0;i<3;i++){
            if(ranks.containsKey(optStd[i])){
                continue;
            }

            ranks.put(optStd[i], ranks.size());
            getMaxResult(ranks);
            ranks.remove(optStd[i]);
        }
    }

    private void calMaxResult(Map<Character, Integer> ranks){
        Stack<Long> numStack = (Stack<Long>) numbers.clone();
        Stack<Character> optStack = (Stack<Character>) operators.clone();

        Stack<Long> numResStack = new Stack<>();
        Stack<Character> optResStack = new Stack<>();

        while(!optStack.isEmpty()){

            //아무 것도 없는 상태
            if(optResStack.isEmpty() && numResStack.isEmpty()){
                numResStack.push(numStack.pop());
                numResStack.push(numStack.pop());
                optResStack.push(optStack.pop());

                continue;
            }

            if(optResStack.isEmpty()){
                optResStack.push(optStack.pop());
                numResStack.push(numStack.pop());

                continue;
            }

            int optResStackRank = ranks.get(optResStack.peek());
            int nowRank = ranks.get(optStack.peek());

            if(optResStackRank <= nowRank){
                optResStack.push(optStack.pop());
                numResStack.push(numStack.pop());

                continue;
            }

            //현재 optResStack의 상단이 더 높은 우선순위를 가질 때
            //optResStackRank > nowRank
            long num1 = numResStack.pop();
            long num2 = numResStack.pop();
            char opt = optResStack.pop();

            long result = getCalResult(num1, num2, opt);
            numResStack.push(result);
        }

        //남은 거 순차적으로 계산
        while(!optResStack.isEmpty()){
            long num1 = numResStack.pop();
            long num2 = numResStack.pop();
            char opt = optResStack.pop();

            long result = getCalResult(num1, num2, opt);

            numResStack.push(result);
        }

        long sum = Math.abs(numResStack.pop());

        if(maxValue<sum){
            maxValue=sum;
        }
    }

    private long getCalResult(long num1, long num2, char opt){
        if(opt == '*'){
            return num1*num2;
        }

        if(opt == '+'){
            return num1+num2;
        }

        return num1-num2;
    }
}
