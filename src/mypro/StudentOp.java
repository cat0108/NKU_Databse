package mypro;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JOptionPane;

import java.util.*;
public class StudentOp {
	private static StudentOp instance;
	private StudentOp() {};
	public static StudentOp getInstance() {
		if(instance==null)
			instance=new StudentOp();
		return instance;
	}
	private String url="jdbc:mysql://localhost:3306/school";
	private String user="root";
	private String password="cat20030624";
	//获取数据库中某个学生的学生信息并返回
	public List<StuInform> getDbDataStudent(int stuId) throws ClassNotFoundException, SQLException{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn=DriverManager.getConnection(url, user, password);
		String sql="select* from stuInform where StudentID="+stuId;
		Statement stmt=conn.createStatement();
		ResultSet result=stmt.executeQuery(sql);
		LinkedList<StuInform> list=new LinkedList<>();
		while(result.next()) {
			StuInform stuInform=new StuInform();
			stuInform.setStuId(result.getInt("StudentID"));
			stuInform.setClassName(result.getString("className"));
			stuInform.setName(result.getString("studentName"));
			stuInform.setSex(result.getString("studentSex"));
			stuInform.setBirthday(result.getString("bornedDay"));
			stuInform.setEntranceDay(result.getString("entranceDay"));
			stuInform.setPassword(result.getString("pwd"));
			list.add(stuInform);
			System.out.println(stuInform);
		}
		result.close();
		stmt.close();
		conn.close();
		conn=null;
		return list;
	}
	//更新密码操作
	public void UpdatePwd(int stuId,String newPwd) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection(url, user, password);
		//采用预编译的方式防止sql注入
		String sql = "update student SET pwd=? where studentId=?";
		// 创建 PreparedStatement 对象
        PreparedStatement statement = connection.prepareStatement(sql);
        //传入两个参数的值
        statement.setString(1, newPwd);
        statement.setInt(2, stuId);
        statement.executeUpdate();
        System.out.println("更新成功，新密码为:"+newPwd);
        
        statement.close();
        connection.close();
	}
	//展示课程列表
	public List<CourseInform> showCourse() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn=DriverManager.getConnection(url, user, password);
		String sql="select *FROM teacher NATURAL JOIN course;";
		Statement stmt=conn.createStatement();
		ResultSet result=stmt.executeQuery(sql);
		LinkedList<CourseInform> list=new LinkedList<>();
		while(result.next()) {
			CourseInform courseInform=new CourseInform();
			courseInform.setCourseID(result.getInt("courseID"));
			courseInform.setCourseName(result.getString("courseName"));
			courseInform.setTeacherID(result.getInt("teacherID"));
			courseInform.setTeacherName(result.getString("teacherName"));
			courseInform.setCourseCredit(result.getInt("courseCredit"));
			list.add(courseInform);
		}
		result.close();
		stmt.close();
		conn.close();
		conn=null;
		return list;
	}
	//显示已选的课程
	public List<Object[]> selectedCourse(int stuId) throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection(url, user, password);
		String sql="SELECT courseName, courseID,teacherName,grade FROM course NATURAL JOIN select_course NATURAL JOIN teacher WHERE StudentID="+stuId;
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
	//选课
	public void selectCourse(int courseId,int stuId)  {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection connection;
		try {
			connection = DriverManager.getConnection(url, user, password);
			//预编译防止sql注入
			String sql="insert into select_course VALUES (?,?,?);";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, stuId);
			statement.setInt(2, courseId);
			statement.setNull(3, Types.INTEGER);
			// 执行插入操作
	        int rowsInserted = statement.executeUpdate();
	        if (rowsInserted > 0) {
	            System.out.println("新记录插入成功！");
	            JOptionPane.showConfirmDialog(null,"选课成功！","提示",JOptionPane.DEFAULT_OPTION);
	        }
	        else {
	        	System.out.println("插入失败");
	        	JOptionPane.showConfirmDialog(null,"选课失败","提示",JOptionPane.DEFAULT_OPTION);
	        }
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showConfirmDialog(null,"选课失败","提示",JOptionPane.DEFAULT_OPTION);
		}

	}
	public void withdrawCourses(int stuId,int courseId) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection connection;
		try {
			connection = DriverManager.getConnection(url, user, password);
			String sql="DELETE from select_course WHERE StudentID=? AND CourseID=?;";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, stuId);
			statement.setInt(2, courseId);
			 int rowsAffected = statement.executeUpdate();

	         // 检查是否成功删除数据
	         if (rowsAffected > 0) {
	             System.out.println("数据删除成功！");
	             JOptionPane.showConfirmDialog(null,"退课成功！","提示",JOptionPane.DEFAULT_OPTION);
	         } else {
	             System.out.println("未找到匹配的数据，删除操作失败。");
	             JOptionPane.showConfirmDialog(null,"退课失败！","提示",JOptionPane.DEFAULT_OPTION);
	         }
	         connection.close();
	         statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showConfirmDialog(null,"退课失败！","提示",JOptionPane.DEFAULT_OPTION);
		}
		
	}
}
