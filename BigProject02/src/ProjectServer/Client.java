package ProjectServer;

import java.util.Scanner;

public class Client { //����һϵ����Ӧ�û�����������ĺ�������ѡ���˿β�ѯ��

	Remote r;
	Client(String host, int port) throws Exception{
		r = new Remote(host, port);
	}
	public void chooseCourse() //����ʾ����ѡ�β���
	{
		System.out.println("--------------ѡ�β���--------------");
		//����id�г�ѧ����Ϣ�����пγ���Ϣ
		String stuId = findStudent().charAt(0);
		if(stuId == null) { //��ѧ��������
			return;
		}
		findAllCourse();
		System.out.print("��������Ҫѡ��Ŀγ̿���ţ�");
		Scanner sc=new Scanner(System.in);
		String courId = sc.next(); //��������
		System.out.println( r.chooseCourse(stuId, courId) ); //���ѡ�ν��
	}
	public void dropCourse() //����ʾ�����˿β���
	{
		System.out.println("--------------�˿β���--------------");
		//����id�г�ѧ����Ϣ
		String stuId = findStudent();
		System.out.print("��������Ҫ�˿εĿγ̿���ţ�");
		Scanner sc=new Scanner(System.in);
		String courId = sc.nextLine(); //��������
		System.out.println( r.chooseCourse(stuId, courId) ); //����˿ν��
	}
	public void addStudent()
	{
		System.out.println("������Ҫ���ѧ������Ϣ��");
		System.out.print("ѧ�ţ�");
		Scanner sc=new Scanner(System.in);
		String id = sc.next(); //����ѧ��
		while(id.length()!=7){
			System.out.println("��������������7λ����ѧ�ţ�");
			id = sc.next();
		}
		System.out.print("������");
		String name = sc.next(); //��������
		System.out.print("�༶��");
		int classroom = sc.nextInt(); //����༶
		System.out.print("�Ա�");
		char gender = sc.next().charAt(0); //�����Ա�
		String student = new String(id+","+name+","+classroom+","+gender);//����������ݵĴ����ӵ�ѧ����Ϣ
		//�����ʧ�ܣ���˵���Ѵ��ڸ�ѧ�ţ���ʾ����
		if(r.addStudent(student)){
			System.out.println("��ӳɹ�");	
		}
		else {
			System.out.println("ѧ���ظ������ʧ��");
		}
		
		ClientUI.delay(2000);
		return;
	}
	public void deleteStudent()
	{
		System.out.print("������Ҫɾ��ѧ����ѧ�ţ�");
		Scanner sc=new Scanner(System.in);
		String id = sc.next(); //����ѧ��
		while(id.length()!=7){
			System.out.println("��������������7λ����ѧ�ţ�");
			id = sc.next();
		}
		String s = r.findStudent(id);
		switch(s.charAt(0)){
		case '0':
			String info = s.substring(1); //��ȥ��һλ���Ӵ�����ʾ������Ϣ
			System.out.println("ɾ��ʧ�ܣ�ʧ��ԭ��"+info);
			break;
		case '1':
			System.out.println("ɾ���ɹ�");
			break;
		}
		
		ClientUI.delay(2000);
		return;
	}
	public void changeStudent()//ȫ��
	{
		System.out.println("������Ҫ�޸ĵ�ѧ����Ϣ��");
		System.out.print("ѧ�ţ�");
		Scanner sc=new Scanner(System.in);
		String id = sc.next(); //����ѧ��
		while(id.length()!=7){
			System.out.println("��������������7λ����ѧ�ţ�");
			id = sc.next();
		}
		System.out.print("������");
		String name = sc.next(); //��������
		System.out.print("�༶��");
		int classroom = sc.nextInt(); //����༶
		System.out.print("�Ա�");
		char gender = sc.next().charAt(0); //�����Ա�
		String student = new String(id+","+name+","+classroom+","+gender);//����������ݵĴ��޸ĵ�ѧ����Ϣ
		if(r.changeStudent(student)) {
			System.out.println("ɾ���ɹ�");
		}
		else {
			System.out.println("�����ڸ�ѧ�ţ�ɾ��ʧ��");
		}
		
		ClientUI.delay(2000);
		return;
	}
	public void findStudent() //����
	{
		
		System.out.print("������Ҫ��ѯѧ����ѧ�ţ�");
		Scanner sc=new Scanner(System.in);
		String id = sc.next(); //����ѧ��
		while(id.length()!=7){
			System.out.println("��������������7λ����ѧ�ţ�");
			id = sc.next();
		}
		String s = r.findStudent(id);
		switch(s.charAt(0)){
		case '0':
			System.out.println("��ѧ��������");
			break;
		case '1':
			System.out.println("ѧ����Ϣ���£�");
			String info[] = s.substring(1).split(",");
			System.out.println("ѧ�ţ�"+info[0]+"\n����:"+info[1]+"\n�༶:"+info[2]+"\n�Ա�"+info[3]);
			//int courseNum = Integer.parseInt(info[4]);
			break;
		}
		
		ClientUI.delay(2000);
		return;
	
	}
	public void addCourse(String id, String name, int classroom, char gender)//ȫ��
	{

	}
	public void deleteCourse()//id
	{
		
	}
	public void changeCourse()//ȫ��
	{
		
	}
	public void findAllCourse()//���пγ���Ϣ
	{
		
	}
}
