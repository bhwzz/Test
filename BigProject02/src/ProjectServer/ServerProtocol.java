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
//			ObjectInputStream ois=new ObjectInputStream(Clientsocket.getInputStream());
//			ObjectOutputStream oos=new ObjectOutputStream(Clientsocket.getOutputStream());
//			PrintWriter pw=new PrintWriter(new OutputStreamWriter(Clientsocket.getOutputStream()));			
//			BufferedReader br=new BufferedReader(new InputStreamReader(Clientsocket.getInputStream()));
//			BufferedWriter bw=new BufferedWriter(new OutputStreamWriter(Clientsocket.getOutputStream()));
			while(true) {
				int i=dis.readInt();
				System.out.println("�ͻ�������"+i);
				switch(i) {
//				case 1://ѡ��:����1ѡ�γɹ�������0ѡ��ʧ�ܣ����ŷ���һ���ַ�����
//					sid=br.readLine();
//					cid=br.readLine();
//					bool=cci.chooseCourse(sid,cid);
//					bw.write(bool);
//					bw.flush();
//					break;
//				case 2://��ѡ
//					sid=br.readLine();
//					cid=br.readLine();
//					bool=cci.dropCourse(sid,cid);
//					bw.write(bool);
//					bw.flush();
//					break;
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
					bool=sii.Delete(sid)?1:0;//ɾ��ѧ���ĺ���Ҫ�жϸ�ѧ����û��ѡ��
					dos.writeInt(bool);//����1��ɾ���ɹ�������0��ɾ��ѧ��ʧ�ܣ������������������޸ģ�
					System.out.println("ɾ�����"+bool);
					if(bool==0) {//ɾ��ʧ�ܷ���һ��ʧ��ԭ���ַ���
						dos.writeUTF("not found");
					}
					dos.flush();
					break;
				case 5://�޸�ѧ����Ϣ��������һ����ѧ����Ϣ�ַ���
					s=dis.readUTF();
					Student stu2=Student.toStudent(s);
					bool=sii.Change(stu2)?1:0;
					System.out.println("�޸Ľ��"+bool);
					dos.writeInt(bool);;//����1���޸ĳɹ�������0���޸�ʧ��
					dos.flush();
					break;
				case 6://����ѧ����Ϣ��������ѧ��id
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
					}
					dos.flush();
					break;
				case 7://���ӿγ�:������һ���ַ�����id��name��������
					s=dis.readUTF();
					
					break;
				case 8://ɾ���γ̣�Ҫ��ÿγ̵�ѡ������Ϊ0�����ؽ��ͬstudent��
					
					break;
				case 9://�޸Ŀγ���Ϣ(ֻ�����޸Ŀγ����֣����γ�id+name//����1��0
					
					break;
				case 10://���ҿγ���Ϣ���������пγ���Ϣ���м����γ̴�����
					
					break;
				case 11://���ӿγ���������id+addnum//����1��0
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("������������û�����ӵ��ͻ���");
		}
	}

}
