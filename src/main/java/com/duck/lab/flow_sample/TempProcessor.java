package com.duck.lab.flow_sample;

import java.util.concurrent.Flow;

// TempInfo를 다른 TempInfo로 변환하는 프로세서
// 온도를 섭씨에서 화씨로 바꾼다.
public class TempProcessor implements Flow.Processor<TempInfo, TempInfo> {
    private Flow.Subscriber<? super TempInfo> subscriber;

    @Override
    public void subscribe(Flow.Subscriber<? super TempInfo> subscriber) {
        this.subscriber = subscriber;
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        subscriber.onSubscribe(subscription);
    }

    @Override
    public void onNext(TempInfo item) {
        subscriber.onNext(new TempInfo(item.getTown(), (item.getTemp()-32)*5/9));
    }

    @Override
    public void onError(Throwable throwable) {
        subscriber.onError(throwable);
    }

    @Override
    public void onComplete() {
        subscriber.onComplete();
    }
}
