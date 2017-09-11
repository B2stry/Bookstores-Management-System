package com.view;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.dao.BookinDao;
import com.pojo.Bookin;


public class BookinView extends JDialog {

	JScrollPane js;
	static JTable table;

	public BookinView() {
		this.setSize(620, 500);
		this.setTitle("�����Ϣ");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(null);
		this.setVisible(true);

		Toolkit tk = Toolkit.getDefaultToolkit();
		Image image = tk.createImage(this.getClass().getResource("bookstore.png")); /* ���ͼ�� */
		this.setIconImage(image);

		ImageIcon background = new ImageIcon(this.getClass().getResource("666.jpg"));
		JLabel label = new JLabel(background);
		label.setBounds(0, 0, 600, 500);
		JPanel imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));

		js = new JScrollPane();
		js.setBounds(10, 20, 545, 390);

		table = new JTable();

		DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
		cr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		table.setDefaultRenderer(Object.class, cr);

		js.setViewportView(table);
		bindBookin();

		this.add(js);

	}

	static public void bindBookin() {

		((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

		DefaultTableModel dtm = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};

		dtm.addColumn("�����");
		dtm.addColumn("����");
		dtm.addColumn("������");
		dtm.addColumn("����");
		dtm.addColumn("����");
		dtm.addColumn("ʱ��");
		
		BookinDao dao = new BookinDao();
		ArrayList<Bookin> list = dao.getAllBookin();
		for (Bookin bookin : list) {
			Vector vec = new Vector();
			
			vec.add(bookin.getId());
			vec.add(bookin.getBname());
			vec.add(bookin.getAname());
			vec.add(bookin.getIprice());
			vec.add(bookin.getInum());
			vec.add(bookin.getItime());
			
			dtm.addRow(vec);
		}
		
		table.setModel(dtm);

		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(0);
		
		table.getColumnModel().getColumn(1).setMaxWidth(140);
		table.getColumnModel().getColumn(1).setMinWidth(140);
		table.getColumnModel().getColumn(1).setPreferredWidth(140);
		
		table.getColumnModel().getColumn(2).setMaxWidth(80);
		table.getColumnModel().getColumn(2).setMinWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		
		table.getColumnModel().getColumn(3).setMaxWidth(80);
		table.getColumnModel().getColumn(3).setMinWidth(80);
		table.getColumnModel().getColumn(3).setPreferredWidth(80);
		
		table.getColumnModel().getColumn(4).setMaxWidth(80);
		table.getColumnModel().getColumn(4).setMinWidth(80);
		table.getColumnModel().getColumn(4).setPreferredWidth(80);
		
		table.getColumnModel().getColumn(5).setMaxWidth(160);
		table.getColumnModel().getColumn(5).setMinWidth(160);
		table.getColumnModel().getColumn(5).setPreferredWidth(160);

	}

	public static void main(String[] args) {
		try {
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF(); // MacWinLookAndFeel
																				// Ƥ��
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		BookinView view = new BookinView();
		view.setVisible(true);
	}
}
