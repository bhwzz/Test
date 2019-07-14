import java.util.*;
import EntityClass.*;

import EntityClass.Course;
import quality.StudentQuality;

public class CourseBuffer {

	public Map<String, Course> CourseMap;// = new LinkedHashMap<String, StudentQuality>();

	//public List<Course> coursebuffeer;
	public Course Find(String s)    //找到返回true 没找到返回false
	//public boolean change(Object s) ;   //找到返回true 没找到返回false
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
			System.out.println("不存在这个课程");
			return false;
		}
	
		CourseMap.put(s.getCourse_id(), s);
		return true;
	}
	
	public boolean Add(Course s) {
		//先看
		if(Find(s.getCourse_id())!=null) {
			System.out.println("已经存在这个课程");
			return false;
		}
	
		CourseMap.put(s.getCourse_id(), s);
		return true;
		
	}
	
	public boolean Delete(String s){
		if(Find(s)==null) {
			System.out.println("不存在这个课程");
			return false;
		}
	
		CourseMap.remove(s);
		return true;
	}
}
