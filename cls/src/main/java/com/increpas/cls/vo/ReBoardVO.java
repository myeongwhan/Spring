package com.increpas.cls.vo;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

public class ReBoardVO {
	private int reno, remno, reupno;
	private String rebody, sdate;
	private Date jDate;
	private Time jTime;
	public int getReno() {
		return reno;
	}
	public void setReno(int reno) {
		this.reno = reno;
	}
	public int getRemno() {
		return remno;
	}
	public void setRemno(int remno) {
		this.remno = remno;
	}
	public int getReupno() {
		return reupno;
	}
	public void setReupno(int reupno) {
		this.reupno = reupno;
	}
	public String getRebody() {
		return rebody;
	}
	public void setRebody(String rebody) {
		this.rebody = rebody;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate() {
		SimpleDateFormat form1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat form2 = new SimpleDateFormat("HH:mm");
		this.sdate = form1.format(jDate)+ " " + form2.format(jTime);
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public Date getjDate() {
		return jDate;
	}
	public void setjDate(Date jDate) {
		this.jDate = jDate;
	}
	public Time getjTime() {
		return jTime;
	}
	public void setjTime(Time jTime) {
		this.jTime = jTime;
	}
	
}
