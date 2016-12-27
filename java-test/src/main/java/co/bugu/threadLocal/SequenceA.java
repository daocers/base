package co.bugu.threadLocal;

/**
 * Created by daocers on 2016/10/26.
 */
public class SequenceA implements Secquence {
    private static int number = 0;
    @Override
    public int getNumber() {
        number = number + 1;
        return number;
    }

    public static void main(String[] args){
        Secquence secquence = new SequenceA();
        ClientThread thread1 = new ClientThread(secquence);
        ClientThread thread2 = new ClientThread(secquence);
        ClientThread thread3 = new ClientThread(secquence);

        thread1.start();
        thread2.start();
        thread3.start();

    }
}
