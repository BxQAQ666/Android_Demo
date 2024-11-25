package org.example.androiddemo.pojo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_user_friend")
public class UserFriend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull(message = "用户ID不能为空")
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user; // 用户ID

    @NotNull(message = "好友ID不能为空")
    @ManyToOne
    @JoinColumn(name = "friend_id", referencedColumnName = "user_id", nullable = false)
    private User friend; // 好友ID

    @NotNull(message = "好友关系状态不能为空")
    @Column(name = "status", nullable = false)
    private Integer status; // 好友关系状态（0: 待审核, 1: 已同意, 2: 已删除）

    @Column(name = "nickname")
    @Size(max = 50, message = "备注名长度不能超过50个字符")
    private String nickname; // 备注名

    @Column(name = "is_blocked")
    private Boolean isBlocked = false; // 是否屏蔽

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now(); // 创建时间
}
