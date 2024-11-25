package org.example.androiddemo.service;

import org.example.androiddemo.pojo.PrivateMessage;
import org.example.androiddemo.pojo.dto.PrivateMessageDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPrivateMessageService {

    /**
     * 发送一条私聊消息
     *
     * @param privateMessageDto 包含发送者、接收者和消息内容的 DTO
     * @return 返回存储后的消息记录
     */
    PrivateMessage addPrivateMessage(PrivateMessageDto privateMessageDto);

    /**
     * 根据条件查询私聊记录（支持分页）
     *
     * @param senderId   发送者用户 ID（可选）
     * @param receiverId 接收者用户 ID（可选）
     * @param pageNumber 当前页码（分页查询）
     * @param pageSize   每页显示条数（分页查询）
     * @return 返回符合条件的消息记录列表
     */
    List<PrivateMessage> getPrivateMessages(Integer senderId, Integer receiverId, int pageNumber, int pageSize);

    /**
     * 删除一条私聊消息（支持软删除）
     *
     * @param messageId 消息的唯一 ID
     */
    void deletePrivateMessage(Long messageId);

    /**
     * 批量删除某用户的私聊消息（支持软删除）
     *
     * @param userId 用户 ID
     */
    void deleteMessagesByUserId(Integer userId);

    /**
     * 撤回一条私聊消息（将状态设置为撤回状态）
     *
     * @param messageId 消息的唯一 ID
     */
    void recallMessage(Long messageId);

    /**
     * 查询两个用户之间的最新一条消息
     *
     * @param senderId   发送者用户 ID
     * @param receiverId 接收者用户 ID
     * @return 返回最新一条消息记录
     */
    PrivateMessage getLatestMessage(Integer senderId, Integer receiverId);

    /**
     * 查询未读消息数量
     *
     * @param userId 用户 ID
     * @return 未读消息的数量
     */
    Long getUnreadMessageCount(Integer userId);

}
