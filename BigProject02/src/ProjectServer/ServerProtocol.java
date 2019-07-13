package ProjectServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import EntityClass.Student;

public class ServerProtocol implements IOStrategy{
	ChooseCourseImple cci=null;
	StuInformationImple sii=null;
	CouInformationImple cii=null;
	
	ServerProtocol(Socket DBsocket){
		cci=new ChooseCourseImple(DBsocket);
		sii=new StuInformationImple(DBsocket);
		cii=new CouInformationImple(DBsocket);
	}
	public ServerProtocol() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void Service(Socket Clientsocket) {
		String sid=null;
		String cid=null; 
		String s=null;
		int bool;
		try {//用户可能有很多请求
			BufferedReader br=new BufferedReader(new InputStreamReader(Clientsocket.getInputStream()));
			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(Clientsocket.getOutputStream()));
			//PrintWriter bw=new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
			int i=br.read();
			while(true) {
				switch(i) {
				case '1'://选课
					sid=br.readLine();
					cid=br.readLine();
					bool=cci.chooseCourse(sid,cid);
					bw.write(bool);
					bw.flush();
					break;
				case '2'://退选
					sid=br.readLine();
					cid=br.readLine();
					bool=cci.dropCourse(sid,cid);
					bw.write(bool);
					bw.flush();
				case '3'://增加学生信息，传给我一个整的字符串
					s=br.readLine();//传给我一个整的字符串
					Student stu=Student.toStudent(s);
					bool=sii.Add(stu)?1:0;
					bw.write(bool);//返回1，增加成功；返回0该学生已存在
					bw.flush();
				case '4'://删除学生信息，传给我一个学生id
					sid=br.readLine();
					bool=sii.Delete(sid)?1:0;//删除学生的函数要判断该学生有没有选课
					bw.write(bool);//返回1：删除成功；返回0：该学生已经选课不能删除
					bw.flush();
				case '5'://修改学生信息，传给我一整个学生信息字符串
					s=br.readLine();
					Student stu2=Student.toStudent(s);
					bool=sii.Change(stu2)?1:0;
					bw.write(bool);//返回1：修改成功；返回0：修改失败
					bw.flush();
				case '6'://查找学生信息，传给我学生id
					sid=br.readLine();
					//返回学生个人信息以及选课信息
					Student stu3=(Student)sii.Find(sid);
					if(stu3==null) {//要查找的学生不存在
						bw.write("not found!");//向输出流中写入not found！
						bw.flush();
					}
					else {
						s=stu3.toString();//学生信息
						bw.write(s);//返回学生个人信息
						int num=sii.FindCourseNum(sid);//学生已选课程数目
						bw.write(num);
						if(num!=0) {//已选课程数数目不为0
							s=sii.FindCourse(sid);//返回所有的已选课程（一个字符串拼接的）
							bw.write(s);
						}
						bw.flush();
					}
				case '7'://增加课程
				case '8'://删除课程，要求该课程的选课人数为0
				case '9'://修改课程信息
				case 10://查找课程信息
					
				default:
					System.out.println("无法处理客户端请求！");
					//返回-1，说明请求错误！
					bw.write(-1);
					bw.flush();
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("没有连接到客户端");
		}
	}

}
