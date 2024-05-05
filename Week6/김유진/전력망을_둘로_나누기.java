import java.util.*;

class Solution {
    public int solution(int n, int[][] wires) {
        List<Integer>[] graph = new List[n+1];
        for(int i=0;i<n+1;i++){
            graph[i] = new ArrayList<>();
        }
        
        for(int[] wire:wires){
            int a = wire[0];
            int b = wire[1];
            
            graph[a].add(b);
            graph[b].add(a);
        }
        
        int diff = Integer.MAX_VALUE;
        for(int[] wire:wires){
            int a = wire[0];
            int b = wire[1];
            
            int aBfs = bfs(graph, a, b, n);
            int bBfs = n-aBfs;
            
            int diffAB = Math.abs(aBfs-bBfs);
            
            diff = diff>diffAB?diffAB:diff;
        }
        
        return diff;
    }
    
    private int bfs(List<Integer>[] graph, int start, int except, int n){
        boolean[] visited = new boolean[n+1];
        Queue<Integer> queue = new LinkedList<>();
        
        queue.add(start);
        visited[start] = true;
        visited[except] = true;
        int count = 1;
        while(!queue.isEmpty()){
            int now = queue.poll();
            
            for(int near: graph[now]){
                if(visited[near]){
                    continue;
                }
                
                visited[near] = true;
                queue.add(near);
                count++;
            }
        }
        
        return count;
    }
}
