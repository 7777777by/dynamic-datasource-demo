package com.by.datasource.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.by.datasource.demo.dto.SchemaDTO;
import com.by.datasource.demo.entity.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT * FROM t_user WHERE ID = #{id}")
    User findUserById(SchemaDTO schema, Long id);
}
