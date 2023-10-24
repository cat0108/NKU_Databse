package mypro;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.protocol.Resultset;
//�ж����������Ƿ���ȷ
public class MyConnect {
	private static MyConnect instance;
	private MyConnect() {};
	public static MyConnect getInstance() {
		if(instance==null)
			instance=new MyConnect();
		return instance;
	}
	//��֤��¼����
	 public String verifyPwdS(int id) {
		 String url="jdbc:mysql://localhost:3306/school";
		 String user="root";
		 String password="cat20030624";
		 Connection connection=null;
		 Statement statement=null;
		 ResultSet result=null;
		 String pwd=null;
		 try {
			Class.forName("com.mysql.cj.jdbc.Driver");//�������ݿ�����
			System.out.println("���������ɹ�");
			connection=DriverManager.getConnection(url, user, password);//��������
			System.out.println("�������ӳɹ�");
			String sql="select pwd from student WHERE StudentId="+id;
			statement=connection.createStatement();//����sql���
			//��ȡ����
			result=statement.executeQuery(sql);
			while(result.next()) {
				pwd=result.getString("pwd");
				System.out.println("password="+pwd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//�ͷ���Դ
			if(result!=null)
				try {
					result.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(statement!=null)
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(connection!=null)
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			connection=null;//�ֶ�����Ϊnull�����ڸ�����ն���
		}
		return pwd;
	 }
	 public String verifyPwdT(int id) {
		 String url="jdbc:mysql://localhost:3306/school";
		 String user="root";
		 String password="cat20030624";
		 Connection connection=null;
		 Statement statement=null;
		 ResultSet result=null;
		 String pwd=null;
		 try {
			Class.forName("com.mysql.cj.jdbc.Driver");//�������ݿ�����
			System.out.println("���������ɹ�");
			connection=DriverManager.getConnection(url, user, password);//��������
			System.out.println("�������ӳɹ�");
			String sql="select pwd from teacher WHERE teacherId="+id;
			statement=connection.createStatement();//����sql���
			//��ȡ����
			result=statement.executeQuery(sql);
			while(result.next()) {
				pwd=result.getString("pwd");
				System.out.println("password="+pwd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//�ͷ���Դ
			if(result!=null)
				try {
					result.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(statement!=null)
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(connection!=null)
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			connection=null;//�ֶ�����Ϊnull�����ڸ�����ն���
		}
		return pwd;
	 }
	 public String verifyPwdA(int id) {
		 String url="jdbc:mysql://localhost:3306/school";
		 String user="root";
		 String password="cat20030624";
		 Connection connection=null;
		 Statement statement=null;
		 ResultSet result=null;
		 String pwd=null;
		 try {
			Class.forName("com.mysql.cj.jdbc.Driver");//�������ݿ�����
			System.out.println("���������ɹ�");
			connection=DriverManager.getConnection(url, user, password);//��������
			System.out.println("�������ӳɹ�");
			String sql="select pwd from admin WHERE userID="+id;
			statement=connection.createStatement();//����sql���
			//��ȡ����
			result=statement.executeQuery(sql);
			while(result.next()) {
				pwd=result.getString("pwd");
				System.out.println("password="+pwd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//�ͷ���Դ
			if(result!=null)
				try {
					result.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(statement!=null)
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(connection!=null)
				try {
					connection.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			connection=null;//�ֶ�����Ϊnull�����ڸ�����ն���
		}
		return pwd;
	 }
}
