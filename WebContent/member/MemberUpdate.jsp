<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>회원정보</h1>
<form action='update.do' method='post'>
번호 : <input type='text' name='mno' value="${member.no}" readonly><br>
이름 : <input type='text' name='mname' value="${member.name}"><br>
이메일 : <input type='text' name='email' value="${member.email}"><br>
가입일 : <input type='text' value="${member.createdDate}" readonly><br>
<input type="hidden" name='mno' value="${member.no}">
<input type='submit' value='확인'>
<input type='button' value='삭제' onclick='location.href="delete.do?mno=${member.no}"'>
<input type='button' value='취소' onclick='location.href="list.do"'>
</form>
</body>
</html>