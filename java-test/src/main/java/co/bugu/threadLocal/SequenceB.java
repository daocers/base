package co.bugu.threadLocal;

/**
 * Created by daocers on 2016/10/26.
 */
public class SequenceB implements Secquence {
    private static ThreadLocal<Integer> numberContainer = new ThreadLocal(){
        @Override
        protected Integer initialValue(){
            return 0;
        }
    };

    @Override
    public int getNumber() {
        numberContainer.set(numberContainer.get() + 1);
        return numberContainer.get();
    }

    public static void main(String[] args){
        Secquence secquence = new SequenceB();
        ClientThread thread1 = new ClientThread(secquence);
        ClientThread thread2 = new ClientThread(secquence);
        ClientThread thread3 = new ClientThread(secquence);

        thread1.start();
        thread2.start();
        thread3.start();


    }
}
