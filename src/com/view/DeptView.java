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

import com.dao.DeptDao;
import com.pojo.Dept;

public class DeptView extends JFrame {
	JScrollPane js;
	static JTable table;

	public DeptView() {
		this.setSize(450, 500);
		this.setTitle("���Ź���");
		this.setResizable(false);// �ı��С
		this.setLocationRelativeTo(null); // ����
		this.setLayout(null); // ���ַ�ʽ ��(�����Ͻ�Ϊ
		this.setVisible(true);

		Toolkit tk = Toolkit.getDefaultToolkit();
		Image image = tk.createImage(this.getClass().getResource("bookstore.png")); /* ���ͼ�� */
		this.setIconImage(image);

		ImageIcon background = new ImageIcon(this.getClass().getResource("666.jpg"));
		JLabel label = new JLabel(background);
		label.setBounds(0, 0, 200, 500);
		JPanel imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));
		
		ImageIcon background2 = new ImageIcon(this.getClass().getResource("timg.jpg"));
		JLabel label2 = new JLabel(background2);
		label2.setBounds(200, 0, 250, 500);
		JPanel imagePanel2 = (JPanel) this.getContentPane();
		imagePanel2.setOpaque(false);
		this.getLayeredPane().add(label2, new Integer(Integer.MIN_VALUE));

		// ��ģ�� JScrollPane JTable
		js = new JScrollPane();
		js.setBounds(10, 20, 250, 380);

		table = new JTable();
		
		js.setViewportView(table);
		bindDept();
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
					
					int did = (Integer) table.getValueAt(row, 0);
					UpdateDeptView view = new UpdateDeptView(did);
					view.setVisible(true);
					}
			}
		});

		JButton btn_add = new JButton("��������");
		btn_add.setBounds(280, 100, 100, 30);
		btn_add.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AddDeptView view = new AddDeptView();
				view.setVisible(true);
			}
		});

		JButton btn_update = new JButton("�޸Ĳ���");
		btn_update.setBounds(280, 160, 100, 30);
		btn_update.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int row = table.getSelectedRow();
				if (row < 0)
					JOptionPane.showMessageDialog(null, "��ѡ��Ҫ�޸ĵ��У�");
				else {
					int did = (Integer) table.getValueAt(row, 0);
					UpdateDeptView view = new UpdateDeptView(did);
					view.setVisible(true);
				}
			}
		});

		JButton btn_del = new JButton("ɾ������");
		btn_del.setBounds(280, 220, 100, 30);
		btn_del.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int row = table.getSelectedRow();
				if (row < 0)
					JOptionPane.showMessageDialog(null, "��ѡ��Ҫɾ�����У�");
				else {
					int ch = JOptionPane.showConfirmDialog(null, "ɾ�������ݲ��ָܻ���ȷ����", "���棡", JOptionPane.YES_NO_OPTION);
					if (ch == JOptionPane.YES_OPTION) {
						int did = (Integer) table.getValueAt(row, 0);
						DeptDao dao = new DeptDao();
						int n = dao.delDept(did);
						if (n > 0) {
							//JOptionPane.showMessageDialog(null, "ɾ���ɹ���");
							bindDept();
						} else
							JOptionPane.showMessageDialog(null, "ɾ��ʧ�ܣ���ɾ���ò����µ�����Ա��");
					}
				}
			}
		});

		this.add(js);
		this.add(btn_add);
		this.add(btn_update);
		this.add(btn_del);
	}

	static public void bindDept() {

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

		dtm.addColumn("����ID");
		dtm.addColumn("��������");

		DeptDao dao = new DeptDao();
		ArrayList<Dept> list = dao.getAllDept();

		for (Dept dept : list) {
			Vector vec = new Vector();
			vec.add(dept.getDid());
			vec.add(dept.getDname());

			dtm.addRow(vec);
		}

		table.setModel(dtm);
		
		
		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
	}
}
