/*
<pre>
@Title: 프로그래머스: 모의고사
@Link: https://school.programmers.co.kr/learn/courses/30/lessons/42840
@Author: hey._.mi
@State : 20240412
*/

import java.util.*;

public class PMockTest {

    static class Solution {

        public static int[] solution(int[] answers) {
            List<Integer> list = new ArrayList<>();
            int[] result = new int[3];
            int[] first = new int[]{1,2,3,4,5};
            int[] second = new int[]{2,1,2,3,2,4,2,5};
            int[] third = new int[]{3,3,1,1,2,2,4,4,5,5};

            for(int i=0; i<answers.length; i++) {
                int answer = answers[i];

                if(answer == first[i%5]) result[0]++;
                if(answer == second[i%8]) result[1]++;
                if(answer == third[i%10]) result[2]++;
            }

//            int max = Arrays.stream(result).max().getAsInt();
            int max = 0;
            for(int i=0; i<3; i++) {
                if(result[i] == max) {
                    list.add(i+1);
                } else if(result[i] > max) {
                    list.clear();
                    max = result[i];
                    list.add(i+1);
                }
            }
            Collections.sort(list);
            System.out.println(list);

            return list.stream().mapToInt(i->i).toArray();
        }

    }
    public static void main(String[]args){
        Solution solution = new Solution();
        System.out.println(Solution.solution(new int[]{1,2,3,4,5})); // [1]
        System.out.println(Solution.solution(new int[]{1,3,2,4,2})); // [1,2,3]

    }
}
