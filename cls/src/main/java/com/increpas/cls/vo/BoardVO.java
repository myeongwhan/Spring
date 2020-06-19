package com.increpas.cls.vo;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

public class BoardVO {
	private int rno, ano, bno, mno, click;
	private String id, title, body, sdate, name;
	private Date bdate;
	private Time btime;
	
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getAno() {
		return ano;
	}
	public void setAno(int ano) {
		this.ano = ano;
	}
	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public int getClick() {
		return click;
	}
	public void setClick(int click) {
		this.click = click;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate() {
		this.sdate = (new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")).format(bdate) + (new SimpleDateFormat("HH:mm:ss")).format(btime);
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBdate() {
		return bdate;
	}
	public void setBdate(Date bdate) {
		this.bdate = bdate;
	}
	public Time getBtime() {
		return btime;
	}
	public void setBtime(Time btime) {
		this.btime = btime;
		setSdate();
	}
	
	@Override
	public String toString() {
		return "BoardVO [rno=" + rno + ", ano=" + ano + ", bno=" + bno + ", mno=" + mno + ", click=" + click + ", id="
				+ id + ", title=" + title + ", body=" + body + ", sdate=" + sdate + ", name=" + name + ", bdate="
				+ bdate + ", btime=" + btime + "]";
	}
	
}
