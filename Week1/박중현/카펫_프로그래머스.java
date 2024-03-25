class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];

        // 테두리 1줄은 갈색으로 칠해져 있는 상황
        // 중앙에는 노란색으로 칠해져 있음

        // b b b b b b b b
        // b y y y y y y b
        // b y y y y y y b
        // b y y y y y y b
        // b y y y y y y b
        // b b b b b b b b

        // 노란색을 기준으로 찾읍시다
        // 노란색의 공약수? 형태를 기준으로 해서 brown 개수가 맞으면 통과 이런식으로 접근

        for(int i = 1; i <= yellow; i++) {
            if(yellow % i==0) {
                int l = i;
                int h = yellow/i;

                if(brown == (l+h+2) * 2) {
                    answer[0] = h+2;
                    answer[1] = l+2;
                    break;
                }
            }
        }

        return answer;
    }
}