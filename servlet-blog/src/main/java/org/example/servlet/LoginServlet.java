package org.example.servlet;

import org.example.Dao.UserDao;
import org.example.model.JSONResponse;
import org.example.model.User;
import org.example.util.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html");
        //这个接口用来,查询文章列表
        //数据库查询所有文章返回
        //单独定义一个包(articalDao),专门来做查询
        JSONResponse json=new JSONResponse();
        try {
            //属于查询当前用户关联的文章数据
            //TODO 需要只查询当前用户的文章
            //TODO 只有下面这一行是业务代码,每个接口不同,其余部分都可以相同

            //获取请求数据
            User user=JSONUtil.deserialize(request.getInputStream(),User.class);
            //处理业务:根据数据去查询
            User query=UserDao.query(user);
            //业务处理成功
            if (query == null) {
               json.setCode("LOG001") ;
               json.setMassage("用户名密码错误");
            }else{//校验用户名密码成功
                json.setSuccess(true);
                //创建一个session对象
                HttpSession session=request.getSession();
                //创建好后,保存用户信息
                session.setAttribute("user",query);
            }

        }catch(Exception ex){
            ex.printStackTrace();
            //业务处理失败:返回false+错误码+错误信息
            //TODO 简单返回错误
            json.setCode("err");
            json.setMassage("系统出错了,请联系管理员");
        }
        //解析请求数据
        String s=JSONUtil.serialize(json);
        response.getWriter().println(s);
    }


}
