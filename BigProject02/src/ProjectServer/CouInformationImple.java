package ProjectServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import EntityClass.Course;

public class CouInformationImple implements InformationOperate {
	DataInputStream dis=null;
	DataOutputStream dos=null;
	public CouInformationImple() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CouInformationImple(Socket DBsocket) {
		super();
		try {
			dis=new DataInputStream(DBsocket.getInputStream());
			dos=new DataOutputStream(DBsocket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public boolean Add(Object o) {//���ӿγ̣�
		boolean bool=false;
		try {
			dos.writeInt(7);
			dos.writeUTF(((Course)o).toString());
			dos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bool=dis.readBoolean();
			System.out.println("���������������ӿγ̲����ɹ���");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bool;
	}

	@Override
	public int Delete(String id) {//�ÿγ̲����ڷ���-1���ÿγ̲���ɾ������-2���ɹ�1
		int bool=0;
		try {
			dos.writeInt(8);
			dos.writeUTF(id);
			dos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			dis.readInt();
			System.out.println("������������ɾ���γ̲����ɹ���");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bool;
	}

	@Override
	public boolean Change(String s) {
		boolean bool=false;
		try {
			dos.writeInt(9);
			dos.writeUTF(s);
			dos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bool=dis.readBoolean();
			System.out.println("�������������޸Ŀγ̲����ɹ���");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bool;
	}

	@Override
	public Object Find(String id) {
		String s=null;
		try {
			dos.writeInt(10);
			dos.writeUTF(id);
			dos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			s=dis.readUTF();
			System.out.println("��������������ҿγ̲����ɹ���");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(s.equals("null"))				
			return null;
		else
			return Course.toCourse(s);
	}
	public List FindAll() throws IOException {//���ҵ�ǰ���пγ���Ϣ
		dos.writeInt(10);
		int num=dis.readInt();
		List<Course> list=new ArrayList<Course>();
		for(int i=1;i<=num;i++) {
			list.add(Course.toCourse(dis.readUTF()));
		}
		return list;
	}
	public boolean AddCapacity(String id,int num) {//���ӿγ�����
		boolean bool=false;
		try {
			dos.writeInt(11);
			dos.writeUTF(id);
			dos.writeInt(num);
			dos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			bool=dis.readBoolean();
			System.out.println("���������������ӿγ����������ɹ���");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bool;
	}
	public void flushFile() {
		try {
			dos.writeInt(-3);
			dos.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
