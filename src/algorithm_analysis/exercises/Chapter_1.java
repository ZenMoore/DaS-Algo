package algorithm_analysis.exercises;


public class Chapter_1 {

    /**
     * Exercise: 1.2
     *     puzzle game: just provide the method of spanning.
     *     When we have spanned a initial letter of a word, we begin to type in.
     *     If not matching, stop typing. If matching, continue.
     */
    public static void _2(){
        String[] words = new String[]{"app","alt","je"};

        char[][] table = new char[][]{
                {'a','j','e'},
                {'l','p','l'},
                {'t','p','p'}
        };

        //Row span
        for(int i=0;i<table.length;i++) {
            //Column span(left to right)
            for(int j=0;j<table[i].length;j++){
                char temp = table[i][j];
            }
            //Column span(right to left)
            for(int j=table[i].length-1;j>=0;j--){
                char temp = table[i][j];
            }
        }

        //Column span
        for(int j=0;j<table[0].length;j++){
            //Row span(up to down)
            for(int i=0;i<table.length;i++){
                char temp = table[i][j];
            }
            //Row span(down to up)
            for(int i=table.length-1;i>=0;i--){
                char temp = table[i][j];
            }
        }

        //Diagonal span like this:///////
        for(int k=0;k<5;k++){
            //up-right to down-left
            for(int i=0;i<k+1;i++){
                char temp = table[i][k-i];
            }
            //down-left to up-right
            for(int i=0;i<k+1;i++){
                char temp = table[k-i][i];
            }
        }

        //Diagonal span like this:\\\\\\\\
        for(int i=0,j=2;i<3;){
            //It is bound to use a reverse..but...I need to pay attention to self-torment...^V^
            for(int a = i,b = j;a==3||b==3;a++,b++){
                char temp = table[a][b];
            }
            if(--j==-1){
                j++;
                i++;
            }
        }
    }

    /**
     * Exercise 1.3
     * @param source
     */
    public static void _3(double source){
       printInt((int)source);
        if(source - (int)source > 0){
            System.out.print(".");
        }
        printDouble(source-(int)source);
    }

    public static void printInt(int digit){
        if(digit/10 > 0){
            printInt(digit/10);
        }
        System.out.print(digit%10);
    }

    // I use a while for the first time. But that isn't recursive. So I change my method.
    public static void printDouble(double digit){
        if(digit < 1 && digit > 0.0000000001){
            System.out.print((int)(digit*10));
            printDouble(digit*10-(int)(digit*10));
        }
    }


}
