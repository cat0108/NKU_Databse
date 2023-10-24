package mypro;

public class StuInform {
	private int stuId;
	private int classId;
	private String name;
	private String sex;
	private String entranceDay;
	private String birthday;
	private String password;
	private String className;
	
	@Override
	public String toString() {
		return "StuInform [stuId=" + stuId + ", classId=" + classId + ", name=" + name + ", sex=" + sex
				+ ", entranceDay=" + entranceDay + ", birthday=" + birthday + ", password=" + password + "]";
	}
	public int getStuId() {
		return stuId;
	}
	public void setStuId(int stuId) {
		this.stuId = stuId;
	}
	public int getClassId() {
		return classId;
	}
	public void setClassId(int classId) {
		this.classId = classId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getEntranceDay() {
		return entranceDay;
	}
	public void setEntranceDay(String entranceDay) {
		this.entranceDay = entranceDay;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
}
