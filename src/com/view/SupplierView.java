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


import com.dao.SupplierDao;
import com.pojo.Supplier;

public class SupplierView extends JFrame{
	JScrollPane js;
	static JTable table;
	
	public SupplierView(){
		this.setSize(500, 600);
		this.setTitle("供应商管理");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setVisible(true);
		
		Toolkit tk = Toolkit.getDefaultToolkit();
		Image image = tk.createImage(this.getClass().getResource("bookstore.png")); /* 你的图标 */
		this.setIconImage(image);

		ImageIcon background = new ImageIcon(this.getClass().getResource("666.jpg"));
		JLabel label = new JLabel(background);
		label.setBounds(0, 0, 450, 500);
		JPanel imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
		
		ImageIcon background2 = new ImageIcon(this.getClass().getResource("timg.jpg"));
		JLabel label2 = new JLabel(background2);
		label2.setBounds(200, 0, 250, 500);
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
		bindSupplier();
		
		ImageIcon icon = new ImageIcon(this.getClass().getResource("add.png"));
		Image icon_add = icon.getImage().getScaledInstance(30, 20, icon.getImage().SCALE_DEFAULT);
		ImageIcon icon_vip2 = new ImageIcon(icon_add);
		JButton btn_add = new JButton("新增供应商", icon_vip2);
		btn_add.setVerticalTextPosition(JButton.BOTTOM);
		btn_add.setHorizontalTextPosition(JButton.CENTER);
		btn_add.setBounds(170, 10, 100, 50);
		btn_add.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
					AddSupplierView view = new AddSupplierView();
					view.setVisible(true);
			}
		});
		
		this.add(js);
		this.add(btn_add);
		
	}
	
	static public void bindSupplier() {

		((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

		DefaultTableModel dtm = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};

		dtm.addColumn("供应商ID");
		dtm.addColumn("名称");
		dtm.addColumn("联系人姓名");
		dtm.addColumn("联系电话");
		dtm.addColumn("地址");
	
		SupplierDao dao = new SupplierDao();
		ArrayList<Supplier> su  = dao.getAllSupplier();

		for (Supplier supplier : su) {
			Vector vec = new Vector();

			vec.add(supplier.getSid());
			vec.add(supplier.getSname());
			vec.add(supplier.getSlink());
			vec.add(supplier.getSphone());
			vec.add(supplier.getAsddress());
		
			dtm.addRow(vec);
		}
		table.setModel(dtm);
		
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
	}

}
