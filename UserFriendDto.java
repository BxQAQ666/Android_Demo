package org.example.androiddemo.pojo.dto;

import org.example.androiddemo.pojo.User;

import java.time.LocalDateTime;

public class UserFriendDto {

    private Integer id;
    private User user; // 用户ID
    private User friend; // 好友ID
    private Integer status; // 好友关系状态（0: 待审核, 1: 已同意, 2: 已删除）
    private String nickname; // 备注名
    private Boolean isBlocked = false; // 是否屏蔽
    private LocalDateTime createdAt = LocalDateTime.now(); // 创建时间
}
