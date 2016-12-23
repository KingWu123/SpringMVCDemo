package com.demo.data;

import org.apache.ibatis.annotations.Select;

/**
 * Created by kingwu on 19/12/2016.
 */
public interface MyBatisMapper {
    @Select("SELECT * FROM spitter WHERE id = #{id}")
    Spitter selectSpitter(long id);
}
