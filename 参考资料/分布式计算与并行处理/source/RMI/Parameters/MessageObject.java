// Copyright MageLang Institute; Version $Id: //depot/main/src/edu/modules/RMI-mml2/magercises/RMIParameters/Solution/MessageObject.java#2 $
import java.io.Serializable;

public class MessageObject implements Serializable {
    static int classNumber = 0;
    private int objNumber;

    public MessageObject() {
        objNumber = classNumber;
        System.out.println("MessageObject: Class Number is #" + classNumber +
                           " Object Number is #" + objNumber);
        classNumber = classNumber + 1;
    }

    public int getNumberFromObject() {
        return objNumber;
    }

    public int getNumberFromClass() {
        return classNumber;
    }
}
