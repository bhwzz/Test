package buffer;



//import java.util.HashMap;
import EntityClass.*;
import filetool.*;

import java.io.*;
import java.util.*;
import java.util.Map.*;

//import org.graalvm.compiler.lir.alloc.lsra.ssa.SSALinearScan;

import quality.*;

public class StudentBuffer {
	public Map<String, StudentQuality> studentMap = new LinkedHashMap<String, StudentQuality>();
	//前面是ID 后面是Student类+flag(是否被修改过）
	private StuFileTool tool1;
	//面向文件的Student工具包
	
	int SIZE = 1000  ;
		//缓存的大小
	int LENGTH = 1024;
	//public Map<String, Integer> map = new TreeMap<String, Integer>();
	
	//检查缓存大小的函数 将缓存数量控制在一定范围内
	public boolean Check() throws Exception {
		if(studentMap.size()<=SIZE)
			return false;
		//如果现在的缓存没有被装满 返回false
		else {
//			studentMap.remove(key)
			Map.Entry<String, StudentQuality> item = (Entry<String, StudentQuality>) studentMap.entrySet();
			//获取第一个键值对
			
			if(item.getValue().flag==0)
			{
				studentMap.remove(item.getKey());
				//没有被修改过 直接丢弃
			}
			
			if(item.getValue().flag!=0)
			{
				
				tool1.writeback(item.getValue().getStudent());
				studentMap.remove(item.getKey());
				//被修改过 写回去
			}
			//TreeMap<String,String>写索引
			//RandomAccessFile 随机访问文件
			
			return true;
		}
//		return true;
		
	}
	
	public Student Find(String ID) throws Exception//找到了return student 没找到return null
	{
		if (studentMap.get(ID)!=null) {
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
			Student s = Student.toStudent(tempString);//查文件\
			if(s.equals(null))
			{
				return null;
			}
			StudentQuality sq = new StudentQuality(s);
			studentMap.put(ID, sq);
			Check();
			//看看有没有溢出
			return s;//返回
		}
	}
	
	public boolean Change(Student s) throws Exception {
		if(Find(s.getId()).equals(null))
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
		if(!Find(s.getId()).equals(null))
		{
			System.out.println("该学生已经存在");
			return false;
		}
		//给tool1加锁
		tool1.add(s);    
		//给tool1解锁   和remove同理 比较省事
		
		StudentQuality sq = new StudentQuality(s);
		sq.flag = 1;
		
		//给map加锁
		studentMap.put(s.getId(), sq);
		//给map解锁
		Check();
		return true;
	}
	
	public boolean Delete(String s) throws Exception {
		if(Find(s).equals(null))
		{
			return false;
		}
	
		studentMap.remove(s);
		
		//tool加锁
		tool1.delete(s);
		//这个要remove----》省事 不用check里面再写一个if了  看看后期有没有bug
		return true;
	}
	
	public static void main(String[] args) {
		System.out.println("lalala");
	}		
	
	
}
