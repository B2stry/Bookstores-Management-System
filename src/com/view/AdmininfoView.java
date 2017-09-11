package com.view;

import java.awt.Image;
import java.awt.JobAttributes;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.dao.AdmininfoDao;
import com.pojo.Admininfo;

public class AdmininfoView extends JFrame {
	JScrollPane js;
	static JTable table;
	JTextField txt_select;
	final JLabel lbl_result;

	public AdmininfoView() {
		this.setSize(900, 600);
		this.setTitle("Ա������");
		this.setResizable(false);// �ı��С
		this.setLocationRelativeTo(null); // ����
		this.setLayout(null); // ���ַ�ʽ ��(�����Ͻ�Ϊ
		this.setVisible(true);

		Toolkit tk = Toolkit.getDefaultToolkit();
		Image image = tk.createImage(this.getClass().getResource("bookstore.png")); /* ���ͼ�� */
		this.setIconImage(image);

		ImageIcon background = new ImageIcon(this.getClass().getResource("666.jpg"));
		JLabel label = new JLabel(background);
		label.setBounds(0, 0, 900, 420);
		JPanel imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));

		ImageIcon background2 = new ImageIcon(this.getClass().getResource("timg.jpg"));
		JLabel label2 = new JLabel(background2);
		label2.setBounds(0, 420, 900, 200);
		JPanel imagePanel2 = (JPanel) this.getContentPane();
		imagePanel2.setOpaque(false);
		this.getLayeredPane().add(label2, new Integer(Integer.MIN_VALUE));

		js = new JScrollPane();
		js.setBounds(10, 50, 825, 400);

		table = new JTable();
		table.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (e.getClickCount() == 2) {
					Point p = e.getPoint();
					int row = table.rowAtPoint(p);
					int column = table.columnAtPoint(p);

					if (column == 8) {
						int aid = (Integer) table.getValueAt(row, 0);
						String atutas = (String) table.getValueAt(row, column);

						if ("��".equals(atutas))
							JOptionPane.showMessageDialog(null, "��Ա������ְ��");
						else {
							String a = "��";
							int flag = JOptionPane.showConfirmDialog(null, "ȷ�ϸ�Ա������ְ��", "Ա����ְ����",
									JOptionPane.OK_CANCEL_OPTION);

							if (flag == JOptionPane.OK_OPTION) {
								AdmininfoDao dao = new AdmininfoDao();
								int n = dao.updateAdmin(a, aid);
								if (n > 0) {
								//	JOptionPane.showMessageDialog(null, "��Ա������ְ��");
									bindAdmininfo();
								} else
									JOptionPane.showMessageDialog(null, "�޸�ʧ�ܣ�");
							}
						}
					} else {
						int aid = (Integer) table.getValueAt(row, 0);
						UpdateAdmininfoView view = new UpdateAdmininfoView(aid);
						view.setVisible(true);
					}
				}
			}
		});

		DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
		cr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		table.setDefaultRenderer(Object.class, cr);

		js.setViewportView(table);
		bindAdmininfo();

		ImageIcon icon = new ImageIcon(this.getClass().getResource("add.png"));
		Image icon_add = icon.getImage().getScaledInstance(60, 25, icon.getImage().SCALE_DEFAULT);
		ImageIcon icon_vip2 = new ImageIcon(icon_add);
		JButton btn_add = new JButton("����Ա��", icon_vip2);
		btn_add.setVerticalTextPosition(JButton.BOTTOM);
		btn_add.setHorizontalTextPosition(JButton.CENTER);
		btn_add.setBounds(50, 460, 150, 50);
		btn_add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AddAdmininfoView view = new AddAdmininfoView();
				view.setVisible(true);
			}
		});

		ImageIcon icon2 = new ImageIcon(this.getClass().getResource("update.png"));
		Image icon_update = icon2.getImage().getScaledInstance(60, 25, icon2.getImage().SCALE_DEFAULT);
		ImageIcon icon_update2 = new ImageIcon(icon_update);
		JButton btn_update = new JButton("�޸���Ϣ", icon_update2);
		btn_update.setVerticalTextPosition(JButton.BOTTOM);
		btn_update.setHorizontalTextPosition(JButton.CENTER);
		btn_update.setBounds(250, 460, 150, 50);
		btn_update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int row = table.getSelectedRow();
				if (row < 0)
					JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�޸ĵ��У�");
				else {
					int aid = (Integer) table.getValueAt(row, 0);
					UpdateAdmininfoView view = new UpdateAdmininfoView(aid);
					view.setVisible(true);
				}
			}
		});

		ImageIcon icon3 = new ImageIcon(this.getClass().getResource("del.png"));
		Image icon_del = icon3.getImage().getScaledInstance(60, 25, icon3.getImage().SCALE_DEFAULT);
		ImageIcon icon_del2 = new ImageIcon(icon_del);
		JButton btn_del = new JButton("ɾ����Ϣ", icon_del2);
		btn_del.setVerticalTextPosition(JButton.BOTTOM);
		btn_del.setHorizontalTextPosition(JButton.CENTER);
		btn_del.setBounds(450, 460, 150, 50);
		btn_del.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int row = table.getSelectedRow();
				if (row < 0) {
					JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ�����У�");
				} else {
					int ch = JOptionPane.showConfirmDialog(null, "ɾ�������ݲ��ָܻ���ȷ����", "����", JOptionPane.YES_NO_OPTION);
					if (ch == JOptionPane.YES_OPTION) {
						int aid = (Integer) table.getValueAt(row, 0);
						AdmininfoDao dao = new AdmininfoDao();
						int n = dao.delAdmininfo(aid);
						if (n > 0) {
							//JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
							bindAdmininfo();
						} else
							JOptionPane.showMessageDialog(null, "����ɾ����Ա���ĵ�¼��Ϣ��");
					}
				}
			}
		});

		ImageIcon icon5 = new ImageIcon(this.getClass().getResource("exit.png"));
		Image icon_exit = icon5.getImage().getScaledInstance(60, 25, icon5.getImage().SCALE_DEFAULT);
		ImageIcon icon_exit2 = new ImageIcon(icon_exit);
		JButton btn_exit = new JButton("��ְ", icon_exit2);
		btn_exit.setVerticalTextPosition(JButton.BOTTOM);
		btn_exit.setHorizontalTextPosition(JButton.CENTER);
		btn_exit.setBounds(650, 460, 150, 50);
		btn_exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int row = table.getSelectedRow();
				if (row < 0)
					JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�޸ĵ��У�");
				else {
					int aid = (Integer) table.getValueAt(row, 0);
					String atutas = (String) table.getValueAt(row, 6);

					if ("��".equals(atutas))
						JOptionPane.showMessageDialog(null, "��Ա������ְ��");
					else {
						String a = "��";
						int flag = JOptionPane.showConfirmDialog(null, "ȷ�ϸ�Ա������ְ��", "Ա����ְ����",
								JOptionPane.OK_CANCEL_OPTION);

						if (flag == JOptionPane.OK_OPTION) {
							AdmininfoDao dao = new AdmininfoDao();
							int n = dao.updateAdmin(a, aid);
							if (n > 0) {
								//JOptionPane.showMessageDialog(null, "��Ա������ְ��");
								bindAdmininfo();
							} else
								JOptionPane.showMessageDialog(null, "�޸�ʧ�ܣ�");
						}

					}
				}
			}
		});

		txt_select = new JTextField();
		txt_select.setBounds(20, 10, 300, 25);
		/*
		 * txt_select.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) { // TODO
		 * Auto-generated method stub String name = txt_select.getText().trim();
		 * if (name.equals("")) { JOptionPane.showMessageDialog(null,
		 * "��������ȷ�Ĳ�ѯ��Ϣ��"); bindAdmininfo(); } else { int n = bindAdmin();
		 * txt_select.setText(""); lbl_result.setText("Ϊ���ҵ��� " + name +
		 * " �����Ա��Ϊ " + n + " ��"); } } });
		 */
		
		txt_select.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
					if(e.getClickCount() == 1){
						new Thread(){
							
							@Override
							public void run() {
								// TODO Auto-generated method stub
								while (true) {
									try {
										Thread.sleep(2000);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

									int n = bindAdmin();

									if (n == 0) {
										JOptionPane.showMessageDialog(null, "��������ȷ�Ĳ�ѯ��Ϣ��");
										bindAdmininfo();
										txt_select.setText("");
										continue;
									} else {
										if (txt_select.getText().trim().equals("")) {
											lbl_result.setText("");
										} else {
											lbl_result.setText("Ϊ���ҵ��� " + txt_select.getText().trim() + " �����Ա��Ϊ " + n + " ��");
										}
									}
								}
							}
						}.start();
					}
			}
		});
		ImageIcon icon4 = new ImageIcon(this.getClass().getResource("select.png"));
		Image icon_select = icon4.getImage().getScaledInstance(15, 15, icon4.getImage().SCALE_DEFAULT);
		ImageIcon icon_select2 = new ImageIcon(icon_select);
		JButton btn_select = new JButton(icon_select2);
		btn_select.setBounds(330, 10, 25, 25);
		lbl_result = new JLabel();
		lbl_result.setBounds(400, 10, 450, 30);
		btn_select.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String name = txt_select.getText().trim();
				if (name.equals("")) {
					JOptionPane.showMessageDialog(null, "��������ȷ�Ĳ�ѯ��Ϣ��");
					bindAdmininfo();
				} else {
					int n = bindAdmin();
					txt_select.setText("");
					lbl_result.setText("Ϊ���ҵ��� " + name + " �����Ա��Ϊ " + n + " ��");
				}
			}
		});

		this.add(js);
		this.add(btn_add);
		this.add(btn_update);
		this.add(btn_del);
		this.add(btn_select);
		this.add(btn_exit);
		this.add(txt_select);
		this.add(lbl_result);
	}

	static public void bindAdmininfo() {

		((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

		DefaultTableModel dtm = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};

		dtm.addColumn("Ա��ID");
		dtm.addColumn("��������");
		dtm.addColumn("����");
		dtm.addColumn("�Ա�");
		dtm.addColumn("�ֻ�����");
		dtm.addColumn("��ͥ��ַ");
		dtm.addColumn("���֤����");
		dtm.addColumn("��ְ����");
		dtm.addColumn("�Ƿ���ְ");

		AdmininfoDao dao = new AdmininfoDao();
		ArrayList<Admininfo> list = dao.getAllAdmininfo();

		for (Admininfo info : list) {
			Vector vec = new Vector();

			vec.add(info.getAid());
			vec.add(info.getDname());
			vec.add(info.getAname());

			if (info.getAsfz().length() > 7) {
				char c = info.getAsfz().charAt(16);
				int cc = c;
				String str = cc % 2 == 1 ? "��" : "Ů";
				vec.add(str);
			} else
				vec.add(null);

			vec.add(info.getAphone());
			vec.add(info.getAdress());
			vec.add(info.getAsfz());

			String b = info.getAtime();
			String[] bb = b.split(" ");
			vec.add(bb[0]);

			vec.add(info.getAtutas());

			dtm.addRow(vec);
		}

		table.setModel(dtm);

		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(0);

		table.getColumnModel().getColumn(1).setMaxWidth(80);
		table.getColumnModel().getColumn(1).setMinWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);

		table.getColumnModel().getColumn(2).setMaxWidth(80);
		table.getColumnModel().getColumn(2).setMinWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);

		table.getColumnModel().getColumn(3).setMaxWidth(50);
		table.getColumnModel().getColumn(3).setMinWidth(50);
		table.getColumnModel().getColumn(3).setPreferredWidth(50);

		table.getColumnModel().getColumn(4).setMaxWidth(130);
		table.getColumnModel().getColumn(4).setMinWidth(130);
		table.getColumnModel().getColumn(5).setPreferredWidth(130);

		table.getColumnModel().getColumn(5).setMaxWidth(130);
		table.getColumnModel().getColumn(5).setMinWidth(130);
		table.getColumnModel().getColumn(5).setPreferredWidth(130);

		table.getColumnModel().getColumn(6).setMaxWidth(150);
		table.getColumnModel().getColumn(6).setMinWidth(150);
		table.getColumnModel().getColumn(6).setPreferredWidth(150);

		table.getColumnModel().getColumn(7).setMaxWidth(120);
		table.getColumnModel().getColumn(7).setMinWidth(120);
		table.getColumnModel().getColumn(7).setPreferredWidth(120);

		table.getColumnModel().getColumn(8).setMaxWidth(80);
		table.getColumnModel().getColumn(8).setMinWidth(80);
		table.getColumnModel().getColumn(8).setPreferredWidth(80);
	}

	public int bindAdmin() {

		((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

		DefaultTableModel dtm = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};

		dtm.addColumn("Ա��ID");
		dtm.addColumn("��������");
		dtm.addColumn("����");
		dtm.addColumn("�Ա�");
		dtm.addColumn("�ֻ�����");
		dtm.addColumn("��ͥ��ַ");
		dtm.addColumn("���֤����");
		dtm.addColumn("��ְ����");
		dtm.addColumn("�Ƿ���ְ");

		AdmininfoDao dao = new AdmininfoDao();
		String aname = txt_select.getText().trim();
		ArrayList<Admininfo> list = dao.getAdmininfoByName(aname);

		for (Admininfo info : list) {
			Vector vec = new Vector();

			vec.add(info.getAid());
			vec.add(info.getDname());
			vec.add(info.getAname());

			if (info.getAsfz().length() > 7) {
				char c = info.getAsfz().charAt(16);
				int cc = c;
				String str = cc % 2 == 1 ? "��" : "Ů";
				vec.add(str);
			}

			vec.add(info.getAphone());
			vec.add(info.getAdress());
			vec.add(info.getAsfz());

			String b = info.getAtime();
			String[] bb = b.split(" ");
			vec.add(bb[0]);

			vec.add(info.getAtutas());
			dtm.addRow(vec);

		}
		table.setModel(dtm);

		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(0);

		table.getColumnModel().getColumn(1).setMaxWidth(80);
		table.getColumnModel().getColumn(1).setMinWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);

		table.getColumnModel().getColumn(2).setMaxWidth(80);
		table.getColumnModel().getColumn(2).setMinWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);

		table.getColumnModel().getColumn(3).setMaxWidth(50);
		table.getColumnModel().getColumn(3).setMinWidth(50);
		table.getColumnModel().getColumn(3).setPreferredWidth(50);

		table.getColumnModel().getColumn(4).setMaxWidth(130);
		table.getColumnModel().getColumn(4).setMinWidth(130);
		table.getColumnModel().getColumn(5).setPreferredWidth(130);

		table.getColumnModel().getColumn(5).setMaxWidth(130);
		table.getColumnModel().getColumn(5).setMinWidth(130);
		table.getColumnModel().getColumn(5).setPreferredWidth(130);

		table.getColumnModel().getColumn(6).setMaxWidth(150);
		table.getColumnModel().getColumn(6).setMinWidth(150);
		table.getColumnModel().getColumn(6).setPreferredWidth(150);

		table.getColumnModel().getColumn(7).setMaxWidth(120);
		table.getColumnModel().getColumn(7).setMinWidth(120);
		table.getColumnModel().getColumn(7).setPreferredWidth(120);

		table.getColumnModel().getColumn(8).setMaxWidth(80);
		table.getColumnModel().getColumn(8).setMinWidth(80);
		table.getColumnModel().getColumn(8).setPreferredWidth(80);

		return list.size();
	}

/*	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			int n = bindAdmin();

			if (n == 0) {
				JOptionPane.showMessageDialog(null, "��������ȷ�Ĳ�ѯ��Ϣ��");
				bindAdmininfo();
				txt_select.setText("");
				continue;
			} else {
				if (txt_select.getText().trim().equals("")) {
					lbl_result.setText("");
				} else {
					lbl_result.setText("Ϊ���ҵ��� " + txt_select.getText().trim() + " �����Ա��Ϊ " + n + " ��");
				}
			}
		}
	}*/

}
