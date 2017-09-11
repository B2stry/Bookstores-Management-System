package com.view;

import java.util.ArrayList;
import java.util.Calendar;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import com.dao.BookoutDao;
import com.pojo.Bookout;

public class LastToView {
	public LastToView() {
		int sum1 = 0, sum2 = 0, sum3 = 0, sum4 = 0, sum5 = 0, sum6 = 0, sum7 = 0, sum8 = 0, sum9 = 0, sum10 = 0,
				sum11 = 0, sum12 = 0;

		DefaultCategoryDataset dataset = new DefaultCategoryDataset();

		BookoutDao dao = new BookoutDao();
		ArrayList<Bookout> list = dao.getAllBookout();
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);

		for (Bookout out : list) {
			String date = out.getOtime();
			String[] str = date.split("-");
			int a = new Integer(str[1]);

			if (((year-1) + "").equals(str[0])) {
				switch (a) {
				case 1: {
					sum1 += out.getOprice() * out.getOnum();
					break;
				}
				case 2: {
					sum2 += out.getOprice() * out.getOnum();
					break;
				}
				case 3: {
					sum3 += out.getOprice() * out.getOnum();
					break;
				}
				case 4: {
					sum4 += out.getOprice() * out.getOnum();
					break;
				}
				case 5: {
					sum5 += out.getOprice() * out.getOnum();
					break;
				}
				case 6: {
					sum6 += out.getOprice() * out.getOnum();
					break;
				}
				case 7: {
					sum7 += out.getOprice() * out.getOnum();
					break;
				}
				case 8: {
					sum8 += out.getOprice() * out.getOnum();
					break;
				}
				case 9: {
					sum9 += out.getOprice() * out.getOnum();
					break;
				}
				case 10: {
					sum10 += out.getOprice() * out.getOnum();
					break;
				}
				case 11: {
					sum11 += out.getOprice() * out.getOnum();
					break;
				}
				case 12: {
					sum12 += out.getOprice() * out.getOnum();
					break;
				}
				}
			}
		}

		dataset.addValue(sum1, "1月", "1月");
		dataset.addValue(sum2, "2月", "2月");
		dataset.addValue(sum3, "3月", "3月");
		dataset.addValue(sum4, "4月", "4月");
		dataset.addValue(sum5, "5月", "5月");
		dataset.addValue(sum6, "6月", "6月");
		dataset.addValue(sum7, "7月", "7月");
		dataset.addValue(sum8, "8月", "8月");
		dataset.addValue(sum9, "9月", "9月");
		dataset.addValue(sum10, "10月", "10月");
		dataset.addValue(sum11, "11月", "11月");
		dataset.addValue(sum12, "12月", "12月");

		JFreeChart chart = ChartFactory.createBarChart3D("上年度各月份销量额", "月份", "销售额", dataset, PlotOrientation.VERTICAL,
				true, true, true);
		ChartFrame cf = new ChartFrame("上年度各月份销量额", chart, true);

		cf.setBounds(250, 80, 900, 600);
		cf.setVisible(true);
	}
public static void main(String[] args) {
	LastToView view = new LastToView();
}
}
