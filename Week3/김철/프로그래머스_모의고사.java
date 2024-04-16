import java.util.*;

public class Solution모의고사 {
    public static void main(String[] args) {
        int n = 3;
        int[] answers = {1,3,2,4,2};
        System.out.println(solution(answers));
    }

    static List<Integer> solution(int[] answers) {
        int[] answer = {};

        // map(사람 번호, 맞힌 개수)
        Map<Integer, Integer> answerMap = new HashMap<>();
        List<Integer> answerList = new ArrayList<>();

        int max = -1;

        for(int i=1; i<=3; i++){
            int[] supoza = new int[]{};
            switch(i){
                case 1:
                    supoza = new int[]{1, 2, 3, 4, 5};
                    break;
                case 2:
                    supoza = new int[]{2, 1, 2, 3, 2, 4, 2, 5};
                    break;
                case 3:
                    supoza = new int[]{3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
                    break;
            }

            int count = solve(supoza, answers);
            max = Math.max(max, count);
            answerMap.put(i, count);
        }

        for(int personNum : answerMap.keySet()){
            if(answerMap.get(personNum) == max){
                answerList.add(personNum);
            }
        }

        if(answerList.size() > 1) Collections.sort(answerList);

        return answerList;
    }

    static int solve(int[] supoza, int[] answers){
        Queue<Integer> q = new ArrayDeque<>();
        int count = 0;

        for(int temp : supoza){
            q.offer(temp);
        }

        for(int answer : answers){
            int supozaInput = q.poll();
            if(answer == supozaInput) count++;
            q.offer(supozaInput);
        }
        return count;
    }
}
