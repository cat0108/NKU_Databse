package mypro;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class AdminOp {
	private static AdminOp instance;
	private AdminOp() {};
	public static AdminOp getInstance() {
		if(instance==null)
			instance=new AdminOp();
		return instance;
	}
	
	private String url="jdbc:mysql://localhost:3306/school";
	private String user="root";
	private String password="cat20030624";
	//��ʾѧԺ����
	public List<Object[]> showCollege() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection(url, user, password);
		String sql="SELECT * from college";
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
	//�޸�ѧԺԺ��
	public void updateMaster(int collegeId,String name) throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection(url, user, password);
		//����Ԥ����ķ�ʽ��ֹsqlע��
		String sql = "UPDATE college SET collegeMaster=? WHERE collegeID=? ";
		// ���� PreparedStatement ����
        PreparedStatement statement = connection.prepareStatement(sql);
        //��������������ֵ
        statement.setString(1, name);
        statement.setInt(2, collegeId);
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
	//���ѧԺ
	public void addCollege(int CollegeId,String collegeName,String Master) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection(url, user, password);
		//����Ԥ����ķ�ʽ��ֹsqlע��
		String sql = "insert into college values (?,?,?,0)";
		// ���� PreparedStatement ����
        PreparedStatement statement = connection.prepareStatement(sql);
        //��������������ֵ
        statement.setInt(1, CollegeId);
        statement.setString(2, collegeName);
        statement.setString(3, Master);
        statement.executeUpdate();
        System.out.println("���³ɹ�");
        JOptionPane.showConfirmDialog(null,"��ӳɹ�","��ʾ",JOptionPane.DEFAULT_OPTION);
        statement.close();
        connection.close();
	}
	//��ʾѧ����Ϣ
	public List<Object[]> showStudent() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection(url, user, password);
		String sql="SELECT * from student";
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
	//���ѧ��
	public void addStu(int stuId,int ClassId,String StuName,String sex,String birth,String entranced,String pwd) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection(url, user, password);
		//����Ԥ����ķ�ʽ��ֹsqlע��
		String sql = "insert into student values (?,?,?,?,?,?,?)";
		// ���� PreparedStatement ����
        PreparedStatement statement = connection.prepareStatement(sql);
        //��������������ֵ
        statement.setInt(1, stuId);
        statement.setInt(2, ClassId);
        statement.setString(3, StuName);
        statement.setString(4, sex);
        statement.setString(5, birth);
        statement.setString(6, entranced);
        statement.setString(7, pwd);
        statement.executeUpdate();
        System.out.println("��ӳɹ�");
        JOptionPane.showConfirmDialog(null,"��ӳɹ�","��ʾ",JOptionPane.DEFAULT_OPTION);
        connection.close();
        statement.close();
	}
	//�޸�ѧ����Ϣ
	public void updateStu(String val,int stuId) throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection(url, user, password);
		//����Ԥ����ķ�ʽ��ֹsqlע��
		String sql = "UPDATE student SET pwd=? WHERE studentID=? ";
		// ���� PreparedStatement ����
        PreparedStatement statement = connection.prepareStatement(sql);
        //��������������ֵ
        statement.setString(1, val);
        statement.setInt(2, stuId);
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("���³ɹ���");
            JOptionPane.showConfirmDialog(null,"���³ɹ���","��ʾ",JOptionPane.DEFAULT_OPTION);
        }
        else {
        	System.out.println("����ʧ��");
        	JOptionPane.showConfirmDialog(null,"����ʧ�ܣ���Ϣ����","��ʾ",JOptionPane.DEFAULT_OPTION);
        }
        connection.close();
        statement.close();
	}
	//ɾ��ѧ����Ϣ
	public void deleteStu(int stuId) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection connection;
		try {
			connection = DriverManager.getConnection(url, user, password);
			String sql="DELETE from student WHERE StudentID=? ";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setInt(1, stuId);
			 int rowsAffected = statement.executeUpdate();

	         // ����Ƿ�ɹ�ɾ������
	         if (rowsAffected > 0) {
	             System.out.println("����ɾ���ɹ���");
	             JOptionPane.showConfirmDialog(null,"ɾ���ɹ���","��ʾ",JOptionPane.DEFAULT_OPTION);
	         } else {
	             System.out.println("δ�ҵ�ƥ������ݣ�ɾ������ʧ�ܡ�");
	             JOptionPane.showConfirmDialog(null,"ɾ��ʧ�ܣ�","��ʾ",JOptionPane.DEFAULT_OPTION);
	         }
	         connection.close();
	         statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showConfirmDialog(null,"ɾ��ʧ�ܣ�","��ʾ",JOptionPane.DEFAULT_OPTION);
		}

	}
	//��ʾ��ʦ��Ϣ
	public List<Object[]> showTeacher() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection(url, user, password);
		String sql="SELECT * from teacher";
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
        connection.close();
        statement.close();
        return data;
	}
	//��ӽ�ʦ
	public void addTea(int tId,int CollegeId,String TeaName,String rank,String sex,int age,String entranced,String pwd) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection(url, user, password);
		//����Ԥ����ķ�ʽ��ֹsqlע��
		String sql = "insert into teacher values (?,?,?,?,?,?,?,?)";
		// ���� PreparedStatement ����
        PreparedStatement statement = connection.prepareStatement(sql);
        //��������������ֵ
        statement.setInt(1, tId);
        statement.setInt(2, CollegeId);
        statement.setString(3, TeaName);
        statement.setString(4, rank);
        statement.setString(5, sex);
        statement.setInt(6, age);
        statement.setString(7, entranced);
        statement.setString(8, pwd);
        statement.executeUpdate();
        System.out.println("��ӳɹ�");
        //ִ�д洢����
        sql="{CALL InsertTeacher(?,?)}";
        statement=connection.prepareStatement(sql);
        statement.setInt(1, tId);
        statement.setInt(2, CollegeId);
        statement.execute();//ִ�д洢����
        
        JOptionPane.showConfirmDialog(null,"��ӳɹ�","��ʾ",JOptionPane.DEFAULT_OPTION);
        connection.close();
        statement.close();
	}
	//�޸Ľ�ʦ����
	public void updateTea(String val,int stuId) throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection(url, user, password);
		//����Ԥ����ķ�ʽ��ֹsqlע��
		String sql = "UPDATE teacher SET pwd=? WHERE teacherID=? ";
		// ���� PreparedStatement ����
        PreparedStatement statement = connection.prepareStatement(sql);
        //��������������ֵ
        statement.setString(1, val);
        statement.setInt(2, stuId);
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("���³ɹ���");
            JOptionPane.showConfirmDialog(null,"���³ɹ���","��ʾ",JOptionPane.DEFAULT_OPTION);
        }
        else {
        	System.out.println("����ʧ��");
        	JOptionPane.showConfirmDialog(null,"����ʧ�ܣ���Ϣ����","��ʾ",JOptionPane.DEFAULT_OPTION);
        }
        connection.close();
        statement.close();
	}
	//ɾ����ʦ��Ϣ
	public void deleteTea(int TeaId) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection connection = null;
		try {
			connection = DriverManager.getConnection(url, user, password);
			//��Ҫɾ���γ̱��н�ʦ������,����Ĭ���ύģʽ
			connection.setAutoCommit(false);
			String outsql="Delete from course where teacherID=?";
			PreparedStatement statement = connection.prepareStatement(outsql);
			statement.setInt(1, TeaId);
			statement.executeUpdate();
			//ɾ�������е�����
			String sql="DELETE from Teacher WHERE teacherID=? ";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, TeaId);
			 int rowsAffected = statement.executeUpdate();
			 connection.commit();//�ύ����
	         // ����Ƿ�ɹ�ɾ������
	         if (rowsAffected > 0) {
	             System.out.println("����ɾ���ɹ���");
	             JOptionPane.showConfirmDialog(null,"ɾ���ɹ���","��ʾ",JOptionPane.DEFAULT_OPTION);
	         } else {
	             System.out.println("δ�ҵ�ƥ������ݣ�ɾ������ʧ�ܡ�");
	             JOptionPane.showConfirmDialog(null,"ɾ��ʧ�ܣ�","��ʾ",JOptionPane.DEFAULT_OPTION);
	         }
	         connection.close();
	         statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				connection.rollback();//����ع�
				connection.close();//ע��Ҫ�ر�����
				JOptionPane.showConfirmDialog(null,"ɾ��ʧ�ܣ�","��ʾ",JOptionPane.DEFAULT_OPTION);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	//��Ӱ༶��Ϣ
	public void addClass(int ClassId,int CollegeId,String ClassName,int classBoss) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection(url, user, password);
		//����Ԥ����ķ�ʽ��ֹsqlע��
		String sql = "insert into class values (?,?,?,?,0)";
		// ���� PreparedStatement ����
        PreparedStatement statement = connection.prepareStatement(sql);
        //��������������ֵ
        statement.setInt(1, ClassId);
        statement.setInt(2, CollegeId);
        statement.setString(3, ClassName);
        statement.setInt(4, classBoss);
        statement.executeUpdate();
        System.out.println("��ӳɹ�");
        JOptionPane.showConfirmDialog(null,"��ӳɹ�","��ʾ",JOptionPane.DEFAULT_OPTION);
        connection.close();
        statement.close();
	}
	//��ӿγ�
	public void addCourse(int courseId,int teaId,String CourseName,int credit) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection(url, user, password);
		//����Ԥ����ķ�ʽ��ֹsqlע��
		String sql = "insert into course values (?,?,?,?)";
		// ���� PreparedStatement ����
        PreparedStatement statement = connection.prepareStatement(sql);
        //��������������ֵ
        statement.setInt(1, courseId);
        statement.setInt(2, teaId);
        statement.setString(3, CourseName);
        statement.setInt(4, credit);
        statement.executeUpdate();
        System.out.println("��ӳɹ�");
        JOptionPane.showConfirmDialog(null,"��ӳɹ�","��ʾ",JOptionPane.DEFAULT_OPTION);
        connection.close();
        statement.close();
	}
	//�鿴�γ�
	public List<Object[]> showCourse() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection(url, user, password);
		String sql="SELECT * from course";
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
	//�鿴�༶
	public List<Object[]> showClass() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection(url, user, password);
		String sql="SELECT * from class";
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
}
