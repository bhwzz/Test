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
		//if���и�ѧ�ŵ�ѧ��������ʾ����return
		if(r.findStudent(id) != null){
			System.out.println("ѧ���ظ������ʧ��");
			return;
		}
		
		System.out.print("������");
		String name = sc.next(); //��������
		System.out.print("�༶��");
		int classroom = sc.nextInt(); //����༶
		System.out.print("�Ա�");
		char gender = sc.next().charAt(0); //�����Ա�
		r.addStudent(id+","+name+","+classroom+","+gender);
		
	}
	public void deleteStudent()
	{
		System.out.print("������Ҫɾ��ѧ����ѧ�ţ�");
		Scanner sc=new Scanner(System.in);
		String id = sc.next(); //����ѧ��
		//ifû�и�ѧ�ŵ�ѧ��������ʾ����return
		if(r.findStudent(id) == null){
			System.out.println("��ѧ��������");
			return;
		}
		else {
			r.deleteStudent(id);
		}
	}
	public void changeStudent()//ȫ��
	{
		System.out.println("������Ҫ�޸ĵ�ѧ����Ϣ��");
		System.out.print("ѧ�ţ�");
		Scanner sc=new Scanner(System.in);
		String id = sc.next(); //����ѧ��
		//ifû�и�ѧ�ŵ�ѧ��������ʾ����return
		if(r.findStudent(id) == null){
			System.out.println("�����ڸ�ѧ��");
			return;
		}
		
		System.out.print("������");
		String name = sc.next(); //��������
		System.out.print("�༶��");
		int classroom = sc.nextInt(); //����༶
		System.out.print("�Ա�");
		char gender = sc.next().charAt(0); //�����Ա�
		r.deleteStudent(id+","+name+","+classroom+","+gender);
	}
	public void findStudent() //����
	{
		
		System.out.print("������Ҫ��ѯѧ����ѧ�ţ�");
		Scanner sc=new Scanner(System.in);
		String id = sc.next(); //����ѧ��
		String s = r.findStudent(id);
		switch(r.charAt(0)){
		case '0':
			System.out.println("��ѧ��������");
			break;
		case '1':
			System.out.println("ѧ����Ϣ���£�");
			String info[] = s.substring(1).split(",");
			System.out.println("ѧ�ţ�"+info[0]+"\n����:"+info[1]+"\n�༶:"+info[2]+"\n�Ա�"+info[3]);
			int courseNum = info[4];
			break;
		}
		else if( s.charAt(0) == 0)
		System.out.println(r.findStudent(id)); //������ҽ��
		return id;
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
