package com.example.yhproject.creditserver.controller;

import com.example.yhproject.creditserver.dto.CreditDeductRequest;
import com.example.yhproject.creditserver.dto.CreditResponse;
import com.example.yhproject.creditserver.service.CreditService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/credits")
@RequiredArgsConstructor
public class CreditController {

    private final CreditService creditService;

    @GetMapping(value = "/{userId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<CreditResponse> get(@PathVariable long userId) {
        int credit = creditService.get(userId);
        return ResponseEntity.ok(new CreditResponse(credit));
    }

    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> deduct(@RequestBody CreditDeductRequest request) {
        log.info("크레딧 차감 (request={})", request);
        creditService.deduct(request.getUserId(), request.getCredit());
        return ResponseEntity.noContent().build();
    }

}
