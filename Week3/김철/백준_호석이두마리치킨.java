import java.util.*;
import java.io.*;

class Main21278{
    static int N,M;
    static int[][] city;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token;

        token = new StringTokenizer(br.readLine());
        N = Integer.parseInt(token.nextToken());
        M = Integer.parseInt(token.nextToken());

        // city[i][j] = i건물 j건물 사이의 거리
        city = new int[N+1][N+1];

        // N이 최대 100이므로, 존재하지 않는 값을 101로 표현
        for(int i=1; i<=N; i++){
            Arrays.fill(city[i], 101);
        }

        for(int i=0; i<M; i++){
            token = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(token.nextToken());
            int B = Integer.parseInt(token.nextToken());
            city[A][B] = city[B][A] = 1;
        }

        // 플로이드 워셜 기법 활용
        // 간선을 거치며 해당 노드로 가는 비용 중 최솟값을 고려하는 것.
        // 1 -> 2 의 거리와
        // 1 -> 3 + 3 -> 2 의 거리를 계산하고 둘을 비교
        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                if(i == j) continue;
                for(int k=1; k<=N; k++){
                    if(i == k || j == k) continue;

                    city[j][k] = Math.min(city[j][i] + city[i][k], city[j][k]);
//                    if(city[j][k] > city[j][i] + city[i][k])
//                        city[j][k] = city[j][i] + city[i][k];

                    // 이렇게 하면 가장 바깥 포문인 i를 여러 번 활용하기 때문에 시간 증가함
//                    if(city[i][k] > city[i][j] + city[j][k])
//                        city[i][k] = city[i][j] + city[j][k];
                }
            }
        }

        // 건물들과 치킨집 거리의 합. (최솟값)
        int min = Integer.MAX_VALUE;
        int chicken1 = 0;   // 치킨집1 의 건물
        int chicken2 = 0;   // 치킨집2 의 건물

        for(int i=1; i<=N; i++){
            for(int j=1; j<=N; j++){
                if(i == j) continue;

                // 모든 건물과 치킨 집과의 거리를 최솟값으로, 모두 더한다.
                int dist = sum(i, j);

                // 최솟값이 갱신되면 두 치킨 집을 기록
                if(min > dist) {
                    chicken1 = i;
                    chicken2 = j;
                    min = dist;
                }
                // 답 도출 시, 우선순위는 작은 번호이다.
                // 해당 이중 포문으로 치킨집을 결정하면
                // 자동으로 오름차순 정렬됨.
            }
        }

        bw.write(chicken1 + " " + chicken2 + " " + min*2);
        bw.close();
    }

    static int sum(int chicken1, int chicken2){
        // 각 건물들과 치킨 집과의 거리를 모두 더한다
        int sum = 0;

        // 각 건물들은 두 치킨 집 중 더 가까운 치킨집 거리로 계산
        for(int i=1; i<=N; i++){
            if(i == chicken1 || i == chicken2) continue;
            sum += Math.min(city[chicken1][i], city[chicken2][i]);
        }
        return sum;
    }
}