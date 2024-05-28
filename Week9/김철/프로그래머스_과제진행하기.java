package programmers;

import java.util.*;

class Solution과제진행하기 {
    private static class Plan{
        String name;
        int start;
        int playtime;
        Plan(String name, int start, int playtime){
            this.name = name;
            this.start = start;
            this.playtime = playtime;
        }
    }
    public static void main(String[] args){
        String[][] plans = new String[][]{{"science", "12:40", "50"}, {"music", "12:20", "40"},
                {"history", "14:00", "30"}, {"computer", "12:30", "100"}};
        System.out.println(solution(plans));
    }
    private static List<String> solution(String[][] plans) {
        List<String> answer;
        answer = new ArrayList<>();

        // plans = 과제 계획이 담긴 배열
        // 과제는 시작 시각에 시작
        // 새 과제 시작 시각이 되면, 진행 과제를 멈추고 새 과제 시작.
        // 과제 끝내고 진행 과제 남아있으면 진행 과제 수행.
        // 새 과제 수행이 우선.
        // 멈춘 과제는 최근에 멈춘 과제부터 시작
        // 멈춘 과제 모아두는 건 스택 형태

        // plans[i][0] = 과제이름
        //plans[i][1] = 시작 시각
        //plans[i][2] = 걸리는 시간

        // 과제를 시작 시간 순으로 저장
        PriorityQueue<Plan> pq = new PriorityQueue<>(
                (o1, o2) -> (o1.start - o2.start)
        );

        // 멈춘 과제 저장
        Deque<Plan> deque = new ArrayDeque<>();

        for(int i=0; i<plans.length; i++){
            String name = plans[i][0];

            String[] startTime = plans[i][1].split(":");
            int start = Integer.parseInt(startTime[0]) * 60 + Integer.parseInt(startTime[1]);

            int playtime = Integer.parseInt(plans[i][2]);

            pq.offer(new Plan(name, start, playtime));
        }

        // 전체 과제 반복문
        while(!pq.isEmpty()){
            Plan p = pq.poll();

            String name = p.name;
            int start = p.start;
            int playtime = p.playtime;

            // 지금 시각
            int currTime = start;

            if(!pq.isEmpty()){
                Plan nextP = pq.peek();

                // 과제 충분히 수행 가능하면
                if(currTime + playtime < nextP.start){
                    answer.add(name);
                    currTime += playtime;

                    // 현재 과제 수행 후 다음 과제 시작까지 시간이 남아 남는 과제를 수행할 수 있다면
                    // 멈춘 과제 반복문 시행
                    while(!deque.isEmpty()){
                        Plan temp = deque.pollLast();

                        // 다음 과제 시작 전까지 끝낼 수 있다면
                        if(currTime + temp.playtime <= nextP.start){
                            answer.add(temp.name);
                            currTime += temp.playtime;
                            continue;
                        }
                        // 다음 과제 시작 전까지 끝낼 수 없다면
                        else{
                            temp.playtime = temp.playtime - (nextP.start - currTime);
                            deque.offer(new Plan(temp.name, temp.start, temp.playtime));
                            // 다음 과제 시행하기 위해 전체 반복문으로 넘어간다.
                            break;
                        }
                    }
                }
                // 시간 딱 맞으면
                else if(start + playtime == nextP.start){
                    answer.add(name);
                    continue;
                }
                // 못 끝내면 멈춘 과제로 넘기기
                else{
                    p.playtime = playtime - (nextP.start - currTime);
                    deque.offer(new Plan(p.name, p.start, p.playtime));
                }
            }
            // 다음 과제가 없고
            else{
                // 멈춘 과제도 없는 경우
                if(deque.isEmpty()){
                    currTime += playtime;
                    answer.add(name);
                }
                // 멈춘 과제는 있는 경우
                else{
                    answer.add(name);

                    while(!deque.isEmpty()){
                        Plan temp = deque.pollLast();
                        answer.add(temp.name);
                    }
                }
            }
        }

        return answer;
    }
}