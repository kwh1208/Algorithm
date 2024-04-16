import java.util.ArrayList;

class Solution {
    public int[] solution(int[] answers) {
        int[] answer = {};
        
        int p1[] = {1,2,3,4,5};
        int p2[] = {2,1,2,3,2,4,2,5};
        int p3[] = {3,3,1,1,2,2,4,4,5,5};
        
        int score[] = new int[3];
        
        for(int i=0; i<answers.length; i++){
            if(answers[i] == p1[i%p1.length]) score[0]++;
            if(answers[i] == p2[i%p2.length]) score[1]++;
            if(answers[i] == p3[i%p3.length]) score[2]++;
        }
        
        int maxScore = score[0];
        ArrayList<Integer> list = new ArrayList();
        
        for(int i=0; i<score.length; i++){
            if(maxScore < score[i]) {
                maxScore = score[i];
                list = new ArrayList();
            }
            if(maxScore <= score[i]) {
                list.add(i);
            }
        }
        
        answer = new int[list.size()];
        for(int i=0; i<answer.length; i++) {
            answer[i] = list.get(i)+1;
        }
        
        return answer;
    }
}