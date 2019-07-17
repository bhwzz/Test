package client;

import java.util.Scanner;

public class Client { //����һϵ����Ӧ�û�����������ĺ�������ѡ���˿β�ѯ��

	ClientRemote r;
	Client(String host, int port) throws Exception{
		r = new ClientRemote(host, port);
	}
	
	
	public void chooseCourse() //����ʾ����ѡ�β���
	{
		System.out.println("--------------ѡ�β���--------------");

		String studentId = ClientReadSource.readStudentId(); //����ѧ��
		/*
		findAllCourse();
		*/
		String courseId = ClientReadSource.readCourseId(); //��������
		String s = r.chooseCourse(studentId, courseId);
		switch(s.charAt(0)){
		case '0':
			String info = s.substring(1); //��ȥ��һλ���Ӵ�����ʾʧ��ԭ��
			System.out.println("ѡ��ʧ�ܣ�ʧ��ԭ��"+info);
			break;
		case '1':
			System.out.println("ѡ�γɹ�");
			break;
		}
	}
	public void dropCourse() //����ʾ�����˿β���
	{
		System.out.println("--------------�˿β���--------------");
		/*����id�г�ѧ����Ϣ
		String stuId = findStudent();*/
		
		String studentId = ClientReadSource.readStudentId(); //����ѧ��
		String courseId = ClientReadSource.readCourseId(); //��������
		String s = r.dropCourse(studentId, courseId);
		switch(s.charAt(0)){
		case '0':
			String info = s.substring(1); //��ȥ��һλ���Ӵ�����ʾʧ��ԭ��
			System.out.println("�˿�ʧ�ܣ�ʧ��ԭ��"+info);
			break;
		case '1':
			System.out.println("�˿γɹ�");
			break;
		}
	}
	public void addStudent()
	{
		System.out.println("������Ҫ���ѧ������Ϣ��");
		String id = ClientReadSource.readStudentId(); //����ѧ��
		String name = ClientReadSource.readStudentName(); //��������
		int classroom = ClientReadSource.readClassroom(); //����༶
		char gender = ClientReadSource.readGender(); //�����Ա�
		String student = new String(id+","+name+","+classroom+","+gender);//����������ݵĴ����ӵ�ѧ����Ϣ
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
		System.out.println("������Ҫɾ��ѧ������Ϣ��");
		String id = ClientReadSource.readStudentId(); //����ѧ��
		String s = r.deleteStudent(id);
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
	public void changeStudent()
	{
		System.out.println("������Ҫ�޸ĵ�ѧ����Ϣ��");
		String id = ClientReadSource.readStudentId(); //����ѧ��
		String name = ClientReadSource.readStudentName(); //��������
		int classroom = ClientReadSource.readClassroom(); //����༶
		char gender = ClientReadSource.readGender(); //�����Ա�
		String student = new String(id+","+name+","+classroom+","+gender);//����������ݵĴ��޸ĵ�ѧ����Ϣ
		//System.out.println(student);
		if(r.changeStudent(student)) {
			System.out.println("�޸ĳɹ�");
		}
		else {
			System.out.println("�����ڸ�ѧ�ţ��޸�ʧ��");
		}

		ClientUI.delay(2000);
		return;
	}
	public void findStudent() 
	{
		
		System.out.println("������Ҫ��ѯѧ������Ϣ��");
		String id = ClientReadSource.readStudentId(); //����ѧ��
		String s[] = r.findStudent(id);
		switch(s[0].charAt(0)){
		case '0':
			System.out.println("��ѧ��������");
			break;
		case '1':
			int selectedCourseNum = s.length - 1;//��ѧ����ѡ�γ���Ŀ
			System.out.println("ѧ����Ϣ���£�");
			String info[] = s[0].substring(1).split(",");
			System.out.println("ѧ�ţ�"+info[0]+"\n����:"+info[1]+"\n�༶:"+info[2]+"\n�Ա�:"+info[3]+"\n��ѡ�γ���Ŀ:"+selectedCourseNum);
			//int courseNum = Integer.parseInt(info[4]);
			if(selectedCourseNum>0) {
				System.out.println("��ѡ�γ���Ϣ���£�");
				String[] course = new String[5];//ÿ���γ���Ϣ�����¼: (String) course_id,course_name, (int) num,left_num,stu_num;
				for(int i=0; i<selectedCourseNum; i++) {
					course = s[i+1].split(",");//��ȡ�γ���Ϣ
					System.out.println("-------�γ�"+(i+1)+"-------");
					System.out.println("�γ̺ţ�"+course[0]+"\n�γ���:"+course[1]+"\n������:"+course[2]+"\n������:"+course[3]+"\n��ѡ����:"+course[4]+"\n");
				}
			}
			break;
		}
		//�����������...
		ClientUI.delay(10000);
		return;
	
	}
	public void addCourse()
	//����ţ��γ�����������
	{
		System.out.println("������Ҫ��ӿγ̵���Ϣ��");
		String id = ClientReadSource.readCourseId(); //����γ̺�
		String name = ClientReadSource.readCourseName();//
		int num = ClientReadSource.readCourseCapacity();
		if(r.addCourse(id,name,num)){
			System.out.println("��ӳɹ�");	
		}
		else {//�����ʧ�ܣ���˵���Ѵ��ڸÿγ̺ţ���ʾ����
			System.out.println("�γ̺��ظ������ʧ��");
		}

		ClientUI.delay(2000);
		return;
	}
	public void deleteCourse()//��������Ŀγ̺�ɾ���γ�
	{
		System.out.println("������Ҫɾ���γ̵Ŀγ���Ϣ��");
		String id = ClientReadSource.readCourseId(); //����γ̺�
		String s = r.deleteCourse(id);
		switch(s.charAt(0)){
		case '0':
			String info = s.substring(1); //��ȥ��һλ���Ӵ�����ʾ������Ϣ
			System.out.println("ɾ��ʧ�ܣ�ʧ��ԭ��"+info);
			break;
		case '1':
			System.out.println("ɾ���ɹ�");
			break;
		}
		
	}
	public void changeCourse()//ȫ��
	{
		System.out.println("������Ҫ�޸ĵĿγ���Ϣ��");
		System.out.print("�γ̺ţ�");
		Scanner sc=new Scanner(System.in);
		String id = sc.next(); //����γ̺�
		while(id.length()!=3){
			System.out.println("��������������3λ���ֿγ̺ţ�");
			id = sc.next();
		}
		System.out.print("�γ�����");
		String name = sc.next(); //����γ���
		if(r.changeCourse(id, name)) {
			System.out.println("�޸ĳɹ�");
		}
		else {
			System.out.println("�����ڸÿγ̺ţ��޸�ʧ��");
		}

		ClientUI.delay(2000);
		return;
	}
	public void findAllCourse()//���пγ���Ϣ
	{
		String allCourseStr[] = r.findAllCourse();
		String course[] = new String[5]; //ÿ���γ���Ϣ�����¼: (String) course_id,course_name, (int) num,left_num,stu_num;
		int courseNum = allCourseStr.length;
		System.out.println("����"+courseNum+"�ſγ̣��γ���Ϣ����");
		for(int i=0; i<courseNum; i++) {
			course = allCourseStr[i].split(",");
			System.out.println("-------�γ�"+(i+1)+"-------");
			System.out.println("�γ̺ţ�"+course[0]+"\n�γ���:"+course[1]+"\n������:"+course[2]+"\n������:"+course[3]+"\n��ѡ����:"+course[4]+"\n");
		}
		
		//�����������...
		ClientUI.delay(10000);
		return;
	}
	public void addCourseCapacity()
	{
		System.out.println("������Ҫ���ӿ������Ŀγ���Ϣ��");
		System.out.print("�γ̺ţ�");
		Scanner sc=new Scanner(System.in);
		String id = sc.next(); //����γ̺�
		while(id.length()!=3){
			System.out.println("��������������3λ���ֿγ̺ţ�");
			id = sc.next();
		}
		System.out.print("����������");
		int addNum = sc.nextInt(); 
		if(r.addCourseCapacity(id, addNum)) {
			System.out.println("���ӿ������ɹ�");
		}
		else {
			System.out.println("�����ڸÿγ̺ţ�����ʧ��");
		}
	}
	public void exitConnection() {//���߷������ر�����
		r.exitConnection();
	}
	public void exitChoosecourseManage() {//���߷������˳�ѡ�ι�����������ˢ��ѡ�μ�¼�ļ���
		r.exitChoosecourseManage();
	}
	public void exitStudentManage() {//�˳�ѧ������
		r.exitStudentManage();
	}
	public void exitCourseManage() {//�˳��γ̹���
		r.exitCourseManage();
	}
	

	
}
