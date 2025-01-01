package com.example.yhproject.creditserver.repository;

import com.example.yhproject.creditserver.entity.CreditHistory;
import com.example.yhproject.creditserver.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface JpaCreditHistoryRepository extends JpaRepository<CreditHistory, Long> {

    @Query("select coalesce(sum(c.changedCredit), 0) " +
        "from CreditHistory c " +
        "where c.user = :user and date(c.createdAt) = :today")
    int findUsedCredit(User user, LocalDate today);

}
