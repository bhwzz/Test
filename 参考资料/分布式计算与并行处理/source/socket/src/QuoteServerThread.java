import java.io.*;
import java.net.*;
import java.util.*;

public class QuoteServerThread extends Thread {
    protected DatagramSocket socket = null;
    protected static final int BUF_SIZE = 256;
    private static final int SERVER_PORT = 4445;
    private static final int TIMES_TO_SEND = 5;

    public QuoteServerThread() throws SocketException {
        this("QuoteServerThread");
    }

    public QuoteServerThread(String name) throws SocketException {
        super(name);
        socket = new DatagramSocket(SERVER_PORT);
    }

    public void run() {
        try {
            for (int i = 0; i < TIMES_TO_SEND; i++) {
                byte[] buf = new byte[BUF_SIZE];
                // receive request
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                // figure out response
                String dString = new Date().toString();
                buf = dString.getBytes();

                // send the response to the client at "address" and "port"
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buf, buf.length, address, port);
                socket.send(packet);
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }
}
