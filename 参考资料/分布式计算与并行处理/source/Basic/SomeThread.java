package Basic;

public class SomeThread extends Thread {
    int id;
    public SomeThread( int id ) {
        this.id = id;
    }

    public void run() {
        for ( int i = 1; i < 10; i++ ) {
            System.out.println( "thread" + id + ":" + i );
        }
    }
}
