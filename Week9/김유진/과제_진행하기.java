import java.util.*;

class Work implements Comparable<Work>{
    String name;
    int start;
    int time;
    
    public Work(String name, String start, String time){
        this.name = name;
        this.start = (Integer.parseInt(start.substring(0, 2)))*60 + Integer.parseInt(start.substring(3));
        this.time = Integer.parseInt(time);
    }
    
    public int compareTo(Work work){
        return this.start - work.start;
    }
    
}

class Solution {
    public String[] solution(String[][] plans) {
        PriorityQueue<Work> works = new PriorityQueue<>();
        for(String[] plan:plans){
            works.add(new Work(plan[0], plan[1], plan[2]));
        }
        
        Stack<Work> stack = new Stack<>();
        List<String> result = new ArrayList<>();
        while(!works.isEmpty()){
            Work nowWork = works.poll();
            if(works.isEmpty()){
                result.add(nowWork.name);
                break;
            }
            
            Work nextWork = works.peek();
            
            int nowWorkEndTime = nowWork.start + nowWork.time;
            
            if(nowWorkEndTime > nextWork.start){
                int spentTime = nextWork.start - nowWork.start;
                nowWork.time -= spentTime;
                stack.add(nowWork);
                
                continue;
            }
            
            //possible
            result.add(nowWork.name);
            
            int leftTime = nextWork.start - nowWorkEndTime;
            if(leftTime >0){
                while(!stack.isEmpty()){
                    Work lastWork = stack.pop();
                    
                    if(lastWork.time <= leftTime){
                        result.add(lastWork.name);
                        
                        leftTime -= lastWork.time;
                        continue;
                    } 
                    
                    lastWork.time -= leftTime;
                    stack.push(lastWork);
                    break;
                }
            }
        }
        
        while(!stack.isEmpty()){
            result.add(stack.pop().name);
        }
        
        String[] resArr = new String[result.size()];
        for(int i=0;i<result.size();i++){
            resArr[i] = result.get(i);
        }
        
        return resArr;
    }
}
