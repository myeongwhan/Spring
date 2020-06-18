<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원리스트</title>
<link rel="stylesheet" href="/cls/css/w3.css">
<link rel="stylesheet" href="/cls/css/user.css">
<script type="text/javascript" src="/cls/js/jquery-3.5.0.min.js"></script>
<style>
</style>
<script type="text/javascript">
	$(document).ready(function(){
		
	});
</script>
</head>
<body>
	<div class="w3-content mxw">
		<h2 class="w3-light-green w3-center w3-padding w3-card-4">${DATA.name } 회원정보</h2>
		<div class="w3-col w3-padding w3-card-4">
			<div class="w3-border-bottom w3-center">
				<img class="w3-border w3-circle w3-card pd-20 w-200" src="/cls/img/${DATA.avatar }">
			</div>
			<div class="w3-border-bottom">
				<div class="w3-col w-200 f16">회원번호: </div>
				<div class="w3-rest f16">${DATA.mno }</div>
			</div>
			<div class="w3-border-bottom">
				<div class="w3-col w-200 f16">회원이름: </div>
				<div class="w3-rest f16">${DATA.name }</div>
			</div>
			<div class="w3-border-bottom">
				<div class="w3-col w-200 f16">회원계정: </div>
				<div class="w3-rest f16">${DATA.id }</div>
			</div>
			<div class="w3-border-bottom">
				<div class="w3-col w-200 f16">회원메일: </div>
				<div class="w3-rest f16">${DATA.mail }</div>
			</div>
			<div class="w3-border-bottom">
				<div class="w3-col w-200 f16">전화번호: </div>
				<div class="w3-rest f16">${DATA.tel }</div>
			</div>
			<div class="w3-border-bottom">
				<div class="w3-col w-200 f16">회원성별: </div>
				<div class="w3-rest f16">${DATA.gen }</div>
			</div>
			<div class="w3-border-bottom w3-margin-bottom">
				<div class="w3-col w-200 f16">가입일자: </div>
				<div class="w3-rest f16">${DATA.sdate }</div>
			</div>
		</div>
	</div>
</body>
</html>