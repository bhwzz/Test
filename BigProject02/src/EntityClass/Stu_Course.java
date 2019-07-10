package EntityClass;

import java.util.Date;
//选课记录类
public class Stu_Course {
	String stuId;
	String couId;
	String date;//选课时间
	
	public Stu_Course() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Stu_Course(String stuId, String couId, String string) {
		super();
		this.stuId = stuId;
		this.couId = couId;
		this.date = string;
	}
	
	public void print() {
		System.out.println("学生："+stuId+"--->"+couId+"选课成功！时间"+date);
	}
	//选课
	//退选
	//已选课程查询
	
}
