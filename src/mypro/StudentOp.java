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
	//��ȡ���ݿ���ĳ��ѧ����ѧ����Ϣ������
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
	//�����������
	public void UpdatePwd(int stuId,String newPwd) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection(url, user, password);
		//����Ԥ����ķ�ʽ��ֹsqlע��
		String sql = "update student SET pwd=? where studentId=?";
		// ���� PreparedStatement ����
        PreparedStatement statement = connection.prepareStatement(sql);
        //��������������ֵ
        statement.setString(1, newPwd);
        statement.setInt(2, stuId);
        statement.executeUpdate();
        System.out.println("���³ɹ���������Ϊ:"+newPwd);
        
        statement.close();
        connection.close();
	}
	//չʾ�γ��б�
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
	//��ʾ��ѡ�Ŀγ�
	public List<Object[]> selectedCourse(int stuId) throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection(url, user, password);
		String sql="SELECT courseName, courseID,teacherName,grade FROM course NATURAL JOIN select_course NATURAL JOIN teacher WHERE StudentID="+stuId;
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		List<Object[]> data = new ArrayList<>();//��ά����洢����
        while (resultSet.next()) {
            Object[] row = new Object[resultSet.getMetaData().getColumnCount()];//��ȡ����
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
	//ѡ��
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
			//Ԥ�����ֹsqlע��
			String sql="insert into select_course VALUES (?,?,?);";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, stuId);
			statement.setInt(2, courseId);
			statement.setNull(3, Types.INTEGER);
			// ִ�в������
	        int rowsInserted = statement.executeUpdate();
	        if (rowsInserted > 0) {
	            System.out.println("�¼�¼����ɹ���");
	            JOptionPane.showConfirmDialog(null,"ѡ�γɹ���","��ʾ",JOptionPane.DEFAULT_OPTION);
	        }
	        else {
	        	System.out.println("����ʧ��");
	        	JOptionPane.showConfirmDialog(null,"ѡ��ʧ��","��ʾ",JOptionPane.DEFAULT_OPTION);
	        }
			statement.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showConfirmDialog(null,"ѡ��ʧ��","��ʾ",JOptionPane.DEFAULT_OPTION);
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

	         // ����Ƿ�ɹ�ɾ������
	         if (rowsAffected > 0) {
	             System.out.println("����ɾ���ɹ���");
	             JOptionPane.showConfirmDialog(null,"�˿γɹ���","��ʾ",JOptionPane.DEFAULT_OPTION);
	         } else {
	             System.out.println("δ�ҵ�ƥ������ݣ�ɾ������ʧ�ܡ�");
	             JOptionPane.showConfirmDialog(null,"�˿�ʧ�ܣ�","��ʾ",JOptionPane.DEFAULT_OPTION);
	         }
	         connection.close();
	         statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showConfirmDialog(null,"�˿�ʧ�ܣ�","��ʾ",JOptionPane.DEFAULT_OPTION);
		}
		
	}
}
