package ProjectServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//服务器类
public class NwServer {
	NwServer(int port,IOStrategy ios) throws IOException{
		ServerSocket ss=new ServerSocket(port);
		while(true) {
			Socket s=ss.accept();
			System.out.println("连接客户端成功！");
			ios.Service(s);
		}
	}
}
