package mypro;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.protocol.Resultset;
//判断输入密码是否正确
public class MyConnect {
	private static MyConnect instance;
	private MyConnect() {};
	public static MyConnect getInstance() {
		if(instance==null)
			instance=new MyConnect();
		return instance;
	}
	//验证登录密码
	 public String verifyPwdS(int id) {
		 String url="jdbc:mysql://localhost:3306/school";
		 String user="root";
		 String password="cat20030624";
		 Connection connection=null;
		 Statement statement=null;
		 ResultSet result=null;
		 String pwd=null;
		 try {
			Class.forName("com.mysql.cj.jdbc.Driver");//加载数据库驱动
			System.out.println("加载驱动成功");
			connection=DriverManager.getConnection(url, user, password);//建立连接
			System.out.println("建立连接成功");
			String sql="select pwd from student WHERE StudentId="+id;
			statement=connection.createStatement();//发送sql语句
			//获取数据
			result=statement.executeQuery(sql);
			while(result.next()) {
				pwd=result.getString("pwd");
				System.out.println("password="+pwd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//释放资源
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
			connection=null;//手动设置为null有利于更早回收对象
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
			Class.forName("com.mysql.cj.jdbc.Driver");//加载数据库驱动
			System.out.println("加载驱动成功");
			connection=DriverManager.getConnection(url, user, password);//建立连接
			System.out.println("建立连接成功");
			String sql="select pwd from teacher WHERE teacherId="+id;
			statement=connection.createStatement();//发送sql语句
			//获取数据
			result=statement.executeQuery(sql);
			while(result.next()) {
				pwd=result.getString("pwd");
				System.out.println("password="+pwd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//释放资源
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
			connection=null;//手动设置为null有利于更早回收对象
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
			Class.forName("com.mysql.cj.jdbc.Driver");//加载数据库驱动
			System.out.println("加载驱动成功");
			connection=DriverManager.getConnection(url, user, password);//建立连接
			System.out.println("建立连接成功");
			String sql="select pwd from admin WHERE userID="+id;
			statement=connection.createStatement();//发送sql语句
			//获取数据
			result=statement.executeQuery(sql);
			while(result.next()) {
				pwd=result.getString("pwd");
				System.out.println("password="+pwd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			//释放资源
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
			connection=null;//手动设置为null有利于更早回收对象
		}
		return pwd;
	 }
}
