package ProjectServer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Properties;

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
				//对于服务器端的线程，每个线程开始运行后都要建立与数据库的socket连接
				Properties p=new Properties();
				try {
					p.load(new FileInputStream("file.properties"));
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				String DBserver=p.getProperty("DBserver");
				int DBport=Integer.parseInt(p.getProperty("DBport"));
				Socket DBsocket = null;
				try {
					DBsocket=new Socket("LocalHost",DBport);
					System.out.println(Thread.currentThread().getName()+"connect DB success!");
				} catch (UnknownHostException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getName()+"is offerring services!");
				ios.Service(socket,DBsocket);
				socket=null;
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
		}
	}
}
