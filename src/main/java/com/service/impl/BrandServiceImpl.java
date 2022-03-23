package com.service.impl;

import com.mapper.BrandMapper;
import com.pojo.Brand;
import com.pojo.PageBean;
import com.service.BrandService;
import com.util.SqlSessionFactoryUtils;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;

/**
 * @author :珠代
 * @description :
 * @create :2022-03-19 16:52:00
 */
public class BrandServiceImpl implements BrandService {
    //创建SqlSessionFactory工厂对象
    SqlSessionFactory factory = SqlSessionFactoryUtils.getSqlSessionFactory();
    //查询所有
    @Override
    public List<Brand> selectAll() {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取mapper
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        //调用方法
        List<Brand> brandList = brandMapper.selectAll();
        //释放资源
        sqlSession.close();

        return brandList;
    }
    //添加
    @Override
    public void add(Brand brand) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取mapper
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        //调用方法
        brandMapper.add(brand);
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();

    }
    //修改
    @Override
    public void update(Brand brand) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取mapper
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        //调用方法
        brandMapper.update(brand);
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();

    }

    //批量删除
    @Override
    public void deleteByIds(int[] ids) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取mapper
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        //调用方法
        brandMapper.deleteByIds(ids);
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();

    }

    /**
     * 删除
     * @param id
     */
    @Override
    public void deleteById(int id) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取mapper
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        //调用方法
        brandMapper.deleteById(id);
        //提交事务
        sqlSession.commit();
        //释放资源
        sqlSession.close();

    }

    @Override
    public PageBean<Brand> selectByPage(int currentPage, int pageSize) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取mapper
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        //计算开始索引
        int begin =(currentPage-1)*pageSize;
        //查询条目数
        int size=pageSize;
        //查询当前页数据
        List<Brand> brands = brandMapper.selectByPage(begin, size);
        //查询总记录数
        int totalCount = brandMapper.selectTotalCount();
        //封装pagebean对象
        PageBean<Brand> pageBean=new PageBean<>();
        pageBean.setRow(brands);
        pageBean.setTotalCount(totalCount);

        //释放资源
        sqlSession.close();

        return pageBean;
    }

    @Override
    public PageBean<Brand> selectByPageAndCondition(int currentPage, int pageSize, Brand brand) {
        //获取SqlSession对象
        SqlSession sqlSession = factory.openSession();
        //获取mapper
        BrandMapper brandMapper = sqlSession.getMapper(BrandMapper.class);
        //计算开始索引
        int begin =(currentPage-1)*pageSize;
        //查询条目数
        int size=pageSize;
        //查询当前页数据
        //处理brand条件 模糊查询
        String brandName = brand.getBrandName();
        if (brandName!=null&&brandName.length()>0){
            brand.setBrandName("%"+brandName+"%");
        }

        String companyName = brand.getCompanyName();
        if (companyName!=null&&companyName.length()>0){
            brand.setCompanyName("%"+companyName+"%");
        }
        List<Brand> brands = brandMapper.selectByPageAndCondition(begin, size,brand);
        //查询总记录数
        int totalCount = brandMapper.selectTotalCountByCondition(brand);
        //封装pagebean对象
        PageBean<Brand> pageBean=new PageBean<>();
        pageBean.setRow(brands);
        pageBean.setTotalCount(totalCount);

        //释放资源
        sqlSession.close();

        return pageBean;
    }
}
