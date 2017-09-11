package com.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.persistence.Column;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import com.dao.AdminDao;
import com.dao.AdmininfoDao;
import com.dao.BooksinfoDao;
import com.pojo.Admin;
import com.pojo.Admininfo;
import com.pojo.Booksinfo;

public class AddBookoutView extends JFrame implements Runnable {
	static JTable table;
	JScrollPane js;
	JTextField txt_money;
	static int sum = 0;

	public AddBookoutView(final Admin admin) {
		this.setSize(1000, 700);
		this.setTitle("��Ʒ����");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setLayout(null);

		Toolkit tk = Toolkit.getDefaultToolkit();
		Image image = tk.createImage(this.getClass().getResource("bookstore.png")); // image.gif�����ͼ��
		this.setIconImage(image);

		ImageIcon background = new ImageIcon(this.getClass().getResource("timg.jpg"));
		JLabel label = new JLabel(background);
		label.setBounds(0, 0, 1000, 700);
		JPanel imagePanel = (JPanel) this.getContentPane();
		imagePanel.setOpaque(false);
		this.getLayeredPane().add(label, new Integer(Integer.MIN_VALUE));

		this.addWindowListener(new WindowListener() {

			@Override
			public void windowOpened(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				
				  MainView view = new MainView(admin); view.setVisible(true);
				  AddBookoutView.this.setVisible(false);
				 
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub

			}
		});

		JLabel lbl_tit = new JLabel("��Ʒ����");
		lbl_tit.setBounds(420, 10, 200, 60);
		lbl_tit.setFont(new Font("΢���ź�", Font.BOLD, 25));
		lbl_tit.setForeground(Color.gray);

		JButton btn_out = new JButton("���ͼ��");
		btn_out.setBounds(20, 65, 100, 40);
		btn_out.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AddBookView view = new AddBookView();
				view.setVisible(true);
			}
		});

		lbl_time.setBounds(780, 0, 200, 40);
		lbl_time.setFont(new Font("΢���ź�", Font.BOLD, 12));
		lbl_time.setForeground(Color.gray);
		new Thread(this).start();

		js = new JScrollPane();
		js.setBounds(5, 130, 935, 410);

		table = new JTable();
		js.setViewportView(table);

		DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
		cr.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
		table.setDefaultRenderer(Object.class, cr);
		((DefaultTableCellRenderer) table.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);

		JLabel lbl_t = new JLabel("�������ڣ�");
		lbl_t.setBounds(600, 90, 80, 25);

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format(date);
		String[] ss = str.split("-");

		JComboBox box_year = new JComboBox();
		box_year.setBounds(660, 90, 70, 25);
		JLabel lbl_year = new JLabel("��");
		lbl_year.setBounds(735, 90, 20, 25);
		box_year.addItem(ss[0]);

		JComboBox box_month = new JComboBox();
		box_month.setBounds(750, 90, 60, 25);
		JLabel lbl_month = new JLabel("��");
		lbl_month.setBounds(815, 90, 20, 25);
		box_month.addItem(ss[1]);

		JComboBox box_day = new JComboBox();
		box_day.setBounds(830, 90, 60, 25);
		JLabel lbl_day = new JLabel("��");
		lbl_day.setBounds(895, 90, 20, 25);
		box_day.addItem(ss[2]);

		JButton btn_cost = new JButton("����");
		btn_cost.setBounds(550, 560, 100, 35);
		btn_cost.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(sum>0){
					PayView view = new PayView(sum,admin);
					view.setVisible(true);
				}
			}
		});

		JButton btn_del = new JButton("���");
		btn_del.setBounds(680, 560, 100, 35);
		btn_del.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				DefaultTableModel model = (DefaultTableModel) table.getModel();
		
				while(model.getRowCount()>0){
					model.removeRow(model.getRowCount()-1);
				}
				ShowBookView.list = new ArrayList<Booksinfo>();
				sum=0;
				}
			
		});

		JButton btn_cancel = new JButton("�˳�");
		btn_cancel.setBounds(810, 560, 100, 35);
		btn_cancel.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				DefaultTableModel model = (DefaultTableModel) table.getModel();
				
				while(model.getRowCount()>0){
					model.removeRow(model.getRowCount()-1);
				}
				ShowBookView.list = new ArrayList<Booksinfo>();
				sum=0;
			
				  MainView view = new MainView(admin);
				  view.setVisible(true);
				  AddBookoutView.this.setVisible(false);
			}
		});

		JLabel lbl_money = new JLabel("Ӧ�ս�");
		lbl_money.setBounds(30, 560, 80, 30);

		txt_money = new JTextField();
		txt_money.setBounds(90, 560, 80, 30);
		txt_money.setEditable(false);
		txt_money.setText(sum + "");
		
		new Thread() {
			public void run() {
				while (true) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					txt_money.setText(sum + "");
					/*if(ShowBookView.list.size() == 0){
						sum = 0;
					}*/
				}
			};
		}.start();

		AdmininfoDao dao = new AdmininfoDao();
		Admininfo info = dao.getAdmininfo(admin.getAid());

		JLabel lbl_p = new JLabel("����Ա��");
		lbl_p.setBounds(230, 560, 80, 30);
		JComboBox box_p = new JComboBox();
		box_p.setBounds(290, 560, 100, 30);
		box_p.addItem(info);

		this.add(lbl_tit);
		this.add(btn_del);
		this.add(txt_money);
		this.add(lbl_money);
		this.add(lbl_p);
		this.add(box_p);
		this.add(btn_cancel);
		this.add(btn_cost);
		this.add(lbl_day);
		this.add(lbl_month);
		this.add(lbl_year);
		this.add(box_year);
		this.add(box_month);
		this.add(box_day);
		this.add(lbl_t);
		this.add(lbl_time);
		this.add(btn_out);
		this.add(js);
	}

	JLabel lbl_time = new JLabel(getTime());

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			lbl_time.setText(getTime());
		}
	}

	private String getTime() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy��MM��dd��  HH:mm:ss");
		String str = sdf.format(date);

		return str;
	}

	static public void bindBooksinfo(ArrayList<Booksinfo> list) {

		DefaultTableModel dtm = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				// TODO Auto-generated method stub
				return false;
			}
		};

		dtm.addColumn("ͼ��ID");
		dtm.addColumn("����");
		dtm.addColumn("����");
		dtm.addColumn("ISBN��");
		dtm.addColumn("������");
		dtm.addColumn("���");
		dtm.addColumn("�ۼ�");
		dtm.addColumn("����");
		dtm.addColumn("�ܽ��");

		for (Booksinfo binfo : list) {

			Vector vec = new Vector();

			vec.add(binfo.getBid());
			vec.add(binfo.getBname());
			vec.add(binfo.getBauthor());
			vec.add(binfo.getBisbn());
			vec.add(binfo.getBconcern());
			vec.add(binfo.getTname());
			vec.add(binfo.getBprice());
			vec.add(binfo.getBnum());

			int sum = (int) (binfo.getBprice() * binfo.getBnum());
			vec.add(sum);

			dtm.addRow(vec);

		}
		
		table.setModel(dtm);
		
		int a = (Integer) table.getValueAt(list.size()-1, 8);
		sum += a;

		table.getColumnModel().getColumn(0).setMaxWidth(0);
		table.getColumnModel().getColumn(0).setMinWidth(0);
		table.getColumnModel().getColumn(0).setPreferredWidth(0);

		table.getColumnModel().getColumn(1).setMaxWidth(165);
		table.getColumnModel().getColumn(1).setMinWidth(165);
		table.getColumnModel().getColumn(1).setPreferredWidth(165);

		table.getColumnModel().getColumn(2).setMaxWidth(140);
		table.getColumnModel().getColumn(2).setMinWidth(140);
		table.getColumnModel().getColumn(2).setPreferredWidth(140);

		table.getColumnModel().getColumn(3).setMaxWidth(140);
		table.getColumnModel().getColumn(3).setMinWidth(140);
		table.getColumnModel().getColumn(3).setPreferredWidth(140);

		table.getColumnModel().getColumn(4).setMaxWidth(150);
		table.getColumnModel().getColumn(4).setMinWidth(150);
		table.getColumnModel().getColumn(4).setPreferredWidth(150);

		table.getColumnModel().getColumn(5).setMaxWidth(120);
		table.getColumnModel().getColumn(5).setMinWidth(120);
		table.getColumnModel().getColumn(5).setPreferredWidth(120);

		table.getColumnModel().getColumn(6).setMaxWidth(60);
		table.getColumnModel().getColumn(6).setMinWidth(60);
		table.getColumnModel().getColumn(6).setPreferredWidth(60);

		table.getColumnModel().getColumn(7).setMaxWidth(60);
		table.getColumnModel().getColumn(7).setMinWidth(60);
		table.getColumnModel().getColumn(7).setPreferredWidth(60);

		table.getColumnModel().getColumn(8).setMaxWidth(90);
		table.getColumnModel().getColumn(8).setMinWidth(90);
		table.getColumnModel().getColumn(8).setPreferredWidth(90);
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

		AdminDao dao = new AdminDao();
		Admin admin = dao.getAdmin(6);

		AddBookoutView view = new AddBookoutView(admin);
		view.setVisible(true);
	}
}
