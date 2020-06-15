<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title></title>
<link rel="stylesheet" href="/cls/css/w3.css" />
<link rel="stylesheet" href="/cls/css/user.css" />
<script type="text/javascript" src="/cls/js/jquery-3.5.0.min.js"></script>
<style>

</style>
<script type="text/javascript">
	$(function(){
		$('#lbtn').click(function(){
			$(location).attr('href', '/cls/member/login.cls');
		});
		$('#jbtn').click(function(){
			$(location).attr('href', '/cls/member/join.cls');
		});
		$('#obtn').click(function(){
			$(location).attr('href', '/cls/member/logout.cls');
		});
		$('#mlbtn').click(function(){
			$(location).attr('href', '/cls/member/memberList.cls');
		});
		$('#rbtn').click(function(){
			$(location).attr('href', '/cls/member/reBoard.cls');
		});
	});
</script>
</head>
<body>
	<div class="w3-content mxw">
		<h2 class="w3-center w3-pink">Main Page</h2>
		<div class="w3-col w3-margin-top">
			<c:if test="${empty SID }">
			<div class="w3-button w3-blue w3-hover-aqua" id="lbtn">Login</div>
			<div class="w3-button w3-green w3-hover-aqua" id="jbtn">Join</div>
			</c:if>
			<c:if test="${not empty SID }">
			<div class="w3-button w3-blue w3-hover-aqua" id="obtn">Logout</div>
			<div class="w3-button w3-indigo w3-hover-blue" id="mlbtn">MemberList</div>
			<div class="w3-button w3-purple w3-hover-red" id="rbtn">reBoard</div>
			</c:if>
		</div>
	</div>
</body>
</html>