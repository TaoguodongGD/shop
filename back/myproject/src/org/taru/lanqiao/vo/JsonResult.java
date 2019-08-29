package org.taru.lanqiao.vo;

import java.io.Serializable;

public class JsonResult  implements Serializable {
	private String status;
	private String msg;
	private Object data;
	
	public String getStatus() {
		return status;
	}
	public String getMsg() {
		return msg;
	}
	public Object getData() {
		return data;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public JsonResult(String status, String msg, Object data) {
		super();
		this.status = status;
		this.msg = msg;
		this.data = data;
	}
	
	

}
