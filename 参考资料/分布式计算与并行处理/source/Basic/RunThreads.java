package Basic;

public class RunThreads {
    public static void main( String[] args ) {
        SomeThread t1 = new SomeThread( 1 );
        t1.start();
        SomeThread t2 = new SomeThread( 2 );
        t2.start();
    }
}
