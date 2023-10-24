package mypro;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class AdminVIew {
	private static AdminVIew instance;
	public AdminVIew() {};
	JFrame frame=new JFrame("管理员");
	JButton addTeacher=new JButton("修改教师信息");
	JButton addStudent=new JButton("修改学生信息");
	JButton addCourse=new JButton("修改课程信息");
	JButton addCollege=new JButton("修改学院信息");
	JButton addClass=new JButton("修改班级信息");
	JButton seeCourse=new JButton("查看所有课程");
	JButton seeClass=new JButton("查看班级信息");
	JPanel panel=new JPanel();
	//修改学院信息函数
	public void changeCollege() {
		JFrame frame=new JFrame("学院信息修改");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setBounds(100, 100, 400, 300);
		JButton showMsg=new JButton("刷新信息");
		JButton updateMsg=new JButton("修改学院信息");
		JButton increaseCollege=new JButton("新增学院");
		JPanel Cpanel=new JPanel();
		JPanel Npanel=new JPanel();
		Npanel.add(showMsg);
		Npanel.add(updateMsg);
		Npanel.add(increaseCollege);
		//用于显示学院信息
		ActionListener actionListener1=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String[] columnNames = {"学院ID", "学院名称", "院长","学院教师数"};
				List<Object[]> data;
				try {
					data = AdminOp.getInstance().showCollege();
					DefaultTableModel tableModel = new DefaultTableModel(data.toArray(new Object[0][]), columnNames);
			        
			        // 创建JTable并设置表格模型
			        JTable table = new JTable(tableModel);
			        
			        // 创建滚动窗格并将JTable放置其中
			        JScrollPane scrollPane = new JScrollPane(table);
			        scrollPane.setSize(400,150);
			        Cpanel.add(scrollPane);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
		
		showMsg.addActionListener(actionListener1);
		//用于修改学院信息
		ActionListener actionListener2=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame myframe=new JFrame("修改信息");
				myframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				myframe.getContentPane().setLayout(new BorderLayout());
				myframe.setBounds(0,0,300,300);
				JLabel label1=new JLabel("输入学院id:");
				JLabel label2=new JLabel("输入新院长姓名:");
				JTextField tf1=new JTextField();
				JTextField tf2=new JTextField();
				tf1.setPreferredSize(new Dimension(150,30));
				tf2.setPreferredSize(new Dimension(150,30));
				JPanel panelC=new JPanel();
				JButton conf=new JButton("确认修改");
				panelC.add(label1);panelC.add(tf1);panelC.add(label2);panelC.add(tf2);
				panelC.add(conf);
				ActionListener myactionListener=new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						try {
							AdminOp.getInstance().updateMaster(Integer.parseInt(tf1.getText()), tf2.getText());
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				};
				conf.addActionListener(myactionListener);
				myframe.add(panelC,BorderLayout.CENTER);
				myframe.setVisible(true);
			}
		};
		updateMsg.addActionListener(actionListener2);
		//增加学院用
		ActionListener actionListener3=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame myframe=new JFrame("修改信息");
				myframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				myframe.getContentPane().setLayout(new BorderLayout());
				myframe.setBounds(0,0,300,300);
				JLabel lb3=new JLabel("输入新学院ID");
				JLabel lb4=new JLabel("输入新学院名称");		
				JLabel lb5=new JLabel("输入新学院院长");
				JTextField tf3=new JTextField();
				JTextField tf4=new JTextField();
				JTextField tf5=new JTextField();
				tf3.setPreferredSize(new Dimension(150,30));
				tf4.setPreferredSize(new Dimension(150,30));
				tf5.setPreferredSize(new Dimension(150,30));
				JButton conf=new JButton("确认更新");
				JPanel Cpanel=new JPanel();
				Cpanel.add(lb3);Cpanel.add(tf3);Cpanel.add(lb4);
				Cpanel.add(tf4);Cpanel.add(lb5);Cpanel.add(tf5);
				Cpanel.add(conf);
				ActionListener actionListener4=new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						try {
							AdminOp.getInstance().addCollege(Integer.parseInt(tf3.getText()), tf4.getText(), tf5.getText());
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				};
				conf.addActionListener(actionListener4);
				myframe.add(Cpanel,BorderLayout.CENTER);
				myframe.setVisible(true);
			}
		};
		increaseCollege.addActionListener(actionListener3);
		frame.add(Npanel,BorderLayout.NORTH);
		frame.add(Cpanel,BorderLayout.CENTER);
		frame.setVisible(true);
	}
	//修改学生的操作
	public void changeStudent() {
		JFrame frame=new JFrame("学生信息修改");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setBounds(100, 100, 600, 400);
		JButton addStu=new JButton("添加学生");
		JButton updateMsg=new JButton("修改学生密码");
		JButton deleteStu=new JButton("删除学生");
		JButton showStu=new JButton("显示学生表");
		JPanel Cpanel=new JPanel();
		JPanel Npanel=new JPanel();
		Npanel.add(addStu);Npanel.add(updateMsg);Npanel.add(deleteStu);Npanel.add(showStu);
		frame.add(Npanel,BorderLayout.NORTH);frame.add(Cpanel,BorderLayout.CENTER);
		//用于显示学生信息
		ActionListener actionListener1=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String[] columnNames = {"学号", "所属班级", "姓名","性别","出生日期","入学时间","pwd"};
				List<Object[]> data;
				try {
					data = AdminOp.getInstance().showStudent();
					DefaultTableModel tableModel = new DefaultTableModel(data.toArray(new Object[0][]), columnNames);
			        
			        // 创建JTable并设置表格模型
			        JTable table = new JTable(tableModel);
			        
			        // 创建滚动窗格并将JTable放置其中
			        JScrollPane scrollPane = new JScrollPane(table);
			        scrollPane.setSize(600,250);
			        Cpanel.add(scrollPane);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
		showStu.addActionListener(actionListener1);
		
		//用于添加学生
		ActionListener actionListener2=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame myframe=new JFrame("添加学生");
				myframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				myframe.getContentPane().setLayout(new BorderLayout());
				myframe.setBounds(0,0,300,350);
				JLabel lb3=new JLabel("输入学生ID");
				JLabel lb4=new JLabel("输入所属班级ID");		
				JLabel lb5=new JLabel("输入学生姓名");
				JLabel lb6=new JLabel("输入性别");
				JLabel lb7=new JLabel("出生日期");
				JLabel lb8=new JLabel("入学时间");
				JLabel lb9=new JLabel("输入密码");
				JTextField tf3=new JTextField();
				JTextField tf4=new JTextField();
				JTextField tf5=new JTextField();
				JTextField tf6=new JTextField();
				JTextField tf7=new JTextField();
				JTextField tf8=new JTextField();
				JTextField tf9=new JTextField();
				tf3.setPreferredSize(new Dimension(180,30));
				tf4.setPreferredSize(new Dimension(180,30));
				tf5.setPreferredSize(new Dimension(180,30));
				tf6.setPreferredSize(new Dimension(180,30));
				tf7.setPreferredSize(new Dimension(180,30));
				tf8.setPreferredSize(new Dimension(180,30));
				tf9.setPreferredSize(new Dimension(180,30));
				JButton conf=new JButton("确认添加");
				JPanel Cpanel=new JPanel();
				Cpanel.add(lb3);Cpanel.add(tf3);Cpanel.add(lb4);Cpanel.add(tf4);Cpanel.add(lb5);Cpanel.add(tf5);
				Cpanel.add(lb6);Cpanel.add(tf6);Cpanel.add(lb7);Cpanel.add(tf7);Cpanel.add(lb8);Cpanel.add(tf8);
				Cpanel.add(lb9);Cpanel.add(tf9);
				Cpanel.add(conf);
				ActionListener actionListener=new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						try {
							AdminOp.getInstance().addStu(Integer.parseInt(tf3.getText()), Integer.parseInt(tf4.getText()), tf5.getText(), tf6.getText(), tf7.getText(), tf8.getText(), tf9.getText());
							myframe.dispose();
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				};
				conf.addActionListener(actionListener);
				myframe.add(Cpanel,BorderLayout.CENTER);
				myframe.setVisible(true);
			}
		};
		addStu.addActionListener(actionListener2);
		//用于修改学生信息
		ActionListener actionListener3=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame myframe=new JFrame("修改学生密码");
				myframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				myframe.getContentPane().setLayout(new BorderLayout());
				myframe.setBounds(0,0,300,350);
				JLabel lb3=new JLabel("输入学生ID");
				JLabel lb5=new JLabel("输入修改后的密码");
				JTextField tf3=new JTextField();
				JTextField tf5=new JTextField();
				tf3.setPreferredSize(new Dimension(180,30));
				tf5.setPreferredSize(new Dimension(180,30));
				JButton conf=new JButton("确认修改");
				JPanel Cpanel=new JPanel();
				Cpanel.add(lb3);Cpanel.add(tf3);Cpanel.add(lb5);Cpanel.add(tf5);
				Cpanel.add(conf);
				ActionListener actionListener=new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						try {
							AdminOp.getInstance().updateStu( tf5.getText(),Integer.parseInt(tf3.getText()));
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				};
				conf.addActionListener(actionListener);
				myframe.add(Cpanel,BorderLayout.CENTER);
				myframe.setVisible(true);
			}
		};
		
		updateMsg.addActionListener(actionListener3);
		
		//用于删除学生
		ActionListener actListener4=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame myframe=new JFrame("删除学生");
				myframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				myframe.getContentPane().setLayout(new BorderLayout());
				myframe.setBounds(0,0,300,350);
				JLabel lb3=new JLabel("输入学生ID");		
				JTextField tf3=new JTextField();
				tf3.setPreferredSize(new Dimension(180,30));
				JButton conf=new JButton("确认删除");
				JPanel Cpanel=new JPanel();
				Cpanel.add(lb3);Cpanel.add(tf3);
				Cpanel.add(conf);
				ActionListener actionListener=new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						AdminOp.getInstance().deleteStu(Integer.parseInt(tf3.getText()));
					}
				};
				conf.addActionListener(actionListener);
				myframe.add(Cpanel,BorderLayout.CENTER);
				myframe.setVisible(true);
			}
		};
		deleteStu.addActionListener(actListener4);
		frame.setVisible(true);
	}
	//用于修改教师信息
	public void changeTeacher() {
		JFrame frame=new JFrame("教师信息修改");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setBounds(100, 100, 600, 400);
		JButton addMsg=new JButton("添加教师");
		JButton updateMsg=new JButton("修改教师密码");
		JButton deleteMsg=new JButton("删除教师");
		JButton showMsg=new JButton("显示教师表");
		JPanel Cpanel=new JPanel();
		JPanel Npanel=new JPanel();
		Npanel.add(addMsg);Npanel.add(updateMsg);Npanel.add(deleteMsg);Npanel.add(showMsg);
		frame.add(Npanel,BorderLayout.NORTH);frame.add(Cpanel,BorderLayout.CENTER);
		
		//用于显示表格
		ActionListener actionListener1=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String[] columnNames = {"教师工号", "所属学院", "姓名","职称","性别","年龄","入职时间","pwd"};
				List<Object[]> data;
				try {
					data = AdminOp.getInstance().showTeacher();
					DefaultTableModel tableModel = new DefaultTableModel(data.toArray(new Object[0][]), columnNames);
			        
			        // 创建JTable并设置表格模型
			        JTable table = new JTable(tableModel);
			        
			        // 创建滚动窗格并将JTable放置其中
			        JScrollPane scrollPane = new JScrollPane(table);
			        scrollPane.setSize(600,250);
			        Cpanel.add(scrollPane);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
		showMsg.addActionListener(actionListener1);
		//用于添加教师
		ActionListener actionListener2=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame myframe=new JFrame("添加教师");
				myframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				myframe.getContentPane().setLayout(new BorderLayout());
				myframe.setBounds(0,0,300,350);
				JLabel lb3=new JLabel("输入教职工ID");
				JLabel lb4=new JLabel("输入所属学院");		
				JLabel lb5=new JLabel("输入姓名");
				JLabel lb6=new JLabel("请输入职称");
				JLabel lb7=new JLabel("请输入性别");
				JLabel lb8=new JLabel("请输入年龄");
				JLabel lb9=new JLabel("请输入入职时间");
				JLabel lb10=new JLabel("请输入密码");
				JTextField tf3=new JTextField();
				JTextField tf4=new JTextField();
				JTextField tf5=new JTextField();
				JTextField tf6=new JTextField();
				JTextField tf7=new JTextField();
				JTextField tf8=new JTextField();
				JTextField tf9=new JTextField();
				JTextField tf10=new JTextField();
				tf3.setPreferredSize(new Dimension(190,30));
				tf4.setPreferredSize(new Dimension(190,30));
				tf5.setPreferredSize(new Dimension(190,30));
				tf6.setPreferredSize(new Dimension(190,30));
				tf7.setPreferredSize(new Dimension(190,30));
				tf8.setPreferredSize(new Dimension(190,30));
				tf9.setPreferredSize(new Dimension(190,30));
				tf10.setPreferredSize(new Dimension(190,30));
				JButton conf=new JButton("确认添加");
				JPanel Cpanel=new JPanel();
				Cpanel.add(lb3);Cpanel.add(tf3);Cpanel.add(lb4);Cpanel.add(tf4);Cpanel.add(lb5);Cpanel.add(tf5);
				Cpanel.add(lb6);Cpanel.add(tf6);Cpanel.add(lb7);Cpanel.add(tf7);Cpanel.add(lb8);Cpanel.add(tf8);
				Cpanel.add(lb9);Cpanel.add(tf9);Cpanel.add(lb10);Cpanel.add(tf10);
				Cpanel.add(conf);
				ActionListener actionListener=new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						try {
							AdminOp.getInstance().addTea(Integer.parseInt(tf3.getText()), Integer.parseInt(tf4.getText()), tf5.getText(), tf6.getText(), tf7.getText(), Integer.parseInt(tf8.getText()), tf9.getText(),tf10.getText());
							myframe.dispose();
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showConfirmDialog(null,"添加失败","提示",JOptionPane.DEFAULT_OPTION);
							e1.printStackTrace();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							JOptionPane.showConfirmDialog(null,"添加失败","提示",JOptionPane.DEFAULT_OPTION);
							e1.printStackTrace();
						}
					}
				};
				conf.addActionListener(actionListener);
				myframe.add(Cpanel,BorderLayout.CENTER);
				myframe.setVisible(true);
			}
		};
		addMsg.addActionListener(actionListener2);
		//用于修改密码
		ActionListener actionListener3=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame myframe=new JFrame("修改教师密码");
				myframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				myframe.getContentPane().setLayout(new BorderLayout());
				myframe.setBounds(0,0,300,350);
				JLabel lb3=new JLabel("输入教师ID");
				JLabel lb5=new JLabel("输入修改后的密码");
				JTextField tf3=new JTextField();
				JTextField tf5=new JTextField();
				tf3.setPreferredSize(new Dimension(180,30));
				tf5.setPreferredSize(new Dimension(180,30));
				JButton conf=new JButton("确认修改");
				JPanel Cpanel=new JPanel();
				Cpanel.add(lb3);Cpanel.add(tf3);Cpanel.add(lb5);Cpanel.add(tf5);
				Cpanel.add(conf);
				ActionListener actionListener=new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						try {
							AdminOp.getInstance().updateTea( tf5.getText(),Integer.parseInt(tf3.getText()));
						} catch (NumberFormatException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				};
				conf.addActionListener(actionListener);
				myframe.add(Cpanel,BorderLayout.CENTER);
				myframe.setVisible(true);
			}
		};
		updateMsg.addActionListener(actionListener3);
		ActionListener actionListener4=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				// TODO Auto-generated method stub
				JFrame myframe=new JFrame("删除教师信息");
				myframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				myframe.getContentPane().setLayout(new BorderLayout());
				myframe.setBounds(0,0,300,350);
				JLabel lb3=new JLabel("输入教师ID");		
				JTextField tf3=new JTextField();
				tf3.setPreferredSize(new Dimension(180,30));
				JButton conf=new JButton("确认删除");
				JPanel Cpanel=new JPanel();
				Cpanel.add(lb3);Cpanel.add(tf3);
				Cpanel.add(conf);
				ActionListener actionListener=new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent e) {
						// TODO Auto-generated method stub
						AdminOp.getInstance().deleteTea(Integer.parseInt(tf3.getText()));
						myframe.dispose();
					}
				};
				conf.addActionListener(actionListener);
				myframe.add(Cpanel,BorderLayout.CENTER);
				myframe.setVisible(true);
			}
		};
		deleteMsg.addActionListener(actionListener4);
		frame.setVisible(true);
	}
	//改变班级
	public void changeClass() {
		JFrame myframe=new JFrame("添加班级");
		myframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		myframe.getContentPane().setLayout(new BorderLayout());
		myframe.setBounds(0,0,300,350);
		JLabel lb3=new JLabel("输入班级编号");
		JLabel lb4=new JLabel("输入所属学院");		
		JLabel lb5=new JLabel("输入班级名称");
		JLabel lb6=new JLabel("请输入班长");
		JTextField tf3=new JTextField();
		JTextField tf4=new JTextField();
		JTextField tf5=new JTextField();
		JTextField tf6=new JTextField();
		tf3.setPreferredSize(new Dimension(190,30));
		tf4.setPreferredSize(new Dimension(190,30));
		tf5.setPreferredSize(new Dimension(190,30));
		tf6.setPreferredSize(new Dimension(190,30));
		JButton conf=new JButton("确认添加");
		JPanel Cpanel=new JPanel();
		Cpanel.add(lb3);Cpanel.add(tf3);Cpanel.add(lb4);Cpanel.add(tf4);Cpanel.add(lb5);Cpanel.add(tf5);
		Cpanel.add(lb6);Cpanel.add(tf6);
		Cpanel.add(conf);
		ActionListener actionListener=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					AdminOp.getInstance().addClass(Integer.parseInt(tf3.getText()), Integer.parseInt(tf4.getText()), tf5.getText(),Integer.parseInt(tf6.getText()));
					myframe.dispose();
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showConfirmDialog(null,"添加失败","提示",JOptionPane.DEFAULT_OPTION);
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showConfirmDialog(null,"添加失败","提示",JOptionPane.DEFAULT_OPTION);
					e1.printStackTrace();
				}
			}
		};
		conf.addActionListener(actionListener);
		myframe.add(Cpanel,BorderLayout.CENTER);
		myframe.setVisible(true);
	}
	public void changeCourse() {
		JFrame myframe=new JFrame("添加课程");
		myframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		myframe.getContentPane().setLayout(new BorderLayout());
		myframe.setBounds(0,0,300,350);
		JLabel lb3=new JLabel("输入课程编号");
		JLabel lb4=new JLabel("输入任课教师");		
		JLabel lb5=new JLabel("输入课程名称");
		JLabel lb6=new JLabel("请输入学分");
		JTextField tf3=new JTextField();
		JTextField tf4=new JTextField();
		JTextField tf5=new JTextField();
		JTextField tf6=new JTextField();
		tf3.setPreferredSize(new Dimension(190,30));
		tf4.setPreferredSize(new Dimension(190,30));
		tf5.setPreferredSize(new Dimension(190,30));
		tf6.setPreferredSize(new Dimension(190,30));
		JButton conf=new JButton("确认添加");
		JPanel Cpanel=new JPanel();
		Cpanel.add(lb3);Cpanel.add(tf3);Cpanel.add(lb4);Cpanel.add(tf4);Cpanel.add(lb5);Cpanel.add(tf5);
		Cpanel.add(lb6);Cpanel.add(tf6);
		Cpanel.add(conf);
		ActionListener actionListener=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					AdminOp.getInstance().addCourse(Integer.parseInt(tf3.getText()), Integer.parseInt(tf4.getText()), tf5.getText(),Integer.parseInt(tf6.getText()));
					myframe.dispose();
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showConfirmDialog(null,"添加失败","提示",JOptionPane.DEFAULT_OPTION);
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showConfirmDialog(null,"添加失败","提示",JOptionPane.DEFAULT_OPTION);
					e1.printStackTrace();
				}
			}
		};
		conf.addActionListener(actionListener);
		myframe.add(Cpanel,BorderLayout.CENTER);
		myframe.setVisible(true);
	}
	public void lookCourse() throws Exception {
		JFrame myframe=new JFrame("课程信息");
		myframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		myframe.getContentPane().setLayout(new BorderLayout());
		myframe.setBounds(0,0,500,350);
		String[] columnNames = {"课程号", "开课教师", "课程名称","学分"};
		List<Object[]> data=AdminOp.getInstance().showCourse();
        DefaultTableModel tableModel = new DefaultTableModel(data.toArray(new Object[0][]), columnNames);
        
        // 创建JTable并设置表格模型
        JTable table = new JTable(tableModel);
        
        // 创建滚动窗格并将JTable放置其中
        JScrollPane scrollPane = new JScrollPane(table);
        myframe.add(scrollPane,BorderLayout.CENTER);
        myframe.setVisible(true);
	}
	public void lookClass() throws Exception {
		JFrame myframe=new JFrame("班级信息");
		myframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		myframe.getContentPane().setLayout(new BorderLayout());
		myframe.setBounds(0,0,500,350);
		String[] columnNames = {"班级编号", "所属学院", "班级名称","班长学号","班级人数"};
		List<Object[]> data=AdminOp.getInstance().showClass();
        DefaultTableModel tableModel = new DefaultTableModel(data.toArray(new Object[0][]), columnNames);
        
        // 创建JTable并设置表格模型
        JTable table = new JTable(tableModel);
        
        // 创建滚动窗格并将JTable放置其中
        JScrollPane scrollPane = new JScrollPane(table);
        myframe.add(scrollPane,BorderLayout.CENTER);
        myframe.setVisible(true);
	}
	public void begin() {
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		JLabel label=new JLabel("欢迎回来，管理员！");
		frame.setBounds(200, 200, 300, 300);
		panel.add(addTeacher);
		panel.add(addStudent);
		panel.add(addCourse);
		panel.add(addCollege);
		panel.add(addClass);
		panel.add(seeCourse);
		panel.add(seeClass);
		frame.add(label,BorderLayout.NORTH);
		frame.add(panel,BorderLayout.CENTER);
		//修改学院信息
		ActionListener actionListener1=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				changeCollege();
			}
		};
		addCollege.addActionListener(actionListener1);
		ActionListener actionListener2=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				changeStudent();
			}
		};
		addStudent.addActionListener(actionListener2);
		ActionListener actionListener3=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				changeTeacher();
			}
		};
		addTeacher.addActionListener(actionListener3);
		ActionListener actionListener4=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				changeClass();
			}
		};
		addClass.addActionListener(actionListener4);
		ActionListener actionListener5=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				changeCourse();
			}
		};
		addCourse.addActionListener(actionListener5);
		ActionListener actionListener6=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					lookCourse();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
		seeCourse.addActionListener(actionListener6);
		ActionListener actionListener7=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					lookClass();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
		seeClass.addActionListener(actionListener7);
		frame.setVisible(true);
	}
}
