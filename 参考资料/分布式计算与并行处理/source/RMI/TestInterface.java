package chapter4;

/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: </p>
 *
 * @author not attributable
 * @version 1.0
 */
interface Life{
    void move(int a,int b);
    void print();
}
class Animal implements Life{
    int x=10;
    int y=10;
    public void move(int a,int b){
        x+=a;
        y+=b;
    }
    public void print(){
        System.out.println( "x = " + x +","+"y="+y);
    }
}
class Bird implements Life{
    int x=10;
    int y=10;
    public void move(int a,int b){
        x*=a;
        y*=b;
    }
    public void print(){
        System.out.println( "x = " + x +","+"y="+y);
    }

}
public class TestInterface {
    public static void afterMove(Life life,int x,int y){
        life.move(x,y);
        life.print();
    }
    public static void main( String[] args ) {
        Animal a=new Animal();
        Bird b=new Bird();
        afterMove(a,10,10);
        afterMove(b,10,10);
    }
}
