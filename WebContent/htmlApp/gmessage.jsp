<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String errorKey =(String)(request.getAttribute("errorKey")==null?"^_^签到结束,返回或退出":request.getAttribute("errorKey"));
%>
<html>
<head>	
<title>location message</title>
<meta charset="utf-8">
<link href="css/style.css" rel='stylesheet' type='text/css' />
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<link type="text/css" rel="stylesheet" href="css/buttons/JFButtonStyle-1.css" />
</head>
<body>
<!-----start-main---->
<div class="login-form">
<form>
	<div class="logo">
		<h1>SIGN OVER</h1>	
	</div>
	<input type="text" readonly="readonly" placeholder="<%=errorKey%>">
	<div>
		<input class="large red button style-1" type="button" value="返回" onclick="window.location.href=''">
	</div>
</form>
</div>
<!-----start-copyright---->
<div class="copy-right">
	<p>Copyright &copy; 2018.Company name All rights reserved.<a href="http://www.cssmoban.com/" target="_blank" title="	江汽物流">江汽物流</a> - soft from <a href="http://www.cssmoban.com/" title="合肥云竹" target="_blank">合肥云竹</a></p> 
</div>
<!-----//end-copyright---->
		 		
</body>
</html>