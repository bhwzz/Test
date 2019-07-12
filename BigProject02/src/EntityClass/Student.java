package EntityClass;

public class Student {
	private String id;
	private String name;
	private int classroom;
	private char gender;
	
	public Student(String id, String name, int classroom, char gender) {
		super();
		this.id = id;
		this.name = name;
		this.classroom = classroom;
		this.gender = gender;
	}

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getClassroom() {
		return classroom;
	}

	public void setClassroom(int classroom) {
		this.classroom = classroom;
	}

	public char getGender() {
		return gender;
	}

	public void setGerder(char gender) {
		this.gender = gender;
	}
//	public void print() {
//		System.out.println(id+","+name+","+classroom+","+gender);
//	}
	
	//传入一个学生信息字符串，输出一个学生对象
	public static Student toStudent(String stu) {
		String [] s=new String[4];
		s=stu.split(",");
		//System.out.println(s[0]+s[1]+Integer.parseInt(s[2])+s[3].charAt(0));
		Student student=new Student(s[0],s[1],Integer.parseInt(s[2]),s[3].charAt(0));
		return student;		
	}
	//将学生转为字符串
	public String toString() {
		return ""+this.id+","+this.name+","+this.classroom+","+this.gender;
	}
	
//	public static void main(String[] args) {
//		Student s1=new Student("0000001","王丽安",10,'女');
//		s1.print();
//		System.out.println(s1.toString());
//		Student s2=toStudent("0000001, 丽安,10,女");
//		s2.print();
//	}
}
