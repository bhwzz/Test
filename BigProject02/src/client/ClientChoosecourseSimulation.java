package client;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class ClientChoosecourseSimulation extends Thread{ 
	private String studentId;
	private String courseId;
	private ClientRemote remote;
	ClientChoosecourseSimulation(){
		
	}
	ClientChoosecourseSimulation(String host, int post, String sid, String cid) throws Exception{
		remote = new ClientRemote(host, post); //通过客户端代理建立到服务器的连接
		//建立选课信息
		studentId=sid;
		courseId=cid;
	}
	@Override
	public void run() {
			String info = remote.chooseCourse(studentId, courseId); //info记录选课结果信息
			remote.exitConnection(); //选课结束后关闭连接
			switch(info.charAt(0)){
			case '0':
				String s = info.substring(1); //除去第一位的子串，表示失败原因
				System.out.println("学生"+studentId+"选课"+courseId+"失败，失败原因"+s);
				break;
			case '1':
				System.out.println("学生"+studentId+"选课"+courseId+"成功");
				break;
			}
	}
	public static void main(String[] args) throws Exception {
		//从文件信息中初始化，包含200条选课请求，学号从9999000-9999199，一半选001课程，一半选002课程
		FileInputStream fis = new FileInputStream("ChooseCourse200.txt");
		DataInputStream dis = new DataInputStream(fis);
		ClientChoosecourseSimulation[] client = new ClientChoosecourseSimulation[200];
		String studentId, CourseId;
		for(int i=0; i<200; i++) {
			studentId = dis.readUTF();
			CourseId = dis.readUTF();
			client[i] = new ClientChoosecourseSimulation("localhost", 4444, studentId, CourseId);
		}
		dis.close();
		fis.close();
		//启动200个选课线程
		for(int i=0; i<200; i++) {
			client[i].start();
		}	
		//所有线程结束后向服务器发送更新选课记录文件的指令(-1)
		while(Thread.activeCount() != 1);
			//System.out.println("仍有线程在运行");//测试debug用
		new Client("localhost",4444).exitChoosecourseManage();
	}
}
