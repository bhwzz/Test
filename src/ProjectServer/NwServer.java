package ProjectServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//��������
public class NwServer {
	NwServer(int port,IOStrategy ios) throws IOException{
		ServerSocket ss=new ServerSocket(port);
		while(true) {
			Socket s=ss.accept();
			ios.ChooseService(s);
		}
	}
}
