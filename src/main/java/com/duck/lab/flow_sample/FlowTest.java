package com.duck.lab.flow_sample;

import java.util.concurrent.Flow;

/*

java9에서 reactive programming을 제공하는 클래스 java.util.concurrent.Flow를 추가함
publish(발행) - subscription(구독)을 지원할 수 있는 Flow 클래스는 중첩된 인터페이스 4개를 포함한다.
- Publisher
- Subscriber
    . onSubscribe(), onNext(), onError(), onComplete()
    . 더 이상 데이터가 없고 종료임을 알리고 싶은 경우 onComplete()를 호출한다.
- Subscription
- Processor
    . Subscriber이며 Publisher다
 */
public class FlowTest {
    public static void main(String args[]) {
        System.out.println("Start FlowTest");
        getCelsiusTemp("Seoul").subscribe(new TempSubscriber());
//        getTemperatures("Seoul").subscribe(new TempSubscriber());
        System.out.println("Finish FlowTest"); //도달하지 않는듯??
    }

    public static Flow.Publisher<TempInfo> getTemperatures(String town) {
        return subscriber -> subscriber.onSubscribe(new TempSubscription(subscriber, town));
    }

    public static Flow.Publisher<TempInfo> getCelsiusTemp(String town) {
        return subscriber -> {
            TempProcessor p = new TempProcessor();
            p.subscribe(subscriber);
            p.onSubscribe(new TempSubscription(p, town));
        };
    }
}
