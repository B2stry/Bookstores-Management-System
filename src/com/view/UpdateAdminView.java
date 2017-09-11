package com.view;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.dao.AdminDao;
import com.dao.AdmininfoDao;
import com.pojo.Admin;
import com.pojo.Admininfo;

public class UpdateAdminView extends JDialog {
	public UpdateAdminView(final Admininfo info, final int adid){
		this.setSize(300, 420);
		this.setTitle("修改员工登录信息！");
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
		
		AdminDao dao= new AdminDao();
		final Admin admin = dao.getAdmin(adid);
		
		JLabel lbl_name = new JLabel("员工姓名：");
		lbl_name.setBounds(25, 30, 80, 30);
		JComboBox  box_name = new JComboBox();
		box_name.setBounds(100, 30, 100, 30);
		box_name.addItem(info);
		
		JLabel lbl_user = new JLabel("用户名：");
		lbl_user.setBounds(25, 80, 80, 30);
		final JTextField txt_user = new JTextField(admin.getAdname());
		txt_user.setBounds(100, 80, 100, 30);
		txt_user.setEditable(false);
		
		JLabel lbl_pwd = new JLabel("密码：");
		lbl_pwd.setBounds(25, 130, 80, 30);
		final JPasswordField txt_pwd = new JPasswordField(admin.getAdpwd());
		txt_pwd.setBounds(100, 130, 100, 30);
		txt_pwd.setEchoChar('*');
		
		JLabel lbl_pass = new JLabel("确认密码：");
		lbl_pass.setBounds(25, 180, 80, 30);
		final JPasswordField txt_pass = new JPasswordField(admin.getAdpwd());
		txt_pass.setBounds(100, 180, 100, 30);
		txt_pass.setEchoChar('*');
		
		JLabel lbl_role = new JLabel("员工权限：");
		lbl_role.setBounds(25, 230, 80, 30);
	    final JComboBox box_role = new JComboBox();
		box_role.setBounds(100, 230, 100, 30);
		box_role.addItem("收银员");
		box_role.addItem("采购员");
		box_role.addItem("销售员");
		box_role.addItem("管理员");
		box_role.setSelectedItem(admin.getAdrole());
		
		JButton btn_ok = new JButton("确认");
		btn_ok.setBounds(60, 290, 120, 30);
		btn_ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(admin.getAdrole().equals("管理员") && box_role.getSelectedItem().equals("管理员") == false){
					JOptionPane.showMessageDialog(null, "该用户权限信息不可修改！");
				}else{
					AdmininfoDao dao = new AdmininfoDao();
					Admininfo ad = dao.getAdmininfoByname(info.getAname());
					int aid = ad.getAid();
					int adid = admin.getAdid();
					String adname = txt_user.getText();
					String adpwd = new String(txt_pwd.getPassword());
					String adpass = new String(txt_pass.getPassword());
					
					String adrole = (String) box_role.getSelectedItem();
					
					if(adpass.equals(adpwd)){
						
						AdminDao dao1 = new AdminDao();
						int n = dao1.updateAdmin(adname, adpwd, adrole, adid);
						
						if(n>0){
							//JOptionPane.showMessageDialog(null, "修改成功！");
							UpdateAdminView.this.setVisible(false);
							AdminView.bindAdmin();
						}else
							JOptionPane.showMessageDialog(null, "修改失败！");
						
					}else{
						JOptionPane.showMessageDialog(null, "两次输入的密码不一致！请重新输入！");
						txt_pwd.setText("");
						txt_pass.setText("");
						
					}
				}
			}
		});
		
		
		this.add(lbl_name);
		this.add(box_name);
		this.add(lbl_user);
		this.add(lbl_pwd);
		this.add(txt_user);
		this.add(txt_pwd);
		this.add(lbl_role);
		this.add(box_role);
		this.add(txt_pwd);
		this.add(txt_pass);
		this.add(txt_pass);
		this.add(lbl_pass);
		this.add(btn_ok);
	}
}
