<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시판글쓰기</title>
    <link rel="stylesheet" href="/cls/css/w3.css">
<style>
    
</style>
<script type="text/javascript" src="/cls/js/jquery-3.5.0.min.js"></script>
<script type="text/javascript">
	
	/*
	function setNum(cnt){
		cnt = cnt + 1;
		return cnt;
	}
	*/
	
	var cnt = 1;
	function addTg(){
		/*cnt = setNum(cnt);*/
		var tag1 = '<input class="file w3-input w3-border" type="file" name="file">';
		$('.box').append(tag1);
		var el = $('.box > input:file');
		//alert('## length' + el.length);
		
		$('.box > input').last().change(function(){
			var str = $(this).val();
			if(str){
				addTg();
			} else {
				$(this).remove();
			}
		});
	}
	$(function(){
		$('#save').click(function(){
			// 데이터 유효성 검사하고
			//$('input[type="file"][value=""]').prop('disabled', 'disabled');
			$('input:file').last().remove();
			$('#frm').submit();
		});
		
		addTg();
		
		$('#cancel').click(function(){
			$(location).attr('href', '/cls/board/boardList.cls');
		});
	});
</script>
</head>
<body>
    <div class="w3-content" style="max-width: 1000px;">
        <div class="w3-center w3-col ">
            <h2>게시판글쓰기</h2>
        </div>
        <form method="post" action="/cls/board/boardWriteProc.cls" id="frm" encType="multipart/form-data" class="w3-row w3-margin-top">
            <div class="w3-row w3-margin-top">
                <div class="w3-card w3-padding">
                    <input class="w3-border-0" type="text" name="title" id="title" placeholder="제목 입력란" style="width: 960px;">
                </div>
            </div>
            <div class="w3-row w3-margin-top w3-left-align box">
                <!-- <input class="file w3-border w3-input" type="file" name="file"> -->
            </div>
            <div class="w3-row w3-margin-top">
                <div class="w3-card w3-padding mih w3-margin-bottom" style="min-height: 700px;">
                    <textarea class="w3-border-0" name="body" id="body" cols="105" rows="10" placeholder="본문 입력란" style="resize: none"></textarea>
                </div>
            </div>
        </form>
        <div class="w3-row w3-margin-top w3-margin-bottom">
            <div class=" w3-button w3-blue" id="cancel">취소</div>
            <div class=" w3-button w3-green" id="save">완료</div>
        </div>
      </div>
</body>
</html>