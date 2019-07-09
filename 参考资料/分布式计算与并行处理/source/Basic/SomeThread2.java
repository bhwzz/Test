package Basic;

public class SomeThread2 implements Runnable {
    private int id;
    SomeThread2( int id ) {
        this.id = id;
    }

    public void run() {
        for ( int i = 1; i < 10; i++ ) {
            System.out.println( "thread" + id + ":" + i );
        }
    }
}
