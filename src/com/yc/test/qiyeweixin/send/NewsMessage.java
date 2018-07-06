package com.yc.test.qiyeweixin.send;  

  
/**
 * 图文消息
 *
 */
public class NewsMessage extends BaseMessage {  
    //图文
	private News news;

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}
    


}  