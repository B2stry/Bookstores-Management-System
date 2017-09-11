package com.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.SimpleDateFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;

import com.pojo.Admin;

public class MainView extends JFrame implements Runnable ,TreeSelectionListener{
	JTree tree;
	Admin admin;
	
	public MainView(final Admin admin) {
		this.setSize(1000, 700);
		this.setTitle("好歹也是个书店管理系统    用户名：" + admin.getAdname() + "  职务：" + admin.getAdrole());
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		
		 this.admin = admin;
		this.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				int flag = JOptionPane.showConfirmDialog(null, "是否退出？", "退出程序", JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.OK_CANCEL_OPTION);

				if (flag == JOptionPane.OK_OPTION) {
					System.exit(0);
				} else if (flag == JOptionPane.CANCEL_OPTION) {
					MainView view = new MainView(admin);
					view.setVisible(true);
				}
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}
		});

		Toolkit tk = Toolkit.getDefaultToolkit();
		Image image = tk.createImage(this.getClass().getResource("bookstore.png")); // image.gif是你的图标
		this.setIconImage(image);

		ImageIcon background = new ImageIcon(this.getClass().getResource("666.jpg"));
		JLabel label = new JLabel(background);
		label.setBounds(170, 0, 900, 520);
		JPanel imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));

		ImageIcon background2 = new ImageIcon(this.getClass().getResource("timg.jpg"));
		JLabel label2 = new JLabel(background2);
		label2.setBounds(0, 520, 1000, 200);
		JPanel imagePanel2 = (JPanel) this.getContentPane();
		imagePanel2.setOpaque(false);
		this.getLayeredPane().add(label2, new Integer(Integer.MIN_VALUE));

		JLabel lbl_title = new JLabel("好歹也是个书店管理系统");
		lbl_title.setBounds(375, 100, 400, 100);
		lbl_title.setFont(new Font("微软雅黑", Font.BOLD, 35));
		lbl_title.setForeground(Color.GRAY);

		DefaultMutableTreeNode root = new DefaultMutableTreeNode("好歹也是个书店管理系统");
		
		tree = new JTree(root);
		tree.setBounds(0, 30, 170, 720);
		tree.setBackground(Color.lightGray);

		DefaultMutableTreeNode in = new DefaultMutableTreeNode("进货管理");
		root.add(in);
		
		DefaultMutableTreeNode in2 = new DefaultMutableTreeNode("图书管理");
		in.add(in2);
		DefaultMutableTreeNode in1 = new DefaultMutableTreeNode("入库信息表");
		in.add(in1);
		
		DefaultMutableTreeNode out = new DefaultMutableTreeNode("销售管理");
		root.add(out);
		
		DefaultMutableTreeNode out5 = new DefaultMutableTreeNode("销售管理");
		out.add(out5);
		DefaultMutableTreeNode out1 = new DefaultMutableTreeNode("销售统计图");
		out.add(out1);
		DefaultMutableTreeNode out2 = new DefaultMutableTreeNode("近7天");
		DefaultMutableTreeNode out3 = new DefaultMutableTreeNode("近30天");
		DefaultMutableTreeNode out4 = new DefaultMutableTreeNode("近3个月");
		out1.add(out2);
		out1.add(out3);
		out1.add(out4);
		
		DefaultMutableTreeNode all = new DefaultMutableTreeNode("销售额统计");
		root.add(all);
		DefaultMutableTreeNode all2 = new DefaultMutableTreeNode("上年度各月份销售额");
		all.add(all2);
		DefaultMutableTreeNode all1 = new DefaultMutableTreeNode("本年度各月份销售额");
		all.add(all1);

		
		DefaultMutableTreeNode set = new DefaultMutableTreeNode("系统管理");
		root.add(set);
		DefaultMutableTreeNode set1 = new DefaultMutableTreeNode("部门管理");
		set.add(set1);
		DefaultMutableTreeNode set2 = new DefaultMutableTreeNode("员工管理");
		set.add(set2);
		DefaultMutableTreeNode set3 = new DefaultMutableTreeNode("会员管理");
		set.add(set3);
		DefaultMutableTreeNode set4 = new DefaultMutableTreeNode("员工登录管理");
		set.add(set4);
		
		tree.addTreeSelectionListener(this);

		JMenuBar bar = new JMenuBar();
		bar.setSize(1000, 30);
		// 系统管理
		JMenu menu1 = new JMenu("系统管理");

		JMenuItem it1 = new JMenuItem("部门管理");
		ImageIcon icon = new ImageIcon(this.getClass().getResource("setting.png"));
		Image icon_s = icon.getImage().getScaledInstance(15, 15, icon.getImage().SCALE_DEFAULT);
		ImageIcon icon_ss = new ImageIcon(icon_s);
		it1.setIcon(icon_ss);
		it1.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (admin.getAdrole().equals("管理员")) {
					DeptView view = new DeptView();
				} else {
					JOptionPane.showMessageDialog(null, "没有访问权限！");
				}

			}
		});

		JMenuItem it2 = new JMenuItem("员工管理");
		ImageIcon icon7 = new ImageIcon(this.getClass().getResource("admin.png"));
		Image icon_a = icon7.getImage().getScaledInstance(15, 15, icon7.getImage().SCALE_DEFAULT);
		ImageIcon icon_aa = new ImageIcon(icon_a);
		it2.setIcon(icon_aa);
		it2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (admin.getAdrole().equals("管理员")) {
					AdmininfoView view = new AdmininfoView();
				} else {
					JOptionPane.showMessageDialog(null, "没有访问权限！");
				}
			}

		});

		JMenuItem it3 = new JMenuItem("会员管理");
		ImageIcon icon6 = new ImageIcon(this.getClass().getResource("vip.png"));
		Image icon_v = icon6.getImage().getScaledInstance(15, 15, icon6.getImage().SCALE_DEFAULT);
		ImageIcon icon_vv = new ImageIcon(icon_v);
		it3.setIcon(icon_vv);
		it3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (admin.getAdrole().equals("管理员") || admin.getAdrole().equals("收银员")
						|| admin.getAdrole().equals("销售员")) {
					ClientView view = new ClientView();
				} else {
					JOptionPane.showMessageDialog(null, "没有访问权限！");
				}

			}
		});

		JMenuItem it4 = new JMenuItem("注销");
		ImageIcon redo = new ImageIcon(this.getClass().getResource("redo.png"));
		Image redo2 = redo.getImage().getScaledInstance(15, 15, redo.getImage().SCALE_DEFAULT);
		ImageIcon redo3 = new ImageIcon(redo2);
		it4.setIcon(redo3);
		it4.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				MainView.this.setVisible(false);
				LoginView view = new LoginView();
				view.setVisible(true);
			}
		});

		JMenuItem it5 = new JMenuItem("退出");
		ImageIcon icon8 = new ImageIcon(this.getClass().getResource("power.png"));
		Image icon_e = icon8.getImage().getScaledInstance(15, 15, icon8.getImage().SCALE_DEFAULT);
		ImageIcon icon_ee = new ImageIcon(icon_e);
		it5.setIcon(icon_ee);
		it5.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int flag = JOptionPane.showConfirmDialog(null, "是否退出？", "退出程序", JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.OK_CANCEL_OPTION);

				if (flag == JOptionPane.OK_OPTION) {
					System.exit(0);
				}
			}
		});

		// 书籍管理
		JMenu menu2 = new JMenu("书籍管理");

		JMenuItem it6 = new JMenuItem("图书管理");
		ImageIcon icon9 = new ImageIcon(this.getClass().getResource("book.png"));

		Image icon_st = icon9.getImage().getScaledInstance(15, 15, icon9.getImage().SCALE_DEFAULT);
		ImageIcon icon_stt = new ImageIcon(icon_st);
		it6.setIcon(icon_stt);
		it6.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (admin.getAdrole().equals("管理员") || admin.getAdrole().equals("理货员")) {
					BooksinfoView view = new BooksinfoView(admin);
					view.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "没有访问权限！");
				}

			}
		});

		JMenuItem it8 = new JMenuItem("类型管理");
		ImageIcon icon11 = new ImageIcon(this.getClass().getResource("item.png"));
		Image icon_s11 = icon11.getImage().getScaledInstance(15, 15, icon11.getImage().SCALE_DEFAULT);
		ImageIcon icon_ss11 = new ImageIcon(icon_s11);
		it8.setIcon(icon_ss11);
		it8.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (admin.getAdrole().equals("管理员") || admin.getAdrole().equals("理货员")) {
					TypeView view = new TypeView();
					view.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "没有访问权限！");
				}
			}
		});

		JMenuItem it9 = new JMenuItem("供应商管理");
		ImageIcon icon_mess = new ImageIcon(this.getClass().getResource("mess.png"));
		Image icon_messs = icon_mess.getImage().getScaledInstance(15, 15, icon_mess.getImage().SCALE_DEFAULT);
		ImageIcon icon_ss12 = new ImageIcon(icon_messs);
		it9.setIcon(icon_ss12);
		it9.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (admin.getAdrole().equals("管理员") || admin.getAdrole().equals("采购员")) {
					SupplierView view = new SupplierView();
					view.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "没有访问权限！");
				}

			}
		});

		JMenuItem it10 = new JMenuItem("员工登录管理");
		ImageIcon icon13 = new ImageIcon(this.getClass().getResource("login.png"));
		Image icon_s13 = icon13.getImage().getScaledInstance(15, 15, icon13.getImage().SCALE_DEFAULT);
		ImageIcon icon_ss13 = new ImageIcon(icon_s13);
		it10.setIcon(icon_ss13);
		it10.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (admin.getAdrole().equals("管理员")) {
					AdminView view = new AdminView();
					view.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "没有访问权限！");
				}

			}
		});

		// 进货管理
		JMenu menu3 = new JMenu("进货管理");

		JMenuItem it11 = new JMenuItem("图书入库信息");
		ImageIcon icon14 = new ImageIcon(this.getClass().getResource("print.png"));
		Image icon_s14 = icon14.getImage().getScaledInstance(15, 15, icon14.getImage().SCALE_DEFAULT);
		ImageIcon icon_ss14 = new ImageIcon(icon_s14);
		it11.setIcon(icon_ss14);
		it11.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				BookinView view = new BookinView();
				view.setVisible(true);
			}
		});

		// 销售管理
		JMenu menu4 = new JMenu("销售管理");

		JMenuItem it12 = new JMenuItem("商品销售");
		ImageIcon icon15 = new ImageIcon(this.getClass().getResource("cost.png"));
		Image icon_s15 = icon15.getImage().getScaledInstance(15, 15, icon15.getImage().SCALE_DEFAULT);
		ImageIcon icon_ss15 = new ImageIcon(icon_s15);
		it12.setIcon(icon_ss15);
		it12.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (admin.getAdrole().equals("管理员") || admin.getAdrole().equals("收银员")
						|| admin.getAdrole().equals("销售员")) {
					AddBookoutView view = new AddBookoutView(admin);
					view.setVisible(true);
					MainView.this.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "没有访问权限！");
				}
			}
		});

		JMenuItem it14 = new JMenuItem("图书销售信息");
		ImageIcon icon16 = new ImageIcon(this.getClass().getResource("notepad.png"));
		Image icon_s16 = icon16.getImage().getScaledInstance(15, 15, icon16.getImage().SCALE_DEFAULT);
		ImageIcon icon_ss16 = new ImageIcon(icon_s16);
		it14.setIcon(icon_ss16);
		it14.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				BookoutView view = new BookoutView();
				view.setVisible(true);
			}
		});

		ImageIcon icon1 = new ImageIcon(this.getClass().getResource("exit.png"));
		Image icon_exit = icon1.getImage().getScaledInstance(60, 45, icon1.getImage().SCALE_DEFAULT);
		ImageIcon icon_exit2 = new ImageIcon(icon_exit);
		JButton btn_exit = new JButton("退  出", icon_exit2);
		btn_exit.setVerticalTextPosition(JButton.BOTTOM);
		btn_exit.setHorizontalTextPosition(JButton.CENTER);
		btn_exit.setBounds(800, 520, 130, 80);
		btn_exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int flag = JOptionPane.showConfirmDialog(null, "是否退出？", "退出程序", JOptionPane.OK_CANCEL_OPTION,
						JOptionPane.OK_CANCEL_OPTION);

				if (flag == JOptionPane.OK_OPTION) {
					System.exit(0);
				}
			}
		});

		ImageIcon icon2 = new ImageIcon(this.getClass().getResource("book.png"));
		Image icon_select = icon2.getImage().getScaledInstance(60, 45, icon2.getImage().SCALE_DEFAULT);
		ImageIcon icon_select2 = new ImageIcon(icon_select);
		JButton btn_select = new JButton("图书管理", icon_select2);
		btn_select.setVerticalTextPosition(JButton.BOTTOM);
		btn_select.setHorizontalTextPosition(JButton.CENTER);
		btn_select.setBounds(200, 520, 130, 80);
		btn_select.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (admin.getAdrole().equals("管理员") || admin.getAdrole().equals("理货员")) {
					BooksinfoView view = new BooksinfoView(admin);
					view.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(null, "没有访问权限！");
				}
			}
		});

		ImageIcon icon3 = new ImageIcon(this.getClass().getResource("cost.png"));
		Image icon_set = icon3.getImage().getScaledInstance(60, 45, icon3.getImage().SCALE_DEFAULT);
		ImageIcon icon_setting = new ImageIcon(icon_set);
		JButton btn_cost = new JButton("商品销售", icon_setting);
		btn_cost.setVerticalTextPosition(JButton.BOTTOM);
		btn_cost.setHorizontalTextPosition(JButton.CENTER);
		btn_cost.setBounds(650, 520, 130, 80);
		btn_cost.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (admin.getAdrole().equals("管理员") || admin.getAdrole().equals("收银员")
						|| admin.getAdrole().equals("销售员")) {
					AddBookoutView view = new AddBookoutView(admin);
					view.setVisible(true);
					MainView.this.setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "没有访问权限！");
				}
			}
		});

		ImageIcon icon4 = new ImageIcon(this.getClass().getResource("admin.png"));
		Image icon_admin = icon4.getImage().getScaledInstance(60, 45, icon4.getImage().SCALE_DEFAULT);
		ImageIcon icon_admin2 = new ImageIcon(icon_admin);
		JButton btn_admin = new JButton("员工管理", icon_admin2);
		btn_admin.setVerticalTextPosition(JButton.BOTTOM);
		btn_admin.setHorizontalTextPosition(JButton.CENTER);
		btn_admin.setBounds(350, 520, 130, 80);
		btn_admin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (admin.getAdrole().equals("管理员")) {
					AdmininfoView view = new AdmininfoView();
				} else {
					JOptionPane.showMessageDialog(null, "没有访问权限！");
				}
			}
		});

		ImageIcon icon5 = new ImageIcon(this.getClass().getResource("vip.png"));
		Image icon_vip = icon5.getImage().getScaledInstance(60, 45, icon5.getImage().SCALE_DEFAULT);
		ImageIcon icon_vip2 = new ImageIcon(icon_vip);
		JButton btn_vip = new JButton("会员管理", icon_vip2);
		btn_vip.setVerticalTextPosition(JButton.BOTTOM);
		btn_vip.setHorizontalTextPosition(JButton.CENTER);
		btn_vip.setBounds(500, 520, 130, 80);
		btn_vip.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (admin.getAdrole().equals("管理员") || admin.getAdrole().equals("收银员")
						|| admin.getAdrole().equals("销售员")) {
					ClientView view = new ClientView();
				} else {
					JOptionPane.showMessageDialog(null, "没有访问权限！");
				}
			}
		});

		lbl_time.setBounds(790, 0, 200, 30);
		lbl_time.setFont(new Font("微软雅黑", Font.BOLD, 12));
		lbl_time.setForeground(Color.GRAY);
		
		new Thread(this).start();
		
		this.add(lbl_time);
		this.add(lbl_title);
		this.add(btn_exit);
		this.add(btn_select);
		this.add(btn_cost);
		this.add(btn_admin);
		this.add(btn_vip);
		this.add(bar);
		this.add(tree);

		bar.add(menu1);
		bar.add(menu2);
		bar.add(menu3);
		bar.add(menu4);

		menu1.add(it1);
		menu1.add(it2);
		menu1.add(it3);
		menu1.add(it10);
		menu1.add(it4);
		menu1.add(it5);

		menu2.add(it8);
		menu2.add(it9);

		menu3.add(it6);
		menu3.add(it11);

		menu4.add(it12);
		menu4.add(it14);

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
			lbl_time.setText(getTime());
		}
	}

	JLabel lbl_time = new JLabel(getTime());

	private String getTime() {
		java.util.Date date = new java.util.Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
		String str = sdf.format(date);
		return str;
	}

	@Override
	public void valueChanged(TreeSelectionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==tree){
			DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
			
			if(node.isLeaf()){
				String str = node.toString();
				if("本年度各月份销售额".equals(str)){
					BookToView view = new BookToView();
				}
				
				if("上年度各月份销售额".equals(str)){
					LastToView view = new LastToView();
				}
				
				if("近7天".equals(str)){
					BookoutChartView view = new BookoutChartView(7);
				}
				if("近30天".equals(str)){
					BookoutChartView view = new BookoutChartView(30);
				}
				if("近3个月".equals(str)){
					BookoutChartView view = new BookoutChartView(90);
				}
				if("部门管理".equals(str)){
					if (admin.getAdrole().equals("管理员")) {
						DeptView view = new DeptView();
					} else {
						JOptionPane.showMessageDialog(null, "没有访问权限！");
					}
				}
				if("员工管理".equals(str)){
					if (admin.getAdrole().equals("管理员")) {
						AdmininfoView view = new AdmininfoView();
					} else {
						JOptionPane.showMessageDialog(null, "没有访问权限！");
					}
				}
				if("会员管理".equals(str)){
					if (admin.getAdrole().equals("管理员") || admin.getAdrole().equals("收银员")
							|| admin.getAdrole().equals("销售员")) {
						ClientView view = new ClientView();
					} else {
						JOptionPane.showMessageDialog(null, "没有访问权限！");
					}
				}
				
				if("入库信息表".equals(str)){
					BookinView view = new BookinView();
					view.setVisible(true);
				}
				
				if("图书管理".equals(str)){
					if (admin.getAdrole().equals("管理员") || admin.getAdrole().equals("理货员")) {
						BooksinfoView view = new BooksinfoView(admin);
						view.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "没有访问权限！");
					}
				}
				
				if("销售管理".equals(str)){
					if (admin.getAdrole().equals("管理员") || admin.getAdrole().equals("收银员")
							|| admin.getAdrole().equals("销售员")) {
						AddBookoutView view = new AddBookoutView(admin);
						view.setVisible(true);
						MainView.this.setVisible(false);
					} else {
						JOptionPane.showMessageDialog(null, "没有访问权限！");
					}
				}
				
				
				if("员工登录管理".equals(str)){
					if (admin.getAdrole().equals("管理员")) {
						AdminView view = new AdminView();
						view.setVisible(true);
					} else {
						JOptionPane.showMessageDialog(null, "没有访问权限！");
					}
				}
				
			}
		}
	}
}
