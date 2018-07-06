<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<html>
<head>	
<title>GPS Mail</title>
<meta charset="utf-8">
<link href="css/style.css" rel='stylesheet' type='text/css' />
<!-- 
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
	<script type="text/javascript">  
		var map;  
		var gpsPoint;  
		var baiduPoint;  
		var gpsAddress;  
		var baiduAddress;  

		function getLocation() {  
			//根据IP获取城市  
			var myCity = new BMap.LocalCity();  
			myCity.get(getCityByIP);  

			//获取GPS坐标  
			if (navigator.geolocation) {  
				navigator.geolocation.getCurrentPosition(showMap, handleError, { enableHighAccuracy: true, maximumAge: 1000 });  
			} else {  
				document.getElementById('baidu').value = "您的浏览器不支持使用HTML5来获取地理位置服务";
				//alert("您的浏览器不支持使用HTML5来获取地理位置服务");  
			}  
		}  
		
		function showMap(value) {  
			var longitude = value.coords.longitude;  
			var latitude = value.coords.latitude;   
			//alert("坐标经度为：" + latitude + "， 纬度为：" + longitude );  
			gpsPoint = new BMap.Point(longitude, latitude);    // 创建点坐标  
			//根据坐标逆解析地址   
			BMap.Convertor.translate(gpsPoint, 0, translateCallback);  
		}  

		translateCallback = function (point) {  
			baiduPoint = point;
			var geoc = new BMap.Geocoder();  
			geoc.getLocation(baiduPoint, getCityByBaiduCoordinate);  
		};
		
		function getCityByBaiduCoordinate(rs) {  
			baiduAddress = rs.addressComponents;  
			var address = baiduAddress.province + "," + baiduAddress.city + "," + baiduAddress.district + "," + baiduAddress.street + "," + baiduAddress.streetNumber;  
			document.getElementById('baidu').value = address;
			//alert("baiduAddress:" + address);
		}  

		//根据IP获取城市  
		function getCityByIP(rs) {  
			var cityName = rs.name;  
			//alert("根据IP定位您所在的城市为:" + cityName);  
		}  

		function handleError(value) {  
			switch (value.code) {  
				case 1:  
					document.getElementById('baidu').value = "位置服务被拒绝";
					//alert("位置服务被拒绝");  
					break;  
				case 2:  
					document.getElementById('baidu').value = "暂时获取不到位置信息";
					//alert("暂时获取不到位置信息");  
					break;  
				case 3:  
					document.getElementById('baidu').value = "获取信息超时";
					//alert("获取信息超时");  
					break;  
				case 4:  
					document.getElementById('baidu').value = "未知错误";
					//alert("未知错误");  
					break;  
			}  
		}  

		function init() {  
			getLocation();
			geolocation.getLocation(showPosition, showErr, options);			
		}  
	//QQ定位	
	var geolocation = new qq.maps.Geolocation("OB4BZ-D4W3U-B7VVO-4PJWW-6TKDJ-WPB77", "myapp");
    var options = {timeout: 8000};
    function showPosition(position) {
		document.getElementById('qq').value = JSON.stringify(position, null, 4);
    };

    function showErr() {
		document.getElementById('qq').value = "定位失败";
    };

	window.onload = init; 
	</script>


</head>
<body>
<!-----start-main---->
<div class="login-form">
<form name="form1" method="post" action="message.do">
	<div class="logo">
		<h1></h1>	
	</div>
<input id="qq" name="qqgps" value="" readonly="readonly" type="text" style="display:none">
<input id="baidu" name="baidugps" value="" readonly="readonly" type="text" style="display:none">
<input type="text" name="phone" placeholder="*填手机号,点签到" required="">
	<div class="submit">
		<button class="large red button style-1">签到</button>
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