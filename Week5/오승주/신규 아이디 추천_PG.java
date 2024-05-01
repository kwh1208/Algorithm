import java.util.*;

class Solution {
    static LinkedList<Character> id;
    static Set<Character> validCharacter = new HashSet<>();
    
    static StringBuilder answer = new StringBuilder();
    
    public String solution(String new_id) {
        initValidCharacter();
        
        id = new LinkedList<>();
        for (int i = 0; i < new_id.length(); i++) {
            id.add(new_id.charAt(i));
        }
        
        step1();
        step2();
        step3();
        step4();
        step5();
        step6();
        step7();
        
        for (Character c : id) {
            answer.append(c);
        }
        return answer.toString();
    }
    
    public static void step1() {
        for (int i = 0; i < id.size(); i++) {
            if (Character.isUpperCase(id.get(i))) {
                id.set(i, Character.toLowerCase(id.get(i)));
            }
        }
    }
    "dfkjdjk^^%sdkjcnlks"
    // iterator()
    public static void step2() {
        for (int i = id.size()-1; 0 <= i; i--) {
            if (!validCharacter.contains(id.get(i))) {
                id.remove(i);
            }
        }
    }
                
    public static void step3() {
        for (int i = 0; i < id.size()-1; i++) {
            if (id.get(i) == '.') {
                if (id.get(i+1) == '.') {
                    id.remove(i+1);
                    i--;
                }
            }
        }
    }
                
    public static void step4() {
        if (!id.isEmpty() && id.peekFirst() == '.') {
            id.remove();
        }
        if (!id.isEmpty() && id.peekLast() == '.') {
            id.removeLast();
        }
    }
                
    public static void step5() {
        if (id.size() == 0) {
            id.add('a');
        }
    }
                
    public static void step6() {
        if (15 <= id.size()) {
            LinkedList<Character> shortenId = new LinkedList<>();
            for (int i = 0; i < 15; i++) {
                shortenId.add(id.get(i));
            }
            
            if (shortenId.peekLast() == '.') {
                shortenId.pollLast();
            }
            id = shortenId;
        }
    }
                
    public static void step7() {
        while (!id.isEmpty() && id.size() < 3) {
            id.addLast(id.peekLast());
        }
    }
    
    public static void initValidCharacter() {
        // 숫자 추가
        for (int i = 0; i < 10; i++) {
            validCharacter.add((char)(i+48));
        }
        
        // 소문자 추가
        for (int i = 97; i < 123; i++) {
            validCharacter.add((char)i);
        }
        
        // 특수기호 추가
        validCharacter.add('-');
        validCharacter.add('_');
        validCharacter.add('.');
    }
}
