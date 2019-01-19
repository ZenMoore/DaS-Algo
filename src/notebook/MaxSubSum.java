package notebook;

/**
 * https://www.cnblogs.com/AlvinZH/p/6795647.html
 * @author AlvinZH
 * Maximum Subsequence Sum Problem
 */
public class MaxSubSum {

    /**
     * The most null method.
     * T(N)=O(N^3)
     */
    public static int maxSubSum1(int[] a){
        int maxSum = 0;
        for(int i = 0;i<a.length;i++){
            for(int j = i;j<a.length;j++){
                int thisSum = 0;
                for(int k = i;k<=j;k++){
                    thisSum += a[k];
                }
                if(thisSum > maxSum){
                    maxSum = thisSum;
                }
            }
        }
        return maxSum;
    }

    /**
     * improved method of No.1
     * T(N)=O(N^2)
     */
    public static int maxSubSum2(int [] a){
        int maxSum = 0;
        for(int i = 0;i<a.length;i++){
            int thisSum = 0;
            for(int j = i;j<a.length;j++){
                thisSum += a[j];
                if(thisSum > maxSum){
                    maxSum = thisSum;
                }
            }
        }
        return maxSum;
    }

    /**
     * Partition Strategy-Recursive
     * T(N)=O(NlogN)
     * @param a
     * @return max_sub_sum
     */
    public static int maxSubSum3(int[] a){

        return maxSumRec(a,0,a.length-1);
    }

    private static int maxSumRec(int[] a,int left,int right){
        //  Base case.
        if(left == right){
            if(a[left] > 0){
                return a[left];
            }else{
                return 0;
            }
        }

        // Outermost layer.
        int center = (left+right)/2;
        int maxLeft = maxSumRec(a,left,center);
        int maxRight = maxSumRec(a,center+1,right);

        int maxLeftBorder = 0,leftBorder = 0;
        for(int i = center;i>=left;i--){
            leftBorder += a[i];
            if(leftBorder > maxLeftBorder){
                maxLeftBorder = leftBorder;
            }
        }

        int maxRightBorder = 0,rightBorder = 0;
        for(int i = center+1;i<=right;i++){
            rightBorder += a[i];
            if(rightBorder > maxRightBorder){
                maxRightBorder = rightBorder;
            }
        }

        return max3(maxRightBorder,maxLeftBorder,maxLeftBorder+maxRightBorder);
    }
     private static int max3(int a,int b,int c){
        int max = 0;
        if(a >= b){
            max = a;
        }else{
            max = b;
        }
         if (max < c){
             max = c;
         }
        return max;
     }

    /**
     * on-line algorithm
     * T(N)=O(N)
     */
    public static int maxSubSum4(int[] a){
        int maxSum = 0, thisSum = 0;
        for(int i = 0;i<a.length;i++){
            thisSum += a[i];

            if(thisSum > maxSum){
                maxSum = thisSum;
            }else if(thisSum < 0){
                thisSum = 0;
            }
        }
        return maxSum;
    }
}
