package ProjectServer;

import java.net.Socket;

public class DBThread extends Thread{
	private Socket socket=null;
	private IOStrategy ios=null;
	
	public DBThread(IOStrategy ios) {
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
				System.out.println("Êý¾Ý¿â¶Ë"+Thread.currentThread().getName()+"is offerring services!");
				ios.Service(socket);
				socket=null;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
	}
}
