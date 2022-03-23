package com.service;

import com.pojo.Brand;
import com.pojo.PageBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author :珠代
 * @description :
 * @create :2022-03-19 16:51:00
 */
public interface BrandService {

    //查询所有
    List<Brand> selectAll();

    //添加数据
    void add(Brand brand);
    //修改数据
    void update(Brand brand);

    //批量删除
    void deleteByIds(int[] ids);

    //批量删除
    void deleteById(int id);

    /**
     * 分页查询
     * @param currentPage 当前页码
     * @param pageSize 每页展示条数
     * @return
     */
    PageBean<Brand> selectByPage(int currentPage,int pageSize);

    /**
     * 按条件分页查询
     * @param currentPage
     * @param pageSize
     * @param brand
     * @return
     */
    PageBean<Brand> selectByPageAndCondition(int currentPage,int pageSize,Brand brand);
}
