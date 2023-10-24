package mypro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class TeacherOp {
	private static TeacherOp instance;
	private TeacherOp() {};
	public static TeacherOp getInstance() {
		if(instance==null)
			instance=new TeacherOp();
		return instance;
	}
	private String url="jdbc:mysql://localhost:3306/school";
	private String user="root";
	private String password="cat20030624";
	//显示个人信息
	public List<Object[]> teacherInform(int teacherId) throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection(url, user, password);
		String sql="select teacherID,teacherName,Collegename,ranks,teacherSex,teacherAge,pwd FROM teacher NATURAL JOIN college WHERE teacherID="+teacherId;
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		List<Object[]> data = new ArrayList<>();//二维数组存储数据
        while (resultSet.next()) {
            Object[] row = new Object[resultSet.getMetaData().getColumnCount()];//获取列数
            for (int i = 1; i <= row.length; i++) {
                row[i - 1] = resultSet.getObject(i);
            }
            data.add(row);
        }
        resultSet.close();
        statement.close();
        connection.close();
        return data;
	}
	//更新密码操作
		public void UpdatePwd(int teacherId,String newPwd) throws Exception {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection(url, user, password);
			//采用预编译的方式防止sql注入
			String sql = "update teacher SET pwd=? where teacherId=?";
			// 创建 PreparedStatement 对象
	        PreparedStatement statement = connection.prepareStatement(sql);
	        //传入两个参数的值
	        statement.setString(1, newPwd);
	        statement.setInt(2, teacherId);
	        statement.executeUpdate();
	        System.out.println("更新成功，新密码为:"+newPwd);
	        
	        statement.close();
	        connection.close();
		}
	//查看开课信息
		public List<Object[]> teachCourse(int teacherId) throws Exception{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection(url, user, password);
			String sql="select teacherName,CourseID,courseName,courseCredit FROM course NATURAL JOIN teacher WHERE teacherID="+teacherId;
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			List<Object[]> data = new ArrayList<>();//二维数组存储数据
	        while (resultSet.next()) {
	            Object[] row = new Object[resultSet.getMetaData().getColumnCount()];//获取列数
	            for (int i = 1; i <= row.length; i++) {
	                row[i - 1] = resultSet.getObject(i);
	            }
	            data.add(row);
	        }
	        resultSet.close();
	        statement.close();
	        connection.close();
	        return data;
		}
		//查看选课学生
		public List<Object[]> courseStudent(int courseId) throws Exception{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection(url, user, password);
			String sql="SELECT StudentID,studentName,CourseID,courseName,grade FROM student NATURAL JOIN course NATURAL JOIN select_course WHERE CourseID="+courseId;
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			List<Object[]> data = new ArrayList<>();//二维数组存储数据
	        while (resultSet.next()) {
	            Object[] row = new Object[resultSet.getMetaData().getColumnCount()];//获取列数
	            for (int i = 1; i <= row.length; i++) {
	                row[i - 1] = resultSet.getObject(i);
	            }
	            data.add(row);
	        }
	        resultSet.close();
	        statement.close();
	        connection.close();
	        return data;
		}
		//更改成绩
		public void updateGrade(int studentId,int CourseId,int grade) throws Exception{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection(url, user, password);
			//采用预编译的方式防止sql注入
			String sql = "UPDATE select_course SET grade=? WHERE StudentID=? AND CourseID=?";
			// 创建 PreparedStatement 对象
	        PreparedStatement statement = connection.prepareStatement(sql);
	        //传入两个参数的值
	        statement.setInt(1, grade);
	        statement.setInt(2, studentId);
	        statement.setInt(3, CourseId);
	        int rowsInserted = statement.executeUpdate();
	        if (rowsInserted > 0) {
	            System.out.println("更新成功！");
	            JOptionPane.showConfirmDialog(null,"更新成功！","提示",JOptionPane.DEFAULT_OPTION);
	        }
	        else {
	        	System.out.println("插入失败");
	        	JOptionPane.showConfirmDialog(null,"更新失败，信息错误","提示",JOptionPane.DEFAULT_OPTION);
	        }
	        statement.close();
	        connection.close();
		}
}
