//Count ways to reach the nth stair using step 1, 2 or 3
//DP
//time o(n)
//space o(n)
import java.util.*; 
import java.lang.*; 
  
public class TripleCount { 
  
    // Returns count of ways to reach 
    // n-th stair using 1 or 2 or 3 steps. 
    public static int findStep(int n) 
    {
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        dp[2] = 4;
        for(int i=3; i<n; i++) {
            dp[i] = dp[i-1] + dp[i-2] + dp[i-3];
        }
        return dp[n-1];
    } 
  
    // Driver function 
    public static void main(String argc[]) 
    { 
        int n = 4; 
        System.out.println(findStep(n)); 
    } 
}