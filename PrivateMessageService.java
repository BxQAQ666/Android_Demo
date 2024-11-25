package org.example.androiddemo.service;

import org.example.androiddemo.pojo.PrivateMessage;
import org.example.androiddemo.pojo.dto.PrivateMessageDto;
import org.example.androiddemo.repository.PrivateMessageRepository;
import org.example.androiddemo.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PrivateMessageService implements IPrivateMessageService {

    @Autowired
    private PrivateMessageRepository privateMessageRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public PrivateMessage addPrivateMessage(PrivateMessageDto privateMessageDto) {
        // 检查发送者和接收者是否存在
        var sender = userRepository.findById(privateMessageDto.getSender().getId())
                .orElseThrow(() -> new IllegalArgumentException("发送者不存在"));
        var receiver = userRepository.findById(privateMessageDto.getReceiver().getId())
                .orElseThrow(() -> new IllegalArgumentException("接收者不存在"));

        // 创建消息对象并拷贝 DTO 的内容
        PrivateMessage privateMessage = new PrivateMessage();
        BeanUtils.copyProperties(privateMessageDto, privateMessage);
        privateMessage.setSender(sender);
        privateMessage.setReceiver(receiver);
        privateMessage.setTimestamp(LocalDateTime.now()); // 设置时间戳

        // 保存消息
        return privateMessageRepository.save(privateMessage);
    }

    @Override
    public List<PrivateMessage> getPrivateMessages(Integer senderId, Integer receiverId, int pageNumber, int pageSize) {
        // 使用分页获取消息
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        return privateMessageRepository.findBySenderIdAndReceiverIdOrderByTimestampDesc(senderId, receiverId, pageRequest)
                .getContent();
    }

    @Override
    public void deletePrivateMessage(Long messageId) {
        // 软删除消息
        Optional<PrivateMessage> messageOpt = privateMessageRepository.findById(messageId);
        if (messageOpt.isPresent()) {
            PrivateMessage message = messageOpt.get();
            message.setDeleted(true); // 标记为已删除
            privateMessageRepository.save(message);
        }
    }

    @Override
    public void deleteMessagesByUserId(Integer userId) {
        // 删除发送者或接收者为指定用户的所有消息
        List<PrivateMessage> messages = privateMessageRepository.findBySenderIdOrReceiverId(userId, userId);
        for (PrivateMessage message : messages) {
            message.setDeleted(true); // 软删除
        }
        privateMessageRepository.saveAll(messages);
    }

    @Override
    public void recallMessage(Long messageId) {
        // 撤回消息
        Optional<PrivateMessage> messageOpt = privateMessageRepository.findById(messageId);
        if (messageOpt.isPresent()) {
            PrivateMessage message = messageOpt.get();
            message.setStatus(1); // 1 表示已撤回
            privateMessageRepository.save(message);
        }
    }

    @Override
    public PrivateMessage getLatestMessage(Integer senderId, Integer receiverId) {
        // 查询最新一条消息
        return privateMessageRepository.findTopBySenderIdAndReceiverIdOrderByTimestampDesc(senderId, receiverId);
    }

    @Override
    public Long getUnreadMessageCount(Integer userId) {
        // 查询接收者为指定用户且状态为未读的消息数量
        return privateMessageRepository.countByReceiverIdAndStatus(userId, 0);
    }
}
