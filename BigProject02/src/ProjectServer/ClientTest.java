package ProjectServer;

public class ClientTest {
	public static void main(String[] args) {
		Thread t1=new Client100("0000001","001");
		Thread t2=new Client100("0000002","001");
		Thread t3=new Client100("0000003","002");
		Thread t4=new Client100("0000004","002");
		Thread t5=new Client100("0000005","003");
		Thread t6=new Client100("0000006","003");
		Thread t7=new Client100("0000007","001");
		t1.start();	
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		
		
		try {
			Thread.currentThread().sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t7.start();
	}
}
