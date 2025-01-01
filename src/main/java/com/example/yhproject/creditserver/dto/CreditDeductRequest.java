package com.example.yhproject.creditserver.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class CreditDeductRequest {

    private long userId;

    private int credit;

}
