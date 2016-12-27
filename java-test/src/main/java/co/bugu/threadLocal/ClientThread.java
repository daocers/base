package co.bugu.threadLocal;

/**
 * Created by daocers on 2016/10/26.
 */
public class ClientThread extends Thread {
    private Secquence secquence;

    public ClientThread(Secquence secquence){
        this.secquence = secquence;
    }

    @Override
    public void run(){
        for(int i = 0; i < 3; i++){
            System.out.println(Thread.currentThread().getName() + "= > " + secquence.getNumber());
        }
    }

}
