import java.io.*;
import java.util.*;
public class Main {


	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));



		// 수열 A가 주어졌을때, 가장 긴 감소하는 부분 수열

		// 10, 30, 10, 20, 20, 10
		// 1  1   1   2   2  3
		// 가장 긴 감소하는 부분 수열 10 30 10 20 20 10 길이는 3

		int N =Integer.parseInt(br.readLine());

		int[] arr = new int[N];

		StringTokenizer st = new StringTokenizer(br.readLine());

		for (int i = 0; i < arr.length; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}

		int[] dp = new int[N];

		for (int i = 0; i < arr.length; i++) {
			dp[i] = 1;
			for (int j=i-1; j >= 0; j--) {
				if (arr[i] < arr[j]) {
					dp[i] = Math.max(dp[j] + 1, dp[i]);
				}
			}
		}

		int maxNum = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			if (maxNum < dp[i]) {
				maxNum = dp[i];
			}
		}

		bw.write(maxNum+"");

		bw.flush();
		bw.close();
	}

}
