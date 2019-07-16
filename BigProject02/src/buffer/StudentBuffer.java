package buffer;



//import java.util.HashMap;
import EntityClass.*;
import filetool.*;

import java.io.*;
import java.util.*;
import java.util.Map.*;

//import org.graalvm.compiler.core.common.type.ArithmeticOpTable.BinaryOp.Add;

//import org.graalvm.compiler.lir.alloc.lsra.ssa.SSALinearScan;

import quality.*;

public class StudentBuffer {
	public Map<String, StudentQuality> studentMap;// = new LinkedHashMap<String, StudentQuality>();
	//前面是ID 后面是Student类+flag(是否被修改过）
	private StuFileTool tool1;
	private Stu_CourseBuffer stucoubufferBuffer = null;
	//面向文件的Student工具包
	
	int SIZE = 10 ;
		//缓存的大小
	int LENGTH = 1024;
	
	protected void finalize() throws Exception {
		Clear();
	}
	public StudentBuffer(String s) throws Exception {
		studentMap = new LinkedHashMap<String, StudentQuality>();
		tool1 = new StuFileTool(s);
		// TODO Auto-generated constructor stub
	}
	
	public void set(Stu_CourseBuffer s) {
		stucoubufferBuffer = s;
	}
	//public Map<String, Integer> map = new TreeMap<String, Integer>();
	
	//检查缓存大小的函数 将缓存数量控制在一定范围内
	public boolean Check() throws Exception {
		if(studentMap.size()<=SIZE) {
			System.out.println("目前学生缓存大小为"+studentMap.size());
			return false;
		}
		//如果现在的缓存没有被装满 返回false
		else {
			System.out.println("目前学生缓存大小为"+studentMap.size());
			if(studentMap.size()<=10) {
	//			studentMap.remove(key)
				Map.Entry<String, StudentQuality> item = (Entry<String, StudentQuality>) 	studentMap.entrySet().iterator().next();
				//获取第一个键值对
				
				if(item.getValue().flag==0)
				{
					System.out.println("丢弃"+item.getKey());
					
					studentMap.remove(item.getKey());
					//没有被修改过 直接丢弃
				}
				
				else if(item.getValue().flag==1)//被增加 or删除
				{
					System.out.println("写回并丢弃"+item.getKey());
					tool1.writeback(item.getValue().getStudent());
					studentMap.remove(item.getKey());
					//被修改过 写回去
				}
				else if(item.getValue().flag==-1) {
					System.out.println("删除并丢弃"+item.getKey());
					tool1.delete(item.getKey());
					studentMap.remove(item.getKey());
				}
				//TreeMap<String,String>写索引
				//RandomAccessFile 随机访问文件
				
				return true;
			}
			else {
				for(int k=0;k<10;k++) {
					Map.Entry<String, StudentQuality> item = (Entry<String, StudentQuality>) 	studentMap.entrySet().iterator().next();
					//获取第一个键值对
					
					if(item.getValue().flag==0)
					{
						studentMap.remove(item.getKey());
						//没有被修改过 直接丢弃
					}
					
					else if(item.getValue().flag==1)//被增加 or删除
					{
						
						tool1.writeback(item.getValue().getStudent());
						studentMap.remove(item.getKey());
						//被修改过 写回去
					}
					else if(item.getValue().flag==-1) {
						tool1.delete(item.getKey());
						studentMap.remove(item.getKey());
					}
				}
				return true;
				
			}
		}
//		return true;
		
	}
	
	public Student Find(String ID) throws Exception//找到了return student 没找到return null
	{
		System.out.println("要查找的学生id"+ID);
		
		if (studentMap.get(ID)!=null) {
			if(studentMap.get(ID).flag==-1) {
				return null;
			}
			Student s = studentMap.get(ID).getStudent();
			StudentQuality sq = new StudentQuality(s);
			studentMap.remove(studentMap.get(ID));
			studentMap.put(ID, sq);
			//把他换到最后一位 先拿出来再放进去
			//studentMap.add(s);
			return s;
		}
		
		else {
			String tempString=tool1.findinfile(ID);
		//	Student s = Student.toStudent(tempString);//查文件\
			if(tempString==null)
			{
				return null;
			}
			Student s = Student.toStudent(tempString);//查文件\
			StudentQuality sq = new StudentQuality(s);
			studentMap.put(ID, sq);
			Check();
			//看看有没有溢出
			return s;//返回
		}
	}
	
	public boolean Change(Student s) throws Exception {
		if(Find(s.getId())==null)
		{
			System.out.println("该学生不存在");
			return false;
		}
		else{
			//Delete(s.getId());
			//加学生锁
		//	tool1.writeback(s);
			studentMap.remove(s);
			//studentMap.add
			//Student s = studentMap.get(ID).getStudent();
			StudentQuality sq = new StudentQuality(s);
			sq.flag = 1;
			studentMap.remove(s.getId());
			studentMap.put(s.getId(), sq);
			//解除学生锁
			return true;
		}
			
	}
	
	public boolean Add(Student s) throws Exception {
		if(!(Find(s.getId())==null))
		{
			System.out.println("该学生已经存在");
			return false;
		}
		//给tool1加锁
		//tool1.add(s);    
		//给tool1解锁   和remove同理 比较省事
		
		StudentQuality sq = new StudentQuality(s);
		sq.flag = 1;
		
		//给map加锁
		studentMap.put(s.getId(), sq);
		//给map解锁
		Check();
		System.out.println("增加学生函数结束！");
		return true;
	}
	
	public int Delete(String s) throws Exception {
		if(Find(s)==null)
		{
			return -1;
		}
	
		else if(stucoubufferBuffer.find(s).size()==0) {
		//	studentMap.remove(s);
		//	tool1.delete(s);
			studentMap.get(s).delflag();
			System.out.println("删除学生函数结束！");
			return 1;
		}
		else return -2;
//		
//		//tool加锁
//		tool1.delete(s);
//		System.out.println("删除学生函数结束！");
		//这个要remove----》省事 不用check里面再写一个if了  看看后期有没有bug
//		return true;
	}
	
	public boolean Clear() throws Exception {
		Iterator<Entry<String, StudentQuality>> entries = studentMap.entrySet().iterator();
		while(entries.hasNext()){
		    Entry<String, StudentQuality> entry = entries.next();
//		    if(entry.getValue().flag==0)
//			{
//				studentMap.remove(entry.getKey());
//				//没有被修改过 直接丢弃
//			}
			
			if(entry.getValue().flag==1)
			{
				
				tool1.writeback(entry.getValue().getStudent());
			//	studentMap.remove(entry.getKey());
				//被修改过 写回去
			}
			else if(entry.getValue().flag==-1) {
				tool1.delete(entry.getKey());
			}
		}
		studentMap.clear();
		System.out.println("缓存清空并全部写回");
		
		return true;
	}
	
	public boolean Fresh() throws Exception {
		Iterator<Entry<String, StudentQuality>> entries = studentMap.entrySet().iterator();
		List<String> list=new ArrayList<String>() ;
		while(entries.hasNext()){
		    Entry<String, StudentQuality> entry = entries.next();
//		    if(entry.getValue().flag==0)
//			{
//				studentMap.remove(entry.getKey());
//				//没有被修改过 直接丢弃
//			}
			
			if(entry.getValue().flag==1)
			{
				
				tool1.writeback(entry.getValue().getStudent());
				entry.getValue().flag=0;
			//	studentMap.remove(entry.getKey());
				//被修改过 写回去
			}
			else if(entry.getValue().flag==-1) {
				tool1.delete(entry.getKey());
				list.add(entry.getKey());
			}
		}
		for(String value:list) {
			studentMap.remove(value);
		}
		
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		StudentBuffer studentBuffer = new StudentBuffer("d:\\test3.txt");
		Student s1 = Student.toStudent("2202197,喵喵,03,男");
		s1.print();
		studentBuffer.Add(s1);
		
		Student s2 = studentBuffer.Find("5673314");
		s2.print();
		studentBuffer.Add(s2);
		System.out.println(studentBuffer.studentMap);
		studentBuffer.Clear();
		System.out.println(studentBuffer.tool1.bufferMap);
		studentBuffer.Delete("2202197");
		studentBuffer.Clear();
		System.out.println(studentBuffer.tool1.bufferMap);
		System.out.println(studentBuffer.studentMap);
	}		
	
	
}
