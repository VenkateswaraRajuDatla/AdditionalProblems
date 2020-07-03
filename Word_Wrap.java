//Given a sequence of words, and a limit on the number of characters that can be put in one line (line width).
// Put line breaks in the given sequence such that the lines are printed neatly. Assume that the length of each word
// is smaller than the line width.

//For example, consider the following string and line width M = 15
 //"Geeks for Geeks presents word wrap problem" 
     
 //Following is the optimized arrangement of words in 3 lines
 //Geeks for Geeks
 //presents word
 //wrap problem 
 
 //The total extra spaces in line 1, line 2 and line 3 are 0, 2 and 3 respectively. 
 //So optimal value of total cost is 0 + 2*2 + 3*3 = 13

//time o(n pow 2)
//space o(n pow 2)
 public class Word_Wrap {

    public String justify(String words[], int width) {
        int len = words.length;
        //spaces left over after filling
        int[][] cost = new int[len][len];

        for(int i=0; i<len; i++) {
            cost[i][i] = width - words[i].length();
            for(int j=i+1; j<len; j++) {
                // calc the left over spaces after deleting the word's len and a space
                cost[i][j] = cost[i][j-1] - words[j].length() - 1; 
            }
        }

        for(int i=0; i<words.length; i++) {
            for(int j=i; j<words.length; j++) {
                if(cost[i][j] < 0) {
                    cost[i][j] = Integer.MAX_VALUE;
                }
                else {
                    cost[i][j] = (int) Math.pow(cost[i][j], 2);
                }
            }
        }

        int[] minCost = new int[len];
        int[] res = new int[len];

        for(int i=len-1; i>=0; i--) {
            minCost[i] = cost[i][len-1];
            res[i] = len;
            for(int j=len-1; j>i; j--) {
                if(cost[i][j-1] == Integer.MAX_VALUE) {
                    continue;
                }
                if(minCost[i] > minCost[j] + cost[i][j-1]) {
                    minCost[i]= minCost[j] + cost[i][j-1];
                    res[i] = j;
                }
            }
        }

        int i = 0;
        int j;
        
        System.out.println("Minimum cost is " + minCost[0]);
        System.out.println("\n");
        //finally put all words with new line added in 
        //string buffer and print it.
        StringBuilder builder = new StringBuilder();
        do{
            j = res[i];
            for(int k=i; k < j; k++){
                builder.append(words[k] + " ");
            }
            builder.append("\n");
            i = j;
        }while(j < words.length);
        
        return builder.toString();
    }

    public static void main(String args[]){
        String words1[] = {"Raju","likes","to","write","code","at", "free", "time"};
        Word_Wrap awl = new Word_Wrap();
        System.out.println(awl.justify(words1, 12));
    }
}