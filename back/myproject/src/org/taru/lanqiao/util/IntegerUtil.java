package org.taru.lanqiao.util;

public class IntegerUtil {
	public static int toInt(Object obj) {
		return  obj== null? 0 : Integer.valueOf(obj.toString());
	}

}
