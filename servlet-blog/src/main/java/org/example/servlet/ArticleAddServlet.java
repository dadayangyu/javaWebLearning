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
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/articleAdd")
public class ArticleAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        JSONResponse json = new JSONResponse();
        try {
            //1 解析请求数据
            Article a = JSONUtil.deserialize(req.getInputStream(), Article.class);
            HttpSession session=req.getSession(false);

            //2 业务请求(新增一篇文章,插入一条数据)
            int n = ArticleDao.insert(a,1);
            json.setSuccess(true);
        } catch (Exception e) {
            //异常没有打印堆栈信息叫做吃异常,
            e.printStackTrace();
            json.setCode("err");
            json.setMassage("登录密码错误");

        }
        String s = JSONUtil.serialize(json);
        resp.getWriter().println(s);
    }
}
