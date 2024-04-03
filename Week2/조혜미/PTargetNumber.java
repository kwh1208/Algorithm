import java.util.*;
import java.util.stream.*;

/*
<pre>
@Title: 프로그래머스: 타겟넘버
@Link: https://school.programmers.co.kr/learn/courses/30/lessons/43165
@Author: hey._.mi
</pre>
*/
public class PTargetNumber {

    class Solution {
        static int answer = 0;

        public static void calc(int[] num, int target, int idx, int res) {

            if(idx >= num.length) {
                if(res == target) {
                    answer++;
                }
                return;
            }

            calc(num, target, idx+1, res+num[idx]);
            calc(num, target, idx+1, res-num[idx]);

        }

        public int solution(int[] numbers, int target) {

            calc(numbers, target, 0, 0);
            return answer;
        }
    }
}
