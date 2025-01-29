import http from 'k6/http';
import {check, sleep} from 'k6';

export const options = {
    scenarios: {
        constant_arrival_rate: {
            executor: 'constant-arrival-rate',
            rate: 5000, // 초당 5000개의 요청
            timeUnit: '1s', // 시간 단위는 1초
            duration: '2s', // 2초 동안 테스트 실행
            preAllocatedVUs: 1000, // 미리 할당된 VU 수 (1000명)
            maxVUs: 1000, // 최대 VU 수 (1000명)
        },
    },
};

export default () => {
    const userId = __VU;

    for (let i = 0; i < 10; i++) {
        // GET 요청
        const getRes = http.get(`http://credit-server:8080/api/credits/${userId}`);

        check(getRes, {
            'GET status is 200': (r) => r.status === 200,
        });

        sleep(0.2); // durations / 반복횟수 만큼 간격을 둬야 함
    }

}
