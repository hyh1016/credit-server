package com.example.yhproject.creditserver.service;

import org.springframework.stereotype.Service;

@Service
public interface CreditService {

    /**
     * 사용자의 크레딧을 조회
     * @param userId 사용자 식별자
     * @return 해당 사용자의 잔여 크레딧
     */
    int get(long userId);

    /**
     * 사용자의 크레딧을 차감
     * @param userId 사용자 식별자
     * @param credit 차감할 크레딧
     */
    void deduct(long userId, int credit);

}
