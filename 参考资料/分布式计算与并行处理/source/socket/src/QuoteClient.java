import java.io.*;
import java.net.*;
import java.util.*;

public class QuoteClient {
    private static final String REMOTE_HOST="localhost";
    private static final int REMOTE_POTE=4445;
    private static final int BUF_SIZE = 256;
    public static void main(String[] args) {
        DatagramSocket socket = null;
        try {
            // get a datagram socket
            socket = new DatagramSocket();
            // send request
            byte[] buf = new byte[BUF_SIZE];
            InetAddress address = InetAddress.getByName(REMOTE_HOST);
            DatagramPacket packet = new DatagramPacket(buf, buf.length, address,
                    REMOTE_POTE);
            socket.send(packet);

            // get response
            packet = new DatagramPacket(buf, buf.length);
            socket.receive(packet);

            // display response
            String received = new String(packet.getData());
            System.out.println("Current time in remote host: " + received);

        } catch (SocketException ex) {
            ex.printStackTrace();
        } catch (UnknownHostException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }finally{
            if(socket!=null){
                socket.close();
            }
        }


    }
}
