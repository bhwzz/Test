package ProjectServer;

import java.net.Socket;

//����������˷������ӿ�
//��װ�������˵ķ�����
public interface IOStrategy {
	public void Service(Socket socket);
}
