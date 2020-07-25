//time o(n!) n is the len of string
//space o(n!). when the string contain unique characters
import java.util.*;
public class permWithDup { 
  
    public void permute(String s) {
        helper("", s);
    }
    
    HashSet<String> set = new HashSet<>();
    private void helper(String prefix, String s) {
        //base case
        if(s.length() == 0){
            if(!set.contains(prefix)) {
                set.add(prefix);
                System.out.println(prefix);
                return;
            }
        }
        //logic
        for(int i=0; i<s.length(); i++) {
            String before = s.substring(0, i);
            String after = s.substring(i+1);
            char ch = s.charAt(i);
            helper(prefix+ch, before+after);
        }
    }
    // Driver code 
    public static void main(String args[]) 
    {
        permWithDup obj = new permWithDup();
        obj.permute("ABA"); 
    } 
}