/*
<pre>
@Title: 프로그래머스: 게임 맵 최단거리
@Link: https://school.programmers.co.kr/learn/courses/30/lessons/1844
@Author: hey._.mi
@State : 20240412
*/

import java.util.*;

public class PShortestDistance {
    static int[] dx = new int[]{1,-1,0,0};
    static int[] dy = new int[]{0,0,1,-1};

    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static class Solution {
        public static int solution(int[][] maps) {
            int answer;
            int m = maps.length;
            int n = maps[0].length;

            Queue<Node> que = new LinkedList<>();
            que.offer(new Node(0,0));

            while(!que.isEmpty()) {
                Node cur = que.poll();
                int cx = cur.x;
                int cy = cur.y;

                for(int i=0; i<4; i++) {
                    int nx = cx+dx[i];
                    int ny = cy+dy[i];

                    if(nx>=0 && nx<n && ny>=0 && ny<m && maps[ny][nx] == 1) {
                        maps[ny][nx] += maps[cy][cx];
//                        System.out.println(maps[ny][nx]);
                        que.offer(new Node(nx, ny));
                    }
                }
            }

            answer = maps[m-1][n-1] > 1 ? maps[m-1][n-1] : -1;
            return answer;

        }
    }
    public static void main(String[]args){
        Solution solution = new Solution();
        System.out.println(Solution.solution(new int[][]{{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,1},{0,0,0,0,1}})); // 11
        System.out.println(Solution.solution(new int[][]{{1,0,1,1,1},{1,0,1,0,1},{1,0,1,1,1},{1,1,1,0,0},{0,0,0,0,1}})); // -1



    }
}
