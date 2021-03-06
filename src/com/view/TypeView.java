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

import com.dao.TypeDao;

public class TypeView extends JFrame {
	JScrollPane js;
	static JTable table;

	public TypeView() {
		this.setSize(450, 500);
		this.setTitle("类型管理");
		this.setResizable(false);// 改变大小
		this.setLocationRelativeTo(null); // 居中
		this.setLayout(null); // 布局方式 空(在左上角为
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


		// 表模型 JScrollPane JTable
		js = new JScrollPane();
		js.setBounds(10, 20, 250, 380);

		table = new JTable();

		js.setViewportView(table);
		bindType();
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

					int tid = (Integer) table.getValueAt(row, 0);
					UpdateTypeView view = new UpdateTypeView(tid);
					view.setVisible(true);
				}
			}
		});
		JButton btn_add = new JButton("新增类型");
		btn_add.setBounds(280, 100, 100, 30);
		btn_add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AddTypeView view = new AddTypeView();
				view.setVisible(true);
			}
		});

		JButton btn_update = new JButton("修改类型");
		btn_update.setBounds(280, 160, 100, 30);
		btn_update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int row = table.getSelectedRow();
				if (row < 0)
					JOptionPane.showMessageDialog(null, "请选中要修改的行！");
				else {
					int tid = (Integer) table.getValueAt(row, 0);
					UpdateTypeView view = new UpdateTypeView(tid);
					view.setVisible(true);
				}
			}
		});

		JButton btn_del = new JButton("删除类型");
		btn_del.setBounds(280, 220, 100, 30);
		btn_del.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int row = table.getSelectedRow();
				if (row < 0)
					JOptionPane.showMessageDialog(null, "请选中要删除的行！");
				else {
					int ch = JOptionPane.showConfirmDialog(null, "删除的数据不能恢复，确定？", "警告！", JOptionPane.YES_NO_OPTION);
					if (ch == JOptionPane.YES_OPTION) {
						int tid = (Integer) table.getValueAt(row, 0);
						TypeDao dao = new TypeDao();
						int n = dao.delType(tid);
						if (n > 0) {
							//JOptionPane.showMessageDialog(null, "删除成功！");
							bindType();
						} else
							JOptionPane.showMessageDialog(null, "删除失败！先删除该部门下的所有员工");
					}
				}
			}
		});

		this.add(js);
		this.add(btn_add);
		this.add(btn_update);
		this.add(btn_del);
	}

	static public void bindType() {

		DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
		cr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		table.setDefaultRenderer(Object.class, cr);

		((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

		DefaultTableModel dtm = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};

		dtm.addColumn("类型ID");
		dtm.addColumn("类型名称");

		TypeDao dao = new TypeDao();
		ArrayList<com.pojo.Type> list = dao.getAllType();

		for (com.pojo.Type type : list) {
			Vector vec = new Vector();
			vec.add(type.getTid());
			vec.add(type.getTname());

			dtm.addRow(vec);
		}

		table.setModel(dtm);
		
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
	}

}
