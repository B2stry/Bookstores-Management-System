package com.view;

import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.dao.SupplierDao;
import com.pojo.Supplier;

public class AddSupplierView extends JDialog {
	public AddSupplierView() {
		this.setSize(360, 450);
		this.setTitle("��ӹ�Ӧ��");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setModal(true);

		Toolkit tk = Toolkit.getDefaultToolkit();
		Image image = tk.createImage(this.getClass().getResource("bookstore.png")); /* ���ͼ�� */
		this.setIconImage(image);

		ImageIcon background = new ImageIcon(this.getClass().getResource("timg.jpg"));
		JLabel label = new JLabel(background);
		label.setBounds(0, 0, 400, 500);
		JPanel imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));

		JLabel lbl_title = new JLabel("������Ӧ��");
		lbl_title.setBounds(100, 10, 200, 30);
		lbl_title.setFont(new Font("΢���ź�", Font.BOLD, 18));

		JLabel lbl_aname = new JLabel("��Ӧ��������");
		lbl_aname.setBounds(30, 70, 80, 30);
		final JTextField txt_aname = new JTextField();
		txt_aname.setBounds(120, 70, 80, 30);

		JLabel lbl_alink = new JLabel("��ϵ��������");
		lbl_alink.setBounds(30, 130, 80, 30);
		final JTextField txt_alink = new JTextField();
		txt_alink.setBounds(120, 130, 80, 30);

		JLabel lbl_address = new JLabel("��ַ��");
		lbl_address.setBounds(30, 190, 80, 30);
		final JTextField txt_address = new JTextField();
		txt_address.setBounds(120, 190, 130, 30);

		JLabel lbl_aphone = new JLabel("�绰���룺");
		lbl_aphone.setBounds(30, 250, 80, 30);
		final JTextField txt_aphone = new JTextField();
		txt_aphone.setBounds(120, 250, 130, 30);

		JButton btn_ok = new JButton("ȷ��");
		btn_ok.setBounds(40, 320, 90, 30);
		btn_ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String sname = txt_aname.getText();
				String slink = txt_alink.getText();
				String saddress = txt_address.getText();
				String sphone = txt_aphone.getText();

				String regex = "[\u4E00-\u9FA5]*";
				String regex2 = "1[3578][0-9]{9}";
				String regex3 = "[\u4E00-\u9FA5]{3,20}";
				
				if (sname.matches(regex) && slink.matches(regex) && sphone.matches(regex2)
						&& saddress.matches(regex3)) {
					
					Supplier su = new Supplier();
					su.setSname(sname);
					su.setSlink(slink);
					su.setAsddress(saddress);
					su.setSphone(sphone);

					SupplierDao dao = new SupplierDao();
					int n = dao.addSupplier(su);

					if (n > 0) {
					//	JOptionPane.showMessageDialog(null, "��ӹ�Ӧ�̳ɹ���");
						SupplierView.bindSupplier();
						AddSupplierView.this.setVisible(false);
					} else
						JOptionPane.showMessageDialog(null, "��ӹ�Ӧ��ʧ�ܣ�");
				} else {
					JOptionPane.showMessageDialog(null, "���벻ƥ�䣡���������룡");
				}
			}
		});

		JButton btn_cancel = new JButton("ȡ��");
		btn_cancel.setBounds(170, 320, 90, 30);
		btn_cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AddSupplierView.this.setVisible(false);
			}
		});

		this.add(btn_ok);
		this.add(btn_cancel);
		this.add(txt_aphone);
		this.add(lbl_aphone);
		this.add(lbl_address);
		this.add(txt_address);
		this.add(lbl_alink);
		this.add(txt_alink);
		this.add(lbl_aname);
		this.add(txt_aname);
		this.add(lbl_title);
	}

}
