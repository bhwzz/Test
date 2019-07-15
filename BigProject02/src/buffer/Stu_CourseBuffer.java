package buffer;

import java.io.*;

import java.util.*;
//
//import com.sun.jndi.rmi.registry.ReferenceWrapper;
//import com.sun.tools.classfile.StackMapTable_attribute.same_frame;
import java.util.Map.Entry;

import EntityClass.*;
import EntityClass.Student;
import quality.StudentQuality;
import filetool.*;

public class Stu_CourseBuffer {

	private StudentBuffer stubuffer;
	private CourseBuffer coubuffer;
//	public Map<String, Set> stu_couBook;
	public Map<String, Map> stu_couBuffer;
	public StuCouTool tool2;
	public File rootfile;
	int LINELENGTH = 1;
	int STUIDSIZE = 7;
	int COUIDSIZE = 3;
	int TIMESIZE = 0;
	int SIZE = 10;
	public Stu_CourseBuffer(StudentBuffer s,CourseBuffer c,String filename) throws Exception {
		stubuffer = s;
		coubuffer = c;
		rootfile = new File(filename);
		tool2 = new StuCouTool(rootfile);
		stu_couBuffer = new HashMap<String, Map>();
		// TODO Auto-generated constructor stub
	}
	
	
	public boolean Check() throws Exception {
		if(stu_couBuffer.size()<=SIZE)
			return false;
		//������ڵĻ���û�б�װ�� ����false
		else {
//			studentMap.remove(key)
			Map.Entry<String, Map> item = (Entry<String, Map>)stu_couBuffer.entrySet().iterator().next();
			//��ȡ��һ����ֵ��
			//ѡ����Ϣò�Ʋ��ᱻ�޸ģ�ֻ�����Ӻ�ɾ��������
//			if(item.getValue().flag==0)
//			{
				stu_couBuffer.remove(item.getKey());
				//û�б��޸Ĺ� ֱ�Ӷ���
			}
//			
//			else if(item.getValue().flag!=0)
//			{
//				
//				tool1.writeback(item.getValue().getStudent());
//				studentMap.remove(item.getKey());
//				//���޸Ĺ� д��ȥ
//			}
			//TreeMap<String,String>д����
			//RandomAccessFile ��������ļ�
			
			return true;
		}
	
//	public Stu_CourseBuffer(StudentBuffer stu,String s) {
//		this.stubuffer = stu;
//		stu_couBuffer = new LinkedHashMap<String, Map>();
//		rootfile = new File(s);
//		// TODO Auto-generated constructor stub
//	}
	public Map find(String Stuid) throws Exception {
		if(stubuffer.Find(Stuid)==null)
		{
			System.out.println("��ѧ��������");
			return null;
		}
		else if(stu_couBuffer.get(Stuid)!=null)
		{
			Map map=stu_couBuffer.get(Stuid);
			stu_couBuffer.remove(Stuid);
			stu_couBuffer.put(Stuid, map);
			return map;
		}
		else {
			Map<String,Stu_Course> map = tool2.get(Stuid);
			if(map==null||map.size()==0)
			{
				System.out.println("��ѧ��δѡ����");
				Map <String ,Stu_Course> map2=new HashMap<String, Stu_Course>();
				return map2;
			}
			stu_couBuffer.put(Stuid, map);
			return map;
		}
	}
	public int find(String Stuid,String Couid) throws Exception {
		if(stubuffer.Find(Stuid)==null)
		{
			System.out.println("��ѧ��������");
			return -1;
		}
		else if(coubuffer.Find(Couid)==null) {
			System.out.println("�ÿγ̲�����");
			return -2;
		}
//		else if(stu_couBook.get(Stuid)==null)
//		{
//			System.out.println("��ѧ��δѡ����");
//			return false;
//		}
		else if(stu_couBuffer.get(Stuid)!=null){
			
			Map<String,Stu_Course> map=(Map)stu_couBuffer.get(Stuid);
			if(map.get(Couid)==null||map.size()==0) {
				System.out.println("��ѧ��δѡ����ڿ�");
				return -3;
			}
			return 1;
		}
	
		else {
			Map map=tool2.get(Stuid);
			if(map==null||map.size()==0) {
				System.out.println("��ѧ��δѡ����");
				return -3;
			}
			stu_couBuffer.put(Stuid, map);
			Check();
			if(map.get(Couid)==null) {
				System.out.println("��ѧ��δѡ����ڿ�");
				return -3;
			}
			return 1;
		}
		
	}
			
	public int add(Stu_Course s) throws Exception			
	{
		String Stuid = s.getstuId();
		String Couid = s.getcouId();
		if(stubuffer.Find(Stuid)==null)
		{
			System.out.println("��ѧ��������");
			return -1;
		}
		else if(coubuffer.Find(Couid)==null)
		{
			System.out.println("�ÿγ̲�����");
			return -2;
		}
		else if(stu_couBuffer.get(Stuid)!=null)
		{//�ڻ��������ҵ����ѧ��
			Map map = stu_couBuffer.get(Stuid);
			if(map.get(Couid)==null) {  //���ѧ��û��ѡ����ڿ�
				if(!coubuffer.AddCourse(Couid)) {
					System.out.println("�ÿγ�������");
					return -3;
				}
				map.put(Couid, s);
				tool2.add(s);
				Check();
				return 1;
			}
			//���ļ���β���һ�� һ����ݸ�ʽд
			
			else {
				System.out.println("�Ѿ�ѡ����ڿ���");
				return -4;
			}
			
		}
		else {
			Map map=tool2.get(Stuid);
			if(map==null||map.size()==0) {
				System.out.println("���ѧ��û��ѡ���κο�");
				if(!coubuffer.AddCourse(Couid)) {
					System.out.println("�ÿγ�������");
					return -3;
				}
				tool2.add(s);//���ļ��������һ����¼
				Map<String, Stu_Course> map2 = new HashMap<String, Stu_Course>();
				map2.put(Couid, s);
				stu_couBuffer.put(Stuid, map2);
				Check();
				return 1;
			}
			else if(map.get(Couid)!=null){
				System.out.println("���ѧ���Ѿ�ѡ����ڿ���");
				return -4;
			}
			else {
				if(!coubuffer.AddCourse(Couid)) {
					System.out.println("�ÿγ�������");
					return -3;
				}
				tool2.add(s);//���ļ��������һ����¼
				map.put(Couid, s);
				stu_couBuffer.put(Stuid, map);
				Check();
				return 1;
			}
		}
	}
//			Map <String,String> map=new HashMap<String, String>();

	public int delete(String Stuid,String Couid) throws Exception {
			if(stubuffer.Find(Stuid)==null)
			{
				System.out.println("��ѧ��������");
				return -1;
			}
			else if(coubuffer.Find(Couid)==null)
			{
				System.out.println("�ÿγ̲�����");
				return -2;
			}
			else if(stu_couBuffer.get(Stuid)!=null) {
				Map map=stu_couBuffer.get(Stuid);
				if(map.get(Couid)==null) {
					System.out.println("��ѧ��ѡ���� ����ûѡ����ڿ�");
					return -3;
				}
				else {
					coubuffer.DropCourse(Couid);
					map.remove(Couid);
					tool2.delete( Stuid, Couid);
					return 1;
				}
			}
			else {
				Map map=tool2.get(Stuid);
				if(map==null||map.size()==0) {
					System.out.println("��ѧ����δѡ����");
					return -3;
				}
				else if(map.get(Couid)==null)
				{
					System.out.println("��ѧ��ûѡ����ڿ�");
					return -3;
				}
				else if(map.get(Couid)!=null) {
					coubuffer.DropCourse(Couid);
					tool2.delete(Stuid, Couid);
					map.remove(Couid);
					stu_couBuffer.put(Stuid, map);
					Check();
					return 1;
				}
			}
			return 0;
		}
			
			
	public static void main(String[] args) {
		//Stu_CourseBuffer mBuffer = new Stu_CourseBuffer(, c, filename)
	}
	
	
	

}
