<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
<title>Start Page</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
	<p>Введи коэффициенты квадратного уравнения</p>
	<form action="result" method="post">
		A=<input name="a" type="text" size="6" />
		B=<input name="b" type="text" size="6" />
		C=<input name="c" type="text" size="6" />
		<input name="resultButton" type="submit" value="Расчитать">
		
	</form>
</body>
</html>