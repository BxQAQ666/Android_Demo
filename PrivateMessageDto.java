package org.example.androiddemo.pojo.dto;

import lombok.Data;
import org.example.androiddemo.pojo.User;
import java.time.LocalDateTime;
@Data
public class PrivateMessageDto {

    private Long messageId;
    private User sender;  // 发送者
    private User receiver;  // 接收者
    private Integer messageType;  // 0-文本, 1-图片, 2-文件等
    private String content;  // 消息内容可为空（如图片或文件时）
    private String attachmentUrl;  // 附件URL，用于图片或文件存储路径
    private LocalDateTime timestamp;  // 发送时间，默认使用数据库当前时间
    private Integer status;  // 0-已发送, 1-已撤回等
    private Boolean deleted;  // 是否已删除，软删除字段
}
