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
import com.pojo.Dept;

public class UpdateTypeView extends JDialog {
	private int tid;

	public UpdateTypeView(final int tid) {

		this.tid = tid;
		this.setSize(350, 200);
		this.setTitle("�޸�����");
		this.setResizable(false);// �ı��С
		this.setLocationRelativeTo(null); // ����
		this.setLayout(null); // ���ַ�ʽ ��(�����Ͻ�Ϊ
		this.setModal(true);

		Toolkit tk = Toolkit.getDefaultToolkit();
		Image image = tk.createImage(this.getClass().getResource("bookstore.png")); /* ���ͼ�� */
		this.setIconImage(image);

		ImageIcon background = new ImageIcon(this.getClass().getResource("timg.jpg"));
		JLabel label = new JLabel(background);
		label.setBounds(0, 0, 350, 300);
		JPanel imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));

		JLabel lbl_username = new JLabel("�޸�������ƣ�"); // ��ǩ ��ʾ�ı�
		lbl_username.setBounds(10, 20, 100, 30);

		final TypeDao dao = new TypeDao();
		com.pojo.Type type = dao.getTypeById(tid);
		// �ı��� �������û����뵥���ı�
		final JTextField txt_dname = new JTextField(type.getTname());
		txt_dname.setBounds(100, 20, 180, 30);

		JButton btn_ok = new JButton("ȷ��");
		btn_ok.setBounds(30, 80, 100, 30);
		btn_ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String tname = txt_dname.getText().trim();
				int n = dao.updateType(tname, tid);
				if (n > 0) {
					//JOptionPane.showMessageDialog(null, "�޸ĳɹ���");
					DeptView.bindDept();
					UpdateTypeView.this.setVisible(false);
				} else
					JOptionPane.showMessageDialog(null, "�޸�ʧ�ܣ�");

			}
		});

		JButton btn_cancel = new JButton("ȡ��");
		btn_cancel.setBounds(170, 80, 100, 30);
		btn_cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				UpdateTypeView.this.setVisible(false);
			}
		});

		this.add(lbl_username);
		this.add(txt_dname);
		this.add(btn_ok);
		this.add(btn_cancel);
	}
}
