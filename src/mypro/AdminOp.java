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
	//显示学院数据
	public List<Object[]> showCollege() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection(url, user, password);
		String sql="SELECT * from college";
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
	//修改学院院长
	public void updateMaster(int collegeId,String name) throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection(url, user, password);
		//采用预编译的方式防止sql注入
		String sql = "UPDATE college SET collegeMaster=? WHERE collegeID=? ";
		// 创建 PreparedStatement 对象
        PreparedStatement statement = connection.prepareStatement(sql);
        //传入两个参数的值
        statement.setString(1, name);
        statement.setInt(2, collegeId);
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("更新成功！");
            JOptionPane.showConfirmDialog(null,"更新成功！","提示",JOptionPane.DEFAULT_OPTION);
        }
        else {
        	System.out.println("更新失败");
        	JOptionPane.showConfirmDialog(null,"更新失败，信息错误","提示",JOptionPane.DEFAULT_OPTION);
        }
        statement.close();
        connection.close();
	}
	//添加学院
	public void addCollege(int CollegeId,String collegeName,String Master) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection(url, user, password);
		//采用预编译的方式防止sql注入
		String sql = "insert into college values (?,?,?,0)";
		// 创建 PreparedStatement 对象
        PreparedStatement statement = connection.prepareStatement(sql);
        //传入两个参数的值
        statement.setInt(1, CollegeId);
        statement.setString(2, collegeName);
        statement.setString(3, Master);
        statement.executeUpdate();
        System.out.println("更新成功");
        JOptionPane.showConfirmDialog(null,"添加成功","提示",JOptionPane.DEFAULT_OPTION);
        statement.close();
        connection.close();
	}
	//显示学生信息
	public List<Object[]> showStudent() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection(url, user, password);
		String sql="SELECT * from student";
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
	//添加学生
	public void addStu(int stuId,int ClassId,String StuName,String sex,String birth,String entranced,String pwd) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection(url, user, password);
		//采用预编译的方式防止sql注入
		String sql = "insert into student values (?,?,?,?,?,?,?)";
		// 创建 PreparedStatement 对象
        PreparedStatement statement = connection.prepareStatement(sql);
        //传入两个参数的值
        statement.setInt(1, stuId);
        statement.setInt(2, ClassId);
        statement.setString(3, StuName);
        statement.setString(4, sex);
        statement.setString(5, birth);
        statement.setString(6, entranced);
        statement.setString(7, pwd);
        statement.executeUpdate();
        System.out.println("添加成功");
        JOptionPane.showConfirmDialog(null,"添加成功","提示",JOptionPane.DEFAULT_OPTION);
        connection.close();
        statement.close();
	}
	//修改学生信息
	public void updateStu(String val,int stuId) throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection(url, user, password);
		//采用预编译的方式防止sql注入
		String sql = "UPDATE student SET pwd=? WHERE studentID=? ";
		// 创建 PreparedStatement 对象
        PreparedStatement statement = connection.prepareStatement(sql);
        //传入两个参数的值
        statement.setString(1, val);
        statement.setInt(2, stuId);
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("更新成功！");
            JOptionPane.showConfirmDialog(null,"更新成功！","提示",JOptionPane.DEFAULT_OPTION);
        }
        else {
        	System.out.println("更新失败");
        	JOptionPane.showConfirmDialog(null,"更新失败，信息错误","提示",JOptionPane.DEFAULT_OPTION);
        }
        connection.close();
        statement.close();
	}
	//删除学生信息
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

	         // 检查是否成功删除数据
	         if (rowsAffected > 0) {
	             System.out.println("数据删除成功！");
	             JOptionPane.showConfirmDialog(null,"删除成功！","提示",JOptionPane.DEFAULT_OPTION);
	         } else {
	             System.out.println("未找到匹配的数据，删除操作失败。");
	             JOptionPane.showConfirmDialog(null,"删除失败！","提示",JOptionPane.DEFAULT_OPTION);
	         }
	         connection.close();
	         statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showConfirmDialog(null,"删除失败！","提示",JOptionPane.DEFAULT_OPTION);
		}

	}
	//显示教师信息
	public List<Object[]> showTeacher() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection(url, user, password);
		String sql="SELECT * from teacher";
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
        connection.close();
        statement.close();
        return data;
	}
	//添加教师
	public void addTea(int tId,int CollegeId,String TeaName,String rank,String sex,int age,String entranced,String pwd) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection(url, user, password);
		//采用预编译的方式防止sql注入
		String sql = "insert into teacher values (?,?,?,?,?,?,?,?)";
		// 创建 PreparedStatement 对象
        PreparedStatement statement = connection.prepareStatement(sql);
        //传入两个参数的值
        statement.setInt(1, tId);
        statement.setInt(2, CollegeId);
        statement.setString(3, TeaName);
        statement.setString(4, rank);
        statement.setString(5, sex);
        statement.setInt(6, age);
        statement.setString(7, entranced);
        statement.setString(8, pwd);
        statement.executeUpdate();
        System.out.println("添加成功");
        //执行存储过程
        sql="{CALL InsertTeacher(?,?)}";
        statement=connection.prepareStatement(sql);
        statement.setInt(1, tId);
        statement.setInt(2, CollegeId);
        statement.execute();//执行存储过程
        
        JOptionPane.showConfirmDialog(null,"添加成功","提示",JOptionPane.DEFAULT_OPTION);
        connection.close();
        statement.close();
	}
	//修改教师密码
	public void updateTea(String val,int stuId) throws Exception{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection(url, user, password);
		//采用预编译的方式防止sql注入
		String sql = "UPDATE teacher SET pwd=? WHERE teacherID=? ";
		// 创建 PreparedStatement 对象
        PreparedStatement statement = connection.prepareStatement(sql);
        //传入两个参数的值
        statement.setString(1, val);
        statement.setInt(2, stuId);
        int rowsInserted = statement.executeUpdate();
        if (rowsInserted > 0) {
            System.out.println("更新成功！");
            JOptionPane.showConfirmDialog(null,"更新成功！","提示",JOptionPane.DEFAULT_OPTION);
        }
        else {
        	System.out.println("更新失败");
        	JOptionPane.showConfirmDialog(null,"更新失败，信息错误","提示",JOptionPane.DEFAULT_OPTION);
        }
        connection.close();
        statement.close();
	}
	//删除教师信息
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
			//需要删除课程表中教师的数据,更改默认提交模式
			connection.setAutoCommit(false);
			String outsql="Delete from course where teacherID=?";
			PreparedStatement statement = connection.prepareStatement(outsql);
			statement.setInt(1, TeaId);
			statement.executeUpdate();
			//删除本表中的数据
			String sql="DELETE from Teacher WHERE teacherID=? ";
			statement = connection.prepareStatement(sql);
			statement.setInt(1, TeaId);
			 int rowsAffected = statement.executeUpdate();
			 connection.commit();//提交事务
	         // 检查是否成功删除数据
	         if (rowsAffected > 0) {
	             System.out.println("数据删除成功！");
	             JOptionPane.showConfirmDialog(null,"删除成功！","提示",JOptionPane.DEFAULT_OPTION);
	         } else {
	             System.out.println("未找到匹配的数据，删除操作失败。");
	             JOptionPane.showConfirmDialog(null,"删除失败！","提示",JOptionPane.DEFAULT_OPTION);
	         }
	         connection.close();
	         statement.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				connection.rollback();//事务回滚
				connection.close();//注意要关闭连接
				JOptionPane.showConfirmDialog(null,"删除失败！","提示",JOptionPane.DEFAULT_OPTION);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	//添加班级信息
	public void addClass(int ClassId,int CollegeId,String ClassName,int classBoss) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection(url, user, password);
		//采用预编译的方式防止sql注入
		String sql = "insert into class values (?,?,?,?,0)";
		// 创建 PreparedStatement 对象
        PreparedStatement statement = connection.prepareStatement(sql);
        //传入两个参数的值
        statement.setInt(1, ClassId);
        statement.setInt(2, CollegeId);
        statement.setString(3, ClassName);
        statement.setInt(4, classBoss);
        statement.executeUpdate();
        System.out.println("添加成功");
        JOptionPane.showConfirmDialog(null,"添加成功","提示",JOptionPane.DEFAULT_OPTION);
        connection.close();
        statement.close();
	}
	//添加课程
	public void addCourse(int courseId,int teaId,String CourseName,int credit) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection(url, user, password);
		//采用预编译的方式防止sql注入
		String sql = "insert into course values (?,?,?,?)";
		// 创建 PreparedStatement 对象
        PreparedStatement statement = connection.prepareStatement(sql);
        //传入两个参数的值
        statement.setInt(1, courseId);
        statement.setInt(2, teaId);
        statement.setString(3, CourseName);
        statement.setInt(4, credit);
        statement.executeUpdate();
        System.out.println("添加成功");
        JOptionPane.showConfirmDialog(null,"添加成功","提示",JOptionPane.DEFAULT_OPTION);
        connection.close();
        statement.close();
	}
	//查看课程
	public List<Object[]> showCourse() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection(url, user, password);
		String sql="SELECT * from course";
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
	//查看班级
	public List<Object[]> showClass() throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection=DriverManager.getConnection(url, user, password);
		String sql="SELECT * from class";
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
}
