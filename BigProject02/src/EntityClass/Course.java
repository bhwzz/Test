package EntityClass;

public class Course {
	private String course_id;
	private String course_name;
	private int num;//课程容量
	private int left_num;//剩余容量
	private int stu_num;//已选人数
	
	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Course(String course_id, String course_name, int num, int left_num, int stu_num) {
		super();
		this.course_id = course_id;
		this.course_name = course_name;
		this.num = num;
		this.left_num = left_num;
		this.stu_num = stu_num;
	}
	
	public String getCourse_id() {
		return course_id;
	}
	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}
	public String getCourse_name() {
		return course_name;
	}
	public void setCourse_name(String course_name) {
		this.course_name = course_name;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public int getLeft_num() {
		return left_num;
	}
	public void setLeft_num(int left_num) {
		this.left_num = left_num;
	}
	public int getStu_num() {
		return stu_num;
	}
	public void setStu_num(int stu_num) {
		this.stu_num = stu_num;
	}
	
	//添加课程(要添加的数量)数据的读写要加锁
	//删除课程
	
}
