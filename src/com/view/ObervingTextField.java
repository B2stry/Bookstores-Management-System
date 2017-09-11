package com.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

// Referenced classes of package com.qt.datapicker:
//			DatePicker

class ObservingTextField extends JTextField implements Observer {

	ObservingTextField() {
	}

	public void update(Observable o, Object arg) {
		Calendar calendar = (Calendar) arg;
		com.view.DatePicker dp = (com.view.DatePicker) o;
	
		setText(dp.formatDate(calendar));
	}
}
