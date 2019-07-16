package ProjectServer;

import java.net.Socket;
import java.util.ArrayList;

public class DBThreadPoolSupport implements IOStrategy{
	ArrayList<DBThread> threads=new ArrayList<DBThread>();
	int INIT_THREADS=1000;
	int MAX_THREADS=1000;
	IOStrategy ios=null;
	
	public DBThreadPoolSupport(IOStrategy ios){//初始化线程池
		this.ios=ios;
		
		for(int i=0;i<INIT_THREADS;i++) {
			DBThread t=new DBThread(ios);
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
		DBThread t=null;
		for(i=0;i<threads.size();i++) {//遍历线程池，找一个空闲线程
			if(threads.get(i).isIdle()) {
				flag=true;
				t=threads.get(i);
				break;
			}
		}
		if(!flag) {//没有空闲线程
			t=new DBThread(ios);
			t.start();
			try {
				Thread.sleep(300);
			} catch (Exception e) {
			} // 等待线程池的线程都“运行”
			threads.add(t);
		}
		t.setSocket(socket);
	}

	@Override
	public void Service(Socket socket1, Socket socket2) {
		// TODO Auto-generated method stub
		
	}
}
