package com.view;

import java.awt.Image;
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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.dao.BooksinfoDao;
import com.pojo.Admin;
import com.pojo.Booksinfo;

public class BooksinfoView extends JFrame {

	static JTable table;
	JScrollPane js;
	static JTextField txt_select;
	final JLabel lbl_result;
	JComboBox cmb_select;
	private Admin admin;

	public BooksinfoView(final Admin admin) {
		this.setSize(900, 650);
		this.setTitle("ͼ�����");
		this.setResizable(false);// �ı��С
		this.setLocationRelativeTo(null); // ����
		this.setLayout(null); // ���ַ�ʽ ��(�����Ͻ�Ϊ
		this.setVisible(true);
		
		this.admin = admin;
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image image = tk.createImage(this.getClass().getResource("bookstore.png")); /* ���ͼ�� */
		this.setIconImage(image);

		ImageIcon background = new ImageIcon(this.getClass().getResource("666.jpg"));
		JLabel label = new JLabel(background);
		label.setBounds(0, 0, 900, 480);
		JPanel imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));

		ImageIcon background2 = new ImageIcon(this.getClass().getResource("timg.jpg"));
		JLabel label2 = new JLabel(background2);
		label2.setBounds(0, 480, 900, 200);
		JPanel imagePanel2 = (JPanel) this.getContentPane();
		imagePanel2.setOpaque(false);
		this.getLayeredPane().add(label2, new Integer(Integer.MIN_VALUE));

		js = new JScrollPane();
		js.setBounds(10, 70, 820, 420);

		table = new JTable();

		DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
		cr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		table.setDefaultRenderer(Object.class, cr);
		((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

		js.setViewportView(table);
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

					int bid = (Integer) table.getValueAt(row, 0);
					UpdateBooksinfoView view = new UpdateBooksinfoView(bid,admin);
					view.setVisible(true);
				}
			}
		});

		cmb_select = new JComboBox();
		cmb_select.setBounds(140, 10, 100, 30);
		cmb_select.addItem("����");
		cmb_select.addItem("����");
		cmb_select.addItem("������");
		cmb_select.addItem("���");
		cmb_select.addItem("ISBN��");

		txt_select = new JTextField();
		txt_select.setBounds(250, 10, 420, 30);

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
			public void mouseClicked(final MouseEvent e) {
				// TODO Auto-generated method stub
				// TODO Auto-generated method stub
				if (e.getClickCount() == 1) {
					new Thread(){
						public void run() {
							// TODO Auto-generated method stub
							while (true) {
								try {
									Thread.sleep(2000);
								} catch (InterruptedException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

								int n = bindBook();

								if (cmb_select.getSelectedItem().equals("ISBN��")) {
										String sel = txt_select.getText();
										if(n==0){
											txt_select.setText("");
											int flag = JOptionPane.showConfirmDialog(null, "��ѯ������ͼ�飬�Ƿ���ӣ�", "��ѯ���", JOptionPane.YES_NO_OPTION);
											if(flag == JOptionPane.YES_OPTION){
												AddBooksinfoView view = new AddBooksinfoView(admin,sel);
												view.setVisible(true);
											}else
												txt_select.setText("");
										}
								} else {
									if (n == 0) {
										JOptionPane.showMessageDialog(null, "��������ȷ�Ĳ�ѯ��Ϣ��");
										bindBooksinfo();
										txt_select.setText("");
										continue;
									} else {
										if (txt_select.getText().trim().equals("")) {
											lbl_result.setText("");
										} else {
											lbl_result.setText("Ϊ���ҵ��� " + txt_select.getText().trim() + " ������鼮����Ϊ " + n + " ��");
										}
									}
								}
							}
						}
					}.start();
				
				}
			}
		});

		ImageIcon icon2 = new ImageIcon(this.getClass().getResource("select.png"));
		Image icon_select = icon2.getImage().getScaledInstance(20, 20, icon2.getImage().SCALE_DEFAULT);
		ImageIcon icon_select2 = new ImageIcon(icon_select);
		JButton btn_select = new JButton(icon_select2);
		btn_select.setBounds(680, 10, 30, 30);

		lbl_result = new JLabel();
		lbl_result.setBounds(30, 45, 850, 20);

		bindBooksinfo();

		btn_select.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String name = txt_select.getText().trim();
				if (name.equals("")) {
					JOptionPane.showMessageDialog(null, "��������ȷ�Ĳ�ѯ��Ϣ��");
					bindBooksinfo();
				} else {
					int n = bindBook();
					txt_select.setText("");
					lbl_result.setText("Ϊ���ҵ��� " + name + " ������鼮����Ϊ " + n + " ��");
				}
			}
		});

		ImageIcon icon = new ImageIcon(this.getClass().getResource("add.png"));
		Image icon_add = icon.getImage().getScaledInstance(60, 30, icon.getImage().SCALE_DEFAULT);
		ImageIcon icon_vip2 = new ImageIcon(icon_add);
		JButton btn_add = new JButton("����ͼ��", icon_vip2);
		btn_add.setVerticalTextPosition(JButton.BOTTOM);
		btn_add.setHorizontalTextPosition(JButton.CENTER);
		btn_add.setBounds(120, 500, 150, 60);
		btn_add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AddBooksinfoView view = new AddBooksinfoView(admin);
				view.setVisible(true);
			}
		});

		ImageIcon icon1 = new ImageIcon(this.getClass().getResource("update.png"));
		Image icon_update = icon1.getImage().getScaledInstance(60, 30, icon2.getImage().SCALE_DEFAULT);
		ImageIcon icon_update1 = new ImageIcon(icon_update);
		JButton btn_update = new JButton("�޸���Ϣ", icon_update1);
		btn_update.setVerticalTextPosition(JButton.BOTTOM);
		btn_update.setHorizontalTextPosition(JButton.CENTER);
		btn_update.setBounds(350, 500, 150, 60);
		btn_update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int row = table.getSelectedRow();
				if (row < 0)
					JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�޸ĵ��У�");
				else {
					int bid = (Integer) table.getValueAt(row, 0);

					UpdateBooksinfoView view = new UpdateBooksinfoView(bid,admin);
					view.setVisible(true);
				}
			}
		});

		ImageIcon icon3 = new ImageIcon(this.getClass().getResource("del.png"));
		Image icon_del = icon3.getImage().getScaledInstance(60, 30, icon3.getImage().SCALE_DEFAULT);
		ImageIcon icon_del2 = new ImageIcon(icon_del);
		JButton btn_del = new JButton("ɾ����Ϣ", icon_del2);
		btn_del.setVerticalTextPosition(JButton.BOTTOM);
		btn_del.setHorizontalTextPosition(JButton.CENTER);
		btn_del.setBounds(570, 500, 150, 60);
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
						int bid = (Integer) table.getValueAt(row, 0);
						BooksinfoDao dao = new BooksinfoDao();
						int n = dao.delBooksinfo(bid);
						if (n > 0) {
							//JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
							bindBooksinfo();
						} else
							JOptionPane.showMessageDialog(null, "ɾ��ʧ�ܣ�");
					}
				}
			}
		});

	//	new Thread(this).start();

		this.add(js);
		this.add(btn_select);
		this.add(txt_select);
		this.add(lbl_result);
		this.add(cmb_select);
		this.add(btn_add);
		this.add(btn_del);
		this.add(btn_update);
	}

	static public void bindBooksinfo() {

		DefaultTableModel dtm = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};

		dtm.addColumn("ͼ��ID");
		dtm.addColumn("����");
		dtm.addColumn("����");
		dtm.addColumn("ISBN��");
		dtm.addColumn("������");
		dtm.addColumn("���");
		dtm.addColumn("�۸�");
		dtm.addColumn("����");
		dtm.addColumn("��Ӧ��");

		BooksinfoDao dao = new BooksinfoDao();
		ArrayList<Booksinfo> list = dao.getAllBooks();

		for (Booksinfo info : list) {

			Vector vec = new Vector();

			vec.add(info.getBid());
			vec.add(info.getBname());
			vec.add(info.getBauthor());
			vec.add(info.getBisbn());
			vec.add(info.getBconcern());
			vec.add(info.getTname());
			vec.add(info.getBprice());
			vec.add(info.getBnum());
			vec.add(info.getSname());

			dtm.addRow(vec);

		}

		table.setModel(dtm);

		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(0);

		table.getColumnModel().getColumn(1).setMaxWidth(140);
		table.getColumnModel().getColumn(1).setMinWidth(140);
		table.getColumnModel().getColumn(1).setPreferredWidth(140);

		table.getColumnModel().getColumn(2).setMaxWidth(120);
		table.getColumnModel().getColumn(2).setMinWidth(120);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);

		table.getColumnModel().getColumn(3).setMaxWidth(120);
		table.getColumnModel().getColumn(3).setMinWidth(120);
		table.getColumnModel().getColumn(3).setPreferredWidth(120);

		table.getColumnModel().getColumn(4).setMaxWidth(120);
		table.getColumnModel().getColumn(4).setMinWidth(120);
		table.getColumnModel().getColumn(4).setPreferredWidth(120);

		table.getColumnModel().getColumn(5).setMaxWidth(100);
		table.getColumnModel().getColumn(5).setMinWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);

		table.getColumnModel().getColumn(6).setMaxWidth(60);
		table.getColumnModel().getColumn(6).setMinWidth(60);
		table.getColumnModel().getColumn(6).setPreferredWidth(60);

		table.getColumnModel().getColumn(7).setMaxWidth(60);
		table.getColumnModel().getColumn(7).setMinWidth(60);
		table.getColumnModel().getColumn(7).setPreferredWidth(60);

		table.getColumnModel().getColumn(8).setMaxWidth(90);
		table.getColumnModel().getColumn(8).setMinWidth(90);
		table.getColumnModel().getColumn(8).setPreferredWidth(90);
	}

	public int bindBook() {

		DefaultTableModel dtm = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};

		dtm.addColumn("ͼ��ID");
		dtm.addColumn("����");
		dtm.addColumn("����");
		dtm.addColumn("ISBN��");
		dtm.addColumn("������");
		dtm.addColumn("���");
		dtm.addColumn("�۸�");
		dtm.addColumn("����");
		dtm.addColumn("��Ӧ��");

		ArrayList<Booksinfo> list = null;

		String name = txt_select.getText().trim();
		BooksinfoDao dao = new BooksinfoDao();
		if (cmb_select.getSelectedItem().equals("����")) {
			list = dao.selBook(name);
		} else if (cmb_select.getSelectedItem().equals("����")) {
			list = dao.selBookByA(name);
		} else if (cmb_select.getSelectedItem().equals("������")) {
			list = dao.selBookByCon(name);
		} else if (cmb_select.getSelectedItem().equals("���")) {
			list = dao.selBookByT(name);
		} else if (cmb_select.getSelectedItem().equals("ISBN��")) {
			list = dao.selBookByIsbn(name);
		}

		for (Booksinfo info : list) {

			Vector vec = new Vector();

			vec.add(info.getBid());
			vec.add(info.getBname());
			vec.add(info.getBauthor());
			vec.add(info.getBisbn());
			vec.add(info.getBconcern());
			vec.add(info.getTname());
			vec.add(info.getBprice());
			vec.add(info.getBnum());
			vec.add(info.getSname());

			dtm.addRow(vec);

		}

		table.setModel(dtm);

		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(0);

		table.getColumnModel().getColumn(1).setMaxWidth(140);
		table.getColumnModel().getColumn(1).setMinWidth(140);
		table.getColumnModel().getColumn(1).setPreferredWidth(140);

		table.getColumnModel().getColumn(2).setMaxWidth(120);
		table.getColumnModel().getColumn(2).setMinWidth(120);
		table.getColumnModel().getColumn(2).setPreferredWidth(120);

		table.getColumnModel().getColumn(3).setMaxWidth(120);
		table.getColumnModel().getColumn(3).setMinWidth(120);
		table.getColumnModel().getColumn(3).setPreferredWidth(120);

		table.getColumnModel().getColumn(4).setMaxWidth(120);
		table.getColumnModel().getColumn(4).setMinWidth(120);
		table.getColumnModel().getColumn(4).setPreferredWidth(120);

		table.getColumnModel().getColumn(5).setMaxWidth(100);
		table.getColumnModel().getColumn(5).setMinWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);

		table.getColumnModel().getColumn(6).setMaxWidth(60);
		table.getColumnModel().getColumn(6).setMinWidth(60);
		table.getColumnModel().getColumn(6).setPreferredWidth(60);

		table.getColumnModel().getColumn(7).setMaxWidth(60);
		table.getColumnModel().getColumn(7).setMinWidth(60);
		table.getColumnModel().getColumn(7).setPreferredWidth(60);

		table.getColumnModel().getColumn(8).setMaxWidth(90);
		table.getColumnModel().getColumn(8).setMinWidth(90);
		table.getColumnModel().getColumn(8).setPreferredWidth(90);

		return list.size();
	}
	
	
	//@Override
/*	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			int n = bindBook();

			if (cmb_select.getSelectedItem().equals("ISBN��")) {
					
					if(n==0){
						int flag = JOptionPane.showConfirmDialog(null, "��ѯ������ͼ�飬�Ƿ���ӣ�", "��ѯ���", JOptionPane.YES_NO_OPTION);
						if(flag == JOptionPane.YES_OPTION){
							AddBooksinfoView view = new AddBooksinfoView(admin,txt_select.getText());
							view.setVisible(true);
							txt_select.setText("");
						}
					}
			} else {
				if (n == 0) {
					JOptionPane.showMessageDialog(null, "��������ȷ�Ĳ�ѯ��Ϣ��");
					bindBooksinfo();
					txt_select.setText("");
					continue;
				} else {
					if (txt_select.getText().trim().equals("")) {
						lbl_result.setText("");
					} else {
						lbl_result.setText("Ϊ���ҵ��� " + txt_select.getText().trim() + " ������鼮����Ϊ " + n + " ��");
					}
				}
			}
		}
	}*/
}
