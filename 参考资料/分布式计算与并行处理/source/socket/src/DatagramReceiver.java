import java.net.*;
import java.io.IOException;

public class DatagramReceiver {
    private static final int PORT = 22222;
    private static final int BUFF_SIZE = 100;

    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            byte[] buff = new byte[BUFF_SIZE];
            DatagramPacket datagram = new DatagramPacket(buff, buff.length);
            socket = new DatagramSocket(PORT);
            socket.receive(datagram);
            String msg = new String(buff);
            System.out.println("Received:"+msg);
        } catch (SocketException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }
        }

    }
}
