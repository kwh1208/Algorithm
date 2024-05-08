import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

class Solution {
    public int getTreeCnt(List<List<Integer>> tree, Stack<Integer> stack, boolean[] visited, int cnt) {
    	while(!stack.isEmpty()) {
    		int index = stack.pop();
    		if(visited[index] != true) {
    			visited[index] = true;
    			for(int value : tree.get(index)) {
    				if(visited[value] != true) {
    					stack.add(value);
    				}
    			}
    			cnt = getTreeCnt(tree, stack, visited, ++cnt);
    		}
    	}
    	
    	return cnt;
    }
    
    public int solution(int n, int[][] wires) {
        int answer = Integer.MAX_VALUE;
        
        List<List<Integer>> tree = new ArrayList<>();
        
        //tree 셋팅
        for(int i=0; i<n+1; i++) {
            tree.add(new ArrayList<>());
        }
        
        for(int i=0; i<wires.length; i++) {
            tree.get(wires[i][0]).add(wires[i][1]);   
            tree.get(wires[i][1]).add(wires[i][0]);            
        }
        

        for(int i=0; i<wires.length; i++) {
        	boolean[] visited = new boolean[n+1]; 
        	Stack<Integer> stack = new Stack<>();

        	stack.add(wires[i][0]); //탐색을 시작할 노드를 stack에 넣어준다.
            visited[wires[i][1]] = true; //연결을 끊을 노드부터는 탐지하지 않으므로 방문 여부를 체크한다.
            int treeCnt = getTreeCnt(tree, stack, visited, 0);
            
            answer = answer > Math.abs((n-treeCnt)-treeCnt) ? Math.abs((n-treeCnt)-treeCnt) : answer; //최소값을 구해야 함
        }
        
        
        return answer;
    }
}