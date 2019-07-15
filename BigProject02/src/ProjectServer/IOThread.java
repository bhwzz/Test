package ProjectServer;

import java.net.Socket;

public class IOThread extends Thread{
//	private Socket clientsocket=null;
//	private Socket DBsocket=null;
	private Socket socket=null;
	private IOStrategy ios=null;
	
	public IOThread(IOStrategy ios) {
		this.ios=ios;
	}
	
	public boolean isIdle() {
		return socket==null;
	}
	
	public synchronized void setSocket(Socket s) {
		this.socket=s;
		notify();
	}
	public synchronized void run() {
		while(true) {
			try {
				Thread.currentThread().wait();
				System.out.println(Thread.currentThread().getName()+"is offerring services!");
				ios.Service(socket);
				socket=null;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
	}
}
