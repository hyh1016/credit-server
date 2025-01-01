package com.example.yhproject.creditserver.repository;

import com.example.yhproject.creditserver.constant.CreditProperties;
import com.example.yhproject.creditserver.entity.CreditHistory;
import com.example.yhproject.creditserver.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
@ConditionalOnProperty(name = CreditProperties.VERSION_PROPERTY_NAME, havingValue = CreditProperties.VERSION_1)
@RequiredArgsConstructor
public class CreditRepositoryV1 implements CreditRepository {

    private final JpaUserRepository userRepository;
    private final JpaCreditHistoryRepository creditHistoryRepository;

    public int getCreditByUserId(long userId) {
        User user = userRepository.findByUserId(userId);
        int credit = creditHistoryRepository.findUsedCredit(user, LocalDate.now());
        return CreditProperties.TOTAL_CREDIT - credit;
    }

    public void createCreditHistory(long userId, int credit) {
        User user = userRepository.findByUserId(userId);
        creditHistoryRepository.save(new CreditHistory(user, credit));
    }

}
