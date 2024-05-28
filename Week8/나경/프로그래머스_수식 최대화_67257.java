import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Programmers_67257 {
    enum Operator {PLUS, MINUS, MULTIPLY}
    static long answer = 0;

    public static long solution(String expression) {
        boolean[] visited = new boolean[3];

        List<String> numList = new ArrayList<>(Arrays.asList(expression.split("[\\+\\-\\*]")));
        List<String> operatorList = new ArrayList<>(Arrays.asList(expression.split("[0-9]+")));
        operatorList.removeIf(String::isEmpty);

        for (Operator operator : Operator.values()) {
            visited = new boolean[3];
            getSum(new ArrayList<>(numList), new ArrayList<>(operatorList), operator, visited);
        }

        return answer;
    }

    public static void getSum(List<String> numList, List<String> operatorList, Operator operator, boolean[] visited) {
        if(operatorList.isEmpty()) {
            return;
        }

        visited[operator.ordinal()] = true;

        for(int i=0; i<operatorList.size(); i++) {
            if (operatorList.get(i).equals(getOperatorSymbol(operator))) {
                long n = 0;
                switch (operator) {
                    case PLUS:
                        n = Long.parseLong(numList.get(i)) + Long.parseLong(numList.get(i + 1));
                        break;
                    case MINUS:
                        n = Long.parseLong(numList.get(i)) - Long.parseLong(numList.get(i + 1));
                        break;
                    case MULTIPLY:
                        n = Long.parseLong(numList.get(i)) * Long.parseLong(numList.get(i + 1));
                        break;
                }
                numList.remove(i + 1);
                numList.remove(i);
                numList.add(i, Long.toString(n));
                operatorList.remove(i);
                i--;

            }
        }

        for (Operator nextOperator : Operator.values()) {
            if(!visited[nextOperator.ordinal()]) {
                getSum(new ArrayList<>(numList), new ArrayList<>(operatorList), nextOperator, Arrays.copyOf(visited, visited.length));
            }
        }

        if(operatorList.isEmpty()) {
            answer = Math.max(answer, Math.abs(Long.parseLong(numList.get(0))));
        }

    }

    private static String getOperatorSymbol(Operator operator) {
        switch (operator) {
            case PLUS:
                return "+";
            case MINUS:
                return "-";
            case MULTIPLY:
                return "*";
        }
        return "";
    }
}
