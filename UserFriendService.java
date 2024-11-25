package org.example.androiddemo.service;

import org.example.androiddemo.pojo.UserFriend;
import org.example.androiddemo.pojo.dto.UserFriendDto;
import org.example.androiddemo.repository.UserFriendRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UserFriendService implements IUserFriendService {

    @Autowired
    private UserFriendRepository userFriendRepository;

    @Override
    public UserFriend addUserFriend(UserFriendDto userFriendNew) {
        UserFriend userFriendPojo = new UserFriend();
        BeanUtils.copyProperties(userFriendNew, userFriendPojo);
        userFriendPojo.setStatus(0); // 新建好友请求默认为 0 (待审核)
        userFriendPojo.setCreatedAt(LocalDateTime.now()); // 设置创建时间
        userFriendRepository.save(userFriendPojo);
        return userFriendPojo;
    }

    @Override
    public UserFriend getUserFriend(Integer userId, Integer friendId) {
        return userFriendRepository.findByUserIdAndFriendId(userId, friendId)
                .orElseThrow(() -> new IllegalArgumentException("找不到用户与好友的关系！"));
    }

    @Override
    public void deleteUserFriend(Integer userId, Integer friendId) {
        UserFriend userFriend = userFriendRepository.findByUserIdAndFriendId(userId, friendId)
                .orElseThrow(() -> new IllegalArgumentException("找不到用户与好友的关系！"));
        userFriend.setStatus(2); // 设置为已删除
        userFriendRepository.save(userFriend);
    }

    @Override
    public UserFriend approveFriendRequest(Integer id) {
        UserFriend friendRequest = userFriendRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("找不到好友请求！"));

        if (friendRequest.getStatus() != 0) { // 检查状态是否为待审核
            throw new IllegalStateException("好友请求的状态不是待审核！");
        }

        friendRequest.setStatus(1); // 设置状态为已同意
        return userFriendRepository.save(friendRequest);
    }

    @Override
    public void rejectFriendRequest(Integer id) {
        UserFriend friendRequest = userFriendRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("找不到好友请求！"));

        if (friendRequest.getStatus() != 0) { // 检查状态是否为待审核
            throw new IllegalStateException("好友请求的状态不是待审核！");
        }

        friendRequest.setStatus(2); // 设置状态为已删除
        userFriendRepository.save(friendRequest);
    }
}
