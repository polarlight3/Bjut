package JavaTest;

/**
 * Created by hadoop on 3/21/16.
 */
public class Ttr extends Thread {
    Boolean flag = true;
    public Ttr(Boolean f){
        flag = f;
    }
    @Override
    public void run()  {
        if(this.flag){
            synchronized(MyLock.locka){
                System.out.println("lockA");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized(MyLock.lockb){
                    System.out.println("lockB");
                }
            }
        }
        else{
            synchronized(MyLock.lockb){
                System.out.println("lockB");
                synchronized(MyLock.locka){
                    System.out.println("lockA");
                }
            }
        }
    }
}