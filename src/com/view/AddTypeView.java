package com.view;

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

import com.dao.DeptDao;
import com.dao.TypeDao;

public class AddTypeView extends JDialog {
	public AddTypeView() {
		this.setSize(350, 200);
		this.setTitle("��������");
		this.setResizable(false);// �ı��С
		this.setLocationRelativeTo(null); // ����
		this.setLayout(null); // ���ַ�ʽ ��
		this.setModal(true);

		Toolkit tk = Toolkit.getDefaultToolkit();
		Image image = tk.createImage(this.getClass().getResource("bookstore.png")); /* ���ͼ�� */
		this.setIconImage(image);

		ImageIcon background = new ImageIcon(this.getClass().getResource("timg.jpg"));
		JLabel label = new JLabel(background);
		label.setBounds(0, 0, 350, 200);
		JPanel imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));

		JLabel lbl_username = new JLabel("�����������ƣ�"); // ��ǩ ��ʾ�ı�
		lbl_username.setBounds(10, 20, 100, 30);
		// �ı��� �������û����뵥���ı�
		final JTextField txt_tname = new JTextField();
		txt_tname.setBounds(100, 20, 180, 30);

		JButton btn_ok = new JButton("ȷ��");
		btn_ok.setBounds(30, 80, 100, 30);
		btn_ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				TypeDao dao = new TypeDao();
				String tname = txt_tname.getText().trim();
				String regex = "[\u4E00-\u9FA5]{2,15}";
				
				if (tname.matches(regex)) {
					int n = dao.addType(tname);
					if (n >= 1){
						//JOptionPane.showMessageDialog(null, "�����ɹ���");
						TypeView.bindType();
					}
					else
						JOptionPane.showMessageDialog(null, "����ʧ�ܣ�");
			
				} else {
					JOptionPane.showMessageDialog(null, "���벻ƥ�䣡���������룡");
				}
			}
		});
		JButton btn_cancel = new JButton("ȡ��");
		btn_cancel.setBounds(170, 80, 100, 30);
		btn_cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AddTypeView.this.setVisible(false);
			}
		});

		this.add(lbl_username);
		this.add(txt_tname);
		this.add(btn_ok);
		this.add(btn_cancel);

	}
}
