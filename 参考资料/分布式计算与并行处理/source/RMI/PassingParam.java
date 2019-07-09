/**
 * <p>Title: </p>
 *
 * <p>Description: Example in Teaching jjava</p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: </p>
 *
 * @author Shang Zhenhong
 * @version 1.0
 */
public class PassingParam {


    private void passValue(int value) {
        value = 10;
        System.out.println("value = " + value);
    }

    private void passRef(Vehicle v) {
        v.passengers = 7;
        v.fuelcap = 16;
        v.mpg = 21;
    }

    public static void main(String[] args) {
        PassingParam pass = new PassingParam();
        Vehicle sportscar = new Vehicle();
        sportscar.passengers = 2;
        sportscar.fuelcap = 14;
        sportscar.mpg = 12;
        pass.passRef(sportscar);
        System.out.println("passengers="+sportscar.passengers);

        int i = 0;
        pass.passValue(i);
        System.out.println("i = " + i);

    }
}
