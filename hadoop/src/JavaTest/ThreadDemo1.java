package JavaTest;

/**
  * Created by hadoop on 3/21/16.
  */
public class ThreadDemo1 {
  public static void main(String[] args) {
    Ttr t1 = new Ttr(true);
    Ttr t2 = new Ttr(false);
    t1.start();
    t2.start();
  }
}
