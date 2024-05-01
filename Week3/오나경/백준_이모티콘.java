import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {

	static boolean[][] visited;
	static int S;
	
	static class Emoticon {
		int emoticon_num;
		int clipboard_num;
		int time;
		
		public Emoticon(int emoticon_num, int clipboard_num, int time) {
			this.emoticon_num = emoticon_num;
			this.clipboard_num = clipboard_num;
			this.time = time;
		}
	}
	
	
	public static void makeEmoticon() {
		Queue<Emoticon> queue = new LinkedList<>();
		queue.add(new Emoticon(1, 0, 0));
		
		while(!queue.isEmpty()) {
			Emoticon now = queue.poll();
			
			if(now.emoticon_num == S) {
				System.out.println(now.time);
				return;
			}
			
			if(now.emoticon_num > 0 && now.emoticon_num < 2000) {
				//복사
				if(!visited[now.emoticon_num][now.emoticon_num]) {
					visited[now.emoticon_num][now.emoticon_num] = true;
					
					queue.offer(new Emoticon(now.emoticon_num, now.emoticon_num, now.time + 1));
				}
				
				//삭제
				if(!visited[now.emoticon_num-1][now.clipboard_num]) {
					visited[now.emoticon_num - 1][now.clipboard_num] = true;
					
					queue.offer(new Emoticon(now.emoticon_num - 1, now.clipboard_num, now.time + 1));
				}
			}
			
			//붙여넣기
			if(now.clipboard_num > 0 && now.emoticon_num + now.clipboard_num < 2000 ) {
				if(!visited[now.emoticon_num+now.clipboard_num][now.clipboard_num]) {
					visited[now.emoticon_num+now.clipboard_num][now.clipboard_num] = true;
					
					queue.offer(new Emoticon(now.emoticon_num + now.clipboard_num, now.clipboard_num, now.time + 1));
				}
			}
		}
	}
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		S = scan.nextInt();
		
		visited = new boolean[2001][2001];
		
		makeEmoticon();
		
	}
}
