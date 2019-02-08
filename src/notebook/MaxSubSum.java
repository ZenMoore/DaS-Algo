package notebook;

/**
 * https://www.cnblogs.com/AlvinZH/p/6795647.html
 * @author AlvinZH
 * Maximum Subsequence Sum Problem
 *
 * 以下算法有个缺陷是：所有默认规定最大子序列和大于零。
 * 一个解决方案是：
 * 从序列中找一项作为maxSum的默认值default。
 * 可行性证明：
 * 仅讨论上述情况的假设：即最大子序列和小于零。
 * (写到这里发现只需要检测一下是不是所有的数字都是小于零的，若是，则取最大值）
 * （这样可以把检测过程和取值过程合并，若取值小于零，则是了，若大于零，则重置使用如下算法。）
 * （但这耗费线性时间。)）
 * 若无法找出大于default的，由于default存在且这是成为上确界，自然是结果。
 * 如可以找出，则得解。
 * 总结：抽项或者检测-取值。
 *
 * 对于联机算法，显得不是这么随意，因为thisSum不仅与maxSum比较，还与0比较，解决方案：
 * 只能采用检测-取值思路。
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
        int max = a[0];
        for(int i:a){
            if(i>max){
                max = i;
            }
        }
        if(max < 0){
            return max;
        }

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
