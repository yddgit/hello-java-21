package com.my.project.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.concurrent.Executors;

class FutureTest {

    @Test
    void futureTest() throws Exception {
        try (var executor = Executors
                .newFixedThreadPool(Runtime.getRuntime().availableProcessors())) {
            var future = executor.submit(() -> "hello, world!");
            Thread.sleep(100);
            // ①
            var result = switch (future.state()) {
                case CANCELLED, FAILED -> throw new IllegalStateException("couldn't finish the work!");
                case SUCCESS -> future.resultNow();
                default -> null;
            };
            Assertions.assertEquals(result, "hello, world!");
        }
    }
}