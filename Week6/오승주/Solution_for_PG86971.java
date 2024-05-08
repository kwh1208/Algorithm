import java.io.*;
import java.util.*;

class Solution {
    static ArrayList<ArrayList<Integer>> graph;
    static Queue<Integer> queue = new ArrayDeque<>();
    
    static boolean[] visited;
    static int answer = 100;
    
    public static int BFS(int node) {
        int cnt = 0;
        
        visited[node] = true;
        cnt++;
        queue.add(node);
        
        while (!queue.isEmpty()) {
            int currNode = queue.poll();
            
            for (Integer nextNode : graph.get(currNode)) {
                if (!visited[nextNode]) {
                    
                    visited[nextNode] = true;
                    cnt++;
                    queue.add(nextNode);
                }
            }
        }
        return cnt;
    }
    
    public int solution(int n, int[][] wires) {
        // 그래프 연결
        graph = new ArrayList<>(n+1);
        for (int i = 0; i < n+1; i++) {
            graph.add(new ArrayList<Integer>());
        }
        
        for (int[] edge : wires) {
            Integer node1 = edge[0];
            Integer node2 = edge[1];
            
            graph.get(node1).add(node2);
            graph.get(node2).add(node1);
        }
        
        // 간선을 하나씩 끊어보면서 BFS 탐색
        for (int[] edge : wires) {
            Integer node1 = edge[0];
            Integer node2 = edge[1];
            
            
            // 간선 연결 끊기
            graph.get(node1).remove(node2);
            graph.get(node2).remove(node1);
            
            
            // 탐색하면서 노드의 개수 구함
            visited = new boolean[n+1];
            answer = Math.min(answer, Math.abs(BFS(node1)-BFS(node2)));
            
            
            // 간선 다시 연결하기
            graph.get(node1).add(node2);
            graph.get(node2).add(node1);
        }
        return answer;
    }
}