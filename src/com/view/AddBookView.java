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
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.dao.BooksinfoDao;
import com.pojo.Booksinfo;

public class AddBookView extends JDialog {
	static JTable table;
	JScrollPane js;
	JTextField txt_select;
	JComboBox cmb_select;

	public AddBookView() {

		this.setSize(600, 500);
		this.setTitle("���ͼ��");
		this.setResizable(false);// �ı��С
		this.setLocationRelativeTo(null); // ����
		this.setLayout(null); // ���ַ�ʽ ��(�����Ͻ�Ϊ
		this.setModal(true);

		Toolkit tk = Toolkit.getDefaultToolkit();
		Image image = tk.createImage(this.getClass().getResource("bookstore.png")); /* ���ͼ�� */
		this.setIconImage(image);

		ImageIcon background = new ImageIcon(this.getClass().getResource("timg.jpg"));
		JLabel label = new JLabel(background);
		label.setBounds(0, 0, 600, 500);
		JPanel imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));

		js = new JScrollPane();
		js.setBounds(10, 70, 530, 350);

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
					
					int bid = (Integer) table.getValueAt(row, 0);
					ShowBookView view = new ShowBookView(bid);
					view.setVisible(true);

				}
			}
		});

		bindBooksinfo();

		DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
		cr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		table.setDefaultRenderer(Object.class, cr);
		((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

		js.setViewportView(table);

		cmb_select = new JComboBox();
		cmb_select.setBounds(70, 10, 100, 30);
		cmb_select.addItem("ISBN��");

		txt_select = new JTextField();
		txt_select.setBounds(180, 10, 220, 30);
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
								
								boolean flag = bindBook();
								
								if (flag == false) {
									int f = JOptionPane.showConfirmDialog(null, "�Ƿ������ӣ�", "��ʾ", JOptionPane.OK_CANCEL_OPTION);
									if(f == JOptionPane.OK_OPTION){
										txt_select.setText("");
										continue;
									}else{
										AddBookView.this.setVisible(false);
										break;
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
		btn_select.setBounds(410, 10, 30, 30);

		btn_select.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				bindBook();
			}
		});


		this.add(js);
		this.add(btn_select);
		this.add(txt_select);
		this.add(cmb_select);
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

		BooksinfoDao dao = new BooksinfoDao();
		ArrayList<Booksinfo> list = dao.getAllBooks();

		for (Booksinfo info : list) {

			Vector vec = new Vector();

			vec.add(info.getBid());
			vec.add(info.getBname());
			vec.add(info.getBauthor());
			vec.add(info.getBisbn());
			vec.add(info.getBconcern());

			dtm.addRow(vec);

		}

		table.setModel(dtm);

		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
	}

	public boolean bindBook() {
		boolean flag = true;

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

		ArrayList<Booksinfo> list = null;

		String bisbn = txt_select.getText().trim();
		BooksinfoDao dao = new BooksinfoDao();

		list = dao.selBookByIsbn(bisbn);

		if (list.size() == 0) {
			JOptionPane.showMessageDialog(null, "û�в�ѯ�������Ϣ��");
			flag = false;
		} else {
			for (Booksinfo info : list) {

				Vector vec = new Vector();

				vec.add(info.getBid());
				vec.add(info.getBname());
				vec.add(info.getBauthor());
				vec.add(info.getBisbn());
				vec.add(info.getBconcern());

				dtm.addRow(vec);

			}

			table.setModel(dtm);

			table.getColumnModel().getColumn(0).setMaxWidth(0);
			table.getColumnModel().getColumn(0).setMinWidth(0);
			table.getColumnModel().getColumn(0).setPreferredWidth(0);
		}
		return flag;
	}

	public static void main(String[] args) {
		try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF(); // MacWinLookAndFeel
																				// Ƥ��
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UIManager.put("RootPane.setupButtonVisible", false);

		AddBookView view = new AddBookView();
		view.setVisible(true);
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
			
			boolean flag = bindBook();
			
			if (flag == false) {
				int f = JOptionPane.showConfirmDialog(null, "�Ƿ������ӣ�", "��ʾ", JOptionPane.OK_CANCEL_OPTION);
				if(f == JOptionPane.OK_OPTION){
					txt_select.setText("");
					continue;
				}else{
					AddBookView.this.setVisible(false);
					break;
				}
			}
		}
	}*/
}
