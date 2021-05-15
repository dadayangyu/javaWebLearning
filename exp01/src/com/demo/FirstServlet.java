package com.demo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FirstServlet
 */
@WebServlet("/FirstServlet")
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public FirstServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//前两行必须有
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		//使用表单提交的请求参数(学号和姓名)
		String sno=request.getParameter("sno");
		String sname=request.getParameter("sname");
		//创建一个student对象
		Student student=new Student(sno,sname);
		//将student对象作为属性存储在请求作用域中
		request.setAttribute("student", student);
		//为实现请求转发,要获取到请求转发器对象
		RequestDispatcher rd= request.getRequestDispatcher("/SecondServlet");
		//在RequestDispatcher接口中定义了两个方法,forward是将请求转发到服务器上的一个静态或者动态资源上,
		//include是将控制转发到指定的资源,并将其包含到当前输出中
		rd.forward(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
