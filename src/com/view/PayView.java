package com.view;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import com.dao.BookoutDao;
import com.dao.BooksinfoDao;
import com.pojo.Admin;
import com.pojo.Bookout;
import com.pojo.Booksinfo;

public class PayView extends JDialog implements Runnable {

	JTextField txt_give;
	JTextField txt_pay;
	int money;
	int sum;

	public PayView(final int sum, final Admin admin) {
		this.setSize(450, 350);
		this.setTitle("支付界面");
		this.setResizable(false);// 改变大小
		this.setLocationRelativeTo(null); // 居中
		this.setLayout(null); // 布局方式 空(在左上角为
		this.setModal(true);

		this.sum = sum;

		Toolkit tk = Toolkit.getDefaultToolkit();
		Image image = tk.createImage(this.getClass().getResource("bookstore.png"));
		this.setIconImage(image);

		ImageIcon background = new ImageIcon(this.getClass().getResource("tim.jpg"));
		JLabel label = new JLabel(background);
		label.setBounds(0, 0, 400, 296);
		JPanel imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));

		JLabel lbl_money = new JLabel("应付金额：");
		lbl_money.setBounds(70, 20, 100, 50);
		lbl_money.setFont(new Font("宋体", Font.BOLD, 16));
		JTextField txt_money = new JTextField(sum + "");
		txt_money.setBounds(245, 25, 90, 25);
		txt_money.setEditable(false);
		JLabel lbl_y2 = new JLabel("元");
		lbl_y2.setBounds(340, 14, 50, 50);
		lbl_y2.setFont(new Font("宋体", Font.BOLD, 16));

		JLabel lbl_pay = new JLabel("支付金额：");
		lbl_pay.setBounds(70, 70, 100, 50);
		lbl_pay.setFont(new Font("宋体", Font.BOLD, 16));
		txt_pay = new JTextField();
		txt_pay.setBounds(245, 80, 90, 25);
		JLabel lbl_y = new JLabel("元");
		lbl_y.setBounds(340, 68, 50, 50);
		lbl_y.setFont(new Font("宋体", Font.BOLD, 16));

		JLabel lbl_give = new JLabel("找    零：");
		lbl_give.setBounds(70, 120, 100, 50);
		lbl_give.setFont(new Font("宋体", Font.BOLD, 16));
		txt_give = new JTextField();
		txt_give.setBounds(245, 135, 90, 25);
		txt_give.setEditable(false);

		JLabel lbl_y3 = new JLabel("元");
		lbl_y3.setBounds(340, 122, 50, 50);
		lbl_y3.setFont(new Font("宋体", Font.BOLD, 16));

		JButton btn_ok = new JButton("确定");
		btn_ok.setBounds(70, 210, 80, 30);
		btn_ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				ArrayList<Booksinfo> list = ShowBookView.list;
				
				for (Booksinfo info : list) {
					Bookout out = new Bookout();
					
					out.setAid(admin.getAid());
					out.setBid(info.getBid());
					out.setOprice(info.getBprice());
					out.setOnum(info.getBnum());
					
					Date date = new Date();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					String otime = sdf.format(date);
					out.setOtime(otime);
					
					BookoutDao dao = new BookoutDao();	
					int n = dao.addBookout(out);		
					
					BooksinfoDao dao1 = new BooksinfoDao();
					int a = dao1.updateBooksinfo(info.getBid(), info.getBnum());
				}
				
				int flag = JOptionPane.showConfirmDialog(null, "是否为会员？", "验证会员", JOptionPane.YES_NO_OPTION);
				if(flag == JOptionPane.YES_OPTION){
					CheckClientView view = new CheckClientView(sum);
					view.setVisible(true);
					PayView.this.setVisible(false);
				}else{
					ShowBookView.list = new ArrayList<Booksinfo>();
					AddBookoutView.sum = 0;
					
					PayView.this.setVisible(false);
				}
				
				DefaultTableModel model = (DefaultTableModel) AddBookoutView.table.getModel();

				while (model.getRowCount() > 0) {
					model.removeRow(model.getRowCount() - 1);
				}
				
			}
		});

		JButton btn_can = new JButton("取消");
		btn_can.setBounds(255, 210, 80, 30);
		btn_can.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				PayView.this.setVisible(false);
			}
		});

		new Thread(this).start();

		this.add(lbl_give);
		this.add(lbl_pay);
		this.add(lbl_money);
		this.add(txt_give);
		this.add(txt_pay);
		this.add(txt_money);
		this.add(btn_can);
		this.add(btn_ok);
		this.add(lbl_y);
		this.add(lbl_y2);
		this.add(lbl_y3);
	}

	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (txt_pay.getText().trim().equals("")) {

			} else {
				if (new Integer(txt_pay.getText().trim()) >= sum) {
					money = new Integer(txt_pay.getText()) - sum;
					txt_give.setText(money + "");
				}
			}
		}
	}
}
