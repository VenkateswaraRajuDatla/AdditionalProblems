/*Given a string s and an int k, return all unique substrings of s of size k with k distinct characters.

Example 1:

Input: s = "abcabc", k = 3
Output: ["abc", "bca", "cab"] or print them directly */ 
import java.util.*;

public class Main {
    
    public static void uniqueSubstringSizeK(String s, int k) {
        List<String> res = new ArrayList<>();
        if(s == null || s.length() == 0)
            return;
        int i=0;
        int n = s.length();
        HashMap<Character, Integer> map = new HashMap<>();
        for(int j=0; j<n; j++) {
            char ch = s.charAt(j);
            if(map.containsKey(ch)) {
                i = Math.max(i, map.get(ch));
            }
            map.put(ch, j+1);
            if(j-i+1 == k && !res.contains(s.substring(i, j + 1))) {
                res.add(s.substring(i, j + 1));
                i++;
            }
        }
        System.out.println(res);
    }
    
    public static void main(String[] args) {
        uniqueSubstringSizeK("abcabc", 3);
        uniqueSubstringSizeK("abacab", 3);
        uniqueSubstringSizeK("awaglknagawunagwkwagl", 4);
    }
}