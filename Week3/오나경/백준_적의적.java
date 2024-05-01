import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static List<List<Integer>> graph;
	static int[] visited;
	static int answer = 1;
	
	public static void dfs(int a, int value) {
		if(answer == 0) {
			return ;
		} else { 
			visited[a] = value;
			for(int temp : graph.get(a)) {
				if(visited[temp] == 0) {
					visited[temp] = value * -1;
					dfs(temp, value*-1);
				}else if (visited[temp] == value) {
					answer = 0;
					return;
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList<>();
		for(int i=0; i<n+1; i++) {
			graph.add(new ArrayList<>());
		}
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int p = Integer.parseInt(st.nextToken());
			int q = Integer.parseInt(st.nextToken());
			graph.get(p).add(q);
			graph.get(q).add(p);
		}
		
		visited = new int[n+1];
		for(int i=1; i<n+1; i++) {
			if(visited[i] == 0) {
				dfs(i,1);
			}
		}
		bw.write(String.valueOf(answer));
		
		bw.flush();
		bw.close();
		br.close();
	}
}
