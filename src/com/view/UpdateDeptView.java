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
import com.pojo.Dept;

public class UpdateDeptView extends JDialog {
	private int did;
	public UpdateDeptView(final int did) {
		this.did = did;
		this.setSize(350, 200);
		this.setTitle("修改部门");
		this.setResizable(false);// 改变大小
		this.setLocationRelativeTo(null); // 居中
		this.setLayout(null); // 布局方式 空(在左上角为
		this.setModal(true);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image image = tk.createImage(this.getClass().getResource("bookstore.png")); /* 你的图标 */
		this.setIconImage(image);

		ImageIcon background = new ImageIcon(this.getClass().getResource("timg.jpg"));
		JLabel label = new JLabel(background);
		label.setBounds(0, 0, 350, 300);
		JPanel imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));

		JLabel lbl_username = new JLabel("修改部门名称："); // 标签 提示文本
		lbl_username.setBounds(10, 20, 100, 30);
		
		final DeptDao dao = new DeptDao();
		Dept dept = dao.getDeptById(did);
		// 文本框 ：接收用户输入单行文本
	    final JTextField txt_dname = new JTextField(dept.getDname());
	    txt_dname.setBounds(100, 20, 180, 30);


		JButton btn_ok = new JButton("确定");
		btn_ok.setBounds(30, 80, 100, 30);
		btn_ok.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String dname = txt_dname.getText().trim();
				int n = dao.updateDept(dname, did);
				if(n>0){
					//JOptionPane.showMessageDialog(null,"修改成功！");
					DeptView.bindDept();
					UpdateDeptView.this.setVisible(false);
				}
				else
					JOptionPane.showMessageDialog(null, "修改失败！");
		
			}
		});

		JButton btn_cancel = new JButton("取消");
		btn_cancel.setBounds(170, 80, 100, 30);
		btn_cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				UpdateDeptView.this.setVisible(false);
			}
		});

		this.add(lbl_username);
		this.add(txt_dname);
		this.add(btn_ok);
		this.add(btn_cancel);
	}
}
