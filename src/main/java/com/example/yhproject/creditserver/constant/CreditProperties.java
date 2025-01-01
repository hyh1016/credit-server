package com.example.yhproject.creditserver.constant;

import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public final class CreditProperties {

    @Value("${credit-server.version}")
    private int version;

    public static final String VERSION_PROPERTY_NAME = "credit-server.version";
    public static final String VERSION_1 = "1";
    public static final String VERSION_2 = "2";

    // 매일 각 사용자에게 주어지는 크레딧 총량. 이건 샘플 코드이므로 소스에서 관리하지만, 실제 운영 환경에서는 DB에 저장하고 캐싱을 적용
    public static final int TOTAL_CREDIT = 30;

    @PostConstruct
    public void init() {
        log.info("활성화된 크레딧 서버 버전: {}", this.version);
    }

}
