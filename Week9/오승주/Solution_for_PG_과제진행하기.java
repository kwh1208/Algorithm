import java.io.*;
import java.util.*;

class Task {
    String name;
    int start;
    int playtime;
    
    public Task(String name, int start, int playtime) {
        this.name = name;
        this.start = start;
        this.playtime = playtime;
    }
}

class StartTimeComparator implements Comparator<Task> {
    @Override
    public int compare(Task task1, Task task2) {
        return task1.start - task2.start;   // 반환값이 양수이면 교환함
    }
}

class Solution {
    static ArrayList<String> answer = new ArrayList<>();
    static Deque<Task> stack = new ArrayDeque<>();      // 잠시 멈춘 과제를 담을 스택
    
    
    public String[] solution(String[][] plans) {
        
        // Tasks를 시작 시각을 기준으로 오름차순 정렬함
        Queue<Task> tasks = new PriorityQueue<Task>(new StartTimeComparator());
        for (String[] task : plans) {
            
            String name = task[0];
            
            
            
            // task의 시작 시각 파싱하는 로직
            String[] startHoursAndMin = task[1].split(":");
            int hour = Integer.parseInt(startHoursAndMin[0]);
            int min = Integer.parseInt(startHoursAndMin[1]);
            int start = (hour * 60) + min;
            
            
            int playtime = Integer.parseInt(task[2]);
            
            tasks.add(new Task(name, start, playtime));
        }
        
        
        
        int currentTime = tasks.peek().start;       // 작업을 처리하는 시작 시각을 0이 아니라 처음 수행하는 작업의 시작 시각으로 지정
        
        
        Task currentTask;
        Task nextTask;
        // plans 배열에 들어있는 과제를 하나씩 꺼냄
        while (!tasks.isEmpty()) {
            currentTask = tasks.poll();
            
            // 마지막 과제인 경우 tasks가 비어있는 경우의 예외처리
            if (tasks.isEmpty()) {
                answer.add(currentTask.name);
                currentTime += currentTask.playtime;
                break;
            }
            
            nextTask = tasks.peek();
            
            
            // 다음 과제가 시작되기 전 현재 진행중인 과제를 끝낼 수 있는 경우
            if (currentTime + currentTask.playtime <= nextTask.start) {
                answer.add(currentTask.name);
                
                // 멈춰둔 과제가 없는 경우 -> 새로운 과제를 시작함
                if (stack.isEmpty()) {
                    currentTime = nextTask.start;       // 이때 currentTime += currentTask.playtime;으로 안해주는 이유는 새로운 과제의 시작 시간보다 이른 경우가 있어서
                    continue;
                }
                
                // 멈춰둔 과제가 있는 경우
                else {
                    currentTime += currentTask.playtime;    // 멈춘 과제의 시작 시간은 중요하지 않음
                    
                    // 현재 시간이 새로운 과제의 시작 시각보다 작은 경우 (-> 멈춰둔 과제를 재시작 할 수 있음) 스택에서 과제를 pop함
                    while (currentTime < nextTask.start) {
                        if (!stack.isEmpty()) {
                            currentTask = stack.pop();
                            
                            // 다음 과제가 시작되기 전 현재 진행중인 과제를 끝낼 수 있다면
                            if (currentTime + currentTask.playtime <= nextTask.start) {
                                answer.add(currentTask.name);
                                currentTime += currentTask.playtime;
                                continue;
                            }
                            
                            // 다음 과제가 시작되기 전 현재 진행중인 과제를 끝낼 수 없다면
                            else {
                                int execTime = nextTask.start-currentTime;
                                currentTime += execTime;
                                currentTask.playtime -= execTime;

                                stack.push(currentTask);
                                break;
                            }
                        }
                        
                        else {                              // 멈춘 과제들을 다 끝냈는데 새롭게 새로운 과제의 시작 시간보다 이른 경우
                            currentTime = nextTask.start;
                            break;
                        }
                    }
                }
            }
            
            // 다음 과제가 시작되기 전 현재 진행중인 과제를 끝낼 수 없는 경우
            else {
                int execTime = nextTask.start - currentTime;
                currentTime += execTime;
                currentTask.playtime -= execTime;
                
                stack.push(currentTask);        // 끝내지 못한 과제 stack에 push함
            }
        }
        
        
        
        // 스택에 남아있는 과제들 처리
        while(!stack.isEmpty()) {
            answer.add(stack.pop().name);
        }

        
        
        return answer.toArray(new String[0]);
    }
}