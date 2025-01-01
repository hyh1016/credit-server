package com.example.yhproject.creditserver.repository;

import com.example.yhproject.creditserver.constant.CreditProperties;
import com.example.yhproject.creditserver.entity.CreditHistory;
import com.example.yhproject.creditserver.entity.User;
import com.example.yhproject.creditserver.entity.UserCredit;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@ConditionalOnProperty(name = CreditProperties.VERSION_PROPERTY_NAME, havingValue = CreditProperties.VERSION_2)
@RequiredArgsConstructor
public class CreditRepositoryV2 implements CreditRepository {

    private final JpaUserRepository userRepository;
    private final JpaUserCreditRepository userCreditRepository;
    private final JpaCreditHistoryRepository creditHistoryRepository;

    @Override
    @Transactional(readOnly = true)
    public int getCreditByUserId(long userId) {
        User user = userRepository.findByUserId(userId);
        UserCredit userCredit = userCreditRepository.findByUser(user);
        return userCredit.getUsableCredit();
    }

    @Override
    @Transactional
    public void createCreditHistory(long userId, int credit) {
        User user = userRepository.findByUserId(userId);

        UserCredit userCredit = userCreditRepository.findByUser(user);
        userCredit.deduct(credit);

        creditHistoryRepository.save(new CreditHistory(user, credit));
    }

}
