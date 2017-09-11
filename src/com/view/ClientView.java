package com.view;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.dao.ClientDao;
import com.pojo.Client;

public class ClientView extends JFrame{
	
	JScrollPane js;
	static JTable table;
	
	public ClientView(){
		this.setSize(500, 600);
		this.setTitle("会员管理");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setVisible(true);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image image = tk.createImage(this.getClass().getResource("bookstore.png")); /* 你的图标 */
		this.setIconImage(image);
		
		ImageIcon background = new ImageIcon(this.getClass().getResource("666.jpg"));
		JLabel label = new JLabel(background);
		label.setBounds(0, 250, 500, 350);
		JPanel imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
		

		ImageIcon background2 = new ImageIcon(this.getClass().getResource("timg.jpg"));
		JLabel label2 = new JLabel(background2);
		label2.setBounds(0, 0, 500, 250);
		JPanel imagePanel2 = (JPanel) this.getContentPane();
		imagePanel2.setOpaque(false);
		this.getLayeredPane().add(label2, new Integer(Integer.MIN_VALUE));

		
		js = new JScrollPane();
		js.setBounds(10, 70, 425, 440);

		table = new JTable();

		DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
		cr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		table.setDefaultRenderer(Object.class, cr);

		js.setViewportView(table);
		bindClient();
		
		ImageIcon icon = new ImageIcon(this.getClass().getResource("add.png"));
		Image icon_add = icon.getImage().getScaledInstance(30, 20, icon.getImage().SCALE_DEFAULT);
		ImageIcon icon_vip2 = new ImageIcon(icon_add);
		JButton btn_add = new JButton("新增会员", icon_vip2);
		btn_add.setVerticalTextPosition(JButton.BOTTOM);
		btn_add.setHorizontalTextPosition(JButton.CENTER);
		btn_add.setBounds(170, 10, 100, 50);
		btn_add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AddClientView view = new AddClientView();
				view.setVisible(true);
			}
		});
		
		this.add(js);
		this.add(btn_add);
		
	}
	
	static public void bindClient() {

		((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

		DefaultTableModel dtm = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};

		dtm.addColumn("会员编号");
		dtm.addColumn("姓名");
		dtm.addColumn("性别");
		dtm.addColumn("联系电话");
		dtm.addColumn("用户积分");
	
		ClientDao dao = new ClientDao();
		ArrayList<Client> list = dao.getAllClient();

		for (Client client : list) {
			Vector vec = new Vector();

			vec.add(client.getCid());
			vec.add(client.getCname());
			vec.add(client.getCsex());
			vec.add(client.getCphone());
			vec.add(client.getCnum());
		
			dtm.addRow(vec);
		}
		
		table.setModel(dtm);
		
/*		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(0);*/
	}

}
