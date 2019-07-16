package ProjectServer;

import java.net.Socket;
import java.util.ArrayList;

//�߳̿���
public class ThreadPoolSupport implements IOStrategy{
	ArrayList<IOThread> threads=new ArrayList<IOThread>();
	int INIT_THREADS=1000;
	int MAX_THREADS=1050;
	IOStrategy ios=null;
	
	public ThreadPoolSupport(IOStrategy ios){//��ʼ���̳߳�
		this.ios=ios;
		//��ʼ��ѡ��Э��
		//((ChooseProtocol)this.ios).Init();
		
		for(int i=0;i<INIT_THREADS;i++) {
			IOThread t=new IOThread(ios);
			t.start();
			threads.add(t);
		}	
		try {
			Thread.sleep(300);
		} catch (Exception e) {
		} // �ȴ��̳߳ص��̶߳������С�
	}
	
	public void  Service(Socket socket) {
		boolean flag=false;
		int i;
		IOThread t=null;
		for(i=0;i<threads.size();i++) {//�����̳߳أ���һ�������߳�
			if(threads.get(i).isIdle()) {
				flag=true;
				t=threads.get(i);
				break;
			}
		}
		if(!flag) {//û�п����߳�
			t=new IOThread(ios);
			t.start();
			try {
				Thread.sleep(300);
			} catch (Exception e) {
			} // �ȴ��̳߳ص��̶߳������С�
			threads.add(t);
		}
		t.setSocket(socket);
	}

	@Override
	public void Service(Socket socket1, Socket socket2) {
		// TODO Auto-generated method stub
		
	}

}
