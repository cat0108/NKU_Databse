package mypro;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

public class studentView {
	private static studentView instance;
	public studentView() {};
	
	JFrame frame=new JFrame("ѧ������");
	JButton changeInform=new JButton("�鿴��Ϣ");
	JButton selectCourse=new JButton("ѡ��");
	JButton selectedCourse=new JButton("�ҵĿγ�");
	JButton changePwd=new JButton("�޸�����");
	String stuId;
	Font font=new Font("����", Font.PLAIN,20 );
	JButton withdrawCourse=new JButton("�˿�");
	//�鿴������Ϣ
	public void seeInform() throws Exception {
		//��ʾ����
		String[] index= {"id","class","name","birthday","sex","entranceDay","pwd"};
		//��ȡ��ѯ���
		List<StuInform> list=StudentOp.getInstance().getDbDataStudent(Integer.parseInt(stuId));
		Object[][] data=new Object[list.size()][index.length];
		for(int i=0;i<list.size();i++) {
			StuInform stuinform=list.get(i);
			data[i][0]=stuinform.getStuId();
			data[i][1]=stuinform.getClassName();
			data[i][2]=stuinform.getName();
			data[i][3]=stuinform.getBirthday();
			data[i][4]=stuinform.getSex();
			data[i][5]=stuinform.getEntranceDay();
			data[i][6]=stuinform.getPassword();
		}
		//����Ĭ�ϱ��
		DefaultTableModel defaultModel=new DefaultTableModel(data, index);
		JTable table=new JTable(defaultModel);
		table.setFillsViewportHeight(true);
		//��ӹ�����,�ڹ������в�����ʾ��ͷ
		JScrollPane jscrollPane=new JScrollPane();
		jscrollPane.setViewportView(table);
		
		jscrollPane.setBounds(25, 210, 750, 40);
		frame.add(jscrollPane);
	}
	//�޸��������
	public void changePassword() {
		JFrame pwdFrame=new JFrame();
		pwdFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		pwdFrame.getContentPane().setLayout(null);
		pwdFrame.setTitle("�޸�����");
		pwdFrame.setBounds(0, 0, 500, 300);
		JLabel label1=new JLabel("������������:");
		JLabel label2=new JLabel("���ٴ�����������:");
		JButton confirmPwd=new JButton("ȷ���޸�����");
		JTextField textField1=new JTextField();
		JTextField textField2=new JTextField();
		
		label1.setBounds(50, 50, 150, 30);
		label2.setBounds(50, 100, 150, 30);
		textField1.setBounds(200, 50, 200, 30);
		textField2.setBounds(200, 100, 200, 30);
		confirmPwd.setBounds(150, 200, 100, 30);
		
		ActionListener actionListener=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(textField1.getText().equals(textField2.getText()) && textField1.getText()!=null) {
					try {
						StudentOp.getInstance().UpdatePwd(Integer.parseInt(stuId), textField1.getText());
						JOptionPane.showConfirmDialog(null,"�޸ĳɹ�","��ʾ",JOptionPane.DEFAULT_OPTION);
						pwdFrame.dispose();
					} catch (NumberFormatException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else
					JOptionPane.showConfirmDialog(null,"Ҫ����ȷ","��ʾ",JOptionPane.DEFAULT_OPTION);
			}
		};
		confirmPwd.addActionListener(actionListener);
		pwdFrame.add(label1);
		pwdFrame.add(label2);
		pwdFrame.add(textField1);
		pwdFrame.add(textField2);
		pwdFrame.add(confirmPwd);
		pwdFrame.setVisible(true);
	}
	
	public void showCourse() throws Exception {
		//��ʾ����
		JFrame newframe=new JFrame("ѡ��");
		newframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		newframe.getContentPane().setLayout(null);
		newframe.setBounds(0, 0, 600, 500);
		String[] index= {"�γ̱��","�γ�����","�ڿ���ʦ","ѧ��"};		
		List<CourseInform> list=StudentOp.getInstance().showCourse();
		Object[][] data=new Object[list.size()][index.length];
		
		for(int i=0;i<list.size();i++) {
			CourseInform courseInform=list.get(i);
			data[i][0]=courseInform.getCourseID();
			data[i][1]=courseInform.getCourseName();
			data[i][2]=courseInform.getTeacherName();
			data[i][3]=courseInform.getCourseCredit();
		}
		//����Ĭ�ϱ��
		DefaultTableModel defaultModel=new DefaultTableModel(data, index);
		JTable table=new JTable(defaultModel);
		table.setFillsViewportHeight(true);
		//��ӹ�����,�ڹ������в�����ʾ��ͷ
		JScrollPane jscrollPane=new JScrollPane();
		jscrollPane.setViewportView(table);
		JLabel showtitle=new JLabel("��ѡ�γ��б�");
		JTextField chooseCourse=new JTextField();
		JLabel choose=new JLabel("������ѡ�εĿκ�");
		JButton chooseBtn=new JButton("ȷ��ѡ��");
		
		showtitle.setFont(font);
		choose.setFont(font);
		chooseCourse.setFont(font);
		chooseBtn.setFont(font);
		jscrollPane.setBounds(50, 40, 500, 150);
		showtitle.setBounds(250, 0, 200, 30);
		choose.setBounds(100, 200, 200, 30);
		chooseCourse.setBounds(300, 200, 100, 30);
		chooseBtn.setBounds(200, 270, 150, 30);
		
		ActionListener actionListener=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					StudentOp.getInstance().selectCourse(Integer.parseInt(chooseCourse.getText()), Integer.parseInt(stuId));
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
		chooseBtn.addActionListener(actionListener);
		newframe.add(jscrollPane);
		newframe.add(showtitle);
		newframe.add(chooseBtn);
		newframe.add(choose);
		newframe.add(chooseCourse);
		newframe.setVisible(true);
	}
	
	public void deleteCourse() throws Exception {
		  String[] columnNames = {"�γ�����", "�γ̱��", "�ον�ʦ","�ɼ�"};
		  List<Object[]> data=StudentOp.getInstance().selectedCourse(Integer.parseInt(stuId));
          DefaultTableModel tableModel = new DefaultTableModel(data.toArray(new Object[0][]), columnNames);
          
          // ����JTable�����ñ��ģ��
          JTable table = new JTable(tableModel);
          
          // �����������񲢽�JTable��������
          JScrollPane scrollPane = new JScrollPane(table);
          scrollPane.setBounds(100,300,600,100);
          frame.add(scrollPane);
	}
	
	public void withdraw() {
		 JFrame frame = new JFrame("�˿�");
	        // ���ô��ڵĴ�С
	        frame.setBounds(0,0,300, 200);
	        // ���ô��ڵĲ���Ϊ null
	        frame.setLayout(null);
	        // ������ǩ
	        JLabel label = new JLabel("�������˿εĿκţ�");
	        label.setBounds(20, 20, 200, 30);
	        // �����ı��ֶ�
	        JTextField textField = new JTextField();
	        textField.setBounds(20, 60, 200, 30);
	        // ������ť
	        JButton button = new JButton("ȷ���˿�");
	        button.setBounds(20, 100, 150, 30);
	        // ����ǩ���ı��ֶκͰ�ť��ӵ�������
	        frame.add(label);
	        frame.add(textField);
	        frame.add(button);
	        ActionListener actionListener=new ActionListener() {
				
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					StudentOp.getInstance().withdrawCourses(Integer.parseInt(stuId), Integer.parseInt(textField.getText()));
					frame.dispose();
				}
			};
			button.addActionListener(actionListener);
	        // ���ô��ڿɼ�
	        frame.setVisible(true);
	}
	public void begin() throws Exception {
		stuId=log_in.getInstance().UserId.getText();
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		JLabel title=new JLabel("ѧ��:"+stuId+",��ӭ��¼");
		frame.setBounds(200, 200, 800, 700);
		title.setBounds(250, 100, 300, 30);
		title.setFont(font);
		changeInform.setBounds(100, 150, 100, 30);
		selectCourse.setBounds(250, 150, 100, 30);
		selectedCourse.setBounds(400, 150, 100, 30);
		changePwd.setBounds(550, 150, 100, 30);
		withdrawCourse.setBounds(300, 450, 100, 30);

		
		ActionListener actionListener1=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					seeInform();//���ò�ѯ��Ϣ����
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
		changeInform.addActionListener(actionListener1);
		
		ActionListener actionListener2=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				changePassword();
			}
		};
		changePwd.addActionListener(actionListener2);
		
		ActionListener actionListener3=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					showCourse();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
		selectCourse.addActionListener(actionListener3);
		ActionListener actionListener4=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					deleteCourse();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
		selectedCourse.addActionListener(actionListener4);
		
		ActionListener actionListener5=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				withdraw();
			}
		};
		withdrawCourse.addActionListener(actionListener5);
		frame.add(title);
		frame.add(changeInform);
		frame.add(selectCourse);
		frame.add(selectedCourse);
		frame.add(changePwd);
		frame.add(withdrawCourse);
		frame.setVisible(true);
	}
}
