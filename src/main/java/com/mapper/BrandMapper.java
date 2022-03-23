package com.mapper;

import com.pojo.Brand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BrandMapper {
    //查询所有
    @Select("select *from tb_brand")
    @ResultMap("brandResultMap")
    List<Brand> selectAll();

    //添加
    @Insert("insert into tb_brand values(null,#{brandName},#{companyName},#{ordered},#{description},#{status})")
    void add(Brand brand);

    //修改
    @Insert("update tb_brand set brand_name=#{brandName},company_name=#{companyName}," +
            "ordered=#{ordered},description=#{description},STATUS=#{status} where id=#{id}")
    void update(Brand brand);

    //批量删除
    void deleteByIds(@Param("ids") int[] ids);

    //删除
    @Select("delete from tb_brand where id=#{id}")
    void deleteById(int id);

    //分页查询
    @Select("select * from tb_brand limit #{begin} ,#{size}")
    @ResultMap("brandResultMap")
    List<Brand> selectByPage(@Param("begin") int begin,@Param("size") int size);

    /**
     * 查询总记录数
     * @return
     */
    @Select("select count(*) from tb_brand")
    int selectTotalCount();

    //分页条件查询

    List<Brand> selectByPageAndCondition(@Param("begin") int begin,@Param("size") int size,@Param("brand") Brand brand);

    /**
     * 根据条件查询总记录数
     * @return
     */
    int selectTotalCountByCondition(Brand brand);
}
