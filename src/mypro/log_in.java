package mypro;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class log_in {
	private static log_in instance;
	private log_in() {};
	public static log_in getInstance() {
		if(instance==null)
			instance=new log_in();
		return instance;
	}
	public static void main(String[] args) {
		getInstance().begin();
	}
	
	JFrame frame=new JFrame("登录");
	
	String User="请输入用户名:";
	String pwd="请您输入密码:";
	String title="学生信息管理系统";
	Font font=new Font("宋体", Font.PLAIN,25 );
	
	JLabel labelUser=new JLabel(User);
	JLabel labelPwd=new JLabel(pwd);
	JLabel labelTitle=new JLabel(title);
	JTextField UserId=new JTextField();
	JPasswordField password=new JPasswordField();
	JButton studentLogin =new JButton("学生登录");
	JButton teacherLogin=new JButton("教师登录");
	JButton adminLogin=new JButton("管理员登录");
	
	private void begin() {
		// TODO Auto-generated method stub
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//关闭不终止程序
		frame.setBounds(100, 100, 700, 700);
		
		labelUser.setFont(font);
		labelPwd.setFont(font);
		labelTitle.setFont(font);
		UserId.setFont(font);
		password.setFont(font);
		
		
		labelUser.setBounds(100, 250, 200, 30);
		labelPwd.setBounds(100, 300, 200, 30);
		labelTitle.setBounds(250,50,200,30);
		UserId.setBounds(300, 250, 250, 30);
		password.setBounds(300, 300, 250, 30);
		studentLogin.setBounds(100, 350, 100, 30);
		teacherLogin.setBounds(250, 350, 100, 30);
		adminLogin.setBounds(400, 350,100 , 30);
		
		frame.add(labelUser);
		frame.add(labelPwd);
		frame.add(labelTitle);
		frame.add(UserId);
		frame.add(password);
		frame.add(studentLogin);
		frame.add(teacherLogin);
		frame.add(adminLogin);
		
		//添加监听器
		ActionListener listener=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//判断密码是否正确
				String realPwd=MyConnect.getInstance().verifyPwdS(Integer.parseInt(UserId.getText()));
				String inputPwd=String.valueOf(password.getPassword());
				System.out.println("输入密码为:"+inputPwd);
				if(inputPwd.equals(realPwd))
					try {
						studentView stuview = new studentView();
						stuview.begin();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				else{
					//弹出框警告密码错误
					JOptionPane.showConfirmDialog(null,"账号或者密码错误！","警告",JOptionPane.DEFAULT_OPTION);
				}
				
			}
		};
		studentLogin.addActionListener(listener);
		
		ActionListener teaListener=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				//判断密码是否正确
				String realPwd=MyConnect.getInstance().verifyPwdT(Integer.parseInt(UserId.getText()));
				String inputPwd=String.valueOf(password.getPassword());
				System.out.println("输入密码为:"+inputPwd);
				if(inputPwd.equals(realPwd))
					try {
						TeacherView tView=new TeacherView();
						tView.begin();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				else{
					//弹出框警告密码错误
					JOptionPane.showConfirmDialog(null,"账号或者密码错误！","警告",JOptionPane.DEFAULT_OPTION);
				}
			}
		};
	teacherLogin.addActionListener(teaListener);
	ActionListener actionListener1=new ActionListener() {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			//判断密码是否正确
			String realPwd=MyConnect.getInstance().verifyPwdA(Integer.parseInt(UserId.getText()));
			String inputPwd=String.valueOf(password.getPassword());
			System.out.println("输入密码为:"+inputPwd);
			if(inputPwd.equals(realPwd))
				try {
					AdminVIew aView=new AdminVIew();
					aView.begin();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			else{
				//弹出框警告密码错误
				JOptionPane.showConfirmDialog(null,"账号或者密码错误！","警告",JOptionPane.DEFAULT_OPTION);
			}
		}
	};
	adminLogin.addActionListener(actionListener1);
		frame.setVisible(true);
	}
	
}
