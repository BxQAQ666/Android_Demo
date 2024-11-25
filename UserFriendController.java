package org.example.androiddemo.controller;

import jakarta.validation.constraints.NotNull;
import org.example.androiddemo.pojo.ResponseMessage;
import org.example.androiddemo.pojo.UserFriend;
import org.example.androiddemo.pojo.dto.UserFriendDto;
import org.example.androiddemo.service.UserFriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/userFriend")
public class UserFriendController {

    @Autowired
    private UserFriendService userFriendService;

    // 增：添加用户好友
    @PostMapping
    public ResponseMessage<UserFriend> add(@Validated @RequestBody UserFriendDto userFriend) {
        UserFriend userFriendNew = userFriendService.addUserFriend(userFriend);
        return ResponseMessage.success(userFriendNew);
    }

    // 删：删除指定用户与朋友的关系
    @DeleteMapping("/{userId}/{friendId}")
    public ResponseMessage<Void> delete(@PathVariable Integer userId, @PathVariable Integer friendId) {
        userFriendService.deleteUserFriend(userId, friendId);
        return ResponseMessage.success();
    }

    // 查：获取指定用户与朋友的关系
    @GetMapping("/{userId}/{friendId}")
    public ResponseMessage<UserFriend> get(@PathVariable Integer userId, @PathVariable Integer friendId) {
        UserFriend userFriend = userFriendService.getUserFriend(userId, friendId);
        return ResponseMessage.success(userFriend);
    }


    // 同意好友请求
    @PutMapping("/{id}/approve")
    public ResponseMessage<UserFriend> approveFriendRequest(@PathVariable @NotNull Integer id) {
        UserFriend approvedFriend = userFriendService.approveFriendRequest(id);
        return ResponseMessage.success(approvedFriend);
    }

    // 拒绝好友请求
    @PutMapping("/{id}/reject")
    public ResponseMessage<Void> rejectFriendRequest(@PathVariable @NotNull Integer id) {
        userFriendService.rejectFriendRequest(id);
        return ResponseMessage.success();
    }
}
