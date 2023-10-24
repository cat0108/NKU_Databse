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
	//��ʾ������Ϣ
	public List<Object[]> teacherInform(int teacherId) throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection(url, user, password);
		String sql="select teacherID,teacherName,Collegename,ranks,teacherSex,teacherAge,pwd FROM teacher NATURAL JOIN college WHERE teacherID="+teacherId;
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
	//�����������
		public void UpdatePwd(int teacherId,String newPwd) throws Exception {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection(url, user, password);
			//����Ԥ����ķ�ʽ��ֹsqlע��
			String sql = "update teacher SET pwd=? where teacherId=?";
			// ���� PreparedStatement ����
	        PreparedStatement statement = connection.prepareStatement(sql);
	        //��������������ֵ
	        statement.setString(1, newPwd);
	        statement.setInt(2, teacherId);
	        statement.executeUpdate();
	        System.out.println("���³ɹ���������Ϊ:"+newPwd);
	        
	        statement.close();
	        connection.close();
		}
	//�鿴������Ϣ
		public List<Object[]> teachCourse(int teacherId) throws Exception{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection(url, user, password);
			String sql="select teacherName,CourseID,courseName,courseCredit FROM course NATURAL JOIN teacher WHERE teacherID="+teacherId;
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
		//�鿴ѡ��ѧ��
		public List<Object[]> courseStudent(int courseId) throws Exception{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection(url, user, password);
			String sql="SELECT StudentID,studentName,CourseID,courseName,grade FROM student NATURAL JOIN course NATURAL JOIN select_course WHERE CourseID="+courseId;
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
		//���ĳɼ�
		public void updateGrade(int studentId,int CourseId,int grade) throws Exception{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection(url, user, password);
			//����Ԥ����ķ�ʽ��ֹsqlע��
			String sql = "UPDATE select_course SET grade=? WHERE StudentID=? AND CourseID=?";
			// ���� PreparedStatement ����
	        PreparedStatement statement = connection.prepareStatement(sql);
	        //��������������ֵ
	        statement.setInt(1, grade);
	        statement.setInt(2, studentId);
	        statement.setInt(3, CourseId);
	        int rowsInserted = statement.executeUpdate();
	        if (rowsInserted > 0) {
	            System.out.println("���³ɹ���");
	            JOptionPane.showConfirmDialog(null,"���³ɹ���","��ʾ",JOptionPane.DEFAULT_OPTION);
	        }
	        else {
	        	System.out.println("����ʧ��");
	        	JOptionPane.showConfirmDialog(null,"����ʧ�ܣ���Ϣ����","��ʾ",JOptionPane.DEFAULT_OPTION);
	        }
	        statement.close();
	        connection.close();
		}
}
