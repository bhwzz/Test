package ProjectServer;

import java.io.IOException;

public class Client100 extends Thread{
	private String sid;
	private String cid;
	Client100(String sid,String cid){
		this.sid=sid;
		this.cid=cid;
	}
	public void run() {
		try {
			ChooseCourseRemote ccr=new ChooseCourseRemote("wanglilili",4444);
			int bool=ccr.chooseCourse(sid,cid);
			if(bool==1) {
				System.out.println(Thread.currentThread().getName()+"ѡ�Σ�"+sid+"-->"+cid+"�ɹ���");
			}
			else if(bool==0){
				System.out.println(Thread.currentThread().getName()+"ѡ�Σ�"+sid+"-->"+cid+"ʧ�ܣ�û�п������ˣ�");
			}
			else
				System.out.println("�γ̱������");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("û���ҵ���������");
		}
	}
}
