import java.io.*;
import java.net.*;
import java.util.*;

public class MulticastClient {
    private static final int MULT_PORT=4446;
    private static final String MULT_ADDRESS="230.0.0.1";
    private static final int BUFF_SIZE=256;
    private static final int RECEIVE_TIMES=1000;
    public static void main(String[] args) {
        MulticastSocket socket = null;
        try {
            socket = new MulticastSocket(MULT_PORT);

            InetAddress address = InetAddress.getByName(MULT_ADDRESS);
            socket.joinGroup(address);

            DatagramPacket packet;
            byte[] buf = new byte[BUFF_SIZE];
            packet = new DatagramPacket(buf, buf.length);
            // get a few quotes
            for (int i = 0; i < RECEIVE_TIMES; i++) {
                socket.receive(packet);
                String received = new String(packet.getData());
                System.out.println("Current time in remote host: " + received);
            }
            socket.leaveGroup(address);
        } catch (IOException ex) {
            ex.printStackTrace();

            /** @todo Handle this exception */
        }finally{
            if (socket != null) {

                socket.close();
            }


        }

    }

}
