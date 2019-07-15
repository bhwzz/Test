package ProjectServer;

import java.io.*;
import java.net.Socket;


public class Remote { //本地（客户端）代理，与服务器通信方式
	String host;
	int port;
	private DataInputStream dis = null;
	private DataOutputStream dos = null;
	BufferedReader br=null;
	PrintWriter pw=null;

	Remote(String host, int port) throws Exception{
		this.host = host;
		this.port = port;
		Socket socket = new Socket(host, port);
		dis = new DataInputStream(socket.getInputStream());
		dos = new DataOutputStream(socket.getOutputStream());
		//br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		//pw=new PrintWriter(new OutputStreamWriter(socket.getOutputStream()));
	}
	public String chooseCourse(String studentId, String CourseId) //返回 （1成功）/（0失败+失败原因）
	{
		try {
			dos.writeInt(1);//1表示选课
			dos.writeUTF(studentId); 
			dos.writeUTF(CourseId); 
			dos.flush();
			return dis.readInt()==1 ? "1" : "0"+dis.readUTF();
		}catch (Exception e) {
			throw new ArithmeticException(e.getMessage());
		}
	}
	public String dropCourse(String studentId, String CourseId) //返回 （1成功）/（0失败+失败原因）
	{
		try {
			dos.writeInt(2);//2表示退课
			dos.writeUTF(studentId);
			dos.writeUTF(CourseId); 
			dos.flush();
			return dis.readInt()==1 ? "1" : "0"+dis.readUTF();
		}catch (Exception e) {
			throw new ArithmeticException(e.getMessage());
		}
	}
	public boolean addStudent(String student)//id,name,classroom,gendar的复合串，中间逗号分隔
	{
		try {
			dos.writeInt(3);//3表示增加学生信息
			dos.writeUTF(student); //以逗号作为分隔符向服务器传数据
			dos.flush();
			return dis.readInt()== 1;
		}catch (Exception e) {
			throw new ArithmeticException(e.getMessage());
		}
	}
	public String deleteStudent(String id)//根据学号删除，返回 （1成功）/（0失败+错误信息）
	{
		try {
			dos.writeInt(4);//4表示删除学生信息
			dos.writeUTF(id);
			dos.flush();
			return dis.readInt()==1 ? "1" : "0"+dis.readUTF();
		}catch (Exception e) {
			throw new ArithmeticException(e.getMessage());
		}
	}
	public boolean changeStudent(String student)//参数同addStudent，修改对应学号id的学生的信息
	{
		try {
			dos.writeInt(5);//5表示修改学生信息
			dos.writeUTF(student); //传递要修改的学生信息字符串
			dos.flush();
			return dis.readInt()== 1;
		}catch (Exception e) {
			throw new ArithmeticException(e.getMessage());
		}

	}
	//查找相应学号的学生信息，返回字符串数组，其中String[0]为是否存在（1存在+学生信息）/（0不存在），String[1]-String[num]为学生已选num门课程的课程信息，以逗号作为课程信息内部分隔符
	public String[] findStudent(String id)
	{
		try {
			dos.writeInt(6);//6表示查找学生信息
			dos.writeUTF(id);
			dos.flush();
			String str = dis.readUTF();
			String[] s = str.split(".");
			if(s.length == 0) { //表示没有分隔，无已选课程，将str装入字符串数组
				String s2[] = new String[1];
				s2[0] = str;
				return s2;
			}			
			else
				return s;
		}catch (Exception e) {
			throw new ArithmeticException(e.getMessage());
		}
	}
	public boolean addCourse(String id, String name, int num) //传递课程号，课程名，课容量。返回(1成功)/(0失败,原因是课序号冲突)
	//课程属性(String) course_id,course_name, (int) num,left_num,stu_num;
	{
		try {
			dos.writeInt(7);//7表示增加课程信息
			dos.writeUTF(id+","+name+","+num); //以逗号作为分隔符向服务器传递要增加的课程号，课程名，课容量
			dos.flush();
			return dis.readInt()== 1;
		}catch (Exception e) {
			throw new ArithmeticException(e.getMessage());
		}
	}
	public String deleteCourse(String id)//返回(1成功)/(0失败+失败原因)①不存在该课程②该课程有人选，无法删除
	{
		try {
			dos.writeInt(8);//8表示删除课程信息
			dos.writeUTF(id);
			dos.flush();
			return dis.readInt()==1 ? "1" : "0"+dis.readUTF();
		}catch (Exception e) {
			throw new ArithmeticException(e.getMessage());
		}
	}
	public boolean changeCourse(String id, String name)	//只能修改课程名字。返回(1成功)/(0失败,原因是不存在该课程号课程)
	{
		try {
			dos.writeInt(9);//9表示修改课程信息
			dos.writeUTF(id+","+name); //以逗号作为分隔符向服务器传递要修改的课程号，课程名
			dos.flush();
			return dis.readInt()== 1;
		}catch (Exception e) {
			throw new ArithmeticException(e.getMessage());
		}
	}
	public String[] findAllCourse()//查找所有课程信息，返回对应数目的课程串(每串课程信息内部属性依然以逗号分隔)
	{
		try {
			dos.writeInt(10);//10表示查找所有课程信息
			dos.flush();
			int num = dis.readInt();
			String[] s = new String[num];
			for(int i=0; i<num; i++) {
				s[i] = dis.readUTF();
			}
			return s;
		}catch (Exception e) {
			throw new ArithmeticException(e.getMessage());
		}
	}
	public boolean addCourseCapacity(String id, int addNum) //增加课程容量，返回(1增加成功)/(0增加失败，原因是不存在该课程)
	{
		try {
			dos.writeInt(11);//11表示增加课程容量
			dos.writeUTF(id);
			dos.writeInt(addNum);
			dos.flush();
			return dis.readInt()== 1;
		}catch (Exception e) {
			throw new ArithmeticException(e.getMessage());
		}
	}
	public void exitChoosecourseManage() {
		try {
			dos.writeInt(-1); //-1表示告诉服务器退出选课管理
		} catch (IOException e) {
			throw new ArithmeticException(e.getMessage());
		}
	}
	public void exitStudentManage() {
		try {
			dos.writeInt(-2); //-2表示告诉服务器退出学生管理
		} catch (IOException e) {
			throw new ArithmeticException(e.getMessage());
		}
	}
	public void exitCourseManage() {
		try {
			dos.writeInt(-3); //-2表示告诉服务器退出课程管理
		} catch (IOException e) {
			throw new ArithmeticException(e.getMessage());
		}
	}
}
