package com.example.yhproject.creditserver.repository;

import com.example.yhproject.creditserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.NoSuchElementException;

public interface JpaUserRepository extends JpaRepository<User, Long> {

     default User findByUserId(long userId) {
        return findById(userId).orElseThrow(() -> new NoSuchElementException("해당 사용자가 없습니다. userId: " + userId));
    }

}
