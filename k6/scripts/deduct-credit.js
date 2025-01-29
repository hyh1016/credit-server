import http from 'k6/http';
import {check, sleep} from 'k6';
import {randomIntBetween} from 'https://jslib.k6.io/k6-utils/1.4.0/index.js';

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

    // POST 요청
    for (let i = 0; i < 10; i++) {
        const postPayload = JSON.stringify({
            userId: userId,
            credit: randomIntBetween(1, 3), // 1~3 랜덤 크레딧
        });
        const postHeaders = {'Content-Type': 'application/json'};
        const postRes = http.post('http://credit-server:8080/api/credits', postPayload, {headers: postHeaders});

        check(postRes, {
            'POST status is 204': (r) => r.status === 204,
        });

        sleep(0.2); // durations / 반복횟수 만큼 간격을 둬야 함
    }
}
