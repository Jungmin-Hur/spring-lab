package com.duck.lab.flow_sample;

import java.util.Random;

// TempInfo 원격온도계 (0에서 99를 임의로 만듬)
public class TempInfo {
    public static final Random random = new Random();

    private final String town;
    private final int temp;

    public TempInfo(String town, int temp) {
        this.town = town;
        this.temp = temp;
    }

    //정적 팩토리 메소드를 이용해서 해당 도시의 TempInfo 인스턴스를 만든다.
    public static TempInfo fetch(String town) {
        if (random.nextInt(10) == 0) { // 1/10 확률로 실패한다.
            throw new RuntimeException("Error!!");
        }
        return new TempInfo(town, random.nextInt(100));
    }

    @Override
    public String toString() {
        return town + " : " + temp;
    }

    public int getTemp() {
        return temp;
    }

    public String getTown() {
        return town;
    }
}
