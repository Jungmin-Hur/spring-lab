package com.duck.lab.flow_sample;

import java.util.concurrent.Flow;

// TempSubscriber 레포트를 관찰하면서 각 도시에 설치된 센서에서 보고한 온도 스트림을 출력한다.
public class TempSubscriber implements Flow.Subscriber<TempInfo> {

    private Flow.Subscription subscription;

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        //subscription을 저장하고 첫번째 요청을 전달함
        this.subscription = subscription;
        System.out.println("Start subscribe!!!");
        subscription.request(1);
    }

    @Override
    public void onNext(TempInfo item) {
        //수신한 온도를 출력하고 다음 정보를 요청함
        System.out.println("onNext called");
        System.out.println(item);
        subscription.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("onError called");
        System.err.println(throwable.getMessage());
    }

    @Override
    public void onComplete() {
        System.out.println("onComplete called!!!");
    }
}
