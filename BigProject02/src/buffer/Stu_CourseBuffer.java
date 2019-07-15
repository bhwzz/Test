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
		//如果现在的缓存没有被装满 返回false
		else {
//			studentMap.remove(key)
			Map.Entry<String, Map> item = (Entry<String, Map>)stu_couBuffer.entrySet().iterator().next();
			//获取第一个键值对
			//选课信息貌似不会被修改？只有增加和删除？？？
//			if(item.getValue().flag==0)
//			{
				stu_couBuffer.remove(item.getKey());
				//没有被修改过 直接丢弃
			}
//			
//			else if(item.getValue().flag!=0)
//			{
//				
//				tool1.writeback(item.getValue().getStudent());
//				studentMap.remove(item.getKey());
//				//被修改过 写回去
//			}
			//TreeMap<String,String>写索引
			//RandomAccessFile 随机访问文件
			
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
			System.out.println("该学生不存在");
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
				System.out.println("该学生未选过课");
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
			System.out.println("该学生不存在");
			return -1;
		}
		else if(coubuffer.Find(Couid)==null) {
			System.out.println("该课程不存在");
			return -2;
		}
//		else if(stu_couBook.get(Stuid)==null)
//		{
//			System.out.println("该学生未选过课");
//			return false;
//		}
		else if(stu_couBuffer.get(Stuid)!=null){
			
			Map<String,Stu_Course> map=(Map)stu_couBuffer.get(Stuid);
			if(map.get(Couid)==null||map.size()==0) {
				System.out.println("该学生未选过这节课");
				return -3;
			}
			return 1;
		}
	
		else {
			Map map=tool2.get(Stuid);
			if(map==null||map.size()==0) {
				System.out.println("该学生未选过课");
				return -3;
			}
			stu_couBuffer.put(Stuid, map);
			Check();
			if(map.get(Couid)==null) {
				System.out.println("该学生未选过这节课");
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
			System.out.println("该学生不存在");
			return -1;
		}
		else if(coubuffer.Find(Couid)==null)
		{
			System.out.println("该课程不存在");
			return -2;
		}
		else if(stu_couBuffer.get(Stuid)!=null)
		{//在缓存中能找到这个学生
			Map map = stu_couBuffer.get(Stuid);
			if(map.get(Couid)==null) {  //这个学生没有选过这节课
				if(!coubuffer.AddCourse(Couid)) {
					System.out.println("该课程已满课");
					return -3;
				}
				map.put(Couid, s);
				tool2.add(s);
				Check();
				return 1;
			}
			//在文件结尾添加一行 一会根据格式写
			
			else {
				System.out.println("已经选过这节课了");
				return -4;
			}
			
		}
		else {
			Map map=tool2.get(Stuid);
			if(map==null||map.size()==0) {
				System.out.println("这个学生没有选过任何课");
				if(!coubuffer.AddCourse(Couid)) {
					System.out.println("该课程已满课");
					return -3;
				}
				tool2.add(s);//往文件里面加入一条记录
				Map<String, Stu_Course> map2 = new HashMap<String, Stu_Course>();
				map2.put(Couid, s);
				stu_couBuffer.put(Stuid, map2);
				Check();
				return 1;
			}
			else if(map.get(Couid)!=null){
				System.out.println("这个学生已经选过这节课了");
				return -4;
			}
			else {
				if(!coubuffer.AddCourse(Couid)) {
					System.out.println("该课程已满课");
					return -3;
				}
				tool2.add(s);//往文件里面加入一条记录
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
				System.out.println("该学生不存在");
				return -1;
			}
			else if(coubuffer.Find(Couid)==null)
			{
				System.out.println("该课程不存在");
				return -2;
			}
			else if(stu_couBuffer.get(Stuid)!=null) {
				Map map=stu_couBuffer.get(Stuid);
				if(map.get(Couid)==null) {
					System.out.println("该学生选过课 但是没选过这节课");
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
					System.out.println("该学生从未选过课");
					return -3;
				}
				else if(map.get(Couid)==null)
				{
					System.out.println("该学生没选过这节课");
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
