package Basic;

import java.awt.*;
import java.applet.*;

public class MyApplet extends Applet {
    public void paint(Graphics g){
        this.setBackground(Color.blue);
        Font font=new Font("Arial",Font.BOLD,40);
        g.setFont(font);
        g.setColor(Color.YELLOW);
        g.drawString("Hellow World!", 100, 100);
    }
}
