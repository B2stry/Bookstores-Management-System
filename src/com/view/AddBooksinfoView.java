package com.view;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.dao.AdmininfoDao;
import com.dao.BooksinfoDao;
import com.dao.SupplierDao;
import com.dao.TypeDao;
import com.pojo.Admin;
import com.pojo.Admininfo;
import com.pojo.Booksinfo;
import com.pojo.Supplier;

public class AddBooksinfoView extends JDialog {
	static JTable table;
	JScrollPane js;

	public AddBooksinfoView(final Admin admin) {
		this.setSize(400, 630);
		this.setTitle("添加图书");
		this.setResizable(false);// 改变大小
		this.setLocationRelativeTo(null); // 居中
		this.setLayout(null); // 布局方式 空(在左上角为
		this.setModal(true);

		Toolkit tk = Toolkit.getDefaultToolkit();
		Image image = tk.createImage(this.getClass().getResource("bookstore.png")); /* 你的图标 */
		this.setIconImage(image);

		ImageIcon background = new ImageIcon(this.getClass().getResource("timg.jpg"));
		JLabel label = new JLabel(background);
		label.setBounds(0, 0, 400, 630);
		JPanel imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
		
		

		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
					AddBooksinfoView.this.setVisible(false);
				}
			}
		});

		JLabel lbl_title = new JLabel("新增图书");
		lbl_title.setBounds(140, 15, 200, 30);
		lbl_title.setFont(new Font("微软雅黑", Font.BOLD, 18));

		JLabel lbl_bname = new JLabel("书名：");
		lbl_bname.setBounds(30, 70, 60, 30);
		final JTextField txt_bname = new JTextField();
		txt_bname.setBounds(100, 70, 200, 30);

		JLabel lbl_bauthor = new JLabel("作者：");
		lbl_bauthor.setBounds(30, 120, 60, 30);
		final JTextField txt_bauthor = new JTextField();
		txt_bauthor.setBounds(100, 120, 200, 30);

		JLabel lbl_bisbn = new JLabel("ISBN码：");
		lbl_bisbn.setBounds(30, 170, 60, 30);
		final JTextField txt_bisbn = new JTextField();
		txt_bisbn.setBounds(100, 170, 150, 30);

		JLabel lbl_bconcern = new JLabel("出版社：");
		lbl_bconcern.setBounds(30, 220, 60, 30);
		final JTextField txt_bconcern = new JTextField();
		txt_bconcern.setBounds(100, 220, 200, 30);

		JLabel lbl_tid = new JLabel("类别：");
		lbl_tid.setBounds(30, 280, 60, 30);
		final JComboBox com_tid = new JComboBox();
		com_tid.setBounds(100, 280, 150, 30);
		TypeDao dao = new TypeDao();
		ArrayList<com.pojo.Type> list = dao.getAllType();
		for (com.pojo.Type type : list) {
			com_tid.addItem(type);
		}
		// JOptionPane.showMessageDialog(null, list);

		JLabel lbl_bprice = new JLabel("价格：");
		lbl_bprice.setBounds(30, 330, 60, 30);
		final JTextField txt_bprice = new JTextField();
		txt_bprice.setBounds(100, 330, 100, 30);

		JLabel lbl_bnum = new JLabel("数量：");
		lbl_bnum.setBounds(30, 380, 60, 30);
		final JTextField txt_bnum = new JTextField();
		txt_bnum.setBounds(100, 380, 100, 30);

		JLabel lbl_sid = new JLabel("供应商：");
		lbl_sid.setBounds(30, 430, 60, 30);
		final JComboBox com_sid = new JComboBox();
		com_sid.setBounds(100, 430, 100, 30);
		SupplierDao dao1 = new SupplierDao();
		ArrayList<Supplier> list1 = dao1.getAllSupplier();
		// JOptionPane.showMessageDialog(null, list1);
		for (Supplier supplier : list1) {
			com_sid.addItem(supplier);
		}

		JButton btn_ok = new JButton("确定");
		btn_ok.setBounds(20, 500, 80, 35);
		btn_ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				String bname = txt_bname.getText().trim();
				String bauthor = txt_bauthor.getText().trim();
				String bisbn = txt_bisbn.getText().trim();
				String bconcern = txt_bconcern.getText().trim();

				com.pojo.Type type = (com.pojo.Type) com_tid.getSelectedItem();
				int tid = type.getTid();

				double bprice = 0;
				try {
					bprice = Double.parseDouble(txt_bprice.getText().trim());
				} catch (Exception e2) {
					// TODO: handle exception
				}
	
				int bnum = Integer.parseInt(txt_bnum.getText().trim());

				Supplier su = (Supplier) com_sid.getSelectedItem();
				int sid = su.getSid();
				
				if (bname.equals("") || bauthor.equals("") || bisbn.equals("") || bconcern.equals("")	|| bnum == 0) {

					JOptionPane.showMessageDialog(null, "信息输入不完整！请重新检查！");

				} else {

					Booksinfo info = new Booksinfo();
					info.setBname(bname);
					info.setBauthor(bauthor);
					info.setBisbn(bisbn);
					info.setBconcern(bconcern);
					info.setTid(tid);
					info.setBprice(bprice);
					info.setBnum(bnum);
					info.setSid(sid);

					BooksinfoDao dao = new BooksinfoDao();
					int n = dao.addBooksinfo(info);

					if (n > 0) {
						//JOptionPane.showMessageDialog(null, "添加成功！");
						AddBooksinfoView.this.setVisible(false);
						BooksinfoView.bindBooksinfo();
						AdmininfoDao dao1 = new AdmininfoDao();
						Admininfo ainfo = dao1.getAdmininfo(admin.getAid());
						AddBookinView view = new AddBookinView(info,ainfo);
						view.setVisible(true);
					} else
						JOptionPane.showMessageDialog(null, "添加失败！");
				}
			}
		});

		JButton btn_zero = new JButton("清空");
		btn_zero.setBounds(135, 500, 80, 35);
		btn_zero.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txt_bname.setText("");
				txt_bauthor.setText("");
				txt_bisbn.setText("");
				txt_bconcern.setText("");
				com.pojo.Type t = new com.pojo.Type();
				com_tid.setSelectedItem(t);
				txt_bprice.setText("");
				txt_bnum.setText("");
				Supplier sup = new Supplier();
				com_sid.setSelectedItem(sup);
			}
		});

		JButton btn_cancel = new JButton("取消");
		btn_cancel.setBounds(250, 500, 80, 35);
		btn_cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AddBooksinfoView.this.setVisible(false);
			}
		});

		this.add(btn_ok);
		this.add(btn_cancel);
		this.add(btn_zero);
		this.add(lbl_bname);
		this.add(txt_bname);
		this.add(lbl_bauthor);
		this.add(txt_bauthor);
		this.add(lbl_bisbn);
		this.add(txt_bisbn);
		this.add(lbl_bconcern);
		this.add(txt_bconcern);
		this.add(lbl_tid);
		this.add(com_tid);
		this.add(lbl_bprice);
		this.add(txt_bprice);
		this.add(lbl_bnum);
		this.add(txt_bnum);
		this.add(lbl_sid);
		this.add(com_sid);
		this.add(lbl_title);

	}
	
	public AddBooksinfoView(final Admin admin, String bisbn) {
		this.setSize(400, 630);
		this.setTitle("添加图书");
		this.setResizable(false);// 改变大小
		this.setLocationRelativeTo(null); // 居中
		this.setLayout(null); // 布局方式 空(在左上角为
		this.setModal(true);

		Toolkit tk = Toolkit.getDefaultToolkit();
		Image image = tk.createImage(this.getClass().getResource("bookstore.png")); /* 你的图标 */
		this.setIconImage(image);

		ImageIcon background = new ImageIcon(this.getClass().getResource("timg.jpg"));
		JLabel label = new JLabel(background);
		label.setBounds(0, 0, 400, 630);
		JPanel imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
	
		this.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyChar() == KeyEvent.VK_ESCAPE) {
					AddBooksinfoView.this.setVisible(false);
				}
			}
		});

		JLabel lbl_title = new JLabel("新增图书");
		lbl_title.setBounds(140, 15, 200, 30);
		lbl_title.setFont(new Font("微软雅黑", Font.BOLD, 18));

		JLabel lbl_bname = new JLabel("书名：");
		lbl_bname.setBounds(30, 70, 60, 30);
		final JTextField txt_bname = new JTextField();
		txt_bname.setBounds(100, 70, 200, 30);

		JLabel lbl_bauthor = new JLabel("作者：");
		lbl_bauthor.setBounds(30, 120, 60, 30);
		final JTextField txt_bauthor = new JTextField();
		txt_bauthor.setBounds(100, 120, 200, 30);

		JLabel lbl_bisbn = new JLabel("ISBN码：");
		lbl_bisbn.setBounds(30, 170, 60, 30);
		final JTextField txt_bisbn = new JTextField(bisbn);
		txt_bisbn.setBounds(100, 170, 150, 30);

		JLabel lbl_bconcern = new JLabel("出版社：");
		lbl_bconcern.setBounds(30, 220, 60, 30);
		final JTextField txt_bconcern = new JTextField();
		txt_bconcern.setBounds(100, 220, 200, 30);

		JLabel lbl_tid = new JLabel("类别：");
		lbl_tid.setBounds(30, 280, 60, 30);
		final JComboBox com_tid = new JComboBox();
		com_tid.setBounds(100, 280, 150, 30);
		TypeDao dao = new TypeDao();
		ArrayList<com.pojo.Type> list = dao.getAllType();
		for (com.pojo.Type type : list) {
			com_tid.addItem(type);
		}
		// JOptionPane.showMessageDialog(null, list);

		JLabel lbl_bprice = new JLabel("价格：");
		lbl_bprice.setBounds(30, 330, 60, 30);
		final JTextField txt_bprice = new JTextField();
		txt_bprice.setBounds(100, 330, 100, 30);

		JLabel lbl_bnum = new JLabel("数量：");
		lbl_bnum.setBounds(30, 380, 60, 30);
		final JTextField txt_bnum = new JTextField();
		txt_bnum.setBounds(100, 380, 100, 30);

		JLabel lbl_sid = new JLabel("供应商：");
		lbl_sid.setBounds(30, 430, 60, 30);
		final JComboBox com_sid = new JComboBox();
		com_sid.setBounds(100, 430, 100, 30);
		SupplierDao dao1 = new SupplierDao();
		ArrayList<Supplier> list1 = dao1.getAllSupplier();
		// JOptionPane.showMessageDialog(null, list1);
		for (Supplier supplier : list1) {
			com_sid.addItem(supplier);
		}

		JButton btn_ok = new JButton("确定");
		btn_ok.setBounds(20, 500, 80, 35);
		btn_ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				String bname = txt_bname.getText().trim();
				String bauthor = txt_bauthor.getText().trim();
				String bisbn = txt_bisbn.getText().trim();
				String bconcern = txt_bconcern.getText().trim();

				com.pojo.Type type = (com.pojo.Type) com_tid.getSelectedItem();
				int tid = type.getTid();

				double bprice = 0;
				try {
					bprice = Double.parseDouble(txt_bprice.getText().trim());
				} catch (Exception e2) {
					// TODO: handle exception
				}
	
				int bnum = Integer.parseInt(txt_bnum.getText().trim());

				Supplier su = (Supplier) com_sid.getSelectedItem();
				int sid = su.getSid();

				if (bname.equals("") || bauthor.equals("") || bisbn.equals("") || bconcern.equals("")	|| bnum == 0) {

					JOptionPane.showMessageDialog(null, "信息输入不完整！请重新检查！");

				} else {

					Booksinfo info = new Booksinfo();
					info.setBname(bname);
					info.setBauthor(bauthor);
					info.setBisbn(bisbn);
					info.setBconcern(bconcern);
					info.setTid(tid);
					info.setBprice(bprice);
					info.setBnum(bnum);
					info.setSid(sid);

					BooksinfoDao dao = new BooksinfoDao();
					int n = dao.addBooksinfo(info);

					if (n > 0) {
						//JOptionPane.showMessageDialog(null, "添加成功！");
						AddBooksinfoView.this.setVisible(false);
						BooksinfoView.bindBooksinfo();
						AdmininfoDao dao1 = new AdmininfoDao();
						Admininfo ainfo = dao1.getAdmininfo(admin.getAid());
						AddBookinView view = new AddBookinView(info,ainfo);
						view.setVisible(true);
					} else
						JOptionPane.showMessageDialog(null, "添加失败！");
				}
			}
		});

		JButton btn_zero = new JButton("清空");
		btn_zero.setBounds(135, 500, 80, 35);
		btn_zero.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				txt_bname.setText("");
				txt_bauthor.setText("");
				txt_bisbn.setText("");
				txt_bconcern.setText("");
				com.pojo.Type t = new com.pojo.Type();
				com_tid.setSelectedItem(t);
				txt_bprice.setText("");
				txt_bnum.setText("");
				Supplier sup = new Supplier();
				com_sid.setSelectedItem(sup);
			}
		});

		JButton btn_cancel = new JButton("取消");
		btn_cancel.setBounds(250, 500, 80, 35);
		btn_cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AddBooksinfoView.this.setVisible(false);
			}
		});

		this.add(btn_ok);
		this.add(btn_cancel);
		this.add(btn_zero);
		this.add(lbl_bname);
		this.add(txt_bname);
		this.add(lbl_bauthor);
		this.add(txt_bauthor);
		this.add(lbl_bisbn);
		this.add(txt_bisbn);
		this.add(lbl_bconcern);
		this.add(txt_bconcern);
		this.add(lbl_tid);
		this.add(com_tid);
		this.add(lbl_bprice);
		this.add(txt_bprice);
		this.add(lbl_bnum);
		this.add(txt_bnum);
		this.add(lbl_sid);
		this.add(com_sid);
		this.add(lbl_title);

	}
}
