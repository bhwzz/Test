package EntityClass;

import java.util.Date;
//ѡ�μ�¼��
public class Stu_Course {
	String stuId;
	String couId;
	String date;//ѡ��ʱ��
	
	public Stu_Course() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Stu_Course(String stuId, String couId, String string) {
		super();
		this.stuId = stuId;
		this.couId = couId;
		this.date = string;
	}
	
	public void print() {
		System.out.println("ѧ����"+stuId+"--->"+couId+"ѡ�γɹ���ʱ��"+date);
	}
	//ѡ��
	//��ѡ
	//��ѡ�γ̲�ѯ
	
}
