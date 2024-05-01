class Solution {
      public String solution(String new_id) {
       String answer = "";
        
       //1단계
       answer = new_id.toLowerCase();
        
       //2단계
       answer = answer.replaceAll("[^0-9a-z-_.]", "");
       
       //3단계
       answer = answer.replaceAll("\\.{2,}", ".");
       
       //4단계
       answer = answer.charAt(0) == '.' ? answer.substring(1) : answer;
       answer = answer.length() > 0 && answer.charAt(answer.length()-1) == '.' ? answer.substring(0, answer.length()-1) : answer;
        
       //5단계
       answer = answer.length() == 0? "a" : answer;
       
       //6단계
       answer = answer.length() >= 16? answer.substring(0,15) : answer;
       answer = answer.charAt(answer.length()-1) == '.' ? answer.substring(0, answer.length()-1) : answer;
       
       //7단계
       String s = answer.charAt(answer.length()-1) + "";
       answer = answer.length() <= 2? answer.concat(s.repeat(3-answer.length())) : answer;
       
        
        return answer;
    }
}