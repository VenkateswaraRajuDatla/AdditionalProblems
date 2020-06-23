//Multiply 2 numbers without *
public class Multiply2numbers {


    private long multiply(long x, long y) {
        boolean negative = false;
        if((x<0 && y>0) ||(x>0 && y<0)) {
            negative = true;
        }
        x = Math.abs(x);
        y = Math.abs(y);

        long res;

        if(Math.min(x, y) == x) {
            res = helper(y, x);
        }
        else {
            res = helper(x, y);
        }
        if(negative)
            return res * -1;
        return res;
    }

    private long helper(long x, long y) {
        //base case
        if(y == 0)
            return 0;
        //logic
        long result = x + helper(x, y-1);
        return result;
    }

    public static void main(String[] args) {

        Multiply2numbers ob = new Multiply2numbers();
        long x = 2, y = -20;

        long multiplyResult = ob.multiply(x, y);
        System.out.println(multiplyResult);

    }
}