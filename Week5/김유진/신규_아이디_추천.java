import java.util.*;

class Solution {
    public String solution(String new_id) {
        new_id = changeCharLower(new_id);
        new_id = removeOtherChar(new_id);
        new_id = changeDoubleFullStop(new_id);
        new_id = removeFrontBackFullStop(new_id);
        new_id = changeBlank(new_id);
        new_id = removeOutOfDist(new_id);
        new_id = makeLongId(new_id);

        return new_id;
    }

    private String changeCharLower(String id){
        return id.toLowerCase();
    }

    private String removeOtherChar(String id){
        return id.replaceAll("[^0-9a-z-._]","");
    }

    private String changeDoubleFullStop(String id){
        char[] idChars = id.toCharArray();
        List<Character> newId = new ArrayList<>();

        newId.add(idChars[0]);
        for(int i=1;i<=idChars.length-1;i++){
            if((idChars[i-1] == idChars[i]) && idChars[i] == '.'){
                continue;
            }

            newId.add(idChars[i]);
        }

        StringBuilder sb = new StringBuilder();
        for(char idChar: newId){
            sb.append(idChar);
        }

        return sb.toString();
    }    

    private String removeFrontBackFullStop(String id){
        if(id.length()==1 && id.charAt(0)=='.'){
            return "";
        }

        if(id.charAt(0) == '.'){
            id = id.substring(1);
        }

        if(id.charAt(id.length()-1)=='.'){
            id = id.substring(0, id.length()-1);
        }

        return id;
    }

    private String changeBlank(String id){
        if(id.length()==0){
            return "a";
        }

        return id;
    }

    private String removeOutOfDist(String id){
        if(id.length()>=16){
            id = id.substring(0, 15);

            while(id.length()>0){
                if(id.charAt(id.length()-1)!='.'){
                    break;
                }

                id = id.substring(0, id.length()-1);
            }
        }

        return id;
    }

    private String makeLongId(String id){
        if(id.length()>=3){
            return id;
        }

        char lastChar = id.charAt(id.length()-1);
        StringBuilder sb = new StringBuilder();
        sb.append(id);

        for(int i=id.length();i<3;i++){
            sb.append(Character.toString(lastChar));
        }

        return sb.toString();

    }
}
