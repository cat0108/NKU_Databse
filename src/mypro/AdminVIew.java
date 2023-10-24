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
	JFrame frame=new JFrame("����Ա");
	JButton addTeacher=new JButton("�޸Ľ�ʦ��Ϣ");
	JButton addStudent=new JButton("�޸�ѧ����Ϣ");
	JButton addCourse=new JButton("�޸Ŀγ���Ϣ");
	JButton addCollege=new JButton("�޸�ѧԺ��Ϣ");
	JButton addClass=new JButton("�޸İ༶��Ϣ");
	JButton seeCourse=new JButton("�鿴���пγ�");
	JButton seeClass=new JButton("�鿴�༶��Ϣ");
	JPanel panel=new JPanel();
	//�޸�ѧԺ��Ϣ����
	public void changeCollege() {
		JFrame frame=new JFrame("ѧԺ��Ϣ�޸�");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setBounds(100, 100, 400, 300);
		JButton showMsg=new JButton("ˢ����Ϣ");
		JButton updateMsg=new JButton("�޸�ѧԺ��Ϣ");
		JButton increaseCollege=new JButton("����ѧԺ");
		JPanel Cpanel=new JPanel();
		JPanel Npanel=new JPanel();
		Npanel.add(showMsg);
		Npanel.add(updateMsg);
		Npanel.add(increaseCollege);
		//������ʾѧԺ��Ϣ
		ActionListener actionListener1=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String[] columnNames = {"ѧԺID", "ѧԺ����", "Ժ��","ѧԺ��ʦ��"};
				List<Object[]> data;
				try {
					data = AdminOp.getInstance().showCollege();
					DefaultTableModel tableModel = new DefaultTableModel(data.toArray(new Object[0][]), columnNames);
			        
			        // ����JTable�����ñ��ģ��
			        JTable table = new JTable(tableModel);
			        
			        // �����������񲢽�JTable��������
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
		//�����޸�ѧԺ��Ϣ
		ActionListener actionListener2=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame myframe=new JFrame("�޸���Ϣ");
				myframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				myframe.getContentPane().setLayout(new BorderLayout());
				myframe.setBounds(0,0,300,300);
				JLabel label1=new JLabel("����ѧԺid:");
				JLabel label2=new JLabel("������Ժ������:");
				JTextField tf1=new JTextField();
				JTextField tf2=new JTextField();
				tf1.setPreferredSize(new Dimension(150,30));
				tf2.setPreferredSize(new Dimension(150,30));
				JPanel panelC=new JPanel();
				JButton conf=new JButton("ȷ���޸�");
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
		//����ѧԺ��
		ActionListener actionListener3=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame myframe=new JFrame("�޸���Ϣ");
				myframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				myframe.getContentPane().setLayout(new BorderLayout());
				myframe.setBounds(0,0,300,300);
				JLabel lb3=new JLabel("������ѧԺID");
				JLabel lb4=new JLabel("������ѧԺ����");		
				JLabel lb5=new JLabel("������ѧԺԺ��");
				JTextField tf3=new JTextField();
				JTextField tf4=new JTextField();
				JTextField tf5=new JTextField();
				tf3.setPreferredSize(new Dimension(150,30));
				tf4.setPreferredSize(new Dimension(150,30));
				tf5.setPreferredSize(new Dimension(150,30));
				JButton conf=new JButton("ȷ�ϸ���");
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
	//�޸�ѧ���Ĳ���
	public void changeStudent() {
		JFrame frame=new JFrame("ѧ����Ϣ�޸�");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setBounds(100, 100, 600, 400);
		JButton addStu=new JButton("���ѧ��");
		JButton updateMsg=new JButton("�޸�ѧ������");
		JButton deleteStu=new JButton("ɾ��ѧ��");
		JButton showStu=new JButton("��ʾѧ����");
		JPanel Cpanel=new JPanel();
		JPanel Npanel=new JPanel();
		Npanel.add(addStu);Npanel.add(updateMsg);Npanel.add(deleteStu);Npanel.add(showStu);
		frame.add(Npanel,BorderLayout.NORTH);frame.add(Cpanel,BorderLayout.CENTER);
		//������ʾѧ����Ϣ
		ActionListener actionListener1=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String[] columnNames = {"ѧ��", "�����༶", "����","�Ա�","��������","��ѧʱ��","pwd"};
				List<Object[]> data;
				try {
					data = AdminOp.getInstance().showStudent();
					DefaultTableModel tableModel = new DefaultTableModel(data.toArray(new Object[0][]), columnNames);
			        
			        // ����JTable�����ñ��ģ��
			        JTable table = new JTable(tableModel);
			        
			        // �����������񲢽�JTable��������
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
		
		//�������ѧ��
		ActionListener actionListener2=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame myframe=new JFrame("���ѧ��");
				myframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				myframe.getContentPane().setLayout(new BorderLayout());
				myframe.setBounds(0,0,300,350);
				JLabel lb3=new JLabel("����ѧ��ID");
				JLabel lb4=new JLabel("���������༶ID");		
				JLabel lb5=new JLabel("����ѧ������");
				JLabel lb6=new JLabel("�����Ա�");
				JLabel lb7=new JLabel("��������");
				JLabel lb8=new JLabel("��ѧʱ��");
				JLabel lb9=new JLabel("��������");
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
				JButton conf=new JButton("ȷ�����");
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
		//�����޸�ѧ����Ϣ
		ActionListener actionListener3=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame myframe=new JFrame("�޸�ѧ������");
				myframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				myframe.getContentPane().setLayout(new BorderLayout());
				myframe.setBounds(0,0,300,350);
				JLabel lb3=new JLabel("����ѧ��ID");
				JLabel lb5=new JLabel("�����޸ĺ������");
				JTextField tf3=new JTextField();
				JTextField tf5=new JTextField();
				tf3.setPreferredSize(new Dimension(180,30));
				tf5.setPreferredSize(new Dimension(180,30));
				JButton conf=new JButton("ȷ���޸�");
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
		
		//����ɾ��ѧ��
		ActionListener actListener4=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame myframe=new JFrame("ɾ��ѧ��");
				myframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				myframe.getContentPane().setLayout(new BorderLayout());
				myframe.setBounds(0,0,300,350);
				JLabel lb3=new JLabel("����ѧ��ID");		
				JTextField tf3=new JTextField();
				tf3.setPreferredSize(new Dimension(180,30));
				JButton conf=new JButton("ȷ��ɾ��");
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
	//�����޸Ľ�ʦ��Ϣ
	public void changeTeacher() {
		JFrame frame=new JFrame("��ʦ��Ϣ�޸�");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		frame.setBounds(100, 100, 600, 400);
		JButton addMsg=new JButton("��ӽ�ʦ");
		JButton updateMsg=new JButton("�޸Ľ�ʦ����");
		JButton deleteMsg=new JButton("ɾ����ʦ");
		JButton showMsg=new JButton("��ʾ��ʦ��");
		JPanel Cpanel=new JPanel();
		JPanel Npanel=new JPanel();
		Npanel.add(addMsg);Npanel.add(updateMsg);Npanel.add(deleteMsg);Npanel.add(showMsg);
		frame.add(Npanel,BorderLayout.NORTH);frame.add(Cpanel,BorderLayout.CENTER);
		
		//������ʾ���
		ActionListener actionListener1=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String[] columnNames = {"��ʦ����", "����ѧԺ", "����","ְ��","�Ա�","����","��ְʱ��","pwd"};
				List<Object[]> data;
				try {
					data = AdminOp.getInstance().showTeacher();
					DefaultTableModel tableModel = new DefaultTableModel(data.toArray(new Object[0][]), columnNames);
			        
			        // ����JTable�����ñ��ģ��
			        JTable table = new JTable(tableModel);
			        
			        // �����������񲢽�JTable��������
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
		//������ӽ�ʦ
		ActionListener actionListener2=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame myframe=new JFrame("��ӽ�ʦ");
				myframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				myframe.getContentPane().setLayout(new BorderLayout());
				myframe.setBounds(0,0,300,350);
				JLabel lb3=new JLabel("�����ְ��ID");
				JLabel lb4=new JLabel("��������ѧԺ");		
				JLabel lb5=new JLabel("��������");
				JLabel lb6=new JLabel("������ְ��");
				JLabel lb7=new JLabel("�������Ա�");
				JLabel lb8=new JLabel("����������");
				JLabel lb9=new JLabel("��������ְʱ��");
				JLabel lb10=new JLabel("����������");
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
				JButton conf=new JButton("ȷ�����");
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
							JOptionPane.showConfirmDialog(null,"���ʧ��","��ʾ",JOptionPane.DEFAULT_OPTION);
							e1.printStackTrace();
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							JOptionPane.showConfirmDialog(null,"���ʧ��","��ʾ",JOptionPane.DEFAULT_OPTION);
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
		//�����޸�����
		ActionListener actionListener3=new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				JFrame myframe=new JFrame("�޸Ľ�ʦ����");
				myframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				myframe.getContentPane().setLayout(new BorderLayout());
				myframe.setBounds(0,0,300,350);
				JLabel lb3=new JLabel("�����ʦID");
				JLabel lb5=new JLabel("�����޸ĺ������");
				JTextField tf3=new JTextField();
				JTextField tf5=new JTextField();
				tf3.setPreferredSize(new Dimension(180,30));
				tf5.setPreferredSize(new Dimension(180,30));
				JButton conf=new JButton("ȷ���޸�");
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
				JFrame myframe=new JFrame("ɾ����ʦ��Ϣ");
				myframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				myframe.getContentPane().setLayout(new BorderLayout());
				myframe.setBounds(0,0,300,350);
				JLabel lb3=new JLabel("�����ʦID");		
				JTextField tf3=new JTextField();
				tf3.setPreferredSize(new Dimension(180,30));
				JButton conf=new JButton("ȷ��ɾ��");
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
	//�ı�༶
	public void changeClass() {
		JFrame myframe=new JFrame("��Ӱ༶");
		myframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		myframe.getContentPane().setLayout(new BorderLayout());
		myframe.setBounds(0,0,300,350);
		JLabel lb3=new JLabel("����༶���");
		JLabel lb4=new JLabel("��������ѧԺ");		
		JLabel lb5=new JLabel("����༶����");
		JLabel lb6=new JLabel("������೤");
		JTextField tf3=new JTextField();
		JTextField tf4=new JTextField();
		JTextField tf5=new JTextField();
		JTextField tf6=new JTextField();
		tf3.setPreferredSize(new Dimension(190,30));
		tf4.setPreferredSize(new Dimension(190,30));
		tf5.setPreferredSize(new Dimension(190,30));
		tf6.setPreferredSize(new Dimension(190,30));
		JButton conf=new JButton("ȷ�����");
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
					JOptionPane.showConfirmDialog(null,"���ʧ��","��ʾ",JOptionPane.DEFAULT_OPTION);
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showConfirmDialog(null,"���ʧ��","��ʾ",JOptionPane.DEFAULT_OPTION);
					e1.printStackTrace();
				}
			}
		};
		conf.addActionListener(actionListener);
		myframe.add(Cpanel,BorderLayout.CENTER);
		myframe.setVisible(true);
	}
	public void changeCourse() {
		JFrame myframe=new JFrame("��ӿγ�");
		myframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		myframe.getContentPane().setLayout(new BorderLayout());
		myframe.setBounds(0,0,300,350);
		JLabel lb3=new JLabel("����γ̱��");
		JLabel lb4=new JLabel("�����ον�ʦ");		
		JLabel lb5=new JLabel("����γ�����");
		JLabel lb6=new JLabel("������ѧ��");
		JTextField tf3=new JTextField();
		JTextField tf4=new JTextField();
		JTextField tf5=new JTextField();
		JTextField tf6=new JTextField();
		tf3.setPreferredSize(new Dimension(190,30));
		tf4.setPreferredSize(new Dimension(190,30));
		tf5.setPreferredSize(new Dimension(190,30));
		tf6.setPreferredSize(new Dimension(190,30));
		JButton conf=new JButton("ȷ�����");
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
					JOptionPane.showConfirmDialog(null,"���ʧ��","��ʾ",JOptionPane.DEFAULT_OPTION);
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showConfirmDialog(null,"���ʧ��","��ʾ",JOptionPane.DEFAULT_OPTION);
					e1.printStackTrace();
				}
			}
		};
		conf.addActionListener(actionListener);
		myframe.add(Cpanel,BorderLayout.CENTER);
		myframe.setVisible(true);
	}
	public void lookCourse() throws Exception {
		JFrame myframe=new JFrame("�γ���Ϣ");
		myframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		myframe.getContentPane().setLayout(new BorderLayout());
		myframe.setBounds(0,0,500,350);
		String[] columnNames = {"�γ̺�", "���ν�ʦ", "�γ�����","ѧ��"};
		List<Object[]> data=AdminOp.getInstance().showCourse();
        DefaultTableModel tableModel = new DefaultTableModel(data.toArray(new Object[0][]), columnNames);
        
        // ����JTable�����ñ��ģ��
        JTable table = new JTable(tableModel);
        
        // �����������񲢽�JTable��������
        JScrollPane scrollPane = new JScrollPane(table);
        myframe.add(scrollPane,BorderLayout.CENTER);
        myframe.setVisible(true);
	}
	public void lookClass() throws Exception {
		JFrame myframe=new JFrame("�༶��Ϣ");
		myframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		myframe.getContentPane().setLayout(new BorderLayout());
		myframe.setBounds(0,0,500,350);
		String[] columnNames = {"�༶���", "����ѧԺ", "�༶����","�೤ѧ��","�༶����"};
		List<Object[]> data=AdminOp.getInstance().showClass();
        DefaultTableModel tableModel = new DefaultTableModel(data.toArray(new Object[0][]), columnNames);
        
        // ����JTable�����ñ��ģ��
        JTable table = new JTable(tableModel);
        
        // �����������񲢽�JTable��������
        JScrollPane scrollPane = new JScrollPane(table);
        myframe.add(scrollPane,BorderLayout.CENTER);
        myframe.setVisible(true);
	}
	public void begin() {
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout());
		JLabel label=new JLabel("��ӭ����������Ա��");
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
		//�޸�ѧԺ��Ϣ
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
