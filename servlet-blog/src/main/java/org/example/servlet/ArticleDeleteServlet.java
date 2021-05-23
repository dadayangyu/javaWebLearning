package org.example.servlet;

import org.example.Dao.ArticleDao;
import org.example.model.JSONResponse;
import org.example.util.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/ArticleDelete")
public class ArticleDeleteServlet  extends HttpServlet{
    @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            req.setCharacterEncoding("UTF-8");
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("application/json");
            JSONResponse json = new JSONResponse();
            try {
                //1 解析请求数据
                String ids=req.getParameter("ids");
                //2 业务请求,根据对各ID删除文章
                int n= ArticleDao.delete(ids.split(","));//这个写法得熟悉
                json.setSuccess(true);
            } catch (Exception e) {
                //异常没有打印堆栈信息叫做吃异常,
                e.printStackTrace();
                json.setCode("err");
                json.setMassage("文章删除出错");
            }
            String s = JSONUtil.serialize(json);
            resp.getWriter().println(s);
        }
}
