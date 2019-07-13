package buffer;



//import java.util.HashMap;
import EntityClass.*;
import filetool.*;

import java.io.*;
import java.util.*;
import java.util.Map.*;

import org.graalvm.compiler.core.common.type.ArithmeticOpTable.BinaryOp.Add;

//import org.graalvm.compiler.lir.alloc.lsra.ssa.SSALinearScan;

import quality.*;

public class StudentBuffer {
	public Map<String, StudentQuality> studentMap;// = new LinkedHashMap<String, StudentQuality>();
	//ǰ����ID ������Student��+flag(�Ƿ��޸Ĺ���
	private StuFileTool tool1;
	//�����ļ���Student���߰�
	
	int SIZE = 2  ;
		//����Ĵ�С
	int LENGTH = 1024;
	public StudentBuffer(String s) throws Exception {
		studentMap = new LinkedHashMap<String, StudentQuality>();
		tool1 = new StuFileTool(s);
		// TODO Auto-generated constructor stub
	}
	//public Map<String, Integer> map = new TreeMap<String, Integer>();
	
	//��黺���С�ĺ��� ����������������һ����Χ��
	public boolean Check() throws Exception {
		if(studentMap.size()<=SIZE)
			return false;
		//������ڵĻ���û�б�װ�� ����false
		else {
//			studentMap.remove(key)
			Map.Entry<String, StudentQuality> item = (Entry<String, StudentQuality>) 	studentMap.entrySet().iterator().next();
			//��ȡ��һ����ֵ��
			
			if(item.getValue().flag==0)
			{
				studentMap.remove(item.getKey());
				//û�б��޸Ĺ� ֱ�Ӷ���
			}
			
			else if(item.getValue().flag!=0)
			{
				
				tool1.writeback(item.getValue().getStudent());
				studentMap.remove(item.getKey());
				//���޸Ĺ� д��ȥ
			}
			//TreeMap<String,String>д����
			//RandomAccessFile ��������ļ�
			
			return true;
		}
//		return true;
		
	}
	
	public Student Find(String ID) throws Exception//�ҵ���return student û�ҵ�return null
	{
		if (studentMap.get(ID)!=null) {
			Student s = studentMap.get(ID).getStudent();
			StudentQuality sq = new StudentQuality(s);
			studentMap.remove(studentMap.get(ID));
			studentMap.put(ID, sq);
			//�����������һλ ���ó����ٷŽ�ȥ
			//studentMap.add(s);
			return s;
		}
		
		else {
			String tempString=tool1.findinfile(ID);
		//	Student s = Student.toStudent(tempString);//���ļ�\
			if(tempString==null)
			{
				return null;
			}
			Student s = Student.toStudent(tempString);//���ļ�\
			StudentQuality sq = new StudentQuality(s);
			studentMap.put(ID, sq);
			Check();
			//������û�����
			return s;//����
		}
	}
	
	public boolean Change(Student s) throws Exception {
		if(Find(s.getId()).equals(null))
		{
			System.out.println("��ѧ��������");
			return false;
		}
		else{
			//Delete(s.getId());
			//��ѧ����
		//	tool1.writeback(s);
			studentMap.remove(s);
			//studentMap.add
			//Student s = studentMap.get(ID).getStudent();
			StudentQuality sq = new StudentQuality(s);
			sq.flag = 1;
			studentMap.remove(s.getId());
			studentMap.put(s.getId(), sq);
			//���ѧ����
			return true;
		}
			
	}
	
	public boolean Add(Student s) throws Exception {
		if(!(Find(s.getId())==null))
		{
			System.out.println("��ѧ���Ѿ�����");
			return false;
		}
		//��tool1����
		tool1.add(s);    
		//��tool1����   ��removeͬ�� �Ƚ�ʡ��
		
		StudentQuality sq = new StudentQuality(s);
		sq.flag = 1;
		
		//��map����
		studentMap.put(s.getId(), sq);
		//��map����
		Check();
		return true;
	}
	
	public boolean Delete(String s) throws Exception {
		if(Find(s).equals(null))
		{
			return false;
		}
	
		studentMap.remove(s);
		
		//tool����
		tool1.delete(s);
		//���Ҫremove----��ʡ�� ����check������дһ��if��  ����������û��bug
		return true;
	}
	
	public boolean Clear() throws Exception {
		Iterator<Entry<String, StudentQuality>> entries = studentMap.entrySet().iterator();
		while(entries.hasNext()){
		    Entry<String, StudentQuality> entry = entries.next();
//		    if(entry.getValue().flag==0)
//			{
//				studentMap.remove(entry.getKey());
//				//û�б��޸Ĺ� ֱ�Ӷ���
//			}
			
			if(entry.getValue().flag!=0)
			{
				
				tool1.writeback(entry.getValue().getStudent());
			//	studentMap.remove(entry.getKey());
				//���޸Ĺ� д��ȥ
			}
		}
		
		return true;
	}
	public static void main(String[] args) throws Exception {
		StudentBuffer studentBuffer = new StudentBuffer("d:\\test3.txt");
		Student s1 = Student.toStudent("2202197,����,03,��");
		s1.print();
		studentBuffer.Add(s1);
		studentBuffer.Clear();
		System.out.println(studentBuffer.tool1.bufferMap);
		studentBuffer.Delete("2202197");
		studentBuffer.Clear();
		System.out.println(studentBuffer.tool1.bufferMap);
	}		
	
	
}
