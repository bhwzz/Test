package ProjectServer;

import java.io.InputStreamReader;

public interface ReqResultProcess {//定义客户端对于发送往服务器端请求的响应的处理函数的接口
	public void ResultProcess(InputStreamReader isr);
	
}
