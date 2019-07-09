package ProjectServer;
//定义选课接口
//封装选课方法和退选方法

public interface ChooseCourse {
	//选课函数（学生id，要选课程id）
	public int chooseCourse(String StuId,String CouId) ;
	//退选函数（学生id，要退的课程id）
	//public boolean dropCourse(String StuId,String CouId);
}
