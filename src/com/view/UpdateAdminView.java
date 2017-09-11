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
		this.setTitle("�޸�Ա����¼��Ϣ��");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setModal(true);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image image = tk.createImage(this.getClass().getResource("bookstore.png")); /* ���ͼ�� */
		this.setIconImage(image);
		
		ImageIcon background = new ImageIcon(this.getClass().getResource("timg.jpg"));
		JLabel label = new JLabel(background);
		label.setBounds(0, 0, 300, 460);
		JPanel imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
		
		AdminDao dao= new AdminDao();
		final Admin admin = dao.getAdmin(adid);
		
		JLabel lbl_name = new JLabel("Ա��������");
		lbl_name.setBounds(25, 30, 80, 30);
		JComboBox  box_name = new JComboBox();
		box_name.setBounds(100, 30, 100, 30);
		box_name.addItem(info);
		
		JLabel lbl_user = new JLabel("�û�����");
		lbl_user.setBounds(25, 80, 80, 30);
		final JTextField txt_user = new JTextField(admin.getAdname());
		txt_user.setBounds(100, 80, 100, 30);
		txt_user.setEditable(false);
		
		JLabel lbl_pwd = new JLabel("���룺");
		lbl_pwd.setBounds(25, 130, 80, 30);
		final JPasswordField txt_pwd = new JPasswordField(admin.getAdpwd());
		txt_pwd.setBounds(100, 130, 100, 30);
		txt_pwd.setEchoChar('*');
		
		JLabel lbl_pass = new JLabel("ȷ�����룺");
		lbl_pass.setBounds(25, 180, 80, 30);
		final JPasswordField txt_pass = new JPasswordField(admin.getAdpwd());
		txt_pass.setBounds(100, 180, 100, 30);
		txt_pass.setEchoChar('*');
		
		JLabel lbl_role = new JLabel("Ա��Ȩ�ޣ�");
		lbl_role.setBounds(25, 230, 80, 30);
	    final JComboBox box_role = new JComboBox();
		box_role.setBounds(100, 230, 100, 30);
		box_role.addItem("����Ա");
		box_role.addItem("�ɹ�Ա");
		box_role.addItem("����Ա");
		box_role.addItem("����Ա");
		box_role.setSelectedItem(admin.getAdrole());
		
		JButton btn_ok = new JButton("ȷ��");
		btn_ok.setBounds(60, 290, 120, 30);
		btn_ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(admin.getAdrole().equals("����Ա") && box_role.getSelectedItem().equals("����Ա") == false){
					JOptionPane.showMessageDialog(null, "���û�Ȩ����Ϣ�����޸ģ�");
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
							//JOptionPane.showMessageDialog(null, "�޸ĳɹ���");
							UpdateAdminView.this.setVisible(false);
							AdminView.bindAdmin();
						}else
							JOptionPane.showMessageDialog(null, "�޸�ʧ�ܣ�");
						
					}else{
						JOptionPane.showMessageDialog(null, "������������벻һ�£����������룡");
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
