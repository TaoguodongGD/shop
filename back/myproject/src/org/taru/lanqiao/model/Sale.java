package org.taru.lanqiao.model;

import java.math.BigDecimal;

public class Sale {
	
	private BigDecimal Sumer;
	private String productName;
	private String  months;
	public String getMonths() {
		return months;
	}
	public void setMonths(String Months) {
		this.months = Months==null?null:Months.trim();
	}
	public BigDecimal getSumer() {
		return Sumer;
	}
	public void setSumer(BigDecimal sumer) {
		Sumer = sumer;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName==null ?null:productName.trim();
	}
	

}
