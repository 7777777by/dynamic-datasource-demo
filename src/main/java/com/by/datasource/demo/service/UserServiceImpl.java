package com.by.datasource.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.by.datasource.demo.dto.SchemaDTO;
import com.by.datasource.demo.dto.UserDTO;
import com.by.datasource.demo.entity.User;
import com.by.datasource.demo.mapper.UserMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {
    @Override
    public UserDTO findUserById(SchemaDTO schema, Long id) {
        User user = this.baseMapper.findUserById(schema, id);
        UserDTO result = new UserDTO();
        BeanUtils.copyProperties(user, result);
        return result;
    }
}
