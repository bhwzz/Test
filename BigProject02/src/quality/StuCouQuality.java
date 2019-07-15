package quality;

import EntityClass.*;
public class StuCouQuality {

	public int flag1;
	public Stu_Course stu_Course;
	public StuCouQuality(Stu_Course s) {
		// TODO Auto-generated constructor stub
		this.flag1=0;
		this.stu_Course=s;
	}
	public void changeflag () {
		flag1=1;
	}
	public void delflag() {
		flag1=-1;
	}
}
