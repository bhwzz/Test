package quality;
import EntityClass.*;
public class StudentQuality {
	public Student student;
	public int flag;
	public StudentQuality() {
		// TODO Auto-generated constructor stub
		this.flag = 0;
	}
	public StudentQuality(Student s) {
		// TODO Auto-generated constructor stub
		this.student = s;
		this.flag = 0;
	}
	public Student getStudent() {
		return student;
	}
}
