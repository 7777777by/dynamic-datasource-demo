package com.by.datasource.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.by.datasource.demo.dto.SchemaDTO;
import com.by.datasource.demo.dto.UserDTO;
import com.by.datasource.demo.entity.User;

public interface IUserService extends IService<User> {
    UserDTO findUserById(SchemaDTO schema, Long id);
}
