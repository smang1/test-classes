package com.smang.utils.completableFuture;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * @author SMA
 * @Date 08/09/2017
 */
public class GenerateNumber implements Supplier<Integer> {

    private final int number;

    GenerateNumber(int number){
        this.number = number;
    }

    @Override
    public Integer get() {
        try {
            //TODO Why sleep
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.exit(-1);
        }
        return this.number;
    }
}
