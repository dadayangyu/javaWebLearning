package com.demo;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ExcelServlet
 */
@WebServlet("/ExcelServlet")
public class ExcelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ExcelServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//固定的两行设置,注意Excel的格式设计
		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		PrintWriter out=response.getWriter();
		St s1=new St("111","yangyang","女",21,"cs");
		St s2=new St("222","wenwen","女",21,"cs");
		out.println("Sno\t"+"Name\t"+"Age\t"+"Type\t");
		out.println(s1.getSno()+"\t"+s1.getName()+"\t"+s1.getAge()+"\t"+s1.getType());
		out.println(s2.getSno()+"\t"+s2.getName()+"\t"+s2.getAge()+"\t"+s2.getType());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
