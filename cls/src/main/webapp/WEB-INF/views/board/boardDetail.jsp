<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시판상세보기</title>
    <link rel="stylesheet" href="/cls/css/w3.css">
<style>
    
</style>
<script type="text/javascript" src="../js/jquery-3.5.0.min.js"></script>
<script type="text/javascript">
	$(function(){
		$('#board').click(function(){
			$('#frm').submit();
		});
		$('#home').click(function(){
			$(location).attr('href', '/cls/main.cls');
		});
	});
</script>
</head>
<body>
	<form method="post" action="/cls/board/boardList.cls" id="frm">
		<input type="hidden" name="nowPage" id="nowPage" value="${PAGE.nowPage }">
		<input type="hidden" name="bno" value="${DATA.bno }">
	</form>
    <div class="w3-content" style="max-width: 1000px;">
        <div class="w3-center w3-col ">
            <h2>상세보기페이지</h2>
        </div>
        <div class="w3-row w3-margin-top">
            <div class="w3-row  w3-right-align ">
                <div class="w3-button w3-red" id="update">수정</div>
                <div class="w3-button w3-red" id="delete">삭제</div>
            </div>
            <div class="w3-row w3-margin-top">
                <div class="w3-card w3-padding w3-center">
                    <h2>${LIST.get(0).title }</h2>
                </div>
            </div>
            <div class="w3-row w3-margin-top">
                <div class="w3-card w3-padding">
                    <div style="display: flex;">
                        <div class="w3-margin-right" style="display: flex;">
                            <p class="w3-margin-right">작성자:</p>
                            <p class="w3-border-right" style="height: 25px; padding-right: 20px;">${LIST.get(0).name }</p>        
                        </div>
                        <div class="w3-margin-right" style="display: flex;">
                            <p class="w3-margin-right">작성일:</p>
                            <p class="w3-border-right" style="height: 25px; padding-right: 20px;">${LIST.get(0).sdate }</p>        
                        </div>     
                    </div>
                    <div style="display: flex;">                        
                        <c:if test="${not empty LIST}">
	                        <p class="w3-margin-right" style="margin-top: 0px;">파일다운</p>                     
	                        <c:forEach var="pic" items="${LIST }" varStatus="st">
	                        	<a href="/cls/upload/${pic.savename }"><img class="w3-margin-right" src="/cls/upload/${pic.savename }" style="width: auto; height: 80px;"></a>                                                   
	                        </c:forEach>
                        </c:if>
                    </div>
                </div>
            </div>
            <div class="w3-row w3-margin-top">
                <div class="w3-card w3-padding mih w3-margin-bottom" style="min-height: 700px;">
                    <div>${LIST.get(0).body }</div>
                </div>
            </div>
        </div>
        <div class="w3-row w3-margin-top w3-margin-bottom">
            <div class=" w3-button w3-blue" id="board">목록으로</div>
            <div class=" w3-button w3-green" id="home">홈으로</div>
        </div>
      </div>
</body>
</html>