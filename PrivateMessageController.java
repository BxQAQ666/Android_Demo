package org.example.androiddemo.controller;

import org.example.androiddemo.pojo.PrivateMessage;
import org.example.androiddemo.pojo.dto.PrivateMessageDto;
import org.example.androiddemo.service.IPrivateMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/private-messages")
public class PrivateMessageController {

    @Autowired
    private IPrivateMessageService privateMessageService;

    // 添加私信
    @PostMapping
    public ResponseEntity<PrivateMessage> addPrivateMessage(@RequestBody PrivateMessageDto privateMessageDto) {
        PrivateMessage message = privateMessageService.addPrivateMessage(privateMessageDto);
        return ResponseEntity.ok(message);
    }

    // 获取发送者与接收者之间的私信
    @GetMapping("/{senderId}/{receiverId}")
    public ResponseEntity<List<PrivateMessage>> getPrivateMessages(
            @PathVariable Integer senderId,
            @PathVariable Integer receiverId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        List<PrivateMessage> messages = privateMessageService.getPrivateMessages(senderId, receiverId, page, size);
        return ResponseEntity.ok(messages);
    }

    // 获取发送者与接收者之间的最新私信
    @GetMapping("/{senderId}/{receiverId}/latest")
    public ResponseEntity<PrivateMessage> getLatestMessage(
            @PathVariable Integer senderId,
            @PathVariable Integer receiverId) {
        PrivateMessage latestMessage = privateMessageService.getLatestMessage(senderId, receiverId);
        return ResponseEntity.ok(latestMessage);
    }

    // 删除指定私信
    @DeleteMapping("/{messageId}")
    public ResponseEntity<Void> deletePrivateMessage(@PathVariable Long messageId) {
        privateMessageService.deletePrivateMessage(messageId);
        return ResponseEntity.noContent().build();
    }

    // 撤回指定私信
    @PutMapping("/{messageId}/recall")
    public ResponseEntity<Void> recallMessage(@PathVariable Long messageId) {
        privateMessageService.recallMessage(messageId);
        return ResponseEntity.noContent().build();
    }

    // 获取指定用户的未读消息数
    @GetMapping("/{userId}/unread-count")
    public ResponseEntity<Long> getUnreadMessageCount(@PathVariable Integer userId) {
        Long unreadCount = privateMessageService.getUnreadMessageCount(userId);
        return ResponseEntity.ok(unreadCount);
    }

    // 删除指定用户的所有消息
    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> deleteMessagesByUserId(@PathVariable Integer userId) {
        privateMessageService.deleteMessagesByUserId(userId);
        return ResponseEntity.noContent().build();
    }
}
