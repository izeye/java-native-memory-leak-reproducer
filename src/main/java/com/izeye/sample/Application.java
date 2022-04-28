package com.izeye.sample;

import java.util.concurrent.TimeUnit;
import java.util.zip.Deflater;

/**
 * Application.
 *
 * @author Johnny Lim
 */
public class Application {

    public static void main(String[] args) throws InterruptedException {
        while (true) {
            new Deflater();
            TimeUnit.MILLISECONDS.sleep(10);
        }
    }

}
