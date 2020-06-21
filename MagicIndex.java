//time o(logn)
//space o(1)
// Find a Magic Index (Value equal to index) in a given array

public class MagicIndex 
{ 
    static int binarySearch(int arr[], int low, int high) 
    { 
        while(low <= high) {
            int mid = low + (high - low)/2;
            if(arr[mid] == mid)
                return mid;
            else if(arr[mid] < mid)
                low = mid + 1;
            else
                high = mid -1;
        }
        return -1;
    } 
        
    //main function 
    public static void main(String args[]) 
    { 
        int arr[] = {-10, -1, 0, 3 , 10, 11, 30, 50, 100}; 
        int n = arr.length; 
        System.out.println("Magic Index is " 
                   + binarySearch(arr,0, n-1));         
    }  
} 