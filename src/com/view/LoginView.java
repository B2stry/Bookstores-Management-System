package com.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.dao.AdminDao;
import com.pojo.Admin;

public class LoginView extends JFrame {
	public LoginView() {
		this.setSize(480, 380);
		this.setTitle("�ô�Ҳ�Ǹ�--��¼");

		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(null);

		Toolkit tk = Toolkit.getDefaultToolkit();
		Image image = tk.createImage(this.getClass().getResource("bookstore.png")); /* ���ͼ�� */
		this.setIconImage(image);

		ImageIcon background = new ImageIcon(this.getClass().getResource("6666.jpg"));
		JLabel label = new JLabel(background);
		label.setBounds(0, 180, 480, 200);
		JPanel imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));


		final JLabel lbl_image = new JLabel();
		lbl_image.setBounds(0, 0, 480, 155);
		lbl_image.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("qq1.png")).getImage().getScaledInstance(
				480, 155, new ImageIcon(this.getClass().getResource("qq1.png")).getImage().SCALE_DEFAULT)));

		final JLabel lbl_b = new JLabel();
		lbl_b.setBounds(110, 55, 40, 40);
		lbl_b.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("check.png")).getImage().getScaledInstance(
				40, 40, new ImageIcon(this.getClass().getResource("check.png")).getImage().SCALE_DEFAULT)));
		
		JLabel lbl_haodai = new JLabel("�ô�Ҳ�Ǹ�");
		lbl_haodai.setBounds(160, 55, 200, 40);
		lbl_haodai.setFont(new Font("΢���ź�", Font.CENTER_BASELINE,31));
		lbl_haodai.setForeground(Color.lightGray);
		
		new Thread() {
			public void run() {
				int i = 2;
				while (true) {
					try {
						Thread.sleep(1200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					lbl_image.setIcon(new ImageIcon(new ImageIcon(this.getClass().getResource("qq"+i+".png")).getImage().getScaledInstance(
							480, 155, new ImageIcon(this.getClass().getResource("qq"+i+".png")).getImage().SCALE_DEFAULT)));
					i++;
					if(i >2)
						i = 1;
				}
			};
		}.start();

		JLabel lbl_username = new JLabel("�û�����");
		lbl_username.setBounds(60, 170, 60, 30);
		lbl_username.setFont(new Font("΢���ź�", Font.BOLD, 13));
		final JTextField txt_username = new JTextField("admin");
		txt_username.setBounds(130, 170, 210, 30);

		JLabel lbl_password = new JLabel("��    �룺");
		lbl_password.setBounds(60, 210, 60, 30);
		lbl_password.setFont(new Font("΢���ź�", Font.BOLD, 13));

		final JPasswordField txt_password = new JPasswordField("admin");
		txt_password.setBounds(130, 210, 210, 30);
		txt_password.setEchoChar('*');

		txt_password.addKeyListener(new KeyListener() { // �����������̼���

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				if (e.getKeyChar() == KeyEvent.VK_ENTER) {
					String adname = txt_username.getText().trim();
					String adpwd = new String(txt_password.getPassword());

					AdminDao dao = new AdminDao();
					Admin admin = dao.checkLogin(adname, adpwd);

					if (adname.equals("")) {
						JOptionPane.showMessageDialog(null, "�û���Ϊ�գ�");
					} else {
						if (adpwd.equals("")) {
							JOptionPane.showMessageDialog(null, "����Ϊ�գ�");
						} else {
							if (admin.getAdid() >= 1) {
								MainView view = new MainView(admin);
								view.setVisible(true);
								LoginView.this.setVisible(false);
							} else {
								JOptionPane.showMessageDialog(null, "�û���������������������룡");
								txt_password.setText("");
							}
						}
					}
				}
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub

			}
		});

		JButton btn_login = new JButton("��   ¼");
		btn_login.setBounds(135, 260, 180, 30);
		btn_login.setFont(new Font("΢���ź�", Font.BOLD, 13));
		btn_login.setBackground(Color.BLUE);
		btn_login.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String adname = txt_username.getText().trim();
				String adpwd = new String(txt_password.getPassword());

				AdminDao dao = new AdminDao();
				Admin admin = dao.checkLogin(adname, adpwd);

				if (adname.equals("")) {
					JOptionPane.showMessageDialog(null, "�û���Ϊ�գ�");
				} else {
					if (adpwd.equals("")) {
						JOptionPane.showMessageDialog(null, "����Ϊ�գ�");
					} else {
						if (admin.getAdid() >= 1) {
							MainView view = new MainView(admin);
							view.setVisible(true);
							LoginView.this.setVisible(false);
						} else {
							JOptionPane.showMessageDialog(null, "�û���������������������룡");
							txt_password.setText("");
						}
					}
				}
			}
		});
		
		this.add(lbl_b);
		this.add(lbl_username);
		this.add(txt_username);
		this.add(lbl_password);
		this.add(txt_password);
		this.add(btn_login);
		this.add(lbl_image);
		lbl_image.add(lbl_haodai);
	}

	public static void main(String[] args) {
		try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF(); // MacWinLookAndFeel
																				// Ƥ��
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UIManager.put("RootPane.setupButtonVisible", false);
		LoginView view = new LoginView();
		view.setVisible(true);
	}
}
