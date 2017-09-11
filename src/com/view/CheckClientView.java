package com.view;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.dao.ClientDao;
import com.dao.DeptDao;
import com.pojo.Booksinfo;
import com.pojo.Client;

public class CheckClientView extends JDialog {

	public CheckClientView(final int sum) {
		this.setSize(350, 200);
		this.setTitle("��Ա��Ϣ��ѯ");
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

		JLabel lbl_cid = new JLabel("��Ա��ţ�"); // ��ǩ ��ʾ�ı�
		lbl_cid.setBounds(20, 20, 100, 30);
		// �ı��� �������û����뵥���ı�
		final JTextField txt_id = new JTextField();
		txt_id.setBounds(90, 20, 180, 30);

		JButton btn_ok = new JButton("ȷ��");
		btn_ok.setBounds(30, 80, 100, 30);
		btn_ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String regex = "[0-9]";

				if (txt_id.getText().matches(regex)) {
					ClientDao dao = new ClientDao();
					Client c = dao.getClientById(new Integer(txt_id.getText()));

					int flag = JOptionPane.showConfirmDialog(null, "      ��Ա������" + c.getCname(), "��֤",
							JOptionPane.YES_NO_OPTION);

					if (flag == JOptionPane.YES_OPTION) {
						int n = dao.UpdateCnum(sum, new Integer(txt_id.getText()));

						if (n > 0) {
							ShowBookView.list = new ArrayList<Booksinfo>();
							AddBookoutView.sum = 0;
							CheckClientView.this.setVisible(false);
						}

					}
				}
			}
		});

		JButton btn_cancel = new JButton("ȡ��");
		btn_cancel.setBounds(170, 80, 100, 30);
		btn_cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CheckClientView.this.setVisible(false);
			}
		});

		this.add(lbl_cid);
		this.add(txt_id);
		this.add(btn_ok);
		this.add(btn_cancel);
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

		CheckClientView view = new CheckClientView(100);
		view.setVisible(true);
	}
}
