package org.example.androiddemo.controller;

import org.example.androiddemo.pojo.ResponseMessage;
import org.example.androiddemo.pojo.User;
import org.example.androiddemo.pojo.dto.UserDto;
import org.example.androiddemo.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    // 增：添加用户
    @PostMapping
    public ResponseMessage add(@Validated @RequestBody UserDto user) {
        User userNew = userService.addUser(user);
        return ResponseMessage.success(userNew);
    }

    // 删：删除指定用户
    @DeleteMapping("/{userId}")
    public ResponseMessage delete(@PathVariable("userId") Integer userId) {
        userService.deleteUser(userId);
        return ResponseMessage.success();
    }

    // 查：获取指定用户信息
    @GetMapping("/{userId}")
    public ResponseMessage get(@PathVariable("userId") Integer userId) {
        User user = userService.getUser(userId);
        return ResponseMessage.success(user);
    }

    // 改：修改用户信息
    @PutMapping
    public ResponseMessage edit(@Validated @RequestBody UserDto user) {
        User userNew = userService.editUser(user);
        return ResponseMessage.success(userNew);
    }
}
