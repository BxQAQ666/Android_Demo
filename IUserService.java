package org.example.androiddemo.service;

import org.example.androiddemo.pojo.User;
import org.example.androiddemo.pojo.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {
    /**
     * 注册\插入用户
     * @param userNew 前端传来的新用户信息
     * @return
     */
    User addUser(UserDto userNew);

    /**
     * 查询用户信息
     * @param userId 前端传来的用户id
     * @return 用户信息
     */
    User getUser(Integer userId);

    /**
     * 修改用户信息
     * @param userNew 修改后的用户信息
     * @return
     */
    User editUser(UserDto userNew);

    /**
     * 删除用户
     *
     * @param userId 前端传来的用户id
     * @return
     */
    void deleteUser(Integer userId);
}
