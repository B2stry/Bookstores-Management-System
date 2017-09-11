package com.view;

import java.awt.Font;
import java.awt.Image;
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
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import com.dao.ClientDao;
import com.pojo.Client;

public class AddClientView extends JDialog {
	public AddClientView(){
		this.setSize(270, 380);
		this.setTitle("添加会员");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setModal(true);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image image = tk.createImage(this.getClass().getResource("bookstore.png")); /* 你的图标 */
		this.setIconImage(image);
		
		ImageIcon background = new ImageIcon(this.getClass().getResource("timg.jpg"));
		JLabel label = new JLabel(background);
		label.setBounds(0, 0, 400, 400);
		JPanel imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
		
		JLabel lbl_title = new JLabel("新增会员");
		lbl_title.setBounds(80, 10, 200, 30);
		lbl_title.setFont(new Font("微软雅黑", Font.BOLD, 16));
		
		JLabel lbl_cname = new JLabel("姓名：");
		lbl_cname.setBounds(20,60 , 80, 30);
		final JTextField txt_cname = new JTextField();
		txt_cname.setBounds(80, 62, 80, 25);
		
		JLabel lbl_sex = new JLabel("性别：");
		lbl_sex.setBounds(20, 180, 80, 30);
		final JCheckBox rb1=new JCheckBox("男");
		rb1.setBounds(80, 180, 50, 26);
		
		 final JCheckBox rb2=new JCheckBox("女");
		rb2.setBounds(135, 180, 50, 26);
		
		//实现 单选的功能
		rb1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(rb1.isSelected()){
					rb2.setSelected(false);				
				}else if(rb2.isSelected()){
					rb2.setSelected(true);
				}else
					rb1.setSelected(true);
			}
		});
		
		rb2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(rb2.isSelected()){
					rb1.setSelected(false);
				}else if(rb1.isSelected()){
					rb1.setSelected(true);
				}else
					rb2.setSelected(true);
			}
		});
		
		rb1.setSelected(true);
		
		JLabel lbl_cphone = new JLabel("手机号码：");
		lbl_cphone.setBounds(15, 120	,80, 30);
		final JTextField txt_cphone = new JTextField();
		txt_cphone.setBounds(80, 122, 100, 25);
		
		JButton btn_ok = new JButton("确认");
		btn_ok.setBounds(15, 250, 80, 30);
		btn_ok.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String regex = "[\u4E00-\u9FA5]{2,4}";
				String regex2 = "1[3578][0-9]{9}";
				
				if(txt_cname.getText().matches(regex) && txt_cphone.getText().matches(regex2)){
					String sex = null;
					if(rb1.isSelected())
						sex = "男";
					else if(rb2.isSelected())
						sex = "女";
					
					Client client = new Client();
					client.setCname(txt_cname.getText().trim());
					client.setCsex(sex);
					client.setCphone(txt_cphone.getText().trim());
					
					ClientDao dao = new ClientDao();
					int n = dao.addClient(client);
					
					if(n>0){
					//	JOptionPane.showMessageDialog(null, "添加会员成功！");
						ClientView.bindClient();
						AddClientView.this.setVisible(false);
						
					}else
						JOptionPane.showMessageDialog(null, "添加会员失败！");
					
				}else{
					JOptionPane.showMessageDialog(null, "请检查姓名和手机号码是否输入正确！");
				}
			}
		});
		
		JButton btn_cancel = new JButton("取消");
		btn_cancel.setBounds(120, 250, 80, 30);
		btn_cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AddClientView.this.setVisible(false);
			}
		});
		
		this.add(btn_cancel);
		this.add(btn_ok);
		this.add(lbl_cname);
		this.add(txt_cname);
		this.add(lbl_cphone);
		this.add(txt_cphone);
		this.add(lbl_sex);
		this.add(rb1);
		this.add(rb2);
		this.add(lbl_title);
	}
	
	public static void main(String[] args) {
		try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF(); // MacWinLookAndFeel
																				// 皮肤
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AddClientView view = new AddClientView();
		view.setVisible(true);
	}
}
