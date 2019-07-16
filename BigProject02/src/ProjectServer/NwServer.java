package ProjectServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

//服务器类
public class NwServer {
	public NwServer(int port,IOStrategy ios) throws IOException{
		ServerSocket ss=new ServerSocket(port);
		int socketCounter = 0;
		while(true) {
			Socket s=ss.accept();
			socketCounter++;
			System.out.println("已接收到第 "+ socketCounter +"客户端的请求");
			System.out.println("socket连接成功！");
			ios.Service(s);
		}
	}
}
