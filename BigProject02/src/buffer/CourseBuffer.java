import java.util.*;
import EntityClass.*;

import EntityClass.Course;
import quality.StudentQuality;

public class CourseBuffer {

	public Map<String, Course> CourseMap;// = new LinkedHashMap<String, StudentQuality>();

	//public List<Course> coursebuffeer;
	public Course Find(String s)    //�ҵ�����true û�ҵ�����false
	//public boolean change(Object s) ;   //�ҵ�����true û�ҵ�����false
	{
		Course c=CourseMap.get(s);
		if(c==null)
		{
			return null;
		}
		return c;
	}
	
	public boolean Change(Course s) {
		if(Find(s.getCourse_id())==null) {
			System.out.println("����������γ�");
			return false;
		}
	
		CourseMap.put(s.getCourse_id(), s);
		return true;
	}
	
	public boolean Add(Course s) {
		//�ȿ�
		if(Find(s.getCourse_id())!=null) {
			System.out.println("�Ѿ���������γ�");
			return false;
		}
	
		CourseMap.put(s.getCourse_id(), s);
		return true;
		
	}
	
	public boolean Delete(String s){
		if(Find(s)==null) {
			System.out.println("����������γ�");
			return false;
		}
	
		CourseMap.remove(s);
		return true;
	}
}
