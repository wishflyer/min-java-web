package cn.dd.core.myBatis.mapper;

import org.apache.ibatis.annotations.Select;

/**
 * Created by czdujb on 2015/11/2.
 */
public interface TestMaBatisMapper {

        @Select("SELECT count(*) num FROM tree_info ")
        int selectTestInfo();


}
