package com.view;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import com.dao.BookoutDao;
import com.pojo.Bookout;

public class BookoutChartView {

	public BookoutChartView(int n) {

		DefaultPieDataset dpd = new DefaultPieDataset();

		BookoutDao dao = new BookoutDao();
		ArrayList<Bookout> list = dao.getAllBookout();
		ArrayList<Bookout> list2 = new ArrayList<Bookout>();
		ArrayList<Bookout> list3 = new ArrayList<Bookout>();
		ArrayList<Bookout> list4 = new ArrayList<Bookout>();

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date2 = null;
		int num =0;
		int oneDay = 24 * 60 * 60 * 1000;
		
		for (Bookout out : list) {
			try {
				date2 = sdf.parse(out.getOtime());
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if ((date.getTime() - date2.getTime()) / oneDay <= 30) {
				list2.add(out);
			}

			if ((date.getTime() - date2.getTime()) / oneDay <= 90) {
				list3.add(out);
			}

			if ((date.getTime() - date2.getTime()) / oneDay <= 7) {
				list4.add(out);
			}
		}

		if (n == 30) {
			for (Bookout bookout : list2) {
				num = dao.getAllBookout(bookout.getBid());
				dpd.setValue(bookout.getBname(), num);
			}
		}

		if (n == 7) {
			for (Bookout bookout : list4) {
				num = dao.getAllBookout(bookout.getBid());
				dpd.setValue(bookout.getBname(), num);
			}
		}

		if (n == 90) {

			for (Bookout bookout : list3) {
				num = dao.getAllBookout(bookout.getBid());
				dpd.setValue(bookout.getBname(), num);
			}
		}

		JFreeChart chart = ChartFactory.createPieChart("近" + n + "天内销售数据图", dpd, true, true, false);
		ChartFrame cf = new ChartFrame("近" + n + "天内销售数据图", chart);
		
		PiePlot plot = (PiePlot) chart.getPlot();
		plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}={1}({2})",NumberFormat.getNumberInstance(),new DecimalFormat("0.00%")));
		
		cf.setBounds(250, 80, 900, 600);
		cf.setVisible(true);
	}
	public static void main(String[] args) {
		BookoutChartView view = new BookoutChartView(7);
	}
}
