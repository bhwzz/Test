import java.net.*;
import java.io.IOException;

public class DatagramSender {
    private static final String HOST = "localhost";
    private static final int PORT = 22222;
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("useage:java Example1Sender message");
            return;
        }
        DatagramSocket socket = null;
        try {
            InetAddress receiveHost = InetAddress.getByName(HOST);
            String msg = args[0];
            byte[] buffer = msg.getBytes();
            DatagramPacket datagram = new DatagramPacket(buffer, buffer.length,
                    receiveHost, PORT);
            socket = new DatagramSocket();
            socket.send(datagram);
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
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
