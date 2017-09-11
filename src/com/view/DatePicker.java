package com.view;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.event.MouseInputListener;

public class DatePicker extends Observable implements Runnable, WindowFocusListener {
	public static class DayLabel extends JLabel implements MouseInputListener, MouseMotionListener {

		private DatePicker parent;
		private Border oldBorder;

		public void setCurrentDayStyle() {
			setFont(DatePicker.bold);
			setForeground(Color.RED);
		}

		public void setSelectedDayStyle() {
			setFont(DatePicker.plain);
			setForeground(Color.BLUE);
			setBorder(BorderFactory.createLineBorder(Color.GRAY));
		}

		public void setWeekendStyle() {
			setFont(DatePicker.plain);
			setForeground(Color.GRAY);
		}

		public void mouseClicked(MouseEvent e) {
			parent.dayPicked(Integer.parseInt(getText()));
		}

		public void mousePressed(MouseEvent mouseevent) {
		}

		public void mouseReleased(MouseEvent mouseevent) {
		}

		public void mouseEntered(MouseEvent e) {
			oldBorder = getBorder();
			Border b = BorderFactory.createBevelBorder(0);
			b = BorderFactory.createEtchedBorder();
			setBorder(b);
		}

		public void mouseExited(MouseEvent e) {
			setBorder(oldBorder);
		}

		public void mouseDragged(MouseEvent mouseevent) {
		}

		public void mouseMoved(MouseEvent mouseevent) {
		}

		public DayLabel(DatePicker parent, int day) {
			super(Integer.toString(day));
			this.parent = parent;
			setHorizontalAlignment(0);
			setFont(DatePicker.plain);
			addMouseListener(this);
		}
	}

	public static class MonthPanel extends JPanel {

		private DatePicker parent;

		private void setDaysOfMonth(Calendar c) {
			Calendar curr = new GregorianCalendar();
			int currdate = curr.get(5);
			int currmon = curr.get(2);
			int curryear = curr.get(1);
			int seldate = -1;
			int selmon = -1;
			int selyear = -1;
			if (parent.selectedDate != null) {
				seldate = parent.selectedDate.get(5);
				selmon = parent.selectedDate.get(2);
				selyear = parent.selectedDate.get(1);
			}
			int date = c.get(5);
			int mon = c.get(2);
			int year = c.get(1);
			int day = c.get(7);
			int start = (7 - (date - day) % 7) % 7;
			int days = c.getActualMaximum(5);
			for (int i = 0; i < start; i++) {
				JLabel lbl = new JLabel("");
				add(lbl);
			}

			int pos = start;
			for (int i = 1; i <= days; i++) {
				pos++;
				DayLabel lbl = new DayLabel(parent, i);
				if (seldate == i && selmon == mon && selyear == year)
					lbl.setSelectedDayStyle();
				if (currdate == i && currmon == mon && curryear == year)
					lbl.setCurrentDayStyle();
				if (pos % 7 == 0 || pos % 7 == 1)
					lbl.setWeekendStyle();
				add(lbl);
			}

		}

		public MonthPanel(DatePicker parent, Calendar c) {
			this.parent = parent;
			GridLayout g = new GridLayout();
			g.setColumns(7);
			g.setRows(0);
			setLayout(g);
			for (int i = 0; i < 7; i++) {
				JLabel wd = new JLabel(parent.getString("week." + i, ""));
				wd.setHorizontalAlignment(0);
				if (i == 0)
					wd.setForeground(Color.RED);
				else if (i == 6)
					wd.setForeground(Color.gray);
				add(wd);
			}

			setDaysOfMonth(c);
			setPreferredSize(new Dimension(220, 120));
		}
	}

	public static class NavigatePanel extends JPanel implements ActionListener {

		private DatePicker parent;
		private JButton premon;
		private JButton preyear;
		private JButton nextmon;
		private JButton nextyear;
		private JLabel lbl;
		private JComboBox monthBox;
		private JComboBox yearBox;
		private String months[];
		private Integer years[];
		private Box box;
		final int height = 10;

		private byte[] getImage(String fileName) throws IOException {
			InputStream is = null;
			IOException e;
			byte abyte0[];
			try {
				is = new BufferedInputStream(com.view.DatePicker.class.getClassLoader().getResourceAsStream(fileName));
				byte b[] = new byte[is.available()];
				is.read(b);
				abyte0 = b;
			} finally {
				try {
					is.close();
				} catch (IOException ioexception) {
				}
			}
			return abyte0;

		}

		public void setCurrentMonth(Calendar c) {
			setMonthComboBox(c);
			setYearComboBox(c);
			if (box == null) {
				box = new Box(0);
				box.add(monthBox);
				box.add(yearBox);
				add(box, "Center");
			}
		}

		private void setMonthComboBox(Calendar c) {
			if (months == null) {
				months = new String[12];
				for (int i = 0; i < 12; i++) {
					String m = parent.getString("month." + i, "");
					months[i] = m;
				}

			}
			if (monthBox == null) {
				monthBox = new JComboBox();
				monthBox.addActionListener(this);
				monthBox.setFont(DatePicker.plain);
				monthBox.setSize(monthBox.getWidth(), 10);
				monthBox.setPreferredSize(new Dimension(monthBox.getWidth(), 10));
			}
			monthBox.setModel(new DefaultComboBoxModel(months));
			monthBox.setSelectedIndex(c.get(2));
		}

		private void setYearComboBox(Calendar c) {
			int y = c.get(1);
			years = new Integer[7];
			int i = y - 3;
			for (int j = 0; i <= y + 3; j++) {
				years[j] = new Integer(i);
				i++;
			}

			if (yearBox == null) {
				yearBox = new JComboBox();
				yearBox.addActionListener(this);
				yearBox.setFont(DatePicker.plain);
				yearBox.setSize(yearBox.getWidth(), 15);
				yearBox.setPreferredSize(new Dimension(yearBox.getWidth(), 15));
			}
			yearBox.setModel(new DefaultComboBoxModel(years));
			yearBox.setSelectedItem(years[3]);
		}

		public void setLabel(Calendar c) {
			if (lbl != null)
				remove(lbl);
			lbl = new JLabel(parent.getString("month." + c.get(2), "") + ", " + c.get(1));
			lbl.setHorizontalAlignment(0);
			add(lbl, "Center");
		}

		public void actionPerformed(ActionEvent e) {
			Object src = e.getSource();
			Calendar c = new GregorianCalendar();
			c.setTime(parent.getCalendar().getTime());
			if (src instanceof JButton) {
				if (e.getSource() == premon)
					c.add(2, -1);
				else if (e.getSource() == nextmon)
					c.add(2, 1);
				else if (e.getSource() == nextyear)
					c.add(1, 1);
				if (e.getSource() == preyear)
					c.add(1, -1);
				try {
					parent.updateScreen(c);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else if (src instanceof JComboBox) {
				JComboBox jcb = (JComboBox) src;
				if (src == monthBox)
					c.set(2, jcb.getSelectedIndex());
				else if (e.getSource() == yearBox) {
					c.set(1, years[jcb.getSelectedIndex()].intValue());
					setYearComboBox(c);
				}
				parent.setMonthPanel(c);
				parent.screen.pack();
			}
		}

		public NavigatePanel(DatePicker parent) throws IOException {
			this.parent = parent;
			setLayout(new BorderLayout());
			Dimension d = new Dimension(20, 20);
			Box box = new Box(0);
			preyear = new JButton();
			preyear.setToolTipText(parent.getString("pre.year", "Previous year."));
			ImageIcon icon = new ImageIcon(getImage("preyear.gif"), "<<");
			preyear.setIcon(icon);
			preyear.addActionListener(this);
			preyear.setPreferredSize(d);
			box.add(preyear);
			box.add(Box.createHorizontalStrut(3));
			premon = new JButton();
			premon.setToolTipText(parent.getString("pre.mon", "Previous Month"));
			icon = new ImageIcon(getImage("premon.gif"), "<");
			premon.setIcon(icon);
			premon.addActionListener(this);
			premon.setPreferredSize(d);
			box.add(premon);
			add(box, "West");
			box = new Box(0);
			nextmon = new JButton();
			nextmon.setToolTipText(parent.getString("next.mon", "Next month."));
			icon = new ImageIcon(getImage("nextmon.gif"), ">");
			nextmon.setIcon(icon);
			nextmon.setPreferredSize(d);
			nextmon.addActionListener(this);
			box.add(nextmon);
			box.add(Box.createHorizontalStrut(3));
			nextyear = new JButton();
			nextyear.setToolTipText(parent.getString("next.year", "Next year."));
			icon = new ImageIcon(getImage("nextyear.gif"), ">>");
			nextyear.setIcon(icon);
			nextyear.setPreferredSize(d);
			nextyear.addActionListener(this);
			box.add(nextyear);
			add(box, "East");
			setCurrentMonth(parent.calendar);
		}
	}

	protected static Font plain = new Font("Arial", 0, 10);
	protected static Font bold = new Font("Arial", 1, 10);
	private MonthPanel monthPanel;
	private NavigatePanel navPanel;
	protected Calendar calendar;
	private Calendar selectedDate;
	private boolean closeOnSelect;
	private Locale locale;
	private DateFormat sdf;
	private JDialog screen;
	private ResourceBundle i18n;

	public DatePicker(Observer observer) {
		this(observer, new Date());
	}

	public DatePicker(Observer observer, Date selecteddate) {
		this(observer, selecteddate, Locale.CHINA);
	}

	public DatePicker(Observer observer, Locale locale) {
		this(observer, new Date(), locale);
	}

	public DatePicker(Observer observer, Date selecteddate, Locale locale) {
		
		closeOnSelect = true;
		this.locale = Locale.CHINA;
		this.locale = locale;
		register(observer);
		screen = new JDialog();
		screen.addWindowFocusListener(this);
		screen.setSize(220, 200);
		screen.setResizable(false);
		screen.setModal(true);
		screen.setUndecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		screen.getRootPane().setWindowDecorationStyle(1);
		screen.setDefaultCloseOperation(2);
		screen.getContentPane().setLayout(new BorderLayout());
		calendar = new GregorianCalendar();
		setSelectedDate(selecteddate);
		Calendar c = calendar;
		if (selectedDate != null)
			c = selectedDate;
		try {
			updateScreen(c);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		screen.getContentPane().add(navPanel, "North");
		screen.setTitle(getString("program.title", "»’¿˙"));
	}

	private void setIconImage(Image image) {
		// TODO Auto-generated method stub
		
	}

	public void start(Component c) {
		if (c != null) {
			Component p = c.getParent();
			int x = c.getX() + c.getWidth();
			int y = c.getY() + c.getHeight();
			for (; p != null; p = p.getParent()) {
				x += p.getX();
				y += p.getY();
			}

			screen.setLocation(x, y);
		} else {
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			screen.setLocation((int) (dim.getWidth() - (double) screen.getWidth()) / 2,
					(int) (dim.getHeight() - (double) screen.getHeight()) / 2);
		}
		SwingUtilities.invokeLater(this);
	}

	public void run() {
		screen.pack();
		screen.setVisible(true);
	}

	public Date parseDate(String date) {
		if (sdf == null)
			sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return sdf.parse(date);
		} catch (Exception e) {
			return null;
		}
	}

	public String formatDate(Date date) {
		if (date == null)
			return "";
		if (sdf == null)
			sdf = SimpleDateFormat.getDateInstance(3, locale);
		return sdf.format(date);
	}

	public String formatDate(Date date, String pattern) {
		if (date == null)
			return "";
		else
			return (new SimpleDateFormat(pattern)).format(date);
	}

	public String formatDate(Calendar date) {
		if (date == null)
			return "";
		else
			return formatDate(date.getTime());
	}

	public String formatDate(Calendar date, String pattern) {
		if (date == null)
			return "";
		else
			return (new SimpleDateFormat(pattern)).format(date.getTime());
	}

	public void setLocale(Locale l) {
		locale = l;
	}

	public Locale getLocale() {
		return locale != null ? locale : Locale.CHINA;
	}

	public void register(Observer observer) {
		if (observer != null)
			addObserver(observer);
	}

	public void unregister(Observer observer) {
		if (observer != null)
			deleteObserver(observer);
	}

	private Calendar getCalendar() {
		return calendar;
	}

	private void setCalendar(Calendar c) {
		calendar = c;
	}

	public void setSelectedDate(Date d) {
		if (d != null) {
			if (selectedDate == null)
				selectedDate = new GregorianCalendar();
			
			selectedDate.setTime(d);
			try {
				updateScreen(selectedDate);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	protected void updateScreen(Calendar c) throws IOException {
		if (navPanel == null)
			navPanel = new NavigatePanel(this);
		navPanel.setCurrentMonth(c);
		setMonthPanel(c);
		screen.pack();
	}

	protected void setMonthPanel(Calendar calendar) {
		if (calendar != null)
			this.calendar.setTime(calendar.getTime());
		if (monthPanel != null)
			screen.getContentPane().remove(monthPanel);
		monthPanel = new MonthPanel(this, calendar);
		screen.getContentPane().add(monthPanel, "Center");
	}

	protected void dayPicked(int day) {
		calendar.set(5, day);
		setSelectedDate(calendar.getTime());
		setChanged();
		notifyObservers(selectedDate);
		if (closeOnSelect) {
			screen.dispose();
			screen.setVisible(false);
		}
	}

	public String getString(String key, String dv) {
		if (i18n == null || getLocale() != i18n.getLocale())
			i18n = ResourceBundle.getBundle("i18n",getLocale());
		String val = i18n.getString(key);
		if (val == null)
			return dv;
		else
			return val;
	}

	public boolean isCloseOnSelect() {
		return closeOnSelect;
	}

	public void windowGainedFocus(WindowEvent windowevent) {
	}

	public void windowLostFocus(WindowEvent e) {
		screen.toFront();
	}

	public JDialog getScreen() {
		return screen;
	}

	public void setCloseOnSelect(boolean closeOnSelect) {
		this.closeOnSelect = closeOnSelect;
	}

}
