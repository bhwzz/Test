package filetool;

import EntityClass.Student;

public class StuQuality {
	private String id;
	private String name;
	private int classroom;
	private char gender;
	StuQuality(String id){
		this.id = id;
	}
	StuQuality(String id,String name,int classroom,char gender){
		this.id = id;
		this.name=name;
		this.classroom = classroom;
		this.gender = gender;
	}
	StuQuality(Student s){
		this.id = s.getId();
		this.name= s.getName();
		this.classroom = s.getClassroom();
		this.gender = s.getGender();
	}
	
	public String get(String name,int classroom,char gender) {
		this.name=name;
		this.classroom = classroom;
		this.gender = gender;
		String classString = String.format("%2d", classroom).replace(" ", "0");
		String string = id+","+name+","+classString+","+gender;
		return string;
	}
	public String get() {
		//String string = id+","+name+","+classroom+","+gender;
		String classString = String.format("%2d", classroom).replace(" ", "0");
		String string = id+","+name+","+classString+","+gender;
		return string;
	}
}
