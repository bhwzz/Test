package filetool;

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