<%@ page pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<html>
<head>
	<link rel="stylesheet" type="text/css"
		  href="https://cdnjs.cloudflare.com/ajax/libs/extjs/6.0.0/classic/theme-crisp/resources/theme-crisp-all.css"/>
	<script type="text/javascript"
			src="https://cdnjs.cloudflare.com/ajax/libs/extjs/6.0.0/ext-all.js"></script>
	<script type="text/javascript" src="app.js"></script>
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