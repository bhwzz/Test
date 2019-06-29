/**
 * <p>Title: </p>
 *
 * <p>Description: </p>
 *
 * <p>Copyright: Copyright (c) 2005</p>
 *
 * <p>Company: KMUST</p>
 *
 * @author Shang Zhenhong
 * @version 1.0
 */
import java.io.*;
import java.net.*;

public class EchoClient {
    private static final String SERVER_NAME = "localhost";
    private static final int SERVER_PORT = 7;
    public static void main(String[] args) throws IOException {

        Socket echoSocket = null;
        PrintWriter out = null;
        BufferedReader in = null;
        BufferedReader stdIn = null;
        try {
            echoSocket = new Socket(SERVER_NAME, SERVER_PORT);
            out = new PrintWriter(echoSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(
                    echoSocket.getInputStream()));
            stdIn = new BufferedReader(new InputStreamReader(System.in));

            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                System.out.println("echo: " + in.readLine());
            }
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + SERVER_NAME);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: " +
                               SERVER_NAME);
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
            if (echoSocket != null) {
                echoSocket.close();
            }
        }
    }
}
