package org.example.androiddemo.pojo;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import java.time.LocalDateTime;

@Table(name = "tb_private_message", indexes = {
        @Index(name = "idx_sender_receiver", columnList = "sender_id, receiver_id"),
        @Index(name = "idx_timestamp", columnList = "timestamp")
})  // 添加索引优化查询
@Data
@Entity
public class PrivateMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "消息ID不能为空")
    @Column(name = "message_id")
    private Long messageId;

    @NotNull(message = "发送者ID不能为空")
    @ManyToOne(fetch = FetchType.LAZY)  // 发送者与用户是多对一关系
    @JoinColumn(name = "sender_id", referencedColumnName = "user_id", nullable = false)
    private User sender;  // 发送者

    @NotNull(message = "接收者ID不能为空")
    @ManyToOne(fetch = FetchType.LAZY)  // 接收者与用户是多对一关系
    @JoinColumn(name = "receiver_id", referencedColumnName = "user_id", nullable = false)
    private User receiver;  // 接收者

    @NotNull(message = "消息类型不能为空")
    @Column(name = "message_type")
    private Integer messageType;  // 0-文本, 1-图片, 2-文件等

    @Size(max = 1000, message = "消息内容不能超过 1000 个字符")
    @Column(name = "content", length = 1000)
    private String content;  // 消息内容可为空（如图片或文件时）

    @Column(name = "attachment_url")
    private String attachmentUrl;  // 附件URL，用于图片或文件存储路径

    @NotNull(message = "发送时间不能为空")
    @Column(name = "timestamp", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime timestamp;  // 发送时间，默认使用数据库当前时间

    @NotNull(message = "消息状态不能为空")
    @Column(name = "status")
    private Integer status;  // 0-已发送, 1-已撤回等

    @Column(name = "deleted", columnDefinition = "BOOLEAN DEFAULT FALSE")
    private Boolean deleted;  // 是否已删除，软删除字段
}
