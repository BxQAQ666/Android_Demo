package org.example.androiddemo.service;

import org.example.androiddemo.pojo.UserFriend;
import org.example.androiddemo.pojo.dto.UserFriendDto;

public interface IUserFriendService {

    /**
     * 添加好友关系
     * @param userFriendNew 前端传来的好友信息
     * @return 新增的好友关系
     */
    UserFriend addUserFriend(UserFriendDto userFriendNew);

    /**
     * 查询好友关系
     * @param userId 用户id
     * @param friendId 好友id
     * @return 好友关系信息
     */
    UserFriend getUserFriend(Integer userId, Integer friendId);

    /**
     * 删除好友关系
     * @param userId 用户id
     * @param friendId 好友id
     */
    void deleteUserFriend(Integer userId, Integer friendId);

    /**
     * 同意好友请求
     * @param id 好友请求 ID
     * @return 更新后的好友关系
     */
    UserFriend approveFriendRequest(Integer id);

    /**
     * 拒绝好友请求
     * @param id 好友请求 ID
     */
    void rejectFriendRequest(Integer id);
}
