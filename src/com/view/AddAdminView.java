package com.view;

import java.awt.Image;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import com.dao.AdminDao;
import com.dao.AdmininfoDao;
import com.pojo.Admin;
import com.pojo.Admininfo;

public class AddAdminView extends JDialog {
	
	public AddAdminView(final Admininfo info){
		this.setSize(300, 420);
		this.setTitle("请添加员工登录信息！");
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
		
		JLabel lbl_name = new JLabel("员工姓名：");
		lbl_name.setBounds(25, 30, 80, 30);
		JComboBox  box_name = new JComboBox();
		box_name.setBounds(100, 30, 100, 30);
		box_name.addItem(info);
		
		JLabel lbl_user = new JLabel("用户名：");
		lbl_user.setBounds(25, 80, 80, 30);
		final JTextField txt_user = new JTextField();
		txt_user.setBounds(100, 80, 100, 30);
		
		JLabel lbl_pwd = new JLabel("密码：");
		lbl_pwd.setBounds(25, 130, 80, 30);
		final JPasswordField txt_pwd = new JPasswordField();
		txt_pwd.setBounds(100, 130, 100, 30);
		txt_pwd.setEchoChar('*');
		
		JLabel lbl_pass = new JLabel("确认密码：");
		lbl_pass.setBounds(25, 180, 80, 30);
		final JPasswordField txt_pass = new JPasswordField();
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
		
		JButton btn_ok = new JButton("确认");
		btn_ok.setBounds(60, 290, 120, 30);
		btn_ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AdmininfoDao dao = new AdmininfoDao();
				Admininfo ad = dao.getAdmininfoByname(info.getAname());
				int aid = ad.getAid();
				
				String adname = txt_user.getText();
				String adpwd = new String(txt_pwd.getPassword());
				String adpass = new String(txt_pass.getPassword());
				String adrole = (String) box_role.getSelectedItem();
				
				if(adpass.equals(adpwd)){
					Admin admin = new Admin();
					admin.setAid(aid);
					admin.setAdname(adname);
					admin.setAdpwd(adpwd);
					admin.setAdrole(adrole);
					
					AdminDao dao1 = new AdminDao();
					int n = dao1.addAdmin(admin);
					
					if(n>0){
						//JOptionPane.showMessageDialog(null, "添加成功！");
						AddAdminView.this.setVisible(false);
					}else
						JOptionPane.showMessageDialog(null, "添加失败！");
					
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

	/*public static void main(String[] args) {
		try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF(); // MacWinLookAndFeel
																				// 皮肤
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AdmininfoDao dao = new AdmininfoDao();
		Admininfo info = dao.getAdmininfo(11);
		JOptionPane.showMessageDialog(null, info);
		AddAdminView view = new AddAdminView(info);
		view.setVisible(true);
	}*/
}
