package com.by.datasource.demo.controller;

import com.by.datasource.demo.dto.SchemaDTO;
import com.by.datasource.demo.dto.UserDTO;
import com.by.datasource.demo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("/api/demo/datasource/dynamic/queryuser")
    public UserDTO queryUser(SchemaDTO schema, UserDTO user) {
        return userService.findUserById(schema, user.getId());
    }
}
