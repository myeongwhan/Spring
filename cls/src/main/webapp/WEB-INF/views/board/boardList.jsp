<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>게시판보기</title>
<link rel="stylesheet" href="/cls/css/w3.css">
<style>
    .mxw{max-width: 1200px;}
    .bnone{display: none;}
</style>
<script type="text/javascript" src="/cls/js/jquery-3.5.0.min.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#write').click(function(){
			$(location).attr('href', '/cls/board/boardWrite.cls');
		});
		
		$('.pbtn').click(function(){
			// 어떤 버튼이 클릭됐는지 알아낸다
			var bstr = $(this).html();
			if(bstr == 'PRE'){
				$('#nowPage').val('${PAGE.startPage - 1}');
			} else if(bstr == 'NEXT'){
				$('#nowPage').val('${PAGE.endPage + 1}');
			} else {
				$('#nowPage').val(bstr);
			}
			$('#frm').attr('action', '/cls/board/boardList.cls');
			$('#frm').submit();
		});
		
		$('.content').click(function(){
			var sno = $(this).attr('id');
			$('#bno').val(sno);
			$('#nowPage').val('${PAGE.nowPage}')	// 상세보기에서 목록으로 돌아갈 때 쓰임
			$('#frm').attr('action', '/cls/board/boardDetailProc.cls');
			$('#frm').submit();
		});
		
	});
	
</script>
</head>
<body>
    <form method="post" action="" id="frm">
    	<input type="hidden" name="nowPage" id="nowPage">
    	<input type="hidden" name="bno" id="bno">
    </form>
    
    <div class="w3-content mxw">
        <div class="w3-center w3-col ">
            <h2>인크레파스 게시판</h2>
        </div>
        <div class="w3-rwo w3-margin-top">
            <div class="w3-col w3-padding w3-right-align w3-margin">
            	<div class="w3-button w3-red" id="write">글쓰기</div>
            </div>
            <table class="w3-col w3-table-all w3-hoverable">
              <thead>
                <tr class="w3-light-grey">
                  <th>글번호</th>
                  <th>제목</th>
                  <th>작성자</th>
                  <th>작성일</th>
                  <th>조회수</th>
                </tr>
              </thead>
	              <!-- 여기에 반복문을 작성하세요 -->
              <c:forEach var="data" items="${LIST}">
	              <tr class="w3-text-gray content" id="${data.bno}">
	                <td>${data.bno }</td>
	                <!-- 1 -->
	                <td>${data.title }</td>
	                <!-- 제목입니다 -->
	                <td>${data.name }</td>
	                <!-- 홍길동 -->
	                <td>${data.sdate }</td>
	                <!-- 2020/05/20 -->
	                <td>${data.click }</td>
	                <!-- 0 -->
	              </tr>
              </c:forEach>
              <!--여기까지-->
            </table>
        </div>
        <div>
			<div class="w3-bar w3-center">
				<c:if test="${PAGE.startPage lt (PAGE.pageGroup + 1) }">
				<span class="ww3-light-gray">PRE</span>
				</c:if>
				<c:if test="${PAGE.startPage ge (PAGE.pageGroup + 1) }">
				<span class="w3-button w3-blue pbtn">PRE</span>
				</c:if>
			  <c:forEach var="pageNO" begin="${PAGE.startPage}" end="${PAGE.endPage}">
				  <span class="w3-button w3-blue pbtn">${pageNO}</span>
			  </c:forEach>
			  <c:if test="${PAGE.endPage ne PAGE.totalPage }">
			  <span class="w3-button w3-blue pbtn">NEXT</span>
			  </c:if>
			  <c:if test="${PAGE.endPage eq PAGE.totalPage }">
			  <span class="w3-light-gray">NEXT</span>
			  </c:if>
			</div>
		</div>
      </div>
</body>
</html>