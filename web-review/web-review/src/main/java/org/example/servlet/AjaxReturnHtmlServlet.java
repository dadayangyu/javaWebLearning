package org.example.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * ajax返回html内容，没有真正的意义，实际代码没有这种使用场景
 * 演示ajax异步
 */
@WebServlet("/test5")
public class AjaxReturnHtmlServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        //返回网页
        resp.setContentType("text/html");

        PrintWriter pw = resp.getWriter();
        pw.println("<!DOCTYPE html>");
        pw.println("<html lang=\"en\">");
        pw.println("<head>");
        pw.println("<meta charset=\"UTF-8\">");
        pw.println("</head>");
        pw.println("<body>");
        pw.println("<h2>用户登录</h2>");
        pw.println("<input type=\"text\" placeholder=\"吃了吗\">");
        pw.println("</body>");
        pw.println("</html>");
    }
}
