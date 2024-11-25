package org.example.androiddemo.service;

import org.example.androiddemo.pojo.User;
import org.example.androiddemo.pojo.dto.UserDto;
import org.example.androiddemo.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User addUser(UserDto userNew) {
        User userPojo = new User();
        BeanUtils.copyProperties(userNew,userPojo);
        userRepository.save(userPojo);
        return userPojo;
    }

    @Override
    public User getUser(Integer userId) {
        return userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("找不到用户！"));
    }

    @Override
    public User editUser(UserDto user) {
        User userPojo = new User();
        BeanUtils.copyProperties(user,userPojo);
        return userRepository.save(userPojo);
    }

    @Override
    public void deleteUser(Integer userId){
        userRepository.deleteById(userId);
    }
}
