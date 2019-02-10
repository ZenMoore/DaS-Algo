package algorithm_analysis.exercises;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Chapter_2 {

    /**
     * 求最小子序列和
     * T(N)=O(N)
     *
     * _17 三个问题的代码解答。
     * https://blog.csdn.net/xiaofengcanyuelong/article/details/78997486
     */
    public static int _17a(int[] a ){
        /*
        //如果说最大子序列问题的联机算法是向上顶的，那么这个就需要向下顶，这一步将数据反转为相反数。
        int[] b = new int[a.length];
        for(int i = 0;i<a.length;i++){
            b[i] = -a[i];
        }

        //下面和最大子序列问题的反转算法没有什么差别。
        return -notebook.MaxSubSum.maxSubSum4(b);
        */

        int minSum, thisSum;

        minSum = 0; thisSum = 0;
        for (int i = 0; i < a.length; i++)
        {
            thisSum += a[i];

            if (thisSum < minSum)
                minSum = thisSum;
            else if (thisSum>0)
                thisSum = 0;
        }
        return minSum;

    }

    /**
     * 求最小正子序列和
     * T(N)=O(N^2)->O(NlogN)
     */
    public static int _17b(int[] a){
        /*
        int minSum = 0, sum = 0;
        for(int i = 0; i<a.length; i++){
            for(int j = i; j<a.length; j++){
                sum += a[j];
                if(minSum > sum && sum > 0){
                    minSum = sum;
                }
            }
        }
        return minSum;
        */

        return 0;
    }

    /**
     * 求最大子序列乘积
     */
    public static int _17c(){

        return 0;
    }

    public static <T extends Function_2> double _18(T f, double low, double high){
            double resultX = 0;

            if((f.f(low)*f.f(high)) > 0){
                resultX = 65532;
            }else if((f.f(low)*f.f(high)) == 0){
                resultX = f.f(low) == 0 ? low : high;
            }else{
                if(high - low <= 0.0001){
                    resultX = (low+high)/2;
                }else{
                    double center = (low + high)/2;

                    if(f.f(center) * f.f(high) < 0){
                        resultX = _18(f, center, high);
                    }else if(f.f(center) * f.f(high) > 0){
                        resultX = _18(f, low, center);
                    }else{
                        resultX = center;
                    }
                }
            }

            return resultX;
        }

    /**
     * prime test 检验素数
     * O(N^{1/2})
     * @param N
     * @return
     */
    public static boolean _20a(int N){
        if(N == 2){
            return true;
        }else if(N % 2 == 0){
            return false;
        }
        for(int i = 3; i<Math.sqrt(N); i+=2){
            if(N % i == 0){
                return false;
            }
        }

        return true;
    }

    /**
     * 计算一个十进制数字的二进制表示的位数
     * O(logN)
     * @param N
     * @return
     */
    public static int _20c(int N){
        int B = 0;

        while((N/=2)!=0){
            B++;
        }

        return ++B;
    }

    /**
     * 打印小于N的全部素数。
     * 算法是：厄拉多塞(Erastothenes)筛
     * O(NloglogN)
     * 算法分析：T(N)=N+N*X+Y(X是素数的倒数和=loglogN,Y是合数的数量(常数))
     * @param N:从2输出直到N
     */
    public static void _21(int N){
        int[] numbers = new int[N-1];
        for(int i = 2; i-2 < numbers.length;i++){
            numbers[i-2] = i;
        }

        for(int i = 2; i <= N; i++){
            if(numbers[i-2] != 0){
                System.out.print(i+"\t");
                for(int j = 1; j*i <= N; j++){
                    numbers[j*i-2] = 0;
                }
            }
        }

    }

    /**
     * 不用递归的快速求幂程序。
     * O(logN)
     * @param x:底数
     * @param N:指数 N >= 1
     * @return 整数幂
     */
    public static int _23(int x, int N){
        int[] pows = new int[(int)Math.log(N)+1];
        pows[0] = x;
        for(int i = 1; i<pows.length; i++){
            pows[i] = pows[i-1]*pows[i-1];
        }

        int result = 1, i = 0;

        do{
            if(N%2 == 1){
                result *= pows[i];
            }
            i++;
        }while( (N/=2) != 0);

        return result;
    }

    /**
     * 返回一个数组的主元素
     * 主元素：大小为N的数组，若一个元素出现的次数超过N/2次，即为主元素。
     * 算法概要：
     * 1. 找出一个候选元。
     * 2. 确定候选元为主元素。
     *
     * 候选元：比较原数组相邻元素，如果相等添加到新数组中，如果不相等则不做处理；对新数组递归的寻找候选元也是原数组的候选元。
     *
     * 需要思考的细节问题：
     * 1. 为什么新数组的候选元也是原数组的候选元。
     * 2. 递归的终止条件。
     * 3. 该算法的运行时间。
     * 4. 如何避免添加新数组。
     * 5. N为奇数时的处理。
     *
     * 问题解答：
     * 2. Recursion is unnecessary if there are two or fewer elements.
     * 3.  The running time is O(N), and satisﬁes T(N)=T(N/2)+O(N).
     * 4. One copy of the original needs to be saved.
     * After this, the B array, and indeed the recursion, can be avoided by placing each Bi in the A array.
     * The difference is that the original recursive strategy implies that O(log N) arrays are used;
     * this guarantees only two copies.
     * 5. One way to do this is to note that if the ﬁrst N−1 elements have a majority,
     * then the last element cannot change this. Otherwise, the last element could be a majority.
     * Thus if N is odd, ignore the last element. Run the algorithm as before.
     * If no majority element emerges, then return the Nth element as a candidate.
     * @param array:默认数组里面不允许存在 -1，因为-1作为标记数字。
     * @return 返回主元素，如为-1则表示没有主元素。
     */
    public static int _26(int[] array){
        //获取候选元
        int[] candidates = getCand(array);

        //
        for(int i = 0, count = 0; i<candidates.length; i++,count = 0){
            for(int j = 0; j<array.length;j++){
                if(candidates[i] == array[j]){
                    count++;
                }
            }
            if(count >= array.length/2+1) return candidates[i];
        }

        return -1;//不存在主元素
    }

    /**
     * 该算法获取主元算法的候选元。
     * @return 可能是候选元的数组，该数组包含两个或者一个候选元。
     */
    private static int[] getCand(int[] array){
        //引入数组B
        int[] list = new int[array.length/2];
        for(int i = 0; i<list.length; i++){
            list[i] = -1;
        }

        int j = 0;
        if(array.length <= 2){//基准情况
            return array;
        }else if(array.length%2 == 0){
            //获取候选元数组
            for(int i = 0; i<array.length; i += 2){
                if((array[i] == array[i+1])){
                  list[j++]=array[i];
                }
            }
        }else{// array.length%2 != 0
            //获取候选元数组
            for(int i = 0; i<array.length-1; i += 2){
                if((array[i] == array[i+1])){
                    list[j++]=array[i];
                }
            }

            //对末项进行处理，如果其他项有主元则末项不做处理，否则将末项作为候选元。
            int[] list0 = trim(list);
            if(_26(list0) == -1){// 不存在主元
                list = new int[1];
                list[0] = array[array.length-1];
                return list;
            }

        }

        //递归
        return getCand(trim(list));
    }

    /**
     * 删除数组的空白项。
     * @param list:原始数组，空白项是-1，该函数不更改原数组。
     * @return 返回新数组，没有空白项-1
     */
    private static int[] trim(int[] list){

        int count = 0;
        for(int i: list){
            if(i != -1){
                count++;
            }else{
                break;
            }
        }
        int[] list0 = Arrays.copyOf(list,count);
        return list0;
    }


}
