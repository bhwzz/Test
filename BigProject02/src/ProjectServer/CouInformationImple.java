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
	public boolean Add(Object o) {//增加课程：
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
			System.out.println("服务器端请求增加课程操作成功！");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bool;
	}

	@Override
	public int Delete(String id) {//该课程不存在返回-1，该课程不可删除返回-2，成功1
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
			System.out.println("服务器端请求删除课程操作成功！");
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
			System.out.println("服务器端请求修改课程操作成功！");
			
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
			System.out.println("服务器端请求查找课程操作成功！");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(s.equals("null"))				
			return null;
		else
			return Course.toCourse(s);
	}
	public List FindAll() throws IOException {//查找当前所有课程信息
		dos.writeInt(10);
		int num=dis.readInt();
		List<Course> list=new ArrayList<Course>();
		for(int i=1;i<=num;i++) {
			list.add(Course.toCourse(dis.readUTF()));
		}
		return list;
	}
	public boolean AddCapacity(String id,int num) {//增加课程容量
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
			System.out.println("服务器端请求增加课程容量操作成功！");
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
