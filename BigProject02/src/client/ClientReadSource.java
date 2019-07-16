package client;

import java.util.Scanner;

abstract public class ClientReadSource {  //定义了从输入中读学号、姓名、班级、性别、课程号、课程名、课余量的方法，增强拓展性（易更改为从其他地方读，比如文件）
	public static String readStudentId() { //读学号
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
	public static int readClassroom() { //读班级
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
		while(!Tool.isChineseCharacters(courseName) || (courseName.length()>=10)){
			System.out.print("输入有误，请输入10位以内汉字！：");
			courseName = sc.next();
		}
		return courseName;
	}
	public static int readCourseCapacity() { //读课程容量
		System.out.print("课程容量：");
		Scanner sc=new Scanner(System.in);
		String courseCapacity = sc.next(); 
		while(!Tool.isNumber(courseCapacity) || courseCapacity.length()>3){
			System.out.println("输入有误，请输入3位以内数字：");
			courseCapacity = sc.next();
		}
		return Integer.parseInt(courseCapacity);
	}
}
