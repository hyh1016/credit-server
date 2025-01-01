package com.example.yhproject.creditserver.repository;

import com.example.yhproject.creditserver.entity.User;
import com.example.yhproject.creditserver.entity.UserCredit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserCreditRepository extends JpaRepository<UserCredit, Long> {

    UserCredit findByUser(User user);

}
