/**
 * Technique: DP
 * 
 * Given different dimensions and unlimited supply of boxes for each dimension, stack boxes
 * on top of each other such that it has maximum height but with caveat that length and width
 * of box on top should be strictly less than length and width of box under it. You can
 * rotate boxes as you like.
 * 
 * 1) Create all rotations of boxes such that length is always greater or equal to width
 * 2) Sort boxes by base area in non increasing order (length * width). This is because box
 * with more area will never ever go on top of box with less area.
 * 3) Take T[] and result[] array of same size as total boxes after all rotations are done
 * 4) Apply longest increasing subsequence type of algorithm to get max height.
 * 
 * If n number of dimensions are given total boxes after rotation will be 3n.
 * So space complexity is O(n)
 * Time complexity - O(nlogn) to sort boxes. O(n^2) to apply DP on it So really O(n^2)
 *
 * References
 * http://www.geeksforgeeks.org/dynamic-programming-set-21-box-stacking-problem/
 * http://people.cs.clemson.edu/~bcdean/dp_practice/
 * https://github.com/mission-peace/interview/blob/master/src/com/interview/dynamic/BoxStacking.java
 */

 //time o(n pow 2)
 //space o(n) , n is the no of possible rotations
import java.util.*;

public class BoxStacking{

     private int maxHeight(Dimension[] input) {
         //get all rotations of box dimension.
        //e.g if dimension is 1,2,3 rotations will be 2,1,3  3,2,1  3,1,2  . Here length is always greater
        //or equal to width and we can do that without loss of generality.
        
        Dimension[] allRotations = new Dimension[input.length * 3];
        createAllRotations(input, allRotations);
        
        //sort these boxes
        Arrays.sort(allRotations);
        
        //apply longest increasing subsequence kind of algorithm on these sorted boxes.
        int len = allRotations.length;
        int[] max = new int[len];
        //used for storing indexes
        int[] res = new int[len];
        
        //initialize the array
        for(int i=0; i<len; i++) {
            max[i] = allRotations[i].height;
            res[i] = i;
        }
        
        for(int i=1; i<len; i++) {
            for(int j=0; j<i; j++) {
                if(allRotations[i].length < allRotations[j].length &&
                    allRotations[i].width < allRotations[j].width) {
                    if((max[j] + allRotations[i].height) > max[i]) {
                        max[i] = max[j] + allRotations[i].height;
                        res[i] = j;
                    }
                }
            }
        }
        
        //find max in T[] and that will be our max height.
        //Result can also be found using result[] array.
        int result = Integer.MIN_VALUE;
        for(int i=0; i < max.length; i++){
            result = Math.max(result, max[i]);
        }
        
        return result;
     }
     
     //create all rotations of boxes, always keeping length greater or equal to width
     private void createAllRotations(Dimension[] input, Dimension[] allRotations) {
         int idx = 0;
         for(int i=0; i<input.length; i++) {
             allRotations[idx++] = Dimension.createDimension(input[i].length,
                        input[i].width, input[i].height);
             allRotations[idx++] = Dimension.createDimension(input[i].height,
                        input[i].width, input[i].length);
             allRotations[idx++] = Dimension.createDimension(input[i].length,
                        input[i].height, input[i].width);
         }
     }
     
     public static void main(String[] args) {
        BoxStacking bs = new BoxStacking();
        Dimension input[] = { new Dimension(3, 2, 5), new Dimension(1, 2, 4) };
        int maxHeight = bs.maxHeight(input);
        System.out.println("Max height is " + maxHeight);
     }
}

class Dimension implements Comparable<Dimension> {
    int height;
    int length;
    int width;
    
    Dimension(int length, int width, int height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }
    
    Dimension() {
        
    }
    
    static Dimension createDimension(int length, int width, int height) {
        Dimension d = new Dimension();
        d.height = height;
        if(length >= width) {
            d.length = length;
            d.width = width;
        }
        else {
            d.length = width;
            d.width = length;
        }
        return d;
    }
    
    //sort by area
    @Override
    public int compareTo(Dimension dimension) {
        int area = this.length * this.width;
        int curarea = dimension.length * dimension.width;
        if(area >= curarea) {
            return -1;
        }
        else {
            return 1;
        }
    }
}