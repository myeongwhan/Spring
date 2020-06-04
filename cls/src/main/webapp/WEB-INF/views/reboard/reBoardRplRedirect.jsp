<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>ReBoard Redirect</title>
<link rel="stylesheet" href="../css/w3.css">
<link rel="stylesheet" href="../css/w3.css">
<script type="text/javascript" src="../js/jquery-3.5.0.min.js"></script>
<style>
</style>
<script type="text/javascript">
	$(function(){
		alert('param.nowpage = ${param.nowPage}');
		$('#frm').submit();
	});
</script>
</head>
<body>
	<%-- GET 방식으로 보내기
	<c:redirect url="/reBoard/reBoard.cls">
		<c:param name="nowPage" value="${param.nowPage }" />
	</c:redirect>
	 --%>
	
	<%-- post방식으로 보내기 --%>
	<form method="POST" action="/clsProj/reBoard/reBoard.cls" id="frm">
		<input type="hidden" name="nowPage" value="${param.nowPage }">
		<input type="hidden" name="upno" value="${param.upno }">
	</form>
</body>
</html>