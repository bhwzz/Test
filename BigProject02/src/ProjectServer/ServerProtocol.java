package ProjectServer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import EntityClass.Course;
import EntityClass.Stu_Course;
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
		System.out.println("��������ʼservice��");
		try {
			DataInputStream dis=new DataInputStream(Clientsocket.getInputStream());
			DataOutputStream dos=new DataOutputStream(Clientsocket.getOutputStream());
			while(true) {
				int i=dis.readInt();
				System.out.println("�ͻ�������"+i);
				switch(i) {
				case 1://ѡ��:����1ѡ�γɹ�������0ѡ��ʧ�ܣ����ŷ���һ���ַ�����
					sid=dis.readUTF();
					cid=dis.readUTF();
					bool=cci.chooseCourse(sid, cid);
					if(bool==1) {
						dos.writeInt(1);
					}
					else if(bool==-1) {
						dos.writeInt(0);
						dos.writeUTF("The student does not exist!");
					}
					else if(bool==-2) {
						dos.writeInt(0);
						dos.writeUTF("The course does not exist!");
					}
					else if(bool==-3) {
						dos.writeInt(0);
						dos.writeUTF("There is no spare capacity for the course!");
					}
					else if(bool==-4) {
						dos.writeInt(0);
						dos.writeUTF("The student has already taken this course!");
					}
					dos.flush();
					break;
				case 2://��ѡ
					sid=dis.readUTF();
					cid=dis.readUTF();
					bool=cci.dropCourse(sid, cid);
					if(bool==1) {
						dos.writeInt(1);
					}
					else if(bool==-1) {
						dos.writeInt(0);
						dos.writeUTF("The student does not exist!");
					}
					else if(bool==-2) {
						dos.writeInt(0);
						dos.writeUTF("The course does not exist!");
					}
					else if(bool==-3) {
						dos.writeInt(0);
						dos.writeUTF("The student had not taken the course!");
					}
					break;
				case 3://����ѧ����Ϣ��������һ�������ַ���
					s=dis.readUTF();//������һ�������ַ���
					Student stu=Student.toStudent(s);
					bool=sii.Add(stu)?1:0;
//					System.out.println("���ӽ��"+bool);
					dos.writeInt(bool);//����1�����ӳɹ�������0��ѧ���Ѵ���
					dos.flush();
					break;
				case 4://ɾ��ѧ����Ϣ��������һ��ѧ��id
					sid=dis.readUTF();
					bool=sii.Delete(sid);//ɾ��ѧ���ĺ���Ҫ�жϸ�ѧ����û��ѡ��
					System.out.println("ɾ�����"+bool);
					if(bool==1)
						dos.writeInt(1);//����1��ɾ���ɹ�������0��ɾ��ѧ��ʧ�ܣ������������������޸ģ�
					else if(bool==-1) {//ɾ��ʧ�ܷ���һ��ʧ��ԭ���ַ���
						dos.writeInt(0);
						dos.writeUTF("The student does not exist!");
					}
					else if(bool==-2){//��ѧ����ѡ�μ�¼
						dos.writeInt(0);
						dos.writeUTF("The student has selected a course that cannot be deleted!");
					}
					dos.flush();
					break;
				case 5://�޸�ѧ����Ϣ��������һ����ѧ����Ϣ�ַ���
					s=dis.readUTF();
					bool=sii.Change(s)?1:0;
					System.out.println("�޸Ľ��"+bool);
					dos.writeInt(bool);;//����1���޸ĳɹ�������0���޸�ʧ��
					dos.flush();
					break;
				case 6://����ѧ����Ϣ��������ѧ��id,����ѧ��������Ϣ+ѧ������ѡ����Ϣ
					sid=dis.readUTF();
					//����ѧ��������Ϣ�Լ�ѡ����Ϣ
					System.out.println("�ͻ���Ҫ���ҵ�ѧ��id��"+sid);
					Student stu3=(Student)sii.Find(sid);
					if(stu3==null)
						dos.writeInt(0);
					else {
						dos.writeInt(1);
						System.out.println("���ݿ���Ҹ�ѧ���ɹ���");
						dos.writeUTF(stu3.toString());
						System.out.println(stu3.toString());
						//ѧ�����ڲ���ѧ��ѡ����Ϣ
						List<Stu_Course> list=sii.FindCourse(sid);
						int num=list.size();
						dos.writeInt(num);//��д��ѡ����Ŀ
						if(num>0) {
							for(int i1=0;i1<num;i1++) {
								dos.writeUTF(list.get(i1).toString());//ÿ��д��һ��ѡ�μ�¼
							}
						}
						dos.flush();
					}
					dos.flush();
					break;
				case 7://���ӿγ�:������һ���ַ�����id��name��������
					s=dis.readUTF();
					bool=cii.Add(Course.toCourse(s+",0,0"))?1:0;
					dos.writeInt(bool);
					dos.flush();
					break;
				case 8://ɾ���γ̣�Ҫ��ÿγ̵�ѡ������Ϊ0������-2��-1��1��
					cid=dis.readUTF();
					dos.writeInt(cii.Delete(cid));
					dos.flush();
					break;
				case 9://�޸Ŀγ���Ϣ(ֻ�����޸Ŀγ����֣����γ�id+name//����1��0
					s=dis.readUTF();
					bool=cii.Change(s)?1:0;
					dos.writeInt(bool);
					dos.flush();
					break;
				case 10://���ҿγ���Ϣ���������пγ���Ϣ���м����γ̴�����
					List<Course> array=cii.FindAll();
					int num=array.size();
					dos.writeInt(num);//��д�ؿγ���Ŀ
					for(int i1=0;i1<num;i1++) {
						dos.writeUTF(array.get(i1).toString());
					}
					dos.flush();
					break;
				case 11://���ӿγ���������id+addnum//����1��0
					cid=dis.readUTF();
					int addnum=dis.readInt();
					bool=cii.AddCapacity(cid, addnum)?1:0;
					dos.writeInt(bool);
					dos.flush();
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("������������û�����ӵ��ͻ���");
		}
	}

}