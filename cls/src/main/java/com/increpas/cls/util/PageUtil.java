package com.increpas.cls.util;

/**
 * 이 클래스는 페이지 이동 기능에 필요한 정보를 계산하고 저장하기 위해서
 * 만들어진 유틸리티 클래스
 * @author 	이명환
 * @since	2020.05.14
 * @version	v.1.0.0
 *
 */
public class PageUtil {
	// 변수 선언
	private int nowPage;	// 현재 보고있는 페이지 *항상필요
	private int totalPage;	// 총 페이지 수
	private int totalCount;	// 총 게시물 수	*항상필요
	
	private int pageRow = 3;	// 한 페이지에 보여줄 게시물 수
	private int pageGroup = 3;	// 한 화면에서 이동 가능한 페이지 수
	
	private int startPage;	// 해당 화면에서 보여질 이동 시작 페이지
	private int endPage;	// 해당 화면에서 보여질 이동 마지막 페이지
	
	private int startCont;	// 현재 페이지에서 보여줄 시작 게시물 rownum
	private int endCont;	// 현재 페이지에서 보여줄 마지막 게시물 rownum
	
	public void setPage() {
		setPage(nowPage, totalCount, pageRow, pageGroup);
	}
	public void setPage(int totalCount) {
		setPage(nowPage, totalCount, pageRow, pageGroup);
	}
	public void setPage(int nowPage, int totalCount) {
		setPage(nowPage, totalCount, pageRow, pageGroup);
	}
	
	public void setPage(int nowPage, int totalCount, int pageRow, int pageGroup) {
		this.nowPage = nowPage;
		this.totalCount = totalCount;
		this.pageRow = pageRow;
		this.pageGroup = pageGroup;
		
		// 나머지 변수를 세팅해준다
		calcPage();
		calcStart();
		calcEnd();
		calcCont();
	}
	
	// 총 페이지 수 계산하는 함수
	public void calcPage() {
		/*
		 	총 페이지 수(totalPage)는 
		 	총 게시물 수(totalCount)를 한 화면에 표시할 게시물 개수(pageRow)로 나누면 된다
		 	단, 경우에 따라서 한 페이지가 증가될 수 있다
		 */
		totalPage = (totalCount % pageRow == 0)?(totalCount / pageRow):(totalCount / pageRow + 1);
	}
	
	// 시작페이지 계산할 함수
	public void calcStart() {
		// 현재 보는 페이지의 그룹을 계산하고
		int tmpGroup = (nowPage - 1) / pageGroup;
		
		// 현재 보는 페이지 그룹의 시작 페이지 번호를 계산
		startPage = tmpGroup * pageGroup + 1;
	}
	// 마지막페이지 계산할 함수
	public void calcEnd() {
		// 현재 보는 페이지의 그룹을 계산하고
		int tmpGroup = (nowPage - 1) / pageGroup;
		
		// 현재 보는 페이지 그룹의 시작 페이지 번호를 계산
		endPage = (tmpGroup + 1) * pageGroup;
		
		if(endPage > totalPage) {
			endPage = totalPage;
		}
	}
	
	// 시작과 종료 게시물 번호를 계산해주는 함수
	public void calcCont() {
		startCont = (nowPage - 1) * pageRow + 1;
		endCont = nowPage * pageRow;
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getPageRow() {
		return pageRow;
	}

	public void setPageRow(int pageRow) {
		this.pageRow = pageRow;
	}

	public int getPageGroup() {
		return pageGroup;
	}

	public void setPageGroup(int pageGroup) {
		this.pageGroup = pageGroup;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getStartCont() {
		return startCont;
	}

	public void setStartCont(int startCont) {
		this.startCont = startCont;
	}

	public int getEndCont() {
		return endCont;
	}

	public void setEndCont(int endCont) {
		this.endCont = endCont;
	}
	@Override
	public String toString() {
		return "PageUtil [nowPage=" + nowPage + ", totalPage=" + totalPage + ", totalCount=" + totalCount + ", pageRow="
				+ pageRow + ", pageGroup=" + pageGroup + ", startPage=" + startPage + ", endPage=" + endPage
				+ ", startCont=" + startCont + ", endCont=" + endCont + "]";
	}
	

}
