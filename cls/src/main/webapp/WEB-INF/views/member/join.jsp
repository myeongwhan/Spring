<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>인크레파스 쪼인</title>
<link rel="stylesheet" href="../css/w3.css" />
<script type="text/javascript" src="/cls/js/jquery-3.5.0.min.js"></script>
<script type="text/javascript" src="/cls/js/join.js"></script>
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
	/*
		비동기 통신
			==> 웹서비스에서 원칙은
				클라이언트가 요청을 하면
				서버는 클라이언트가 요청한 문서를 응답(전송)해준다
				그리고 그 직후, 서버는 클라이언트와의 연결을 끊는다
				따라서 이런 통신 방식을 단절형 통신이라 이야기한다
				
				그리고 서버가 전달된 문서는
				원칙은 클라이언트가 전송받은 문서와 동일해야 된다
				이런 경우를 동기형이라 이야기한다
				
				반대로, 서버가 전달해준 문서와
				클라이언트가 받은 문서를 일부분만 교체한 경우
				비동기라 이야기한다
				
				결론적으로
				비동기처리란, 서버가 보내준 문서의 일부분만
				서버와 통신을 해서 새로 받아서 교체해서
				일부분만 교체하는 처리를 말한다
				영어로 Ajax라 부른다
				
				그런데 이 비동기 처리는 jQuery에서 함수로 정의가 되어있고
				형식]
					$.ajax({
						url: '주소',				==> 요청주소
						type: 'get | post',			==> 데이터 전송방식
						dataType: 'text | html | xml | json',	==> 데이터 전송타입
						data: {						==> 전송될 데이터(파라미터)
							넘겨줄 데이터 나열
							변수이름: 데이터,
							변수이름: 데이터,
								...
						},
						success: function(data){	==> 통신에 성공할 경우 실행할 함수
							성공시 처리내용
						},
						error: function(){			==> 통신에 실패할 경우 실행할 함수
							실패시 처리내용
						}
					})
	*/
</script>
</head>
<body>
	<div class="w3-col l3 m3"><p></p></div>
	<div class="w3-col l6 m6 s12">
		<form class="w3-col" method="post" action="/cls/member/joinProc.cls" name="frm" id="frm" encType="multipart/form-data">
			<div class="w3-col w3-padding w3-blue w3-card">
				<h3 class="w3-center">Increpas Join</h3>
			</div>
			<div class="w3-col w3-padding w3-border w3-card" style="margin-top: 10px;">
				<!-- 아이디 -->
				<div class="w3-row">
					<label class="w3-col m3 w3-right-align w3-padding" for="id">I D :</label>
					<div class="w3-col m9 w3-padding">
						<div class="w3-col m9">
							<input type="text" id="id" name="id" />						
						</div>
						<small class="w3-col m3 w3-orange w3-button" id="idck" style="padding-left: 12px; height: 29px;">중복확인</small>
					</div>
					<p class="w3-col w3-center" id="idmsg" style="display: none;"></p>
					<div class="w3-col w3-center" id="idCont" style="display: none;">
						<!-- <h4 class="w3-col m6">I D : </h4><h4 class="w3-col m6" id="getId"></h4>
						<h4 class="w3-col m6">NAME : </h4><h4 class="w3-col m6" id="getName"></h4>
						<h4 class="w3-col m6">TEL : </h4><h4 class="w3-col m6" id="getTel"></h4>
						<h4 class="w3-col m6">MAIL : </h4><h4 class="w3-col m6" id="getMail"></h4> -->
						<!-- 
							문제]
								아이디체크 버튼을 클릭했을 때
								입력한 아이디가 존재하면 해당 아이디의 정보를 조회해서
								idCont 태그에 추가하고 보여주세요
						 -->
					</div>
				</div>
				<!-- 비번 -->
				<div class="w3-row">
					<label class="w3-col m3 w3-right-align w3-padding" for="pw">P W :</label>
					<div class="w3-col m9 w3-padding">
						<input class="w3-col m12" type="password" id="pw" name="pw">
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
						<input class="w3-col m12" type="file" id="file" name="file"/>
					</div>
					<div class="w3-row w3-center" >
						<img id="img" style="width: 100px; height: auto;" src="" />
					</div>
				</div>
				<!-- 이름 -->
				<div class="w3-row">
					<label class="w3-col m3 w3-right-align w3-padding" for="name">이 름 :</label>
					<div class="w3-col m9 w3-padding">
						<input class="w3-col m12" type="text" id="name" name="name">
					</div>
				</div>
				<!-- 이메일 -->
				<div class="w3-row">
					<label class="w3-col m3 w3-right-align w3-padding" for="mail">이메일 :</label>
					<div class="w3-col m9 w3-padding">
						<input class="w3-col m12" type="text" id="mail" name="mail">
					</div>
				</div>
				<!-- 폰번 -->
				<div class="w3-row">
					<label class="w3-col m3 w3-right-align w3-padding" for="tel">휴대폰 :</label>
					<div class="w3-col m9 w3-padding">
						<input class="w3-col m12" type="text" id="tel" name="tel">
					</div>
				</div>
				<!-- 성별 -->
				<div class="w3-row">
					<label class="w3-col m3 w3-right-align w3-padding" for="gen">성 별 :</label>
					<div class="w3-col m9 w3-padding">
						<span class="w3-col m6">
							<input type="radio" id="M" name="gen" value="M"><label for="M">남자</label>
							<input type="radio" id="F" name="gen" value="F" style="margin-left: 10px;"><label for="F">여자</label>
						</span>
					</div>
				</div>
				<!-- 성별 안 아바타선택 -->
				<div class="w3-row " id="genbox">
					
				</div>
				<div class="w3-row">
					<label class="w3-col m3 w3-right-align w3-padding w3-hide" for="avt">아바타 :</label>
					<!-- <div class="w3-col m9 w3-padding">
						<div class="w3-row" id="avt">
							<div class="w3-col">
								<input type="radio" id="avt" name="avt" />
							</div>
						</div>
					</div> -->
				</div>
				<!-- 년월일 -->
				<div class="w3-row">
					<label class="w3-col m3 w3-right-align w3-padding" for="birth">생년월일 :</label>
					<div class="w3-col m9 w3-padding">
						<select class="w3-col m4" id="year">
							<option>년</option>
						</select>
						<select class="w3-col m4" id="month">
							<option>월</option>
						</select>
						<select class="w3-col m4" id="day">
							<option>일</option>
						</select>
					</div>
				</div>
			</div>
		</form>
		<!-- 확인취소버튼 -->
		<div class="w3-col m6 w3-padding w3-card w3-center w3-blue w3-button" id="jbtn">회원가입</div>
		<div class="w3-col m6 w3-padding w3-card w3-center w3-red w3-button" id="hbtn">홈으로</div>
	</div>
</body>
</html>