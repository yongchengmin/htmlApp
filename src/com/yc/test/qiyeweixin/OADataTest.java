package com.yc.test.qiyeweixin;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import com.yc.test.qiyeweixin.pojo.Approve;
import com.yc.test.qiyeweixin.pojo.CheckIn;
import com.yc.test.qiyeweixin.service.OADataService;
import com.yc.test.qiyeweixin.utils.WeiXinParamesUtil;
import com.yc.test.qiyeweixin.utils.WeiXinUtil;
public class OADataTest {

	/**
	 * 1.获取打卡数据
	 */
	public static void testGetCheckInData(){
		//1、取得本地时间：
		Calendar cal = Calendar.getInstance();
		//2、取得时间偏移量：
//		int zoneOffset = cal.get(Calendar.ZONE_OFFSET);
		//3、取得夏令时差：
//		int dstOffset = cal.get(Calendar.DST_OFFSET);
		//4、从本地时间里扣除这些差量，即可以取得UTC时间：
//		cal.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));
		long endtime = cal.getTimeInMillis();
		long starttime = endtime - 2*(1000*3600*24);
		endtime = endtime/1000;
		starttime = starttime/1000;
		
		List<String> useridlist=new ArrayList<String>();
		useridlist.add("MinYongCheng");
		useridlist.add("SongGuiBin");
		useridlist.add("GongWenNa");
		
		CheckIn checkIn = new CheckIn(2,starttime,endtime,useridlist);  
		System.out.println("checkIn:"+checkIn);

		//2.获取access_token:根据企业id和通讯录密钥获取access_token,并拼接请求url
		String accessToken= WeiXinUtil.getAccessToken(WeiXinParamesUtil.corpId, WeiXinParamesUtil.checkInSecret).getToken();
		System.out.println("accessToken:"+accessToken);

		//3.调用service,获取打卡数据
		OADataService oads= new OADataService();
		oads.getCheckInData(accessToken, checkIn);
	}
	
	/**
	 * 1.获取审批数据
	 */
	@Test
	public void testGetApproveData(){
		//1.创建checkIn对象，并将对象转换成json字符串  
		long starttime=1504195200;  //2017年9月1日
		
		//1、取得本地时间：
		Calendar cal = Calendar.getInstance();
		//2、取得时间偏移量：
		int zoneOffset = cal.get(Calendar.ZONE_OFFSET);
		//3、取得夏令时差：
		int dstOffset = cal.get(Calendar.DST_OFFSET);
		//4、从本地时间里扣除这些差量，即可以取得UTC时间：
		cal.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));

		long endtime=cal.getTimeInMillis()/1000;

		
		
		Approve approve = new Approve(starttime,endtime);  
		System.out.println("approve:"+approve);

		//2.获取access_token:根据企业id和通讯录密钥获取access_token,并拼接请求url
		String accessToken= WeiXinUtil.getAccessToken(WeiXinParamesUtil.corpId, WeiXinParamesUtil.approveSecret).getToken();
		System.out.println("accessToken:"+accessToken);

		//3.调用service,获取审批数据
		OADataService oads= new OADataService();
		oads.getApproveData(accessToken, approve);
		
		System.out.println("--------------------------testGetApproveData---------------------------");
	}
	@Test
	public void testUTCDate(){
		
		//方法一：
		
		//1、取得本地时间：
		Calendar cal = Calendar.getInstance();
		//2、取得时间偏移量：
		int zoneOffset = cal.get(Calendar.ZONE_OFFSET);
		//3、取得夏令时差：
		int dstOffset = cal.get(Calendar.DST_OFFSET);
		//4、从本地时间里扣除这些差量，即可以取得UTC时间：
		cal.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));

		long endtime1=cal.getTimeInMillis()/1000;
		
		long endtime2=System.currentTimeMillis()/1000;
		
		
		System.out.println("endtime1:"+endtime1);
		System.out.println("endtime2:"+endtime2);
		
		System.out.println("--------------------------testUTCDate---------------------------");
	}

	public static void timeInMillisTest(){
		long starttime=1504195200;  //2017年9月1日
		
		//1、取得本地时间：
		Calendar cal = Calendar.getInstance();
		//2、取得时间偏移量：
//		int zoneOffset = cal.get(Calendar.ZONE_OFFSET);
		//3、取得夏令时差：
//		int dstOffset = cal.get(Calendar.DST_OFFSET);
		//4、从本地时间里扣除这些差量，即可以取得UTC时间：
//		cal.add(Calendar.MILLISECOND, -(zoneOffset + dstOffset));

//		long endtime=cal.getTimeInMillis()/1000;
		long endtime=cal.getTimeInMillis();
		
		long between_days=(endtime-starttime)/(1000*3600*24);  
		System.out.println(between_days);
		if(between_days > 5){
			System.out.println("超时");
			starttime = endtime - 2*(1000*3600*24);
			System.out.println(starttime);
			between_days=(endtime-starttime)/(1000*3600*24);  
			System.out.println(between_days);
			
			
			Date date2 = new Date();
			date2.setTime(endtime);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//24小时制
			System.out.println(simpleDateFormat.format(date2));
			
			Date date1 = new Date();
			date1.setTime(starttime);
			System.out.println(simpleDateFormat.format(date1));
			
		}
	}
	public static void main(String[] args) {
		testGetCheckInData();
	}

}
