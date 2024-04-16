import java.util.Scanner;

public class Main {

	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		//데이터 입력
		int n = scan.nextInt();
		int[] arr = new int[n];
		int[] oper = new int[4];
		int operCnt = 0;
		
		for(int i=0; i<n; i++) {
			arr[i] = scan.nextInt();
		}
		
		for(int i=0; i<4; i++) {
			oper[i] = scan.nextInt();
			operCnt += oper[i];
		}
		
		scan.close();
		
		
		// 로직
		calc(arr, oper, 1, arr[0]);
		
		System.out.println(max);
		System.out.println(min);
		
		
	}
	
	public static void calc(int[] arr, int[] oper, int index, int sum) {
		
		if(arr.length == index) { //마지막 연산
			//sum = getSum(sum, getOper(oper), arr[index]);
			max = Math.max(max, sum);
			min = Math.min(min, sum);
			
			return;
		}
		
		for(int i=0; i<4; i++) {
			if(oper[i] > 0) {
				int num = arr[index];
				oper[i]--;
				calc(arr, oper, index+1, getSum(sum, i, num));
				oper[i]++;				
			}		
		}
	}
	
	public static int getOper(int[] oper) {
		for(int i=0; i<4; i++) {
			if(oper[i] > 0 ) {
				return i;
			}
		}
		return -1;
	}
	
	public static int getSum(int sum, int oper, int num) {
		switch(oper) {
			case 0: sum += num; break;
			case 1: sum -= num; break;
			case 2: sum *= num; break;
			case 3: sum /= num; break;
		}
		
		return sum;
	}
}
