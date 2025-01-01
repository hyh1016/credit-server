package com.example.yhproject.creditserver.service;

import com.example.yhproject.creditserver.constant.CreditProperties;
import com.example.yhproject.creditserver.repository.CreditRepositoryV2;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@ConditionalOnProperty(name = CreditProperties.VERSION_PROPERTY_NAME, havingValue = CreditProperties.VERSION_2)
@RequiredArgsConstructor
public class CreditServiceV2 implements CreditService {

    private final CreditRepositoryV2 repository;

    @Override
    public int get(long userId) {
        return repository.getCreditByUserId(userId);
    }

    @Override
    public void deduct(long userId, int credit) {
        repository.createCreditHistory(userId, credit);
    }

}
