package com.example.yhproject.creditserver.repository;

public interface CreditRepository {

    int getCreditByUserId(long userId);

    void createCreditHistory(long userId, int credit);

}
