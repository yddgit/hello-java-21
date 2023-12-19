package com.my.project.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigInteger;

class MathematicsTest {

    @Test
    void divisions() throws Exception {
        //<1>
        var five = Math.divideExact( 10, 2) ;
        Assertions.assertEquals(five , 5);
    }

    @Test
    void multiplication() throws Exception {
        var start = BigInteger.valueOf(10);
        // ②
        var result = start.parallelMultiply(BigInteger.TWO);
        Assertions.assertEquals(BigInteger.valueOf(10 * 2), result);
    }
}