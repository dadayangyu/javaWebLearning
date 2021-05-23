package org.example.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.Dao.ArticleDao;
import org.example.model.Article;
import org.example.model.JSONResponse;
import org.example.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "/articleList")
public class ArticleListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        //这个接口用来,查询文章列表
        //数据库查询所有文章返回
        //单独定义一个包(articalDao),专门来做查询
        JSONResponse json=new JSONResponse();
        try {
            //属于查询当前用户关联的文章数据
            //需要只查询当前用户的文章
            HttpSession session=request.getSession(false);
            User user= (User) session.getAttribute("user");
            //TODO 只有下面这一行是业务代码,每个接口不同,其余部分都可以相同
            List<Article> query = ArticleDao.query(1);
            JSONResponse r = new JSONResponse();
            //业务处理成功
            json.setSuccess(true);
            json.setData(query);
        }catch(Exception ex){
            ex.printStackTrace();
            //业务处理失败:返回false+错误码+错误信息
            //TODO 简单返回错误
            json.setCode("err");
            json.setMassage("系统出错了,请联系管理员");
        }
        ObjectMapper m=new ObjectMapper();
        String s=m.writeValueAsString(json);
        response.getWriter().println(s);
    }
}
