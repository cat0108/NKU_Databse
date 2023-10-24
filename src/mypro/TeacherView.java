package mypro;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class TeacherView {
	private static TeacherView instance;
	public TeacherView() {};

	JFrame frame=new JFrame("��ʦ����");
	JButton showInform=new JButton("�鿴������Ϣ");
	JButton seeCourse=new JButton("�鿴����γ�");
	JButton defineGrade=new JButton("�ɼ�����");
	JButton changePwd=new JButton("�޸�����");
	Font font=new Font("����", Font.PLAIN,20 );
	String teaId;
	int teacherId;
	
	public void showInform() throws Exception {
		String[] columnNames = {"ְ����", "����", "����ѧԺ","ְ��","�Ա�","����","��¼����"};
		List<Object[]> data=TeacherOp.getInstance().teacherInform(teacherId);
        DefaultTableModel tableModel = new DefaultTableModel(data.toArray(new Object[0][]), columnNames);
        
        // ����JTable�����ñ��ģ��
        JTable table = new JTable(tableModel);
        
        // �����������񲢽�JTable��������
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(100,300,600,100);
        frame.add(scrollPane);
	}
	
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
						TeacherOp.getInstance().UpdatePwd(teacherId, textField1.getText());
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
	
	public void lookCourse() throws Exception {
		String[] columnNames = { "����", "�γ̱��","�γ�����","�γ�ѧ��"};
		List<Object[]> data=TeacherOp.getInstance().teachCourse(teacherId);
        DefaultTableModel tableModel = new DefaultTableModel(data.toArray(new Object[0][]), columnNames);
        
        // ����JTable�����ñ��ģ��
        JTable table = new JTable(tableModel);
        
        // �����������񲢽�JTable��������
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(100,420,600,200);
        frame.add(scrollPane);
	}
	//�鿴����γ̵�����ѧ��
	public void seeMyCourses() throws Exception {
		JFrame frame=new JFrame("�༭�ɼ�");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setBounds(0, 0, 600, 500);
		JLabel title=new JLabel("������γ̺�");
		JTextField theCourse=new JTextField();
		JButton Btn=new JButton("�鿴ѡ��ѧ��");
		JLabel label1=new JLabel("������ѧ��");
		JLabel label2=new JLabel("������ɼ�");
		JTextField textField1=new JTextField();
		JTextField textField2=new JTextField();
		JButton confirmBtn=new JButton("ȷ�ϸ���");
		
		title.setBounds(50, 50, 100, 30);
		theCourse.setBounds(150, 50, 100, 30);
		Btn.setBounds(350, 50, 150, 30);
		label1.setBounds(50, 300, 100, 30);
		label2.setBounds(50, 350, 100, 30);
		textField1.setBounds(150, 300, 100, 30);
		textField2.setBounds(150, 350, 100, 30);
		confirmBtn.setBounds(300, 325, 100, 30);
		ActionListener actionListener1=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String[] columnNames = { "ѧ��", "ѧ������","�γ̱��","�γ�����","ѧ���ɼ�"};
				List<Object[]> data;
				try {
					data = TeacherOp.getInstance().courseStudent(Integer.parseInt(theCourse.getText()));
					DefaultTableModel tableModel = new DefaultTableModel(data.toArray(new Object[0][]), columnNames);
				     
			        // ����JTable�����ñ��ģ��
			        JTable table = new JTable(tableModel);
			        
			        // �����������񲢽�JTable��������
			        JScrollPane scrollPane = new JScrollPane(table);
			        scrollPane.setBounds(20,100,500,200);
			        frame.add(scrollPane);
						
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		       
			}
		};
		Btn.addActionListener(actionListener1);
		ActionListener actionListener2=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int stuId=Integer.parseInt(textField1.getText());
				int grade=Integer.parseInt(textField2.getText());
				int courseId=Integer.parseInt(theCourse.getText());
				try {
					TeacherOp.getInstance().updateGrade(stuId, courseId, grade);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
		confirmBtn.addActionListener(actionListener2);
		frame.add(title);
		frame.add(Btn);
		frame.add(theCourse);
		frame.add(label1);
		frame.add(label2);
		frame.add(textField1);
		frame.add(textField2);
		frame.add(confirmBtn);
		frame.setVisible(true);
	}
	public void begin() {
		teaId=log_in.getInstance().UserId.getText();
		teacherId=Integer.parseInt(teaId);
		frame.getContentPane().setLayout(null);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLabel title=new JLabel("ְ����:"+teaId+",��ӭ��¼");
		frame.setBounds(200, 200, 800, 700);
		title.setBounds(250, 100, 300, 30);
		title.setFont(font);
		showInform.setBounds(100, 150, 150, 30);
		seeCourse.setBounds(300, 150, 150, 30);
		defineGrade.setBounds(500, 150, 150, 30);
		changePwd.setBounds(100, 250, 150, 30);
		
		ActionListener actionListener1=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					showInform();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
		showInform.addActionListener(actionListener1);
		
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
					lookCourse();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
		seeCourse.addActionListener(actionListener3);
		ActionListener actionListener4=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				try {
					seeMyCourses();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		};
		defineGrade.addActionListener(actionListener4);
		frame.add(title);
		frame.add(showInform);
		frame.add(seeCourse);
		frame.add(defineGrade);
		frame.add(changePwd);
		frame.setVisible(true);
	}
}
