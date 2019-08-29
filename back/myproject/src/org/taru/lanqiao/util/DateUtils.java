package org.taru.lanqiao.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

	public static Date toDate(String obj) throws ParseException {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return obj == null ? null : formatter.parse(obj);
	}

	public static String toString(Date obj) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return obj == null ? null : formatter.format(obj);
	}

	public static String toDate1(String obj) throws ParseException {

		Date date = new Date(obj);
		SimpleDateFormat dateformat1=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

		String a1=dateformat1.format(date);
		return a1;
//		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd yyyy HH:mm:ss", Locale.US );
//		Date dd = sdf.parse(obj); //将字符串改为date的格式
// 		String resDate= new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(dd);
//System.out.println("我需要的时间格式"+resDate);
////		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
////		return obj == null ? null : formatter.parse(obj);
	}
}
