package EntityClass;

public class Student {
	private String id;
	private String name;
	private int classroom;
	private char gerder;
	
	public Student(String id, String name, int classroom, char gerder) {
		super();
		this.id = id;
		this.name = name;
		this.classroom = classroom;
		this.gerder = gerder;
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

	public char getGerder() {
		return gerder;
	}

	public void setGerder(char gerder) {
		this.gerder = gerder;
	}
	
	
}
