package ProjectServer;

import java.net.Socket;

//定义服务器端服务函数接口
//封装服务器端的服务函数
public interface IOStrategy {
	public void ChooseService(Socket socket);//选课服务
	//public void DropService(Socket socket);//退课服务
}
