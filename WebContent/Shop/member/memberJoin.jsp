<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>Insert title here</title>
</head>
<body>
	
	<form action="memberJoinPro.jsp" method="post">
		<table border="1">
		<tr>
			<td>아이디</td>
			<td><input type="text" name="id">
				<input type="button" value="중복체크"></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="passwd1"></td>
		</tr>
		
		<tr>
			<td>비밀번호 확인</td>
			<td>
			<input type="password" name="passwd2">
			<input type="button" value="비밀번호확인" onclick=""></td>
		</tr>
		
		<tr>
			<td>이 름</td>
			<td><input type="text" name="name"></td>
		</tr>
		
		<tr>
			<td>성별</td>
			<td><input type="radio" name="gender" value="남">남
			<input type="radio" name="gender" value="여">여</td>
		</tr>
		
		<tr>
			<td>생년월일</td>
			<td><input type="text" name="birth">
			</td>
		</tr>
		
		<tr>
			<td>전화번호</td>
			<td><select name="tel1">
					<option value="010" selected>010</option>
					<option value="010">011</option>
					<option value="010">012</option>
			</select>-
			<input type="text" name="tel2">-
			<input type="text" name="tel3"> </td>
		</tr>
		
		<tr>
			<td>주소</td>
			<td><input type="text" name="addr"></td>
		</tr>
		</table>
		
		<input type="submit" value="회원 가입">
		<input type="reset" value="취소">
	</form>
</body>
</html>