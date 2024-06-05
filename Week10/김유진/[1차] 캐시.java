import java.util.*;

class Cache{
    int cacheSize;
    LinkedList<String> queue = new LinkedList<String>();

    public Cache(int cacheSize){
        this.cacheSize = cacheSize;
    }

    public boolean isCached(String city){
        for(int i=0;i<queue.size();i++){
            if(queue.get(i).equals(city)){
                backwardCity(i);

                return true;
            }
        }

        return false;
    }

    void backwardCity(int idx){
        String city = queue.remove(idx);
        queue.add(city);
    }

    public void addCity(String city){
        if(queue.size() == cacheSize){
            queue.poll();
        }

        if(queue.size() == cacheSize){
            return;
        } 

        queue.add(city);
    }


}

class Solution {
    public int solution(int cacheSize, String[] cities) {
        Cache cache = new Cache(cacheSize);

        int res = 0;
        for(String city:cities){
            city = city.toLowerCase();

            if(cache.isCached(city)){
                res += 1;

                continue;
            }

            res+=5;
            cache.addCity(city);
        }

        return res;
    }
}
