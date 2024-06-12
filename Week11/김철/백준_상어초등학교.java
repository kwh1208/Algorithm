package Main;

import java.io.*;
import java.util.*;

public class Main21608 {

    // 어떤 학생의 자리가 x행,y열인 경우, 인접한 좋아하는 친구 수, 인접한 빈 칸 수 나타내기 위함.
    static class Point implements Comparable<Point>{
        int x, y, likeCnt, emptyCnt;
        Point(int x, int y, int likeCnt, int emptyCnt){
            this.x = x;
            this.y = y;
            this.likeCnt = likeCnt;
            this.emptyCnt = emptyCnt;
        }
        // return이 -이면 왼쪽 먼저 +이면 오른쪽 먼저
        @Override
        public int compareTo(Point o) {
            // 좋아하는 수 같으면
            if(this.likeCnt == o.likeCnt){
                // 빈 칸 수 같으면
                if(this.emptyCnt == o.emptyCnt) {
                    // 행 같으면 -> 들어온 입력값 o가 더 크면 내림차순
                    if(this.x == o.x) return o.y - this.y;
                    // 내림차순
                    return o.x - this.x;

                }
                // 빈 칸 수 오름차순
                return this.emptyCnt - o.emptyCnt;
            }
            // 좋아하는 수 오름차순
            return this.likeCnt - o.likeCnt;
        }
    }
    // 학생 수, 만족도 점수
    static int N, answer;
    // 입력 순서로 주어지는 학생을 저장하기 위한 배열
    static int[] studentArr;
    // 인접 판단 배열
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    // 해당 학생과, 학생이 좋아하는 친구 리스트 를 Map 형식으로 저장
    static Map<Integer, List<Integer>> likeMap;
    // 자리 정할 배열
    static int[][] classRoom;

    // 1. 좋아하는 학생이 제일 많이 인접하는 칸으로
    // 2. 비어있는 칸이 가장 많이 인접하는 칸으로
    // 3. 행의 번호가 가장 작은 칸으로 -> 열의 번호가 가장 작은 칸으로
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer token;

        N = Integer.parseInt(br.readLine());
        studentArr = new int[N*N];
        likeMap = new HashMap<>();
        classRoom = new int[N][N];

        for(int i=0; i<N*N; i++){
            token = new StringTokenizer(br.readLine());
            int student = Integer.parseInt(token.nextToken());
            studentArr[i] = student;
            List<Integer> tempList = new ArrayList<>();

            for(int j=1; j<=4; j++){
                tempList.add(Integer.parseInt(token.nextToken()));
            }
            // 각 학생이 좋아하는 친구 목록을 Map 형식으로 저장
            likeMap.put(student, tempList);
        }

        // 1. 자리 배치하기
        for (int i = 0; i < N*N; i++) {
            // 자리 찾기 함수로 studentArr[i] 학생이 앉을 수 있는 '자리의 후보' 들을 리스트로 담는다.(다르게 한 것)
            List<Point> list = findSeat(studentArr[i]);

            // 미리 선언했던 Comparable 우선순위에 의해서 정렬
            // 자리 리스트 중 가장 끝에 있는 것이 우선순위 1등 자리
            Collections.sort(list);

            // 자리 선정 완료료
           classRoom[list.get(list.size()-1).x][list.get(list.size()-1).y] = studentArr[i];
        }

        // 2. 점수 합산
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 주변 학생 수에 따라 점수 합산
                int count = getStudentCount(i, j, classRoom[i][j]);
                if (count > 0) {
                    answer += (int) Math.pow(10, count - 1);
                }
            }
        }

        bw.write(answer + "");
        bw.close();
    }

    // 입력으로 들어온 student가 classRoom[i][j]에 적합한지 판단하는 메소드
    static List<Point> findSeat(int student){
        List<Point> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                // 이미 자리에 누구 앉아있으면 skip
                if (classRoom[i][j] > 0) continue;
                // student가 classRoom[i][j]에 앉게 되었을 때
                // 메소드로 인접한 좋아하는 학생 수, 빈칸 수 구하기
                Point curr = new Point(i, j, getStudentCount(i, j, student), getEmptyCount(i, j));

                // 비교할 seat이 null이라면 초기화 후 skip
                list.add(curr);

            }
        }
        return list;
    }
    
    // 인접한 좋아하는 학생 수
    static int getStudentCount(int x, int y, int student) {
        int count = 0;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            // 범위 벗어나면 skip
            if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                continue;
            }
            // 인접한 학생이 좋아하는 학생에 포함되면 count 증가
            if (likeMap.get(student).contains(classRoom[nx][ny])) {
                count++;
            }
        }
        return count;
    }

    // 인접한 빈 칸 수
    static int getEmptyCount(int x, int y) {
        int count = 0;

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            // 범위 벗어나면 skip
            if (nx < 0 || nx >= N || ny < 0 || ny >= N) {
                continue;
            }
            // 인접한 칸이 빈 칸이면 count
            if (classRoom[nx][ny] == 0) {
                count++;
            }
        }
        return count;
    }
}
//https://gogigood.tistory.com/72