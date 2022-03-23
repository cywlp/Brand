package com.web.servlet; /**
 * @description :替换httpservlet,根据请求的最后一段路径进行方法分发
 * @author :珠代
 * @create :2022-03-19 21:48:00
 */

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1、获取请求路径
        String uri = req.getRequestURI(); //  /Brand/brand/selectAll

        //1.1获取最后一段路径，方法名
        int index = uri.lastIndexOf('/');
        String methodName = uri.substring(index + 1);//  selectAll

        //2、执行方法
        //2.1获取BrandServlet字节码对象 Class
        //谁调用我（this），我（this）代表谁
        Class<? extends BaseServlet> cls = this.getClass();

        //2.2、获取方法Method对象
        try {
            Method method = cls.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //2.3、执行方法
            try {
                method.invoke(this, req, resp);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

    }
}
