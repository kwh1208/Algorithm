package programmers;

import java.util.*;

class Solution캐시 {
    public static void main(String[] args) {
        String[] cities = new String[]{"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
        System.out.println(solution(3, cities));
    }

    private static int solution(int cacheSize, String[] cities) {
        int answer = 0;

        Deque<String> cacheQue = new ArrayDeque<>();
        // LRU = 오랫동안 사용 안한 것을 제거한다.
        // q.size() = cacheSize
        // 캐시에 있을 경우 실행시간 = 1
        // 캐시에 없을 경우 실행시간 = 5

        for(int i=0; i<cities.length; i++){
            String currCity = cities[i].toLowerCase();

            // 현재 처리할 도시가 캐시에 있으면
            if(cacheQue.contains(currCity)){
                cacheQue.remove(currCity);
                answer += 1;

            }
            // 캐시에 없는데
            else{
                // 캐시 사이즈 여유도 없으면
                if(cacheQue.size() >= cacheSize) cacheQue.poll();
                answer += 5;
            }

            // 캐시에 있든 없든 무조건 큐에 넣기
            cacheQue.offer(currCity);
        }

        return cacheSize == 0 ? cities.length * 5 : answer;
    }
}