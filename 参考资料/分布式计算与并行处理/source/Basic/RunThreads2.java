package Basic;

public class RunThreads2 {
    public static void main( String[] args ) {
        Thread t1=new Thread(new SomeThread2(1));
        t1.start();
        Thread t2=new Thread(new SomeThread2(2));
        t2.start();
    }
}
