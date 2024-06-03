import java.util.*;

class Solution {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        // 캐시
        // 도시 이름을 검색하면 해당 도시와 관련된 맛집

        // 이 프로그램의 테스팅 업무를 담당하고 있는 어피치는 서비스를 오픈하기 전 각 로직에 대한 성능 측정을 수행, 제이지가 작성한 부분 중 데이터베이스에서 게시물을 가져오는 부분의 실행시간이 너무 오래 걸림

        List<String> cacheList = new LinkedList<>();
        Deque<String> citiesDeque = new LinkedList<>();

        // 만약 캐시사이즈가 0이라면 그냥 *5 해서 리턴하면 됨
        if(cacheSize == 0) {
            return cities.length * 5;
        }

        int sum = 0;

        for(int i = 0; i < cities.length; i++) {
            citiesDeque.offer(cities[i]);
        }

        // 하나만 먼저 넣기
        cacheList.add(citiesDeque.remove());
        sum += 5;

        while(!citiesDeque.isEmpty()) {
            String str = citiesDeque.poll();

            boolean stringWasInCache = false;

            for(int i = 0; i < cacheList.size(); i++) {
                if(str.toLowerCase().equals(cacheList.get(i).toLowerCase())) {
                    cacheList.remove(i);
                    cacheList.add(str);

                    stringWasInCache = true;
                    sum+=1;
                    break;
                }
            }

            if(!stringWasInCache) {
                if(cacheList.size() >= cacheSize) {
                    cacheList.remove(0);
                }
                cacheList.add(str);
                sum+=5;
            }

        }

        answer = sum;

        return answer;
    }
}