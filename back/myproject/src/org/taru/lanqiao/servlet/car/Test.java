package org.taru.lanqiao.servlet.car;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
	public static void main(String[] args) {
		Date d = null;
		try {
			d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("Apr 10, 2019 7:10:03 PM");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		System.out.println(d.toString());
	}
}
