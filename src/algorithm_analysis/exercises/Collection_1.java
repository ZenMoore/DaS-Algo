package algorithm_analysis.exercises;

public class Collection_1<T> {

    private T[] tuple;
    private int size;


    public Collection_1(T[] tuple){
        this.tuple = tuple;
        this.size = tuple.length;
    }

    public boolean isEmpty(){
        return tuple.length == 0?true:false;
    }

    public void makeEmpty(){
        /*
        这样的做法不是很好，
         */
        tuple = (T[]) new Object[]{};
        size = 0;
    }

    public void insert(T chose, int key){
        T[] temps = (T[]) new Object[tuple.length+1];
        for(int i = 0;i<tuple.length+1;i++){
            if(i == key){
                temps[i] = chose;
            }else if(i > key){
                temps[i+1] = tuple[i];
            }else{
                temps[i] = tuple[i];
            }
        }
         tuple = temps;
    }

    public void remove(int key){
        T[] temps = (T[]) new Object[tuple.length-1];
        for(int i = 0;i<tuple.length+1;i++){
            if(i == key){
                continue;
            }else if(i > key){
                temps[i-1] = tuple[i];
            }else{
                temps[i] = tuple[i];
            }
        }
        tuple = temps;
    }

    public boolean isPresent(T chose){
        boolean init = false;
        for(int i = 0;i<tuple.length;i++){
            if(tuple[i].equals(chose)){
                init = true;
                break;
            }
        }
        return init;
    }

}
