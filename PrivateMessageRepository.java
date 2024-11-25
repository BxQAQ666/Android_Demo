package org.example.androiddemo.repository;

import org.example.androiddemo.pojo.PrivateMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PrivateMessageRepository extends JpaRepository<PrivateMessage, Long> {

    // 按发送者和接收者查找消息，支持分页
    Page<PrivateMessage> findBySenderIdAndReceiverIdOrderByTimestampDesc(Integer senderId, Integer receiverId, Pageable pageable);

    // 查找与指定发送者或接收者相关的消息
    List<PrivateMessage> findBySenderIdOrReceiverId(Integer senderId, Integer receiverId);

    // 获取接收者未读消息的数量
    Long countByReceiverIdAndStatus(Integer receiverId, Integer status);

    // 查找发送者和接收者之间的最新一条消息
    PrivateMessage findTopBySenderIdAndReceiverIdOrderByTimestampDesc(Integer senderId, Integer receiverId);

    // 查找接收者的所有未读消息
    List<PrivateMessage> findByReceiverIdAndStatus(Integer receiverId, Integer status);

    // 查询两个用户之间的所有消息（双向）
    @Query("SELECT pm FROM PrivateMessage pm WHERE " +
            "(pm.sender.id = :senderId AND pm.receiver.id = :receiverId) " +
            "OR (pm.sender.id = :receiverId AND pm.receiver.id = :senderId) " +
            "ORDER BY pm.timestamp DESC")
    List<PrivateMessage> findMessagesBetweenSenderAndReceiver(Integer senderId, Integer receiverId);

    // 分页查询某个用户的所有未被删除的消息
    @Query("SELECT pm FROM PrivateMessage pm WHERE " +
            "(pm.sender.id = :senderId OR pm.receiver.id = :receiverId) AND pm.deleted = false")
    Page<PrivateMessage> findAllMessagesForUser(Integer senderId, Integer receiverId, Pageable pageable);
}
