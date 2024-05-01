import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static char[] shortcut = null; //단축키 저장하는 배열
	static int shortcutCnt = 0;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		int N = Integer.parseInt(scan.nextLine());
		shortcut = new char[N];
		
		for(int i=0; i < N; i++) { //입력받는 옵션의 개수만큼 반복
			String line = scan.nextLine();
			String[] words = line.split(" ");		
			
			Queue<Integer> queue = new LinkedList<>();
			
			int q = 0;
			queue.add(q);
			for(int j=1; j<words.length; j++) {
				q += words[j-1].length()+1;
				queue.add(q);
			}
			
			q = 0;
			for(int j=0; j<words.length; j++) {
				if(j>0) { q += words[j-1].length()+1; }
				for(int k=1; k<words[j].length(); k++) {
					queue.add(q+k);
				}
			}
			
			//queue 돌아가면서 체크
			int finalIndex = -1;
			while(!queue.isEmpty()) {
				int target = queue.poll();
				int j=0;
				for(j=0; j<shortcutCnt; j++) {
					if(line.toLowerCase().charAt(target) == shortcut[j]) {
						break;
					}
				}
				
				if(j == shortcutCnt) { //지정된 단축키 배열에 해당값이 없으면
					finalIndex = target;
					shortcut[shortcutCnt++] = line.toLowerCase().charAt(target);
					break;
				}
			}
			
			//출력
			for(int j=0; j<line.length(); j++) {
				char ch = line.charAt(j);
				if(j == finalIndex) {
					System.out.print("[" + ch + "]");
				}else {
					System.out.print(ch);
				}
			}
		
			System.out.println();
		}	
		
		scan.close();
	}
}