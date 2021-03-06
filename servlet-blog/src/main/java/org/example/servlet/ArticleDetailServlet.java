package org.example.servlet;

import org.example.Dao.ArticleDao;
import org.example.model.Article;
import org.example.model.JSONResponse;
import org.example.util.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/articleDetail")
public class ArticleDetailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        JSONResponse json = new JSONResponse();
        try {
            //1 解析请求数据
            String sid = req.getParameter("id");
            //2 业务请求(新增一篇文章,插入一条数据)
            Article a = ArticleDao.queryById(Integer.parseInt(sid));
            json.setSuccess(true);
            //设置业务数据
            json.setData((List<Article>) a);
        } catch (Exception e) {
            //异常没有打印堆栈信息叫做吃异常,
            e.printStackTrace();
            json.setCode("err");
            json.setMassage("文章的详情出错了");
        }
        String s = JSONUtil.serialize(json);
        resp.getWriter().println(s);
    }
}
