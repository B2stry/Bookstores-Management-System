package com.view;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.dao.AdmininfoDao;
import com.dao.DeptDao;
import com.pojo.Admininfo;
import com.pojo.Dept;

public class AddAdmininfoView extends JDialog {
	JButton btn_cal;
	//final JTextField txt_atime;
	ObservingTextField tf_time;

	public AddAdmininfoView() {
		this.setSize(380, 550);
		this.setTitle("添加员工信息");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
	
		this.setModal(true);

		Toolkit tk = Toolkit.getDefaultToolkit();
		Image image = tk.createImage(this.getClass().getResource("bookstore.png")); /* 你的图标 */
		this.setIconImage(image);

		ImageIcon background = new ImageIcon(this.getClass().getResource("timg.jpg"));
		JLabel label = new JLabel(background);
		label.setBounds(0, 0, 380, 550);
		JPanel imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));

		JLabel lbl_title = new JLabel("新增员工");
		lbl_title.setBounds(125, 10, 200, 20);
		lbl_title.setFont(new Font("微软雅黑", Font.BOLD, 18));

		JLabel lbl_did = new JLabel("部门：");
		lbl_did.setBounds(30, 50, 100, 30);
		final JComboBox com_did = new JComboBox();
		com_did.setBounds(100, 50, 120, 30);
		DeptDao dao = new DeptDao();
		ArrayList<Dept> list = dao.getAllDept();
		for (Dept dept : list) {
			com_did.addItem(dept);
		}

		JLabel lbl_aname = new JLabel("姓名：");
		lbl_aname.setBounds(30, 100, 100, 30);
		final JTextField txt_aname = new JTextField();
		txt_aname.setBounds(100, 100, 100, 30);

		JLabel lbl_aphone = new JLabel("手机号码：");
		lbl_aphone.setBounds(30, 150, 110, 30);
		final JTextField txt_aphone = new JTextField();
		txt_aphone.setBounds(100, 150, 180, 30);

		JLabel lbl_adress = new JLabel("家庭住址：");
		lbl_adress.setBounds(30, 200, 110, 30);
		final JTextField txt_adress = new JTextField();
		txt_adress.setBounds(100, 200, 180, 30);

		JLabel lbl_atime = new JLabel("入职日期：");
		lbl_atime.setBounds(30, 250, 110, 30);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = new Date();
		String str = sdf.format(date);
		
		tf_time = new ObservingTextField();
		tf_time.setBounds(100, 250, 120, 30);
		tf_time.setEditable(false);
		tf_time.setText(str);

		btn_cal = new JButton("日历");
		btn_cal.setBounds(230, 250, 70, 30);
		btn_cal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				com.view.DatePicker dp = new com.view.DatePicker(tf_time, getLocale().CHINA);
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				java.util.Date selectedDate = dp.parseDate(tf_time.getText());
				
				dp.setSelectedDate(selectedDate);
				dp.start(tf_time);
				
			}
		});

		JLabel lbl_asfz = new JLabel("身份证号码：");
		lbl_asfz.setBounds(30, 300, 110, 30);
		final JTextField txt_asfz = new JTextField();
		txt_asfz.setBounds(100, 300, 180, 30);

		JLabel lbl_state = new JLabel("是否在职：");
		lbl_state.setBounds(30, 350, 110, 26);
		// 单选按钮
		final JCheckBox rb1 = new JCheckBox("是");
		rb1.setBounds(110, 350, 60, 26);

		final JCheckBox rb2 = new JCheckBox("否");
		rb2.setBounds(210, 350, 60, 26);

		// 实现 单选的功能
		rb1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (rb1.isSelected()) {
					rb2.setSelected(false);
				} else if (rb2.isSelected()) {
					rb2.setSelected(true);
				} else
					rb1.setSelected(true);
			}
		});

		rb2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (rb2.isSelected()) {
					rb1.setSelected(false);
				} else if (rb1.isSelected()) {
					rb1.setSelected(true);
				} else
					rb2.setSelected(true);
			}
		});

		rb1.setSelected(true);

		JButton btn_ok = new JButton("确认");
		btn_ok.setBounds(40, 400, 100, 40);
		btn_ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Dept dept = (Dept) com_did.getSelectedItem();
				int did = dept.getDid();
				String atutas = null;
				if (rb1.isSelected())
					atutas = "是";
				else if (rb2.isSelected())
					atutas = "否";
				String atime  = tf_time.getText().trim();
				
				Admininfo info = new Admininfo();
				if (txt_aname.getText().trim().equals("") || txt_aphone.getText().trim().equals("")
						|| txt_adress.getText().trim().equals("") || txt_asfz.getText().trim().equals("") || did == 0) {
					JOptionPane.showMessageDialog(null, "请检查输入的信息是否有空！");
				} else {
					info.setDid(did);
					info.setAname(txt_aname.getText().trim());
					info.setAphone(txt_aphone.getText().trim());
					info.setAdress(txt_adress.getText().trim());
					info.setAsfz(txt_asfz.getText().trim());
					info.setAtutas(atutas);
					info.setAtime(atime);
					
					AdmininfoDao dao = new AdmininfoDao();
					int n = dao.addAdmininfo(info);

					if (n > 0) {
					//	JOptionPane.showMessageDialog(null, "添加员工信息成功！");
						AddAdmininfoView.this.setVisible(false);
						AdmininfoView.bindAdmininfo();
						AddAdminView view = new AddAdminView(info);
						view.setVisible(true);
					} else
						JOptionPane.showMessageDialog(null, "添加员工信息失败！");
				}
			}
		});

		JButton btn_cancel = new JButton("取消");
		btn_cancel.setBounds(180, 400, 100, 40);
		btn_cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AddAdmininfoView.this.setVisible(false);
			}
		});

		this.add(btn_cancel);
		this.add(btn_ok);
		this.add(lbl_aname);
		this.add(txt_aname);
		this.add(lbl_aphone);
		this.add(txt_aphone);
		this.add(lbl_adress);
		this.add(txt_adress);
		this.add(lbl_asfz);
		this.add(txt_asfz);
		this.add(lbl_did);
		this.add(com_did);
		this.add(lbl_adress);
		this.add(txt_adress);
		this.add(lbl_title);
		this.add(lbl_state);
		this.add(rb1);
		this.add(rb2);
		this.add(lbl_atime);
		this.add(tf_time);
		this.add(btn_cal);
	}

}
