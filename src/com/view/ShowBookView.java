package com.view;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.dao.BooksinfoDao;
import com.pojo.Booksinfo;

public class ShowBookView extends JDialog {

	static ArrayList<Booksinfo> list = new ArrayList<Booksinfo>();

	public ShowBookView(int bid) {
		this.setSize(450, 350);
		this.setTitle("出售图书信息");
		this.setResizable(false);// 改变大小
		this.setLocationRelativeTo(null); // 居中
		this.setLayout(null); // 布局方式 空(在左上角为
		this.setModal(true);

		BooksinfoDao dao = new BooksinfoDao();
		final Booksinfo info = dao.selBookById(bid);

		Toolkit tk = Toolkit.getDefaultToolkit();
		Image image = tk.createImage(this.getClass().getResource("bookstore.png")); /* 你的图标 */
		this.setIconImage(image);

		ImageIcon background = new ImageIcon(this.getClass().getResource("tim.jpg"));
		JLabel label = new JLabel(background);
		label.setBounds(0, 0, 400, 296);
		JPanel imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));

		JLabel lbl_name = new JLabel("书  名：");
		lbl_name.setBounds(35, 30, 80, 50);

		JLabel lbl_bname = new JLabel(info.getBname());
		lbl_bname.setBounds(85, 30, 130, 50);

		JLabel lbl_author = new JLabel("作  者：");
		lbl_author.setBounds(230, 30, 80, 50);

		JLabel lbl_bauthor = new JLabel(info.getBauthor());
		lbl_bauthor.setBounds(280, 30, 100, 50);

		JLabel lbl_type = new JLabel("类  型：");
		lbl_type.setBounds(35, 90, 80, 50);

		JLabel lbl_btype = new JLabel(info.getTname());
		lbl_btype.setBounds(85, 90, 100, 50);

		JLabel lbl_num = new JLabel("库  存：");
		lbl_num.setBounds(230, 90, 80, 50);

		JLabel lbl_bnum = new JLabel(info.getBnum() + "");
		lbl_bnum.setBounds(280, 90, 80, 50);

		JLabel lbl_onum = new JLabel("购买数量：");
		lbl_onum.setBounds(35, 150, 80, 50);

		final JTextField txt_bonum = new JTextField();
		txt_bonum.setBounds(130, 165, 150, 25);

		final JButton btn_ok = new JButton("确认");
		btn_ok.setBounds(60, 220, 100, 35);
		btn_ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (new Integer(txt_bonum.getText()) > info.getBnum()){
					JOptionPane.showMessageDialog(null, "库存不足！请及时进货！");
					txt_bonum.setText("");

				}
				else {
					info.setBnum((new Integer(txt_bonum.getText())));
					list.add(info);

					AddBookoutView.bindBooksinfo(list);
					ShowBookView.this.setVisible(false);
				}
			}
		});

		JButton btn_can = new JButton("取消");
		btn_can.setBounds(235, 220, 100, 35);
		btn_can.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				ShowBookView.this.setVisible(false);
			}
		});

		this.add(lbl_name);
		this.add(lbl_bname);
		this.add(lbl_num);
		this.add(lbl_bnum);
		this.add(lbl_onum);
		this.add(txt_bonum);
		;
		this.add(lbl_author);
		this.add(lbl_bauthor);
		this.add(lbl_type);
		this.add(lbl_btype);
		this.add(btn_can);
		this.add(btn_ok);

	}

	public static void main(String[] args) {
		try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF(); // MacWinLookAndFeel
																				// 皮肤
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UIManager.put("RootPane.setupButtonVisible", false);

		ShowBookView view = new ShowBookView(1);
		view.setVisible(true);
	}

	static public ArrayList<Booksinfo> getInfo() {
		return list;
	}
}
