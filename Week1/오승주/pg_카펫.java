class Solution {
    public int[] solution(int brown, int yellow) {
        int[] answer = new int[2];
        
        // 노란색 블록이 1개일 경우는 한가지 경우밖에 없으므로
        if (yellow == 1) {
            answer[0] = 3;
            answer[1] = 3;
            return answer;
        }
        
        // 노란색 블록 개수의 약수 짝을 구함 -> 중복 X((1,12) == (12, 1))
        for (int i = 1; i <= yellow/2; i++) {
            // 나누어 떨어지는 경우 -> 약수
            if (yellow % i == 0) {
                // 가로의 길이가 더 긴 카페트
                int width = yellow/i;
                int height = i;
                
                int borderWidth = width+2;
                int borderHeight = height+2;
                
                if ((borderWidth*borderHeight-yellow) == brown) {
                    answer[0] = borderWidth;
                    answer[1] = borderHeight;
                    
                    break;
                }
            }
        }
        return answer;
    }
}