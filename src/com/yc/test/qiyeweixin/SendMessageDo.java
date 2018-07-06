package com.yc.test.qiyeweixin;

import java.net.URL;
import java.util.Date;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import net.sf.json.JSONObject;

import com.yc.test.qiyeweixin.send.Text;
import com.yc.test.qiyeweixin.send.TextMessage;
import com.yc.test.qiyeweixin.service.SendMessageService;
import com.yc.test.qiyeweixin.utils.WeiXinParamesUtil;
import com.yc.test.qiyeweixin.utils.WeiXinUtil;
import com.yc.utils.esbUtils.DateUtil;
import com.yc.utils.files.PropertiesUtil;

public class SendMessageDo {
	private static Log logger = LogFactory.getLog(SendMessageDo.class); 
	public static String loadPath = null,accessTokenPath = null;
	static String loadPathFile = "message.properties",accessTokenFile = "accessToken.properties";
	static {
		setLoadPath();
		setAccessTokenPath();
	}
	public static void setLoadPath(){
		URL url = SendMessageDo.class.getResource(loadPathFile);
		loadPath = url.getPath();
	}
	public static void setAccessTokenPath(){
		URL url = SendMessageDo.class.getResource(accessTokenFile);
		accessTokenPath = url.getPath();
	}
	public static void testSendTextMessage(){
		if(loadPath == null){
			setLoadPath();
		}
		if(accessTokenPath == null){
			setAccessTokenPath();
		}
		String key = PropertiesUtil.getPropertiesKey(loadPath, "content-demo");
		String content = key +".\n "+DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");
		//0.设置消息内容
//		String content=" this is a demo node message.\n click here" +
//				"<a href=\"http://work.weixin.qq.com\">【节点信息展示】" +
//				"</a>"+".\n "+DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss");

		//1.创建文本消息对象
		TextMessage message=new TextMessage();
		//1.1非必需
		String touser = PropertiesUtil.getPropertiesKey(loadPath, "touser-demo");
		message.setTouser(touser);  //不区分大小写 |LiangYue|SongGuiBin|GongWenNa|ChenJiaoYun
		//textMessage.setToparty("1");
		//txtMsg.setTotag(totag);
		//txtMsg.setSafe(0);

		//1.2必需
		message.setMsgtype("text");
		message.setAgentid(WeiXinParamesUtil.agentId);

		Text text=new Text();
		text.setContent(content);
		message.setText(text);

		//2.获取access_token:根据企业id和通讯录密钥获取access_token,并拼接请求url
//		WeiXinUtil.getAccessToken(WeiXinParamesUtil.corpId, WeiXinParamesUtil.agentSecret).getToken();//企业微信内部部门Secret
		String accessToken= WeiXinUtil.getAccessTokenByExpires(WeiXinParamesUtil.corpId, WeiXinParamesUtil.agentSecret);
//		System.out.println("accessToken:"+accessToken);
		
		sendMessage(accessToken, message);
	}
	public static void sendMessage(String accessToken,TextMessage message){
		//3.发送消息：调用业务类，发送消息
		SendMessageService sms=new SendMessageService();
		JSONObject jsonObject = sms.sendMessage(accessToken, message);
		
		//4.错误消息处理
		if (null != jsonObject) {  
			System.out.println("jsonObject:"+jsonObject.toString());
			if (0 != jsonObject.getInt("errcode")) {  
				logger.error("连接失败 errcode:{"+jsonObject.getInt("errcode")+"} errmsg:{"+jsonObject.getString("errmsg")+"}"
						+"\n"+"即将进行重新获取token...");  
				accessToken = WeiXinUtil.getAccessTokenNoExpires(WeiXinParamesUtil.corpId, WeiXinParamesUtil.agentSecret);
//				sendMessage(accessToken, message);
				//这里将来记录新的任务执行,或者记录失败任务ID,由运维人员分析后统一置为READY
			}  
		} else {
			logger.error("接口返回NULL");
		}
	}
	public static void main(String[] args) {
		testSendTextMessage();
	}
}
