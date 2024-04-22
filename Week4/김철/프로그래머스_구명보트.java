import java.util.Arrays;

public class 프로그래머스_구명보트 {

    public static void main(String[] args){
        int[] people = {70, 80, 50};
        int limit = 100;

        System.out.println(solution(new int[]{70, 50, 80, 50}, limit));
        System.out.println(solution(people, limit));
    }

    public static int solution(int[] people, int limit) {
        int answer = 0;

        boolean[] visited = new boolean[people.length];

        Arrays.sort(people);

        // 제일 무거운 사람과 제일 가벼운 사람을 구출한다.
        // idx = 제일 가벼운 사람의 인덱스
        int idx = 0;

        for(int i=people.length-1; i>=0; i--){
            if(visited[i]) continue;
            if(people[i] + people[idx] <= limit){
                visited[i] = true;
                visited[idx] = true;
                idx++;
                answer++;
                continue;
            }
            else{
                answer++;
                visited[i] = true;
            }

        }

        return answer;
    }
}