package ProjectServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//��������
public class NwServer {
	public NwServer(int port,IOStrategy ios) throws IOException{
		ServerSocket ss=new ServerSocket(port);
		int socketCounter = 0;
		while(true) {
			Socket s=ss.accept();
			socketCounter++;
			System.out.println("�ѽ��յ��� "+ socketCounter +"�ͻ��˵�����");
			System.out.println("socket���ӳɹ���");
			ios.Service(s);
		}
	}
}
