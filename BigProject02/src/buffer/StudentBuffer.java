package buffer;


//import java.util.HashMap;
import EntityClass.*;
import java.util.*;
import java.util.Map.Entry;

public class StudentBuffer {
	public Map<String, Student> studentMap = new LinkedHashMap<String, Student>();
	
	//��黺���С�ĺ��� ����������������һ����Χ��
	public boolean check() {
		if(studentMap.size()>=100)
			return false;
		else {
//			studentMap.remove(key)
			Map.Entry<String, Student> item = (Entry<String, Student>) studentMap.entrySet();
			studentMap.remove(item.getKey());
			return true;
		}
		
	}
	
	public Student find(String ID)
	{
		if (studentMap.get(ID)!=null) {
			Student s = studentMap.get(ID);
			studentMap.remove(studentMap.get(ID));
			studentMap.put(ID, s);
			//studentMap.add(s);
			return s;
		}
		
		else {
			Student s = FindInFile(ID);//���ļ�
			studentMap.put(ID, s);
			return s;//����
		}
	}
	
	public Student FindInFile(String ID) {
		return Student s;
	}
	public static void main(String[] args) {
		System.out.println("lalala");
	}		
	
	
}
