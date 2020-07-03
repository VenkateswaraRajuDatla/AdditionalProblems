import java.util.*;

public class PermutationsOfStringWithoutDup {
    
    List<String> res;
    public List<String> permutations(String str) {
        res = new ArrayList<>();
        helper("", str);
        return res;
    }

    private void helper(String prefix, String str) {
        //base case
        if(str.length() == 0)
        {
            res.add(prefix);
            return;
        }
        //logic
        for(int i=0; i<str.length(); i++) {
            String before = str.substring(0, i);
            String after = str.substring(i+1);
            char ch = str.charAt(i);
            helper(prefix+ch, before+after);
        }
    }
}