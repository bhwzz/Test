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
		System.out.println(stuId+","+couId+","+date);
	}
	
	public static Stu_Course toStuCourse(String sc) {
		String []s=new String[3];
		s=sc.split(",");
		return new Stu_Course(s[0],s[1],s[2]);
	}
	public String toString() {
		return ""+this.stuId+","+this.couId+","+this.date;
	}
	public static void main(String[] args) {
		Stu_Course sc=Stu_Course.toStuCourse("1234567,001,2019-04-11");
		sc.print();
		System.out.println(sc.toString());
	}
	//选课
	//退选
	//已选课程查询
	
}
