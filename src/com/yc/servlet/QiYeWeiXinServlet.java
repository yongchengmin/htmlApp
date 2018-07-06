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

import com.yc.test.qiyeweixin.SendMessageDo;
import com.yc.utils.esbUtils.DateUtil;

public class QiYeWeiXinServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected static ApplicationContext ac;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public QiYeWeiXinServlet() {
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
    	  String demo = request.getParameter("demo");
    	  String errorKey = "errorKey",errorMess = null;
    	  String dispatcher = "/overmessage.jsp";
    	  if(!StringUtils.isEmpty(demo)){
    		  SendMessageDo.testSendTextMessage();
    		  errorMess = "演示结束,请退出";
    	  }else {
    		  errorMess = "功能暂无,请退出";
    	  }
    	  System.out.println(errorMess+":"+DateUtil.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
    	  request.setAttribute(errorKey,errorMess);
    	  request.getRequestDispatcher(dispatcher).forward(request,response);
      }

      protected void returnErrorRequest(HttpServletResponse response,String s) throws IOException {
      	PrintWriter toClient = response.getWriter(); //得到向客户端输出文本的对象
  		response.setContentType("text/html;charset=utf-8");
  		toClient.write(s);
  		toClient.close();
      }
}
