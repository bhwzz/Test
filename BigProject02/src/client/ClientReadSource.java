package client;

import java.util.Scanner;

abstract public class ClientReadSource {  //�����˴������ж�ѧ�š��������༶���Ա𡢿γ̺š��γ������������ķ�������ǿ��չ�ԣ��׸���Ϊ�������ط����������ļ���
	public static String readStudentId() { //��ѧ��
		System.out.print("������ѧ�ţ�");
		Scanner sc=new Scanner(System.in);
		String studentId = sc.next(); 
		while(!Tool.isNumber(studentId) || studentId.length()!=7){
			System.out.println("��������������7λ����ѧ�ţ�");
			studentId = sc.next();
		}
		return studentId;
	}
	public static String readStudentName() { //������
		System.out.print("������");
		Scanner sc=new Scanner(System.in);
		String studentName = sc.next(); 
		while(!Tool.isChineseCharacters(studentName) || (studentName.length()!=2 && studentName.length()!=3) ){
			System.out.print("��������������2-3λ���֣���");
			studentName = sc.next();
		}
		return studentName;
	}
	public static int readClassroom() { //���༶
		System.out.print("�༶��");
		Scanner sc=new Scanner(System.in);
		String classroom = sc.next(); 
		while(!Tool.isNumber(classroom) || classroom.length()>2) { //���༶����λ������2λ���򱨴���������
			System.out.println("����������������λ�������֣�");
			classroom = sc.next();
		}
		return Integer.parseInt(classroom);
	}
	public static char readGender() { //���Ա�
		System.out.print("�Ա�");
		Scanner sc=new Scanner(System.in);
		char gender = sc.next().charAt(0); //�����Ա�
		while(gender!='��' && gender!='Ů'){
			System.out.println("��Ч�Ա���������/Ů��");
			gender = sc.next().charAt(0);
		}
		return gender;
	}
	public static String readCourseId() { //���γ̺�
		System.out.print("������γ̺ţ�");
		Scanner sc=new Scanner(System.in);
		String courseId = sc.next(); 
		while(!Tool.isNumber(courseId) || courseId.length()!=3){
			System.out.println("��������������3λ���ֿγ̺ţ�");
			courseId = sc.next();
		}
		return courseId;
	}
	public static String readCourseName() { //���γ���
		System.out.print("�γ�����");
		Scanner sc=new Scanner(System.in);
		String courseName = sc.next(); 
		while(!Tool.isChineseCharacters(courseName) || (courseName.length()>=10)){
			System.out.print("��������������10λ���ں��֣���");
			courseName = sc.next();
		}
		return courseName;
	}
	public static int readCourseCapacity() { //���γ�����
		System.out.print("�γ�������");
		Scanner sc=new Scanner(System.in);
		String courseCapacity = sc.next(); 
		while(!Tool.isNumber(courseCapacity) || courseCapacity.length()>3){
			System.out.println("��������������3λ�������֣�");
			courseCapacity = sc.next();
		}
		return Integer.parseInt(courseCapacity);
	}
}
