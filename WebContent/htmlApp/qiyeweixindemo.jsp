<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>	
<title>节点消息DEMO</title>
<meta charset="utf-8">
<link href="css/style.css" rel='stylesheet' type='text/css' />
<!-- qiyeweixindemo.jsp 演示节点消息发送至企业微信演示使用
<meta name="viewport" content="width=device-width, initial-scale=1">
 -->

<!--  -->
<meta http-equiv="Cache-Control" content="no-cache"/>  
<meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=2.0"/> 

<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<link type="text/css" rel="stylesheet" href="css/buttons/JFButtonStyle-1.css" />

<style type="text/css">
.logo{
	margin-bottom: -25px;
}
#qq,#baidu{
	margin: 0;
}
</style>
	<script type="text/javascript" src="https://3gimg.qq.com/lightmap/components/geolocation/geolocation.min.js"></script>

	<script type="text/javascript" src="http://api.map.baidu.com/api?v=1.3"></script>  
	<script type="text/javascript" src="http://developer.baidu.com/map/jsdemo/demo/convertor.js"></script>

</head>
<body>
<!-----start-main---->
<div class="login-form">
<form name="form1" method="post" action="qiyemessage.do">

	<div class="logo">
		<h1></h1>	
	</div>
	
	<input id="demo" name="demo" value="demo" readonly="readonly" type="text" style="display:none">
	<div class="submit">
		<button class="large red button style-1">发    送</button>
	</div>
</form>
</div>
<!-----start-copyright---->
<div class="copy-right">
	<p>Copyright &copy; 2018.Company name All rights reserved.<a href="#" target="_blank" title="	江汽物流">江汽物流</a> - soft from <a href="#" title="合肥云竹" target="_blank">合肥云竹</a></p> 
</div>
<!-----//end-copyright---->
</body>
</html>