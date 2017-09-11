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

		dataset.addValue(sum1, "1��", "1��");
		dataset.addValue(sum2, "2��", "2��");
		dataset.addValue(sum3, "3��", "3��");
		dataset.addValue(sum4, "4��", "4��");
		dataset.addValue(sum5, "5��", "5��");
		dataset.addValue(sum6, "6��", "6��");
		dataset.addValue(sum7, "7��", "7��");
		dataset.addValue(sum8, "8��", "8��");
		dataset.addValue(sum9, "9��", "9��");
		dataset.addValue(sum10, "10��", "10��");
		dataset.addValue(sum11, "11��", "11��");
		dataset.addValue(sum12, "12��", "12��");

		JFreeChart chart = ChartFactory.createBarChart3D("����ȸ��·�������", "�·�", "���۶�", dataset, PlotOrientation.VERTICAL,
				true, true, true);
		ChartFrame cf = new ChartFrame("����ȸ��·�������", chart, true);

		cf.setBounds(250, 80, 900, 600);
		cf.setVisible(true);
	}
public static void main(String[] args) {
	LastToView view = new LastToView();
}
}
