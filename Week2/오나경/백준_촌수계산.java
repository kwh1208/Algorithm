import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	public static List<Integer>[] list;
	public static boolean[] checked;
	public static int answer = -1;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		int q1 = Integer.parseInt(st.nextToken());
		int q2 = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(br.readLine());
		
		list = new ArrayList[n+1];
		checked = new boolean[n+1];
		
		for(int i=1; i<=n; i++) {
			list[i] = new ArrayList<>();
		}
		
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			list[x].add(y);
			list[y].add(x);
		}
		
		findNRelatives(q1, q2, 0);	
		
		System.out.println(answer);
	}
	
	public static void findNRelatives(int start, int end, int cnt) {
		if(start == end) {
			answer = cnt;
			return;
		}
		
		checked[start] = true;
		
		for(int i=0; i<list[start].size(); i++) {
			int next = list[start].get(i);
			if(!checked[next]) {
				findNRelatives(next, end, cnt+1);
			}
		}
	}
}



