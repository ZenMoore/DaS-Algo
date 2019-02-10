package notebook;

/**
 * GCD
 * @author ZenMoore
 */
public class Gcd {

    /**
     * 著名的欧几里得算法，也就是辗转相除法。
     * T(N) = O(logN)
     * 这里使用了尾递归，所以很容易不适用递归。
     * @param a
     * @param b
     * @return
     */
    public static int euclidean(int a, int b){
        if(a < b){
            int t = a;
            a = b;
            b = t;
        }

        if(a % b == 0){
            return b;
        }else{
            return euclidean(a, a%b);
        }
    }

    /**
     * 对欧几里得算法的不断简化。<br>
     * 最原始的尾递归形式->先大小排列再循环形式->先循环再大小交换形式->用位运算执行交换->合并所有计算式。<br>
     * 下面这个代码只能在C和C++运行，因为Java对操作数和操作符结合在顺序上的处理不同<br>
     *
     * @code int gcd(int a,int b) {
     * 	while (b ^= a ^= b ^= a %= b);
     * 	return a;
     * }
     *
     * @param a 必须大于或者等于 b
     * @param b
     * @return
     */
    public static int bit(int a, int b){
//      原始思路。
//        if(a < b){
//            int t = a;
//            a = b;
//            b = t;
//        }
//        while(b!=0){
//            int t = b;
//            b = a % b;
//            a = t;
//        }
//        return a;


        while((a %= b) >0){
            b^=a;
            a^=b;
            b^=a;
        }
        return b;

    }

}
