package Week31.권우현;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 저울 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer str;
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int[][] arr = new int[n][n];
        for (int i = 0; i < m; i++) {
            str = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(str.nextToken())-1;
            int b = Integer.parseInt(str.nextToken())-1;
            arr[a][b] = 1;
            arr[b][a] = -1;
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (arr[i][k]==1&&arr[k][j]==1) {
                        arr[i][j] = 1;
                    }
                    if (arr[i][k]==-1&&arr[k][j]==-1) {
                        arr[i][j] = -1;
                    }
                }
            }
        }

        for (int i = 0; i < n; i++) {
            int ans = 0;
            for (int j = 0; j < n; j++) {
                if (arr[i][j]!=0) {
                    ans++;
                }
            }
            System.out.println(n-ans-1);
        }


    }
}
