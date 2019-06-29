import java.net.*;
import java.io.*;

public class KKMultiServer {
    private static final int SERVER_PORT = 4444;
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        boolean listening = true;

        try {
            serverSocket = new ServerSocket(SERVER_PORT);
            while (listening) {
                new KKMultiServerThread(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            System.err.println("Could not listen on port: " + SERVER_PORT);
            System.exit( -1);
        } finally {
            if (serverSocket != null) {
                serverSocket.close();
            }
        }
    }
}
