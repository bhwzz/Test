import java.io.*;
import java.net.*;

public class KnockKnockClient {
    private static final String SERVER_NAME = "localhost";
    private static final int SERVER_PORT = 4444;
    public static void main(String[] args) throws IOException {

        Socket kkSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        BufferedReader stdIn = null;

        try {
            kkSocket = new Socket(SERVER_NAME, SERVER_PORT);
            out = new PrintWriter(kkSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(kkSocket.
                    getInputStream()));

            stdIn = new BufferedReader(new InputStreamReader(System.in));
            String fromServer;
            String fromUser;

            while ((fromServer = in.readLine()) != null) {
                System.out.println("Server: " + fromServer);
                if (fromServer.equals("Bye.")) {
                    break;
                }

                fromUser = stdIn.readLine();
                if (fromUser != null) {
                    System.out.println("Client: " + fromUser);
                    out.println(fromUser);
                }
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about remote host.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println(
                    "Couldn't get I/O for the connection to remote host.");
            System.exit(1);
        } finally {
            if (out != null) {
                out.close();
            }
            if (in != null) {
                in.close();
            }
            if (stdIn != null) {
                stdIn.close();
            }
            if (kkSocket != null) {
                kkSocket.close();
            }
        }
    }
}
