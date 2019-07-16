package ProjectServer;

import java.io.IOException;
import java.util.Scanner;

public class Client { //包含一系列响应用户操作和需求的函数，如选课退课查询等

	Remote r;
	Client(String host, int port) throws Exception{
		r = new Remote(host, port);
	}
	
	public static String readStudentId() { //定义了读学号的方法，增强拓展性（易更改为从其他地方读，比如文件）
		System.out.print("请输入学号：");
		Scanner sc=new Scanner(System.in);
		String studentId = sc.next(); 
		while(!Tool.isNumber(studentId) || studentId.length()!=7){
			System.out.println("输入有误，请输入7位数字学号：");
			studentId = sc.next();
		}
		return studentId;
	}
	public static String readStudentName() { //读姓名
		System.out.print("姓名：");
		Scanner sc=new Scanner(System.in);
		String studentName = sc.next(); 
		while(!Tool.isChineseCharacters(studentName) || (studentName.length()!=2 && studentName.length()!=3) ){
			System.out.print("输入有误，请输入2-3位汉字！：");
			studentName = sc.next();
		}
		return studentName;
	}
	public int readClassroom() { //读班级
		System.out.print("班级：");
		Scanner sc=new Scanner(System.in);
		String classroom = sc.next(); 
		while(!Tool.isNumber(classroom) || classroom.length()>2) { //若班级数字位数多于2位，则报错，重新输入
			System.out.println("输入有误，请输入两位以内数字：");
			classroom = sc.next();
		}
		return Integer.parseInt(classroom);
	}
	public static char readGender() { //读性别
		System.out.print("性别：");
		Scanner sc=new Scanner(System.in);
		char gender = sc.next().charAt(0); //读入性别
		while(gender!='男' && gender!='女'){
			System.out.println("无效性别，请输入男/女：");
			gender = sc.next().charAt(0);
		}
		return gender;
	}
	public static String readCourseId() { //读课程号
		System.out.print("请输入课程号：");
		Scanner sc=new Scanner(System.in);
		String courseId = sc.next(); 
		while(!Tool.isNumber(courseId) || courseId.length()!=3){
			System.out.println("输入有误，请输入3位数字课程号：");
			courseId = sc.next();
		}
		return courseId;
	}
	public static String readCourseName() { //读课程名
		System.out.print("课程名：");
		Scanner sc=new Scanner(System.in);
		String courseName = sc.next(); 
		return courseName;
	}
	public static int readCourseCapacity() { //读课程容量
		System.out.print("课程容量：");
		Scanner sc=new Scanner(System.in);
		int courseCapacity = sc.nextInt(); 
		return courseCapacity;
	}
	public void chooseCourse() //按提示进行选课操作
	{
		System.out.println("--------------选课操作--------------");

		String studentId = readStudentId(); //读入学号
		/*
		findAllCourse();
		*/
		String courseId = readCourseId(); //读入课序号
		String s = r.chooseCourse(studentId, courseId);
		switch(s.charAt(0)){
		case '0':
			String info = s.substring(1); //除去第一位的子串，表示失败原因
			System.out.println("选课失败，失败原因："+info);
			break;
		case '1':
			System.out.println("选课成功");
			break;
		}
	}
	public void dropCourse() //按提示进行退课操作
	{
		System.out.println("--------------退课操作--------------");
		/*根据id列出学生信息
		String stuId = findStudent();*/
		
		String studentId = readStudentId(); //读入学号
		String courseId = readCourseId(); //读入课序号
		String s = r.dropCourse(studentId, courseId);
		switch(s.charAt(0)){
		case '0':
			String info = s.substring(1); //除去第一位的子串，表示失败原因
			System.out.println("退课失败，失败原因："+info);
			break;
		case '1':
			System.out.println("退课成功");
			break;
		}
	}
	public void addStudent()
	{
		System.out.println("请输入要添加学生的信息：");
		String id = readStudentId(); //读入学号
		String name = readStudentName(); //读入姓名
		int classroom = readClassroom(); //读入班级
		char gender = readGender(); //读入性别
		String student = new String(id+","+name+","+classroom+","+gender);//向服务器传递的待增加的学生信息
		if(r.addStudent(student)){
			System.out.println("添加成功");	
		}
		else {
			System.out.println("学号重复，添加失败");
		}

		ClientUI.delay(2000);
		return;
	}
	public void deleteStudent()
	{
		System.out.println("请输入要删除学生的信息：");
		String id = readStudentId(); //读入学号
		String s = r.deleteStudent(id);
		switch(s.charAt(0)){
		case '0':
			String info = s.substring(1); //除去第一位的子串，表示错误信息
			System.out.println("删除失败，失败原因："+info);
			break;
		case '1':
			System.out.println("删除成功");
			break;
		}
		
		ClientUI.delay(2000);
		return;
	}
	public void changeStudent()
	{
		System.out.println("请输入要修改的学生信息：");
		String id = readStudentId(); //读入学号
		String name = readStudentName(); //读入姓名
		int classroom = readClassroom(); //读入班级
		char gender = readGender(); //读入性别
		String student = new String(id+","+name+","+classroom+","+gender);//向服务器传递的待修改的学生信息
		//System.out.println(student);
		if(r.changeStudent(student)) {
			System.out.println("修改成功");
		}
		else {
			System.out.println("不存在该学号，修改失败");
		}

		ClientUI.delay(2000);
		return;
	}
	public void findStudent() 
	{
		
		System.out.print("请输入要查询学生的信息：");
		String id = readStudentId(); //读入学号
		String s[] = r.findStudent(id);
		switch(s[0].charAt(0)){
		case '0':
			System.out.println("该学生不存在");
			break;
		case '1':
			int selectedCourseNum = s.length - 1;//该学生已选课程数目
			System.out.println("学生信息如下：");
			String info[] = s[0].substring(1).split(",");
			System.out.println("学号："+info[0]+"\n姓名:"+info[1]+"\n班级:"+info[2]+"\n性别:"+info[3]+"\n已选课程数目:"+selectedCourseNum);
			//int courseNum = Integer.parseInt(info[4]);
			if(selectedCourseNum>0) {
				System.out.println("已选课程信息如下：");
				String[] course = new String[5];//每条课程信息五个记录: (String) course_id,course_name, (int) num,left_num,stu_num;
				for(int i=0; i<selectedCourseNum; i++) {
					course = s[i+1].split(",");//读取课程信息
					System.out.println("-------课程"+(i+1)+"-------");
					System.out.println("课程号："+course[0]+"\n课程名:"+course[1]+"\n课容量:"+course[2]+"\n课余量:"+course[3]+"\n已选人数:"+course[4]+"\n");
				}
			}
			break;
		}
		//按任意键继续...
		ClientUI.delay(10000);
		return;
	
	}
	public void addCourse()
	//课序号，课程名，课容量
	{
		System.out.println("请输入要添加课程的信息：");
		String id = readCourseId(); //读入课程号
		String name = readCourseName();//
		int num = readCourseCapacity();
		if(r.addCourse(id,name,num)){
			System.out.println("添加成功");	
		}
		else {//若添加失败，则说明已存在该课程号，提示错误
			System.out.println("课程号重复，添加失败");
		}

		ClientUI.delay(2000);
		return;
	}
	public void deleteCourse()//根据输入的课程号删除课程
	{
		System.out.println("请输入要删除课程的课程信息：");
		String id = readCourseId(); //读入课程号
		String s = r.deleteCourse(id);
		switch(s.charAt(0)){
		case '0':
			String info = s.substring(1); //除去第一位的子串，表示错误信息
			System.out.println("删除失败，失败原因："+info);
			break;
		case '1':
			System.out.println("删除成功");
			break;
		}
		
	}
	public void changeCourse()//全串
	{
		System.out.println("请输入要修改的课程信息：");
		System.out.print("课程号：");
		Scanner sc=new Scanner(System.in);
		String id = sc.next(); //读入课程号
		while(id.length()!=3){
			System.out.println("长度有误，请输入3位数字课程号：");
			id = sc.next();
		}
		System.out.print("课程名：");
		String name = sc.next(); //读入课程名
		if(r.changeCourse(id, name)) {
			System.out.println("修改成功");
		}
		else {
			System.out.println("不存在该课程号，修改失败");
		}

		ClientUI.delay(2000);
		return;
	}
	public void findAllCourse()//所有课程信息
	{
		String allCourseStr[] = r.findAllCourse();
		String course[] = new String[5]; //每条课程信息五个记录: (String) course_id,course_name, (int) num,left_num,stu_num;
		int courseNum = allCourseStr.length;
		System.out.println("共有"+courseNum+"门课程，课程信息如下");
		for(int i=0; i<courseNum; i++) {
			course = allCourseStr[i].split(",");
			System.out.println("-------课程"+(i+1)+"-------");
			System.out.println("课程号："+course[0]+"\n课程名:"+course[1]+"\n课容量:"+course[2]+"\n课余量:"+course[3]+"\n已选人数:"+course[4]+"\n");
		}
		
		//按任意键继续...
		ClientUI.delay(10000);
		return;
	}
	public void addCourseCapacity()
	{
		System.out.println("请输入要增加课容量的课程信息：");
		System.out.print("课程号：");
		Scanner sc=new Scanner(System.in);
		String id = sc.next(); //读入课程号
		while(id.length()!=3){
			System.out.println("长度有误，请输入3位数字课程号：");
			id = sc.next();
		}
		System.out.print("增加容量：");
		int addNum = sc.nextInt(); 
		if(r.addCourseCapacity(id, addNum)) {
			System.out.println("增加课容量成功");
		}
		else {
			System.out.println("不存在该课程号，增加失败");
		}
	}
	public void exitConnection() {//告诉服务器关闭连接
		r.exitConnection();
	}
	public void exitChoosecourseManage() {//告诉服务器退出选课管理（服务器将刷新选课记录文件）
		r.exitChoosecourseManage();
	}
	public void exitStudentManage() {//退出学生管理
		r.exitStudentManage();
	}
	public void exitCourseManage() {//退出课程管理
		r.exitCourseManage();
	}
	

	
}
