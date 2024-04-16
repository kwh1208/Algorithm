import java.io.*;
import java.util.*;

public class Main14226 {

    static class Node{
        int screen;
        int clipboard;
        int time;
        Node(int screen, int clipboard, int time){
            this.screen = screen;         // 화면 이모티콘 개수
            this.clipboard = clipboard;   // 클립보드 이모티콘 개수
            this.time = time;             // screem, clipboard 일 때의 시간
        }
    }
    static int S;
    static boolean[][] visited = new boolean[1001][1001];
    static Queue<Node> q = new ArrayDeque<>();
    static int answer;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        S = Integer.parseInt(br.readLine());

        BFS();
        bw.write(answer + "");
        bw.close();
    }

    static void BFS(){
        q.offer(new Node(1, 0, 0));
        visited[1][0] = true;

        while(!q.isEmpty()){
            Node now = q.poll();

            int screen = now.screen;
            int clipboard = now.clipboard;
            int time = now.time;
            if(screen == S) {
                answer = time;
                return;
            }

            // 여기부터 3가지 행동으로 나뉜다.
            // 1. 클립보드 갱신하는 경우
            // 2. 클립보드에 있는거 저장하는 경우
            // 3. 하나 삭제하는 경우
            // 3가지 경우의 수를 모두 탐색하며,
            // 이모티콘 개수 1~S까지 각 숫자마다 도달할 때 필요한 최소 시간을 구해나간다.

            // 복사
            q.offer(new Node(screen, screen, time + 1));

            // 붙여넣기
            if(clipboard > 0 && screen + clipboard <= S &&
                    !visited[screen + clipboard][clipboard]){
                q.offer(new Node(screen + clipboard, clipboard, time + 1));
                visited[screen + clipboard][clipboard] = true;
            }

            // 삭제
            if(screen > 0 && !visited[screen - 1][clipboard]){
                q.offer(new Node(screen - 1, clipboard, time + 1));
                visited[screen - 1][clipboard] = true;
            }
        }
    }


    // DP + BFS
//https://moonsbeen.tistory.com/236

    //    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
//
//        S = Integer.parseInt(br.readLine());
//
//        DFS(1, 0);
//
//        bw.write(minTime + "");
//        bw.close();
//    }
//
//    static void DFS(int now, int time){
//        if(S == now){
//            minTime = Math.min(minTime, time);
//            return;
//        }
//        if(now < S){
//            if(S % now == 0 && now <= 1000){
//                stack.push(now); // 복사
//                DFS(now + stack.peek(), time + 2); // 붙여넣기
//                return;
//            }
//            else{
//                DFS(now + stack.peek(), time + 1); // 붙여넣기
//                return;
//            }
//        }
//        else if(now > S){
//            DFS(now - 1, time + 1);
//        }
//    }
// 처음에 혼자 풀어본 것
// 안되는 이유 : 최솟값에서 출발하고 최솟값을 더하는 DP의 특성을 활용못함
// S개 목표로 했을 때, 클립보드 저장 개수가 달라도 카운트 정답이 동일한 경우가 생길 수 있음.
// 이후 S 이후로 진행될 때는 클립보드 저장 개수에 따라 답이 달라짐.

    //1 -- 연산 2번, 클립보드 저장, 클립보드 붙여넣기 --> 2
//2 -- 연산 2번, 클립보드 저장, 클립보드 붙여넣기 --> 4
//4 -- 연산 1번, 클립보드 붙여넣기 --> 6
//6 -- 나의 배수라면  클립보드 저장, 클립보드 붙여넣기, 연산 2번 --> 12
//12 -- 나의 배수 아니라 클립보드 붙여넣기만, 연산 1번 --> 18

}
