package com.view;


import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

import com.dao.BookinDao;
import com.dao.BookoutDao;
import com.dao.BooksinfoDao;
import com.dao.SupplierDao;
import com.dao.TypeDao;
import com.pojo.Admin;
import com.pojo.Bookin;
import com.pojo.Bookout;
import com.pojo.Booksinfo;
import com.pojo.Supplier;

public class UpdateBooksinfoView extends JDialog {
	static JTable table;
	JScrollPane js;

	public UpdateBooksinfoView(final int bid, final Admin admin) {
		this.setSize(400, 630);
		this.setTitle("修改图书信息");
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
					UpdateBooksinfoView.this.setVisible(false);
				}
			}
		});

		JLabel lbl_title = new JLabel("修改图书信息");
		lbl_title.setBounds(125, 15, 200, 30);
		lbl_title.setFont(new Font("微软雅黑", Font.BOLD, 18));
		
		BooksinfoDao dao = new BooksinfoDao();
		final Booksinfo info = dao.selBookById(bid);

		JLabel lbl_bname = new JLabel("书名：");
		lbl_bname.setBounds(30, 70, 60, 30);
		final JTextField txt_bname = new JTextField(info.getBname());
		txt_bname.setBounds(100, 70, 200, 30);

		JLabel lbl_bauthor = new JLabel("作者：");
		lbl_bauthor.setBounds(30, 120, 60, 30);
		final JTextField txt_bauthor = new JTextField(info.getBauthor());
		txt_bauthor.setBounds(100, 120, 200, 30);

		JLabel lbl_bisbn = new JLabel("ISBN码：");
		lbl_bisbn.setBounds(30, 170, 60, 30);
		final JTextField txt_bisbn = new JTextField(info.getBisbn());
		txt_bisbn.setBounds(100, 170, 150, 30);

		JLabel lbl_bconcern = new JLabel("出版社：");
		lbl_bconcern.setBounds(30, 220, 60, 30);
		final JTextField txt_bconcern = new JTextField(info.getBconcern());
		txt_bconcern.setBounds(100, 220, 200, 30);

		JLabel lbl_tid = new JLabel("类别：");
		lbl_tid.setBounds(30, 280, 60, 30);
		final JComboBox com_tid = new JComboBox();
		com_tid.setBounds(100, 280, 150, 30);
		TypeDao dao1 = new TypeDao();
		ArrayList<com.pojo.Type> list = dao1.getAllType();
		for (com.pojo.Type type : list) {
			com_tid.addItem(type);
		}
		// JOptionPane.showMessageDialog(null, list);
		com.pojo.Type t = dao1.getTypeById(info.getBid());
		com_tid.setSelectedItem(t);
		
		JLabel lbl_bprice = new JLabel("价格：");
		lbl_bprice.setBounds(30, 330, 60, 30);
		final JTextField txt_bprice = new JTextField(info.getBprice()+"");
		txt_bprice.setBounds(100, 330, 100, 30);

		JLabel lbl_bnum = new JLabel("数量：");
		lbl_bnum.setBounds(30, 380, 60, 30);
		final JTextField txt_bnum = new JTextField(info.getBnum()+"");
		txt_bnum.setBounds(100, 380, 100, 30);

		JLabel lbl_sid = new JLabel("供应商：");
		lbl_sid.setBounds(30, 430, 60, 30);
		final JComboBox com_sid = new JComboBox();
		com_sid.setBounds(100, 430, 100, 30);
		SupplierDao dao11 = new SupplierDao();
		ArrayList<Supplier> list1 = dao11.getAllSupplier();
		// JOptionPane.showMessageDialog(null, list1);
		for (Supplier supplier : list1) {
			com_sid.addItem(supplier);
		}
		Supplier su = dao11.getSupplierById(info.getSid());
		com_sid.setSelectedItem(su);

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

				if (bname.equals("") || bauthor.equals("") || bisbn.equals("") || bconcern.equals("")	|| bnum <info.getBnum()) {

					JOptionPane.showMessageDialog(null, "信息输入不完整！请重新检查！");

				} else {

					BooksinfoDao dao = new BooksinfoDao();
					int n  = dao.updateBooksinfo(bid, bname, bauthor, bisbn, bconcern, tid, bprice, bnum, sid);
					if(bnum>info.getBnum()){
						BookinDao dao1 = new BookinDao();
						Bookin in = new Bookin();
					
						in.setBid(bid);
						in.setAid(admin.getAid());
						in.setInum(bnum-info.getBnum());
						in.setIprice(dao1.getBookin(bid).getIprice());
						
						Date date = new Date();
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						String itime = sdf.format(date);
						in.setItime(itime);
					
						dao1.addBookin(in);
					}
					if (n > 0) {
					//	JOptionPane.showMessageDialog(null, "修改成功！");
						UpdateBooksinfoView.this.setVisible(false);
						BooksinfoView.bindBooksinfo();
					} else
						JOptionPane.showMessageDialog(null, "修改失败！");
					BooksinfoView.txt_select.setText("");
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
				UpdateBooksinfoView.this.setVisible(false);
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
