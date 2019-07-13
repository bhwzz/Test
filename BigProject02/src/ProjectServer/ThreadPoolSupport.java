package ProjectServer;

import java.net.Socket;
import java.util.ArrayList;

//线程库类
public class ThreadPoolSupport implements IOStrategy{
	ArrayList<IOThread> threads=new ArrayList<IOThread>();
	int INIT_THREADS=100;
	int MAX_THREADS=150;
	IOStrategy ios=null;
	
	ThreadPoolSupport(IOStrategy ios){//初始化线程池
		this.ios=ios;
		//初始化选课协议
		//((ChooseProtocol)this.ios).Init();
		
		for(int i=0;i<INIT_THREADS;i++) {
			IOThread t=new IOThread(ios);
			t.start();
			threads.add(t);
		}	
		try {
			Thread.sleep(300);
		} catch (Exception e) {
		} // 等待线程池的线程都“运行”
	}
	
	public void  Service(Socket socket) {
		boolean flag=false;
		int i;
		IOThread t=null;
		for(i=0;i<threads.size();i++) {//遍历线程池，找一个空闲线程
			if(threads.get(i).isIdle()) {
				flag=true;
				t=threads.get(i);
				break;
			}
		}
		if(!flag) {//没有空闲线程
			t=new IOThread(ios);
			t.start();
			try {
				Thread.sleep(300);
			} catch (Exception e) {
			} // 等待线程池的线程都“运行”
			threads.add(t);
		}
		t.setSocket(socket);
	}

}
