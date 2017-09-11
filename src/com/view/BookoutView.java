package com.view;

import java.awt.Image;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.dao.BookinDao;
import com.dao.BookoutDao;
import com.pojo.Bookin;
import com.pojo.Bookout;

public class BookoutView extends JDialog {
	JScrollPane js;
	static JTable table;
	
	public BookoutView(){
			this.setSize(620, 500);
			this.setTitle("销售信息");
			this.setResizable(false);
			this.setLocationRelativeTo(null);
			this.setLayout(null);
			this.setVisible(true);

			Toolkit tk = Toolkit.getDefaultToolkit();
			Image image = tk.createImage(this.getClass().getResource("bookstore.png")); /* 你的图标 */
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

			dtm.addColumn("销售编号");
			dtm.addColumn("书名");
			dtm.addColumn("操作人");
			dtm.addColumn("进价");
			dtm.addColumn("数量");
			dtm.addColumn("时间");
			
			BookoutDao dao = new BookoutDao();
			ArrayList<Bookout> list = dao.getAllBookout();
			for (Bookout bookout : list) {
				Vector vec = new Vector();
				
				vec.add(bookout.getOid());
				vec.add(bookout.getBname());
				vec.add(bookout.getAname());
				vec.add(bookout.getOprice());
				vec.add(bookout.getOnum());
				vec.add(bookout.getOtime());
				
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
																					// 皮肤
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			BookoutView view = new BookoutView();
			view.setVisible(true);
		}
	}

