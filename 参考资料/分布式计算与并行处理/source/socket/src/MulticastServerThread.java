import java.io.*;
import java.net.*;
import java.util.*;

public class MulticastServerThread extends QuoteServerThread {
    private long FIVE_SECONDS = 5000;
    private static final int MULT_PORT=4446;
    private static final String MULT_ADDRESS="230.0.0.1";

    public MulticastServerThread() throws IOException {
        super("MulticastServerThread");
    }

    public void run() {
        try {
            for (int i = 0; i < 5000; i++) {
                byte[] buf = new byte[BUF_SIZE];

                // construct quote
                String dString = new Date().toString();
                buf = dString.getBytes();

                // send it
                InetAddress group = InetAddress.getByName(MULT_ADDRESS);
                DatagramPacket packet = new DatagramPacket(buf, buf.length,
                        group, MULT_PORT);
                socket.send(packet);

                // sleep for a while
                try {
                    sleep(FIVE_SECONDS);
                } catch (InterruptedException e) {}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(socket!=null){
                socket.close();
            }
        }
    }
}
