package ProjectServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//��������
public class NwServer {
	public NwServer(int port,IOStrategy ios) throws IOException{
		ServerSocket ss=new ServerSocket(port);
		while(true) {
			Socket s=ss.accept();
			System.out.println("socket���ӳɹ���");
			ios.Service(s);
		}
	}
}
