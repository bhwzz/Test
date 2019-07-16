package ProjectServer;

import java.io.IOException;

public class Client100 extends Thread{  //未完成
	private String studentId;
	private String courseId;
	private Remote remote;
	Client100(){
		
	}
	Client100(String host, int post, String sid, String cid) throws Exception{
		remote = new Remote(host, post); //通过客户端代理建立到服务器的连接
		//建立选课信息
		studentId=sid;
		courseId=cid;
	}
	@Override
	public void run() {
			String info = remote.chooseCourse(studentId, courseId); //info记录选课结果信息
			remote.exitConnection(); //选课结束后关闭连接
			System.out.println("学生"+studentId+"选课"+courseId+"结果：");
			switch(info.charAt(0)){
			case '0':
				String s = info.substring(1); //除去第一位的子串，表示失败原因
				System.out.println("选课失败，失败原因"+s);
				break;
			case '1':
				System.out.println("选课成功");
				break;
			}
	}
	public static void main(String[] args) throws Exception {
		Client100[] client = new Client100[100];
		for(int i=0; i<100; i++) {
			client[i] = new Client100("localhost", 4444, i+1234500+"", "001");
		}
		for(int i=0; i<100; i++) {
			client[i].start();
		}
	}
}
