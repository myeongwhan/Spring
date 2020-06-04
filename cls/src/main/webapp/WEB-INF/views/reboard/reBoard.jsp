<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>댓글</title>
<link rel="stylesheet" href="../css/w3.css">
<link rel="stylesheet" href="../css/user.css">
<script type="text/javascript" src="../js/jquery-3.5.0.min.js"></script>
<style>
</style>
<script type="text/javascript">
	$(document).ready(function(){
		$('.hbtn').click(function(){
			var tid = $(this).attr('id');
			var url = '';
			if(tid == 'login'){
				url = '/clsProj/member/login.cls';
			} else if(tid == 'logout'){
				url = '/clsProj/member/logoutProc.cls';
			} else if(tid == 'home'){
				url = '/clsProj/main.cls';
			} else if(tid == 'write'){ // 댓글(원문)쓰기버튼
				url = '/clsProj/reBoard/reBoardReply.cls';
			}
			
			$(location).attr('href', url);
		});
		
		$('.btn').click(function(){
			var tid = $(this).attr('id');
			var url = '';
			if(tid == 'del'){	// 삭제버튼
				var str = $(this).parent().attr('id');	// ==> 상위태그(작성글) id값
				$('#reno').val(str);
				$('#frm').attr('action', '/clsProj/reBoard/reBoardDel.cls');
			} else if(tid == 'con'){	// 수정버튼
				var str = $(this).parent().attr('id');	// ==> 상위태그(작성글) id값
				$('#reno').val(str);
				$('#frm').attr('action', '/clsProj/reBoard/reBoardEdit.cls');
			} else if(tid == 're'){		// 댓글입력버튼
				var str = $(this).parent().attr('id');	// ==> 상위태그(작성글) id값
				$('#nowPage').val('${PAGE.nowPage}');
				$('#reno').val(str);
				$('#frm').attr('action', '/clsProj/reBoard/reBoardReply.cls');
			}
			
			$('#frm').submit();
		});
		
		$('.pbtn').click(function(){
			var bstr = $(this).html();
			if(bstr == 'PRE'){
				$('#nowPage').val('${PAGE.startPage - 1}');
			} else if(bstr == 'NEXT'){
				$('#nowPage').val('${PAGE.endPage + 1}');
			} else {
				$('#nowPage').val(bstr);
			}
			$('#frm').attr('action', '/clsProj/reBoard/reBoard.cls');
			$('#frm').submit();
		});
	});
</script>
</head>
<body>
	<form method="post" action="" id="frm">
		<input type="hidden" name="nowPage" id="nowPage">
		<input type="hidden" name="reno" id="reno">
		<input type="hidden" name="body" id="body">
		<input type="hidden" name="redate" id="redate">
		<input type="hidden" name="avatar" id="avatar">
	</form>
	<div class="w3-content mxw2">
		<div class="w3-col">
			<h2 class="w3-blue w3-center w3-padding w3-margin-bottom">댓글 리스트</h2>
			<div class="w3-col">
				<c:if test="${empty SID }">
					<div class="hbtn w3-col m1 w3-right w3-button w3-blue" id="login">Login</div>
				</c:if>
				<c:if test="${not empty SID }">
					<div class="hbtn w3-col m1 w3-right w3-button w3-red" id="logout">Logout</div>
					<div class="hbtn w3-col m1 w3-right w3-button w3-orange" id="write">Write</div>
				</c:if>
				<div class="hbtn w3-col m1 w3-left w3-button w3-green" id="home">Home</div>
			</div>
			
				<%--
				--%>
				<c:forEach var="data" items="${LIST }">
				<div style="padding-left: ${data.step * 50}px;">
					<div class="w3-col w3-border" id="">
						<div class="w3-col w3-center w-100 m2 pd-10 w3-border-right">
							<img class="w-auto" style="height: 80px; margin: 10px 10px 0px 10px;" src="/clsProj/img/${data.avatar }" />
							<h5 class="w3-col w3-center mg-0 pd-0"><b>${data.id }</b></h5>
						</div>
						<div class="w3-rest pdh-10">
							<div class="w3-col w3-border-bottom">
								<h5 class="w3-half w3-left" style="margin: 0px; padding-right: 0px;">${data.sDate }</h5>
							</div>
							<div class="w3-col w3-border-bottom">
								<h6 class="w3-half w3-padding" id="body">${data.body }</h6>
							</div>
							<hr>
							<div class="w3-col" style="margin-top: 10px;" id="${data.reno }">
							<c:if test="${SID eq data.id }">
								<span class="w3-button w3-red w3-right btn" style="padding: 3px; width: 80px;" id="del">삭제</span>
								<span class="w3-button w3-orange w3-right btn" style="padding: 3px; width: 80px;" id="con">수정</span>
							</c:if>
							<c:if test="${not empty SID }">
								<span class="w3-button w3-blue w3-right btn" style="padding: 3px; width: 80px;" id="re">댓글</span>
							</c:if>
							</div>
						</div>
					</div>
				</div>
				</c:forEach> 
				<%-- 
				--%>
			
			<c:if test="${empty LIST }">
				<div class="w3-col w3-border w3-card" id="gmsg">
					<h3 class="w3-padding w3-center ">아직 작성된 글이 없습니다</h3>
				</div>
			</c:if>
		</div>
		<div>
			<div class="w3-bar w3-center">
				<c:if test="${PAGE.startPage lt 4 }">
				<span class="ww3-light-gray">PRE</span>
				</c:if>
				<c:if test="${PAGE.startPage ge 4 }">
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