package com.duck.lab.flow_sample;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Flow;

// TempSubscriber 레포트를 관찰하면서 각 도시에 설치된 센서에서 보고한 온도 스트림을 출력한다.
public class TempSubscription implements Flow.Subscription {
    private final Flow.Subscriber<? super TempInfo> subscriber;
    private final String town;

    public TempSubscription(Flow.Subscriber<? super TempInfo> subscriber, String town) {
        this.subscriber = subscriber;
        this.town = town;
    }

    private static final ExecutorService executor = Executors.newSingleThreadExecutor();

    //TempInfo에 에러를 임의로 발생시키는 코드가 없더라도 StackOverflowError 발생하지 않음
    @Override
    public void request(long n) {
        executor.submit(() -> {
            for(long i=0L; i<n; i++) {
                try {
                    TempInfo temp = TempInfo.fetch(town);
                    System.out.println(temp.getTemp() + "--->");
                    if(temp.getTemp() < 10) {
                        subscriber.onComplete();
                        break;
                    }
                    subscriber.onNext(temp);
                } catch (Exception e) {
                    subscriber.onError(e);
                    break;
                }
            }
        });
    }

//    //TempInfo에 에러를 임의로 발생시키는 코드 (if (random.nextInt(10) == 0) 에 진입하지 못하면) StackOverflowError발생
//    @Override
//    public void request(long n) {
//        //Subscriber가 만든 요청을 한개씩 반복
//        for (long i = 0L; i < n; i++) {
//            System.out.println(i);
//            try {
//                // 현재 온도를 subscriber로 전달함
//                subscriber.onNext(TempInfo.fetch(town));
//            } catch (Exception e) {
//                subscriber.onError(e);
//                break; //??? break안하면???
//            }
//        }
//    }

    @Override
    public void cancel() {
        //구독이 취소되면 완료(onComplete)신호를 Subscriber로 전달
        subscriber.onComplete();
    }
}
