<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
</head>
<body>
	
	<form action="memberJoinPro.jsp" method="post">
		<table border="1">
		<tr>
			<td>���̵�</td>
			<td><input type="text" name="id">
				<input type="button" value="�ߺ�üũ"></td>
		</tr>
		<tr>
			<td>��й�ȣ</td>
			<td><input type="password" name="passwd1"></td>
		</tr>
		
		<tr>
			<td>��й�ȣ Ȯ��</td>
			<td>
			<input type="password" name="passwd2">
			<input type="button" value="��й�ȣȮ��" onclick=""></td>
		</tr>
		
		<tr>
			<td>�� ��</td>
			<td><input type="text" name="name"></td>
		</tr>
		
		<tr>
			<td>����</td>
			<td><input type="radio" name="gender" value="��">��
			<input type="radio" name="gender" value="��">��</td>
		</tr>
		
		<tr>
			<td>�������</td>
			<td><input type="text" name="birth">
			</td>
		</tr>
		
		<tr>
			<td>��ȭ��ȣ</td>
			<td><select name="tel1">
					<option value="010" selected>010</option>
					<option value="010">011</option>
					<option value="010">012</option>
			</select>-
			<input type="text" name="tel2">-
			<input type="text" name="tel3"> </td>
		</tr>
		
		<tr>
			<td>�ּ�</td>
			<td><input type="text" name="addr"></td>
		</tr>
		</table>
		
		<input type="submit" value="ȸ�� ����">
		<input type="reset" value="���">
	</form>
</body>
</html>