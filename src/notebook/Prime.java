package notebook;

public class Prime {

    /**
     *   prime test 检验素数
     *   O(N^{1/2})
     *   若用B表示一个十进制数字的二进制表示的位数
     *   则更准确的表示运行时间的方式是：O(2^{B/2})
     *   因为后者更加准确的表示了输入的规模。
     *   @param N
     *   @return
     *
     * @param N
     * @return
     */
    public static boolean test(int N){
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
     * 打印小于N的全部素数。
     * 算法是：厄拉多塞(Erastothenes)筛
     * O(NloglogN)
     * 算法分析：T(N)=N+N*X+Y(X是素数的倒数和=loglogN,Y是合数的数量(常数))
     * @param N:从2输出直到N
     */
    public static void printPrimes(int N){
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
}
