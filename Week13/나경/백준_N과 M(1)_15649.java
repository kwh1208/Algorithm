import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringJoiner;

public class BOJ15649 {

    static public void dfs(int N, int M, int cnt, boolean[] visited, List<Integer> list) {
        if(cnt == M) {
            StringJoiner joiner = new StringJoiner(" ");

            for (Integer number : list) {
                joiner.add(number.toString());
            }
            System.out.println(joiner);
            return;
        }

        for(int i=1; i<=N; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            list.add(i);
            dfs(N, M, cnt+1, visited, list);
            list.remove(list.size()-1);
            visited[i] = false;

        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        int N = scan.nextInt();
        int M = scan.nextInt();

        dfs(N,M,0, new boolean[N+1], new ArrayList<>());
    }

}
