import java.util.*;

class Solution {
    static String[][] operators = {
        {"+", "-", "*"},
        {"+", "*", "-"},
        {"-", "+", "*"},
        {"-", "*", "+"},
        {"*", "+", "-"},
        {"*", "-", "+"}
    };
    
    public long solution (String expression) {
        ArrayList<String> expressionAsList = new ArrayList<>();
        
        int start = 0;
        for (int i = 0; i < expression.length(); i++) {
            if (expression.charAt(i) == '+' || expression.charAt(i) == '-' || expression.charAt(i) == '*') {
                
                expressionAsList.add(expression.substring(start, i));
                expressionAsList.add(expression.charAt(i) + "");
                
                start = i + 1;
            }
        }
        expressionAsList.add(expression.substring(start));
        
        long max = 0;
        for (int r = 0; r < 6; r++) {
            ArrayList<String> temp = new ArrayList<String>(expressionAsList);
            
            for (int c = 0; c < 3; c++) {
                String currentOp = operators[r][c];
                
                for (int index = 0; index < temp.size(); index++) {
                    if (currentOp.equals(temp.get(index))) {
                        
                        String num1 = temp.get(index-1);
                        String num2 = temp.get(index+1);
                        String op = temp.get(index);
                        
                        String result = calculate(num1, op, num2);
                        temp.set(index-1, result);
                        
                        temp.remove(index);
                        temp.remove(index);
                        
                        index--;
                    }
                }
            }
            max = Math.max(max, Math.abs(Long.parseLong(temp.get(0))));
        }
        return max;
    }
    
    public static String calculate (String num1, String op, String num2) {
        if (op.equals("+"))
            return Long.parseLong(num1) + Long.parseLong(num2) + "";
        else if (op.equals("-"))
            return Long.parseLong(num1) - Long.parseLong(num2) + "";
        else
            return Long.parseLong(num1) * Long.parseLong(num2) + "";
    }
}