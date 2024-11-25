package org.example.androiddemo.repository;

import org.example.androiddemo.pojo.UserFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserFriendRepository extends JpaRepository<UserFriend, Integer> {
    Optional<UserFriend> findByUserIdAndFriendId(Integer userId, Integer friendId);
}
