package ProjectServer;

import java.util.Scanner;

public class Client {
	public static void main(String[] args) {		
		mainInterface();		
		}
			
	//主界面
	public static void mainInterface(){
		
		while(true) {
			System.out.println("----------------------------");
			System.out.println("    1.选课管理");
			System.out.println("    2.学生信息管理");
			System.out.println("    3.课程信息管理");
			System.out.println("    4.退出");
			System.out.print("请输入你要进行的操作编号：  ");
			Scanner sc=new Scanner(System.in);
			int i=sc.nextInt();
			switch(i) {
			case 1:
				chooseCourseInterface();
				break;
			case 2:
				studentInterface();
				break;
			case 3:
				courseInterface();
				break;
			case 4:
				System.out.println("谢谢您的使用！");
				System.exit(0);
			default:
				System.out.println("请输入正确编号：");
				break;
				
			}
		}		
	}
	//选课管理界面
	public static void chooseCourseInterface(){
		System.out.println("----------选课管理--------------");
		System.out.println("    1.选课");
		System.out.println("    2.退课");
		System.out.println("    3.返回");
		System.out.print("请输入你要进行的操作编号：  ");
		while(true) {
			Scanner sc=new Scanner(System.in);
			int i=sc.nextInt();
			switch(i) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				return;
			default:
				System.out.println("请输入正确编号：");
				break;
			}
		}
	}
	public static void studentInterface(){
		System.out.println("----------学生信息管理--------------");
		System.out.println("    1.增加学生信息");
		System.out.println("    2.删除学生信息");
		System.out.println("    3.修改学生信息");
		System.out.println("    4.删除学生信息");
		System.out.println("    5.返回");
		System.out.print("请输入你要进行的操作编号：  ");
		while(true) {
			Scanner sc=new Scanner(System.in);
			int i=sc.nextInt();
			switch(i) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				return;
			default:
				System.out.println("请输入正确编号：");
				break;
			}
		}
	}
	public static void courseInterface(){
		System.out.println("--------------课程信息管理--------------");
		System.out.println("    1.增加课程信息");
		System.out.println("    2.删除课程信息");
		System.out.println("    3.修改课程信息");
		System.out.println("    4.查询课程信息");
		System.out.println("    5.返回");
		System.out.print("请输入你要进行的操作编号：  ");
		while(true) {
			Scanner sc=new Scanner(System.in);
			int i=sc.nextInt();
			switch(i) {
			case 1:
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				return;
			default:
				System.out.println("请输入正确编号：");
				break;
			}
		}
	}

}
