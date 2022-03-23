package com.web.servlet;

import com.alibaba.fastjson.JSON;
import com.pojo.Brand;
import com.pojo.PageBean;
import com.service.BrandService;
import com.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

/**
 * @author :珠代
 * @description :
 * @create :2022-03-19 21:51:00
 */
@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet{

    private BrandService brandService= new BrandServiceImpl();

    //查询所有
    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1、调用service查询
        List<Brand> brands = brandService.selectAll();

        //2、转为json
        String jsonString = JSON.toJSONString(brands);

        //3、写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    //添加
    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        //接收品牌数据
        BufferedReader br = request.getReader();
        String params= br.readLine();//json字符串

        //转为Brand对象
        Brand brand = JSON.parseObject(params, Brand.class);

        //调用service添加
        brandService.add(brand);

        //响应成功标识
        response.getWriter().write("success");
    }

    //添加
    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        //接收品牌数据
        BufferedReader br = request.getReader();
        String params= br.readLine();//json字符串

        //转为Brand对象
        Brand brand = JSON.parseObject(params, Brand.class);

        //调用service添加
        brandService.update(brand);

        //响应成功标识
        response.getWriter().write("success");
    }

    //批量删除
    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        //1、接收数据 [1,2,3]
        BufferedReader br = request.getReader();
        String params= br.readLine();//json字符串

        //转为int[]
        int[] ids = JSON.parseObject(params, int[].class);

        //调用service删除
        brandService.deleteByIds(ids);

        //响应成功标识
        response.getWriter().write("success");
    }

    /**
     * 根据ID删除
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void deleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        //接收品牌数据
        BufferedReader br = request.getReader();
        String params= br.readLine();//json字符串

        //转为Brand对象
        Integer id = JSON.parseObject(params, Integer.class);

        //调用service删除
        brandService.deleteById(id);

        //响应成功标识
        response.getWriter().write("success");
    }

    /**
     * 分页查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1、接收 当前页码 和每页展示条数
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage=Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        //2、调用service查询
        PageBean<Brand> pageBean = brandService.selectByPage(currentPage, pageSize);

        //3、转为json
        String jsonString = JSON.toJSONString(pageBean);

        //4、写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

    /**
     * 分页条件查询
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //1、接收 当前页码 和每页展示条数
        String _currentPage = request.getParameter("currentPage");
        String _pageSize = request.getParameter("pageSize");

        int currentPage=Integer.parseInt(_currentPage);
        int pageSize = Integer.parseInt(_pageSize);

        //获取查询条件对象
        BufferedReader br = request.getReader();
        String params= br.readLine();//json字符串

        //转为Brand
        Brand brand = JSON.parseObject(params, Brand.class);

        //2、调用service查询
        PageBean<Brand> pageBean = brandService.selectByPageAndCondition(currentPage, pageSize,brand);

        //3、转为json
        String jsonString = JSON.toJSONString(pageBean);

        //4、写数据
        response.setContentType("text/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }

}
