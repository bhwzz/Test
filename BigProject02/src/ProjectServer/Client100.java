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
				System.out.println(Thread.currentThread().getName()+"选课："+sid+"-->"+cid+"成功！");
			}
			else if(bool==0){
				System.out.println(Thread.currentThread().getName()+"选课："+sid+"-->"+cid+"失败！没有课余量了！");
			}
			else
				System.out.println("课程编号有误");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("没有找到服务器！");
		}
	}
}
