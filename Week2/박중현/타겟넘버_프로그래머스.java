class Solution {
    static int answer = 0;

    public int solution(int[] numbers, int target) {
        // n개의 음이 아닌 정수들이 있다
        // 이 정수들을 순서를 바꾸지 않고 적절히 더하거나 빼서 타겟 넘버를 만들려고 한다.
        // 순서를 바꾸지 않는다
        // [1,1,1,1,1]로 숫자 3을 만드려면 다음 다섯 방법
        // -1 + 1 + 1 + 1+ 1

        // System.out.println(sum);

        dfs(numbers,target, 0, 0);

        return answer;
    }

    public void dfs(int[] arr, int target, int sum, int cnt){
        // System.out.println("n은 "+n +" sum은 "+sum);

        if(cnt == arr.length){
            if(sum == target){
                answer++;
            }
        }
        else{
            dfs(arr, target, sum + arr[cnt], cnt+1);
            dfs(arr, target, sum - arr[cnt], cnt+1);
        }

    }
}