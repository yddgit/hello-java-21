package com.my.project.java;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.SequencedCollection;
import java.util.SequencedMap;
import java.util.SequencedSet;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.stream.IntStream;

import static java.util.FormatProcessor.FMT;

class HelloJava21Test {
    @Test
    void testSequenced() {
        SequencedCollection<String> list = new ArrayList<>();
        list.addLast("first");
        list.addLast("second");
        list.addLast("third");
        SequencedSet<String> set = new LinkedHashSet<>();
        set.addLast("first");
        set.addLast("second");
        set.addLast("third");
        SequencedMap<String, String> map = new LinkedHashMap<>();
        map.putLast("first", "1st");
        map.putLast("second", "2nd");
        map.putLast("third", "3rd");
        System.out.println(list.reversed());
        System.out.println(list);
        System.out.println(set.reversed());
        System.out.println(set);
        System.out.println(map.reversed());
        System.out.println(map);
    }

    @Test
    void testStringTemplate() {
        int a = 1;
        long b = 2L;
        float c = 3.0f;
        double d = 4.0d;
        String s = "string";
        Object o = null;
        String result = STR."a=\{a}, b=\{b}, c=\{c}, d=\{d}, sum=\{a+b+c+d}, s=\{s}";
        System.out.println(result);
        String multiLine = STR."""
            a=\{a},
            b=\{b},
            c=\{c},
            d=\{d},
            o=\{o},
            sum=\{a+b+c+d},
            random=\{Math.random()},
            raw=\{123},
            literal=\{"aaa"},
            s=\{s}""";
        System.out.println(multiLine);
        String format = FMT."a=%02d\{a}, b=%02d\{b}, c=%2.2f\{c}, d=%2.3f\{d}, sum=%02.4f\{a+b+c+d}, s=\{s}";
        System.out.println(format);
    }

    @Test
    void testUnnamedVariable() {
        List<String> list = List.of("a", "b", "c");
        var count = 0;
        for(var _ : list) {
            System.out.println(++count);
        }
    }

    @Test
    void testVirtualThread() {
        Runnable printThread = () -> System.out.println(Thread.currentThread());
        ThreadFactory virtualThreadFactory = Thread.ofVirtual().factory();
        ThreadFactory platformThreadFactory = Thread.ofPlatform().factory();
        Thread virtualThread = virtualThreadFactory.newThread(printThread);
        Thread platformThread = platformThreadFactory.newThread(printThread);
        virtualThread.start();
        platformThread.start();
    }

    @Test
    void testLargeNumberVirtualThread() {
        long start = System.currentTimeMillis();
        try (var executor = Executors.newVirtualThreadPerTaskExecutor()) {
            IntStream.range(0, 10_000).forEach(i -> executor.submit(() -> {
                Thread.sleep(Duration.ofSeconds(1));
                return i;
            }));
        } // executor.close() is called implicitly, and waits
        long virtual = System.currentTimeMillis();
        System.out.println(Duration.ofMillis(virtual - start));
        try (var executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors())) {
            IntStream.range(0, 100).forEach(i -> executor.submit(() -> {
                Thread.sleep(Duration.ofSeconds(1));
                return i;
            }));
        } // executor.close() is called implicitly, and waits
        long platform = System.currentTimeMillis();
        System.out.println(Duration.ofMillis(platform - virtual).toString());
    }
}
