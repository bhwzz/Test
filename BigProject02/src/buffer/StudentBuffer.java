package buffer;


//import java.util.HashMap;
import EntityClass.*;

import java.io.*;
import java.util.*;
import java.util.Map.Entry;



//import org.graalvm.compiler.lir.alloc.lsra.ssa.SSALinearScan;

import quality.*;

public class StudentBuffer {
	public Map<String, StudentQuality> studentMap = new LinkedHashMap<String, StudentQuality>();
	public Map<String, Integer> map = new TreeMap<String, Integer>();
	int LENGTH = 1024;
	//��黺���С�ĺ��� ����������������һ����Χ��
	public boolean check() {
		if(studentMap.size()<=100)
			return false;
		else {
//			studentMap.remove(key)
			Map.Entry<String, StudentQuality> item = (Entry<String, StudentQuality>) studentMap.entrySet();
			//��һ��д��
			//TreeMap<String,String>д����
			//RandomAccessFile ��������ļ�
			studentMap.remove(item.getKey());
			return true;
		}
		
	}
	
	public Student find(String ID)
	{
		if (studentMap.get(ID)!=null) {
			Student s = studentMap.get(ID).getStudent();
			StudentQuality sq = new StudentQuality(s);
			studentMap.remove(studentMap.get(ID));
			studentMap.put(ID, sq);
			//studentMap.add(s);
			return s;
		}
		
		else {
			Student s = FindInFile(ID);//���ļ�
			StudentQuality sq = new StudentQuality(s);
			studentMap.put(ID, sq);
			check();
			return s;//����
		}
	}
	
	public Student FindInFile(String ID) {
		RandomAccessFile readAccessFile =  new RandomAccessFile("d:\\students.txt" , "r"); 
		//�����������
		int t=map.get(ID);
		
		//������������
		readAccessFile.seek((LENGTH-1)*t);  //length��ʾһ�еĳ���
		String string = readAccessFile.readLine();
		//StudentQuality studenttemp = new StudentQuality(string);
		Student tempStudent = new Student(string);
		return tempStudent;
	}
	
	public boolean Change(String ID)
	public static void main(String[] args) {
		System.out.println("lalala");
	}		
	
	
}
