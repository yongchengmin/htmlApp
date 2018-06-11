package com.yc.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.yc.utils.esbUtils.DateUtil;
import com.yc.utils.esbUtils.FileUtil;

public class MessageServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static ApplicationContext ac;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public MessageServlet() {
        // TODO Auto-generated constructor stub
    }
    public void init(ServletConfig sc) throws ServletException {
		super.init(sc);
		ac = WebApplicationContextUtils.getRequiredWebApplicationContext(sc.getServletContext());
	}
    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
     protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
     	doPost(request, response);
     }
     
     /**
      * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
      */
      protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	  String phone = request.getParameter("phone");
    	  String baidugps = request.getParameter("baidugps");
    	  String qqgps = request.getParameter("qqgps");
    	  String errorKey = "errorKey",errorMess = null;
    	  String dispatcher = "/gmessage.jsp";
    	  if(StringUtils.isEmpty(phone) && StringUtils.isEmpty(baidugps) && StringUtils.isEmpty(qqgps)){
    		  dispatcher = "/gm.jsp";
    	  }else {
    		  System.out.println(phone+":"+baidugps+":"+qqgps);
        	  if(StringUtils.isEmpty(baidugps) && StringUtils.isEmpty(qqgps)){
        		  errorMess = "操作神速,请返回重新签到";
        	  }else {
        		  errorMess = phone+"签到结束,返回或退出";
        		  String createDate = DateUtil.format(new Date(), "ddHHmmssSSS");
        		  //本地创建txt,增加读取文本功能,解析文本内容传入系统
//        		  FileUtil.mkdir("D:\\htmlmail\\gps");
        		  FileUtil.createTxt("gps-"+phone+"-"+createDate+".txt", phone+"#"+baidugps+"#"+qqgps+"#"+DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"), "UTF-8");
        	  }
    	  }
    	  request.setAttribute(errorKey,errorMess);
//    	  returnErrorRequest(response,"phone:"+phone);
    	  request.getRequestDispatcher(dispatcher).forward(request,response);
      }

      protected void returnErrorRequest(HttpServletResponse response,String s) throws IOException {
      	PrintWriter toClient = response.getWriter(); //得到向客户端输出文本的对象
  		response.setContentType("text/html;charset=utf-8");
  		toClient.write(s);
  		toClient.close();
      }
}
