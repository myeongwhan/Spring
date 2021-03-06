<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>edit</title>
<link rel="stylesheet" href="../css/w3.css" />
<link rel="stylesheet" href="../css/user.css" />
<script type="text/javascript" src="/cls/js/jquery-3.5.0.min.js"></script>
<style>
	.imgsize {
		width: 90px;
		height: auto;
	}
	.rel {
		position: relative;
    	right: -21px;
    }
    .rel2 {
		position: relative;
    	top: 12px;
    }
</style>
<script type="text/javascript">
	$(function(){
		// 아바타 수정 처리
		var sano = $('#oldAno').text();
		alert(sano);
		$('input[type="radio"]').click(function(){
			var newAno = $(this).val();
			alert(newAno);
			if(sano != newAno){
				$('#ano').val(newAno);
			}
			alert($('#ano').val());
		});
		
		// 비번일치
		$('#pwck').keyup(function(){
			var pw = $('#spw').val();
			var pwck = $('#pwck').val();
			if(pw == pwck){
				$('#pwckshow').html('일치');
				$('#pwckshow').css('color', 'blue');
			} else{
				$('#pwckshow').html('다름');			
				$('#pwckshow').css('color', 'red');
			}
		});
		function addTag(e){
			var img = URL.createObjectURL(e.target.files[0]);
//			document.getElementById('img').setAttribute('src', img);
//			$('#img').attr('src', img);
			$('#imgbox').append('<img class="w3-card imgwin w3-border w3-margin-top w3-margin-bottom pd-10 imgbox1" src="' + img + '" alt="Image">');
			$('#filebox').append('<input class="w3-col" type="file" name="file">');
			$('#filebox > input').last().change(addTag);
		}
		
		/*
			문제]
				띄운 이미지 태그를 추가하는 input 태그의 내용이 변경될 때
				해당 이미지 태그의 src 속성값을 변경처리하세요
		*/
		
		// 프사 미리보기
		$('#file').change(function(e){
			addTag(e);
		});
		
		var mgen = '남자';
		var fgen = '여자';
		var tt = '${DATA.gen}';
		if(tt == mgen){
			$('#M').prop('checked', 'checked');
		} else if(tt == fgen) {
			$('#F').prop('checked', 'checked');
		}
		
		$('input[value=${DATA.ano}]').prop('checked', 'checked');
		
		// 수정/취소얼럿
		$('#ebtn').click(function(){
//			alert('가입완료 서밋 안만듦');
			// 데이터 무결성 검사해야됨
 			alert($('#pw').val());
			alert($('#ano').val());
			var str = $('#spw').val();
			$('#frm').prepend('<input type="hidden" name="pw" value="' + str + '">');
			alert(str);
			/* if(str == null) {
				$('#pw').prop('disabled','disabled');
			} */
			$('#frm').submit();
		});
		$('#hbtn').click(function(){
//			alert('홈으로돌아감 홈링크안만듦');
			$(location).attr('href', '/cls/main.cls');
		});
	});
</script>
</head>
<body>
	<div class="w3-col l3 m3"><p></p></div>
	<div class="w3-col l6 m6 s12">
		<form class="w3-col" method="post" action="/cls/member/memberEditProc.cls" name="frm" id="frm" encType="multipart/form-data">
			<input type="hidden" name="mno" id="mno" value="${DATA.mno }">
			<input type="hidden" name="id" id="id" value="${DATA.id }">
			<input type="hidden" name="ano" id="ano" value="0">
			<div style="display: none;" id="oldAno">${DATA.ano }</div>
			<div class="w3-col w3-padding w3-blue w3-card">
				<h3 class="w3-center">${DATA.name }님 edit</h3>
			</div>
			<div class="w3-col w3-padding w3-border w3-card" style="margin-top: 10px;">
				<!-- 아이디 -->
				<div class="w3-row">
					<label class="w3-col m3 w3-right-align w3-padding" for="id">I D :</label>
					<div class="w3-col m9 w3-padding">
						<div class="w3-col m9">
							<div type="text" >${DATA.id }</div>
						</div>
					</div>
				</div>
				<!-- 비번 -->
				<div class="w3-row">
					<label class="w3-col m3 w3-right-align w3-padding" for="pw">P W :</label>
					<div class="w3-col m9 w3-padding">
						<input class="w3-col m12" type="password" id="spw" name="spw">
					</div>
				</div>
				<!-- 비번확인 -->
				<div class="w3-row">
					<label class="w3-col m3 w3-right-align w3-padding" for="pwck">P W ck :</label>
					<div class="w3-col m9 w3-padding">
						<input class="w3-col m12" type="password" id="pwck" name="pwck">
						<div class="w3-col m12" id="pwckshow"></div>
					</div>
				</div>
				<!-- 프사 -->
				<div class="w3-row">
					<label class="w3-col m3 w3-right-align w3-padding" for="file">프사 :</label>
					<div class="w3-col m9 w3-padding">
						<div id="filebox">
							<input class="w3-col m12" type="file" id="file" name="file"/>
						</div>
					</div>
					<div class="w3-row w3-center" id="imgbox" >
					<c:forEach var="img" items="${PIC.savename}">
						<img id="img" style="width: 100px; height: auto;" src="/cls/profile/${PIC.savename}" />
					</c:forEach>
					</div>
				</div>
				<!-- 이름 -->
				<div class="w3-row">
					<label class="w3-col m3 w3-right-align w3-padding" for="name">이 름 :</label>
					<div class="w3-col m9 w3-padding">
						<div class="w3-col m12" type="text" id="name" name="name">${DATA.name }</div>
					</div>
				</div>
				<!-- 이메일 -->
				<div class="w3-row">
					<label class="w3-col m3 w3-right-align w3-padding" for="mail">이메일 :</label>
					<div class="w3-col m9 w3-padding">
						<div class="w3-col m12" type="text" id="mail" name="mail">${DATA.mail }</div>
					</div>
				</div>
				<!-- 폰번 -->
				<div class="w3-row">
					<label class="w3-col m3 w3-right-align w3-padding" for="tel">휴대폰 :</label>
					<div class="w3-col m9 w3-padding">
						<div class="w3-col m12" type="text" id="tel" name="tel">${DATA.tel }</div>
					</div>
				</div>
				<!-- 성별 -->
				<div class="w3-row">
					<label class="w3-col m3 w3-right-align w3-padding" for="gen">성 별 :</label>
					<div class="w3-col m9 w3-padding">
					<c:if test="${DATA.gen eq '남자' }">
						<span class="w3-col m6">
							<span >남자</span>
						</span>
					</c:if>
					<c:if test="${DATA.gen eq '여자' }">
						<span class="w3-col m6">
							<span >여자</span>
					</c:if>
					</div>
				</div>
				<!-- 성별 안 아바타선택 -->
				<div class="w3-row " id="genbox">
					<c:if test="${DATA.gen eq '남자' }">
					<label class=" rel2 w3-col m3 w3-right-align w3-padding" style="padding-left:0px!important">아바타 선택 : </label>
						<div class="w3-col m9 rel">
							<input type="radio" id="avt1" name="avt" class="w3-radio" value="11"><label for="avt1"><img class=" w3-button imgsize" src="../img/img_avatar1.png"></label>
							<input type="radio" id="avt2" name="avt" class="w3-radio" value="12"><label for="avt2"><img class=" w3-button imgsize" src="../img/img_avatar2.png"></label>
							<input type="radio" id="avt3" name="avt" class="w3-radio" value="13"><label for="avt3"><img class=" w3-button imgsize" src="../img/img_avatar3.png"></label>
						</div>
					</c:if>
					<c:if test="${DATA.gen eq '여자' }">
					<label class=" rel2 w3-col m3 w3-right-align w3-padding" style="padding-left:0px!important">아바타 선택 : </label>
						<div class="w3-col m9 rel">
							<input type="radio" id="avt4" name="avt" class="w3-radio" value="14"><label for="avt4"><img class=" w3-button imgsize" src="../img/img_avatar4.png"></label>
							<input type="radio" id="avt5" name="avt" class="w3-radio" value="15"><label for="avt5"><img class=" w3-button imgsize" src="../img/img_avatar5.png"></label>
							<input type="radio" id="avt6" name="avt" class="w3-radio" value="16"><label for="avt6"><img class=" w3-button imgsize" src="../img/img_avatar6.png"></label>
						</div>
					</c:if>
				</div>
				<div class="w3-row">
					<label class="w3-col m3 w3-right-align w3-padding w3-hide" for="avt">아바타 :</label>
				</div>
				<!-- 년월일 -->
				<div class="w3-row">
					<label class="w3-col m3 w3-right-align w3-padding" for="birth">생년월일 :</label>
					<div class="w3-col m9 w3-padding">
						<div class="w3-col m12">${DATA.birth }</div>
					</div>
				</div>
			</div>
		</form>
		<!-- 확인취소버튼 -->
		<div class="w3-col m6 w3-padding w3-card w3-center w3-blue w3-button" id="ebtn">수정</div>
		<div class="w3-col m6 w3-padding w3-card w3-center w3-red w3-button" id="hbtn">홈으로</div>
	</div>
</body>
</html>