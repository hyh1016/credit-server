package com.example.yhproject.creditserver.service;

import com.example.yhproject.creditserver.constant.CreditProperties;
import com.example.yhproject.creditserver.repository.CreditRepositoryV1;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@ConditionalOnProperty(name = CreditProperties.VERSION_PROPERTY_NAME, havingValue = CreditProperties.VERSION_1)
@RequiredArgsConstructor
public class CreditServiceV1 implements CreditService {

    private final CreditRepositoryV1 repository;

    @Override
    @Transactional(readOnly = true)
    public int get(long userId) {
        return repository.getCreditByUserId(userId);
    }

    @Override
    @Transactional
    public void deduct(long userId, int credit) {
        repository.createCreditHistory(userId, credit);
    }

}
