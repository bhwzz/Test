package ProjectServer;

import java.net.Socket;

//����������˷������ӿ�
//��װ�������˵ķ�����
public interface IOStrategy {
	public void ChooseService(Socket socket);//ѡ�η���
	//public void DropService(Socket socket);//�˿η���
}
