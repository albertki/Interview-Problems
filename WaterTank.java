// McKinsey - interview problem
import java.util.*;

class WaterTank {
    public static void main(String[] args) {
        // Input: coordinates of top-right corner of each block in water tank
        // Output: Area of total water within tank (excluding blocks)
        // * Water has to be contained "between" blocks *
        
        // case w/ unit-width blocks
        int[][] a = {{1,2,3,4,5,6}, {3,0,0,2,0,4}};
        
        // case w/ variable-width blocks
        int[][] b = {{5,10,12,13,14,17,22,24,26}, {13,5,8,6,0,10,6,9,7}};
        
        waterArea(b);
    }
    public static void waterArea(int[][] arr) {
        int water = 0;
        int n = arr[0].length; // # of coordinates

        // record greatest height(s) going from L to R
        int[] left = new int[n];
        left[0] = arr[1][0];
        for(int i=1; i<left.length; i++) {
            left[i] = Math.max(left[i-1], arr[1][i]);
        }
        //System.out.println(Arrays.toString(left));

        // record greatest height(s) going from R to L
        int[] right = new int[n];
        right[right.length-1] = arr[1][n-1];
        for(int i=right.length-2; i>=0; i--) {
            right[i] = Math.max(right[i+1], arr[1][i]);
        }
        //System.out.println(Arrays.toString(right));

        // Add water up to min height(of L and R block)
        // Then subtract height of actual block
        // (no water exists L of first block(i=0), and R of last block)
        for(int i=1; i<n; i++) {
            water += Math.min(left[i], right[i])*(arr[0][i]-arr[0][i-1]);
            water -= arr[1][i]*(arr[0][i]-arr[0][i-1]);
        }

        System.out.println(water);
    }
}