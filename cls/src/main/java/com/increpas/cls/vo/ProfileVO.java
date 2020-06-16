package com.increpas.cls.vo;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

import org.springframework.web.multipart.MultipartFile;

/**
 * 이 클래스는 프로필 사진의 정보를 기억할 클래스
 * @author	이명환
 * @since	2020.06.16
 * @version	v.1.0
 *
 */
public class ProfileVO {
	private int pno, mno;
	private long len;
	private String pcode, oriname, savename, dir, sdate, isshow;
	private Date pdate;
	private Time ptime;
	
	/*
		업로드된 파일을 기억할 변수는 (fileupload.jar에서)
			MultipartFile
		이라는 클래스 형태로 만들어야 한다
		이때 주의사항
			만약 name값이 하나이면 일반 변수로 만들면 되고,
			만약 같은 name값이 여러 개(다중 업로드)인 경우는 배열 변수로 만들면 된다
			
			예]
				MultipartFile upfile;	==> 단일 업로드
				MultipartFile[] upfile;	==> 다중 업로드
	 */
	private MultipartFile[] file;
	
	// getters & setters
	
	public MultipartFile[] getFile() {
		return file;
	}
	public void setFile(MultipartFile[] file) {
		this.file = file;
	}
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	public int getMno() {
		return mno;
	}
	public void setMno(int mno) {
		this.mno = mno;
	}
	public long getLen() {
		return len;
	}
	public void setLen(long len) {
		this.len = len;
	}
	public String getPcode() {
		return pcode;
	}
	public void setPcode(String pcode) {
		this.pcode = pcode;
	}
	public String getOriname() {
		return oriname;
	}
	public void setOriname(String oriname) {
		this.oriname = oriname;
	}
	public String getSavename() {
		return savename;
	}
	public void setSavename(String savename) {
		this.savename = savename;
	}
	public String getDir() {
		return dir;
	}
	public void setDir(String dir) {
		this.dir = dir;
	}
	public String getSdate() {
		return sdate;
	}
	public void setSdate() {
		SimpleDateFormat form1 = new SimpleDateFormat("yyyy년 MM월 dd일");
		SimpleDateFormat form2 = new SimpleDateFormat(" HH:mm:ss");
		this.sdate = form1.format(pdate) + form2.format(ptime);
	}
	public void setSdate(String sdate) {
		this.sdate = sdate;
	}
	public String getIsshow() {
		return isshow;
	}
	public void setIsshow(String isshow) {
		this.isshow = isshow;
	}
	public Date getPdate() {
		return pdate;
	}
	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}
	public Time getPtime() {
		return ptime;
	}
	public void setPtime(Time ptime) {
		this.ptime = ptime;
		setSdate();
	}
	
	@Override
	public String toString() {
		return "ProfileVO [pno=" + pno + ", mno=" + mno + ", len=" + len + ", pcode=" + pcode + ", oriname=" + oriname
				+ ", savename=" + savename + ", dir=" + dir + ", isshow=" + isshow + ", pdate=" + pdate + ", ptime="
				+ ptime + "]";
	}
	
}
