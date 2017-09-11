package com.view;

import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.dao.AdminDao;
import com.dao.AdmininfoDao;
import com.pojo.Admin;
import com.pojo.Admininfo;

public class AdminView extends JFrame{
	JScrollPane js;
	static JTable table;
	
	public AdminView(){
		this.setSize(500, 600);
		this.setTitle("员工登录信息管理");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setVisible(true);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image image = tk.createImage(this.getClass().getResource("bookstore.png")); /* 你的图标 */
		this.setIconImage(image);
		
		ImageIcon background = new ImageIcon(this.getClass().getResource("666.jpg"));
		JLabel label = new JLabel(background);
		label.setBounds(0, 0, 500, 600);
		JPanel imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
		
		js = new JScrollPane();
		js.setBounds(10, 70, 425, 440);

		table = new JTable();
		table.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub
				if (e.getClickCount() == 2) {
					Point p = e.getPoint();
					int row = table.rowAtPoint(p);
					int column = table.columnAtPoint(p);
					
					String adname = (String) table.getValueAt(row, 1);
					int adid = (Integer) table.getValueAt(row, 0);
					AdmininfoDao dao = new AdmininfoDao();
					Admininfo info  = dao.getAdmininfoByname(adname);
					UpdateAdminView view = new UpdateAdminView(info, adid);
					view.setVisible(true);
					
					}
			}
		});

		DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
		cr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		table.setDefaultRenderer(Object.class, cr);

		js.setViewportView(table);
		bindAdmin();
		
		ImageIcon icon = new ImageIcon(this.getClass().getResource("update.png"));
		Image icon_add = icon.getImage().getScaledInstance(30, 20, icon.getImage().SCALE_DEFAULT);
		ImageIcon icon_vip2 = new ImageIcon(icon_add);
		JButton btn_add = new JButton("修改员工信息", icon_vip2);
		btn_add.setVerticalTextPosition(JButton.BOTTOM);
		btn_add.setHorizontalTextPosition(JButton.CENTER);
		btn_add.setBounds(170, 10, 100, 50);
		btn_add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int row = table.getSelectedRow();
				if (row < 0)
					JOptionPane.showMessageDialog(null, "请选中要修改的行！");
				else {
					String adname = (String) table.getValueAt(row, 1);
					int adid = (Integer) table.getValueAt(row, 0);
					AdmininfoDao dao = new AdmininfoDao();
					Admininfo info  = dao.getAdmininfoByname(adname);
					UpdateAdminView view = new UpdateAdminView(info, adid);
					view.setVisible(true);
				}
			}
		});
		
		this.add(js);
		this.add(btn_add);	
	}
	
	static public void bindAdmin() {

		((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

		DefaultTableModel dtm = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};

		dtm.addColumn("员工编号");
		dtm.addColumn("员工姓名");
		dtm.addColumn("用户名");
		dtm.addColumn("密码");
		dtm.addColumn("权限");
		
		AdminDao dao = new AdminDao();
		ArrayList<Admin> list =  dao.getAllAdmin();
		
		for (Admin admin : list) {
			Vector vec = new Vector();
			
			vec.add(admin.getAdid());
			
			AdmininfoDao dao1 = new AdmininfoDao();
			Admininfo info = dao1.getAdmininfo(admin.getAid());
			vec.add(info.getAname());
			vec.add(admin.getAdname());
			vec.add(admin.getAdpwd());
			vec.add(admin.getAdrole());
			
			dtm.addRow(vec);
		}
		
		table.setModel(dtm);
		
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
	}
}
