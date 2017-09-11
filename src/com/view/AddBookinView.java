package com.view;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.dao.AdmininfoDao;
import com.dao.BookinDao;
import com.dao.BooksinfoDao;
import com.pojo.Admininfo;
import com.pojo.Bookin;
import com.pojo.Booksinfo;

public class AddBookinView extends JDialog {
	public AddBookinView(final Booksinfo info, final Admininfo ainfo) {
		this.setSize(310, 410);
		this.setTitle("请添图书入库信息！");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setModal(true);

		Toolkit tk = Toolkit.getDefaultToolkit();
		Image image = tk.createImage(this.getClass().getResource("bookstore.png")); /* 你的图标 */
		this.setIconImage(image);

		ImageIcon background = new ImageIcon(this.getClass().getResource("timg.jpg"));
		JLabel label = new JLabel(background);
		label.setBounds(0, 0, 300, 460);
		JPanel imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));

		JLabel lbl_name = new JLabel("书名：");
		lbl_name.setBounds(25, 30, 80, 30);
		final JComboBox box_name = new JComboBox();
		box_name.setBounds(70, 30, 170, 30);
		box_name.addItem(info);

		JLabel lbl_price = new JLabel("进价：");
		lbl_price.setBounds(25, 80, 80, 30);
		final JTextField txt_price = new JTextField();
		txt_price.setBounds(70, 80, 100, 30);

		JLabel lbl_num = new JLabel("数量：");
		lbl_num.setBounds(25, 130, 80, 30);
		final JTextField txt_num = new JTextField(info.getBnum()+"");
		txt_num.setBounds(70, 130, 100, 30);
		txt_num.setEditable(false);
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		final String str = sdf.format(date);
		JLabel lbl_time = new JLabel("时间：");
		lbl_time.setBounds(25, 180, 80, 30);
		final JTextField txt_time = new JTextField(str);
		txt_time.setBounds(70, 180, 150, 30);
		txt_time.setEditable(false);


		JLabel lbl_role = new JLabel("操作人：");
		lbl_role.setBounds(25, 230, 80, 30);
		final JComboBox box_role = new JComboBox();
		box_role.setBounds(70, 230, 120, 30);
		box_role.addItem(ainfo);

		JButton btn_ok = new JButton("确认");
		btn_ok.setBounds(65, 285, 120, 30);
		btn_ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int n = 0;
				
				int inum = info.getBnum();
				String itime = str;
				Double iprice = new Double(txt_price.getText());
				
				AdmininfoDao dao = new AdmininfoDao();
				Admininfo ad = dao.getAdmininfoByname(ainfo.getAname());
				int aid = ad.getAid();
				
				BooksinfoDao dao1 = new BooksinfoDao();
				Booksinfo add = dao1.selBookByName(info.getBname());
				int bid = add.getBid();
				
				if(iprice != 0){
					Bookin in = new Bookin();
					in.setAid(aid);
					in.setBid(bid);
					in.setInum(inum);
					in.setIprice(iprice);
					in.setItime(itime);

					BookinDao dao2 = new BookinDao();
					n = dao2.addBookin(in);
					
					if (n > 0) {
					//	JOptionPane.showMessageDialog(null, "添加成功！");
						AddBookinView.this.setVisible(false);
					} else
						JOptionPane.showMessageDialog(null, "添加失败！");
				}else
					JOptionPane.showMessageDialog(null, "请输入进价！");
				
			
			}
		});

		this.add(lbl_name);
		this.add(box_name);
		this.add(lbl_price);
		this.add(lbl_time);
		this.add(txt_price);
		this.add(txt_time);
		this.add(lbl_role);
		this.add(box_role);
		this.add(lbl_num);
		this.add(txt_num);
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
		AdmininfoDao dao = new AdmininfoDao();
		Admininfo ainfo = dao.getAdmininfo(1);
		
		BooksinfoDao dao1 = new BooksinfoDao();
		Booksinfo info = dao1.selBookById(8);
		
		AddBookinView view = new AddBookinView(info, ainfo);
		view.setVisible(true);
	}
}
