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
	//ǰ����ID ������Student��+flag(�Ƿ��޸Ĺ���
	private StuFileTool tool1;
	//�����ļ���Student���߰�
	
	int SIZE = 1000  ;
		//����Ĵ�С
	int LENGTH = 1024;
	//public Map<String, Integer> map = new TreeMap<String, Integer>();
	
	//��黺���С�ĺ��� ����������������һ����Χ��
	public boolean Check() {
		if(studentMap.size()<=SIZE)
			return false;
		//������ڵĻ���û�б�װ�� ����false
		else {
//			studentMap.remove(key)
			Map.Entry<String, StudentQuality> item = (Entry<String, StudentQuality>) studentMap.entrySet();
			//��ȡ��һ����ֵ��
			
			if(item.getValue().flag==0)
			{
				studentMap.remove(item.getKey());
				//û�б��޸Ĺ� ֱ�Ӷ���
			}
			
			if(item.getValue().flag!=0)
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
			Student s = Student.toStudent(tempString);//���ļ�\
			if(s.equals(null))
			{
				return null;
			}
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
			tool1.writeback(s);
			//���ѧ����
			return true;
		}
			
	}
	
	public boolean Add(Student s) throws Exception {
		if(!Find(s.getId()).equals(null))
		{
			System.out.println("��ѧ���Ѿ�����");
			return false;
		}
		tool1.add(s);
		return true;
	}
	
	public boolean Delete(String s) throws Exception {
		if(Find(s).equals(null))
		{
			return false;
		}
	
		tool1.delete(s);
		return true;
	}
	
	public static void main(String[] args) {
		System.out.println("lalala");
	}		
	
	
}
