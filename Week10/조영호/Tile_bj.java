package Week10.조영호;
import java.io.*;

public class Tile_bj {
    // 다이나믹 프로그래밍으로 해결할 수 있는 문제였습니다.
    // 먼저, N = 1, 2, ... 5 까지 나열해 보면서 생각해보겠습니다.
    // N = 1 ) 1
    // N = 2 ) 00, 11
    // N = 3 ) 001, 111, 100
    // N = 4 ) 0000, 1100, 0011, 1111, 1001
    // N = 5 ) 00001, 11001, 00111, 11111, 10011, 00100, 11100, 10000
    // 위와 같이 1, 2, 3, 5, 8, ... 개로 증가합니다.
    // 위 문제는 피보나치 수열의 점화식을 이용하여 풀면 되는 문제
    // 이제, 왜 타일의 개수가 피보나치 수열의 형태로 증가하는지 알아보겠습니다.
    // 먼저, N = 1과 N = 2는 원래 알고 있는 값이라 가정하고 N = 3 부터 전개해 보겠습니다.
    // N에는 00타일과 1타일을 이어붙일 수 있는데, (N - 1)번째에서는 00타일을 사용할 수 없습니다.
    // 따라서, (N - 2)번째에서 00타일을 사용하여 이어붙여야 합니다.
    // 현재, N = 1은 "1"이므로 "100" 혹은 "001"을 타일로 사용할 수 있습니다.
    // 다음으로, (N - 1)번째에서 1타일만을 사용하여 이어붙이면 되는데, 100, 111, 001이 있습니다.
    // 위에서 "100"은 이미 타일로 사용하였으므로 중복되는 "100"을 제외하여 "111"과 "001"을 새롭게 타일로 사용할 수 있게 되었습니다. 또는, 중복되는 "001"을 제외하고 "111"과 "100"을 사용하여도 무방합니다.
    // 여기서 중요한 점은 중복되는 값은 배제하는 것이었습니다.
    // 그리고 그 방법은 어렵지 않습니다. 이어붙이는 방향을 다르게 하는 것이죠.
    // 00타일을 이어붙일 때는 맨 뒤로 이어붙이고, 1타일을 이어붙일 때는 맨 앞으로 이어붙이는 것입니다.
    // 이렇게 하면, 중복을 배제할 수 있고 dp[i] = dp[i - 2] + dp[i - 1] 이라는 식을 도출
	static final int MOD = 15746;

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = Integer.parseInt(br.readLine());

		if (N == 1) {
			bw.write("1\n");
			bw.flush();
			bw.close();
			br.close();
			return;
		}

		long[] dp = new long[N + 1];

		dp[1] = 1;
		dp[2] = 2;

		for (int i = 3; i <= N; i++) {
			dp[i] = (dp[i - 2] + dp[i - 1]) % MOD;
		}

		bw.write(dp[N] + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
}
