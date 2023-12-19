package com.my.project.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class StringsTest {

    @Test
    void repeat() throws Exception {
        // ①
        var line = new StringBuilder()
                .repeat("-", 10)
                .toString();
        Assertions.assertEquals("----------", line);
    }

    @Test
    void emojis() throws Exception {
        // ②
        var shockedFaceEmoji = "\uD83E\uDD2F";
        var cp = Character.codePointAt(shockedFaceEmoji.toCharArray(), 0);
        Assertions.assertTrue(Character.isEmoji(cp));
        System.out.println(shockedFaceEmoji);
    }
}