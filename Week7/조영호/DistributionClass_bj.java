package Week7.조영호;

import java.util.*;
import java.io.*;

public class DistributionClass_bj {
    // 문제
    // 수강신청의마스터 김종혜 선생님에게 새로운 과제가 주어졌다.
    // 김종혜 선생님한테는 Si에 시작해서 Ti에 끝나는 N개의 수업이 주어지는데,
    // 최소의 강의실을 사용해서 모든 수업을 가능하게 해야 한다.
    // 참고로, 수업이 끝난 직후에 다음 수업을 시작할 수 있다.
    // ( 즉,Ti ≤ Sj 일 경우 i수업과 j 수업은 같이들을 수 있다.)
    // 수강신청 대충한게 찔리면, 선생님을 도와드리자!

    // 입력
    // 첫번째 줄에 N이 주어진다.(1≤N≤200,000)
    // 이후 N개의 줄에 Si, Ti가 주어진다.(0≤Si<Ti≤109)

    // 출력
    // 강의실의 개수를 출력하라.

    // 접근 방법
    // 이 문제는 최소한의 강의실을 사용해서 주어진 강의를 모두 가능하게 만드는 방법을 찾는 문제
    // 첫째 줄에 1 - 3 강의를 위해 강의실 1이 필요하고,
    // 둘째 줄에 2 - 4 강의를 위해 강의실 2가 필요하고,
    // 셋째 줄에 3 - 5 강의를 시작 할 시점에는 강의실 1에서 강의가 끝난 시점 이므로 강의실 1을 이용할 수 있다.
    // 그래서 총 강의실 2개가 필요하다는 결과가 나온다.
    // 예제 입력은 단 3개 뿐이라 쉽지만, 입력이 많아지면 어떻게 해야 할까?
    // 일단 시작시간이 빠른 순으로 정렬해야 한다.
    // 어차피 빠른 시각에 시작하는 강의를 먼저 처리해야 하기때문이다.
    // 그런데 추가로 종료시간도 빠른 순으로 정렬해야지 편할 것이다.
    // 따라서 시작시간이 같을 경우 종료시간이 빠른 순으로, 이외에는 시작시간이 빠른 순으로 정렬한다.
    // 그리고 강의실을 나타내는 PriorityQueue<Integer> room를 만들고, 이번에는 종료시간이 빠른 순으로 poll() 되도록
    // 만든다.
    // 그리고 이 우선순위 큐 room에는 종료시간만 저장한다.
    // 그렇게 되면, 새로운 강의를 어느 강의실에 배정할 지 정할 때 room에서 가장 빠른 종료시간 값을 꺼내서 이 값과 새로운 강의의
    // 시작시간을 비교한다.
    // 만약 꺼낸 종료시간이 시작시간보다 크면 새로운 강의실을 사용해야 할 것이고, 꺼낸 종료시간이 시작시간보다 작으면 poll()한 뒤
    // room에 새로운 강의의 종료시간을 넣으면 될 것이다.
    // 그리고 마지막에 남아있는 PriorityQueue<> room의 크기가 정답이 될 것이다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;
        // (1) 입력받은 강의의 우선순위를 정해준다.
        PriorityQueue<int[]> pq = new PriorityQueue<>(((o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            } else {
                return o1[0] - o2[0];
            }
        }));
        // (2) 강의를 입력받아서 pq에 저장한다.
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            pq.offer(new int[] { start, end });
        }
        // (3) 강의실을 나타내고, 강의의 종료시간을 담을 우선순위 큐 room을 만든다.
        PriorityQueue<Integer> room = new PriorityQueue<>();
        room.offer(0);

        // (4) room에서 하나씩 값을 peek() 하여 값을 비교하고, poll()을 할지 말지 결정한다.
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            if (room.peek() <= cur[0]) {
                room.poll();
            }
            room.offer(cur[1]);
        }

        System.out.println(room.size());
    }
}
