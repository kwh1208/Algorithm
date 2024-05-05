import java.util.*;
import java.io.*;

class Place{
    int x;
    int y;
    boolean visited = false;
    
    public Place(int x, int y){
        this.x=x;
        this.y=y;
    }
    
    public void doVisit(){
        this.visited = true;
    }
    
    public boolean isVisited(){
        return this.visited;
    }
}

public class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int T = Integer.parseInt(br.readLine());
        StringJoiner sj = new StringJoiner("\n");
        for(int t=0;t<T;t++){
            int storeCnt = Integer.parseInt(br.readLine());
            
            StringTokenizer st = new StringTokenizer(br.readLine());
            int skX = Integer.parseInt(st.nextToken());
            int skY = Integer.parseInt(st.nextToken());
            Place sk = new Place(skX, skY);
            
            List<Place> stores = new ArrayList<>();
            for(int i=0;i<storeCnt;i++){
                st = new StringTokenizer(br.readLine());
                int storeX = Integer.parseInt(st.nextToken());
                int storeY = Integer.parseInt(st.nextToken());
                
                stores.add(new Place(storeX, storeY));
            }
            
            st = new StringTokenizer(br.readLine());
            int festivalX = Integer.parseInt(st.nextToken());
            int festivalY = Integer.parseInt(st.nextToken());
            
            Place festival = new Place(festivalX, festivalY);
            stores.add(festival);
            
            sj.add(bfs(stores, sk, festival));
        }        
        
        System.out.print(sj);
    }
    
    private static String bfs(List<Place> places, Place now, Place festival){
        now.doVisit();
        Queue<Place> queue = new LinkedList<>();
        queue.add(now);
        
        while(!queue.isEmpty()){
            Place place = queue.poll();
            
            for(Place nearPlace:places){
                if(nearPlace.isVisited()){
                    continue;
                }
                
                if(calDist(place, nearPlace)>1000){
                    continue;
                }
                
                queue.add(nearPlace);
                nearPlace.doVisit();
            }
        }
        
        return festival.isVisited()?"happy":"sad";
    }
    
    private static int calDist(Place p1, Place p2){
        return Math.abs(p1.x-p2.x) + Math.abs(p1.y-p2.y);
    }
}
