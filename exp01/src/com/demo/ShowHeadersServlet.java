package com.demo;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ShowHeadersServlet
 */
@WebServlet("/ShowHeadersServlet")
public class ShowHeadersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowHeadersServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	   response.setContentType("text/html;UTF-8");
	   PrintWriter out=response.getWriter();
	   //检索出所有请求头
       out.print("<html><head>");
       out.print("<head><title>请求头信息</title></head>");out.println("<body>");
       out.print("服务器收到的请求头信息<p>");
       //形成表格结构
       out.print("<table border=\"1\">");
       out.print( "<tr><td>HeaderName</td>");
	   out.print( "<td>HeaderValue</td></tr>");
       //获取多行请求头信息
      Enumeration <String> headers=request.getHeaderNames();
      //遍历完所有的元素,使用循环
      while(headers.hasMoreElements()) {
    	  String header=(String)headers.nextElement();
    	  out.print( "<tr><td>" + header+"&nbsp&nbsp"+ "</td>\n");
    	  String value=request.getHeader(header);
    	  out.print("<td> " + value +"&nbsp&nbsp"+ "</td></tr>\n");
    	  out.print(header+"="+value+"<br/>");    	  
      }
    //以表格的形式将请求头的参数名和参数值显示出来
      out.print("</table></body></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
