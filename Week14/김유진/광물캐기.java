import java.util.*;

enum Mineral{
    diamond(25), iron(5), stone(1);
    
    int cost;
    
    Mineral(int cost){
        this.cost = cost;
    }
}

class Minerals implements Comparable<Minerals>{
    List<Mineral> minerals;
    int value;
    
    Minerals(List<Mineral> minerals){
        this.minerals = minerals;
        
        int sum=0;
        for(Mineral mineral:minerals){
            sum+=mineral.cost;
        }
        
        this.value=sum;
    }
    
    public int compareTo(Minerals minerals){
        return Integer.compare(minerals.value, this.value);
    }
}

class Solution {
    public int solution(int[] picks, String[] minerals) {
        PriorityQueue<Minerals> queue= new PriorityQueue<>();
        boolean flag= true;
        
        int picksCnt = 0;
        for(int pick:picks){
            picksCnt+=pick;
        }
        
        
        if(picksCnt * 5 < minerals.length){
            flag = false;
            
            for(int i=0;i<picksCnt;i++){
                
                //5개씩 
                List<Mineral> list = new ArrayList<>();
                for(int j=0;j<5;j++){
                    int idx = 5*i + j;
                    list.add(getMineralType(minerals[idx]));
                }
                
                queue.add(new Minerals(list));
            }
        }
        
        int idx = 0;
        
        while(flag){
            List<Mineral> list = new ArrayList<>();
            for(int i = idx;i<idx+5;i++){
                if(i == minerals.length){
                    flag = false;
                    break;
                }
                
                list.add(getMineralType(minerals[i]));
            }
            
            idx+=5;
            queue.add(new Minerals(list));
        }
        
        
        int sum=0;
        
        while(!queue.isEmpty()){
            Minerals m = queue.poll();
            
            if(picks[0]!=0){
                sum+=m.minerals.size();
                
                picks[0]--;
                continue;
            }
            
            if(picks[1]!=0){
                for(Mineral mineral:m.minerals){
                    if(mineral == Mineral.diamond){
                        sum+=5;
                        continue;
                    }
                    
                    sum+=1;
                }
                
                picks[1]--;
                continue;
            }
            
            if(picks[2]!=0){
            for(Mineral mineral:m.minerals){
                if(mineral == Mineral.stone){
                    sum+=1;
                    continue;
                }
                
                if(mineral == Mineral.iron){
                    sum+=5;
                    continue;
                }
                
                sum+=25;
                }
                
                picks[2]--;
                continue;
            }
        }
        
        return sum;
    }
    
    Mineral getMineralType(String mineral){
        if(mineral.equals("diamond")){
            return Mineral.diamond;
        }
        
        if(mineral.equals("iron")){
            return Mineral.iron;
        }
        
        return Mineral.stone;
    }
}
