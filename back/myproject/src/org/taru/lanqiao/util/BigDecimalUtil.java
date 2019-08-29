package org.taru.lanqiao.util;

import java.math.BigDecimal;

public class BigDecimalUtil {
	public static BigDecimal toBigDecimal(String obj) {
		if(obj ==null) {
			return null;
		}else {
			return new BigDecimal(obj);
		}
	
	}

}
