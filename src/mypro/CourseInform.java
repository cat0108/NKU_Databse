package mypro;

public class CourseInform {
	private int courseID;
	private int teacherID;
	private String teacherName;
	private String courseName;
	private int courseCredit;
	
	@Override
	public String toString() {
		return "CourseInform [courseID=" + courseID + ", teacherID=" + teacherID + ", teacherName=" + teacherName
				+ ", courseName=" + courseName + ", courseCredit=" + courseCredit + "]";
	}
	public int getCourseID() {
		return courseID;
	}
	public void setCourseID(int courseID) {
		this.courseID = courseID;
	}
	public int getTeacherID() {
		return teacherID;
	}
	public void setTeacherID(int teacherID) {
		this.teacherID = teacherID;
	}
	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherNaem) {
		this.teacherName = teacherNaem;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getCourseCredit() {
		return courseCredit;
	}
	public void setCourseCredit(int courseCredit) {
		this.courseCredit = courseCredit;
	}
	
}
