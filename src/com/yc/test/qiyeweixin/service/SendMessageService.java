package com.yc.test.qiyeweixin.service;

import net.sf.json.JSONObject;

import com.google.gson.Gson;
import com.yc.test.qiyeweixin.send.BaseMessage;
import com.yc.test.qiyeweixin.utils.WeiXinUtil;

/**@desc  : 发送消息
 */
public class SendMessageService {
	private  static  String sendMessage_url="https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=ACCESS_TOKEN";  

    /**
     * @desc ：0.公共方法：发送消息
     *  
     * @param accessToken
     * @param message void
     */
	public JSONObject sendMessage(String accessToken,BaseMessage message){

		//1.获取json字符串：将message对象转换为json字符串	
		Gson gson = new Gson(); 
		String jsonMessage =gson.toJson(message);      //使用gson.toJson(user)即可将user对象顺序转成json
//		System.out.println("jsonTextMessage:"+jsonMessage);

		//无限死循环测试代码...
//		accessToken = "test";
		//2.获取请求的url  
		sendMessage_url=sendMessage_url.replace("ACCESS_TOKEN", accessToken);

		//3.调用接口,发送消息
		JSONObject jsonObject = WeiXinUtil.httpRequest(sendMessage_url, "POST", jsonMessage);
		return jsonObject;  
		
	}
	
	
	
	
}
