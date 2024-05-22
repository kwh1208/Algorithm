import java.util.*;
import java.io.*;

class Main{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        List<Integer> lessons = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        int sum = 0;
        int maxLesson = Integer.MIN_VALUE;
        for(int i=0;i<N;i++){
            int lesson = Integer.parseInt(st.nextToken());
            sum += lesson;
            if(maxLesson < lesson){
                maxLesson = lesson;
            }
            
            lessons.add(lesson);
        }
        
        int min = maxLesson;
        int max = sum;
        
        int res = -1;
        while(min<=max){
            int mid = (min+max)/2;
            int count = countBlueRay(lessons, mid);
            
            if(count > M){
                min = mid+1;
                continue;
            }
            
            res = mid;
            max = mid -1;
        }
        
        System.out.println(res);
    }
    
    private static int countBlueRay(List<Integer> lessons, int size){
        int blueRay = 0;
        int cnt=1;
        for(int lesson:lessons){
            if(blueRay+lesson <= size){
                blueRay+=lesson;
                continue;
            }
            
            blueRay=lesson;
            cnt++;
        }
        
        return cnt;
    }
}
