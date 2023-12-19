package com.my.project.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.Instant;

class RecordsTest {

    record User(String name, long accountNumber) {
    }

    record UserDeletedEvent(User user) {
    }

    record UserCreatedEvent(String name) {
    }

    record ShutdownEvent(Instant instant) {
    }

    @Test
    void respondToEvents() throws Exception {
        Assertions.assertEquals(
                respond(new UserCreatedEvent("jlong")), "the new user with name jlong has been created"
        );
        Assertions.assertEquals(
                respond(new UserDeletedEvent(new User("jlong", 1))),
                "the user jlong has been deleted"
        );
    }

    String respond(Object o) {
        // ①
        if (o instanceof ShutdownEvent(Instant instant)) {
            System.out.println(
                    STR."going to to shutdown the system at \{instant.toEpochMilli()}");
        }
        return switch (o) {
            // ②
            case UserDeletedEvent(var user) -> STR."the user \{user.name()} has been deleted";
            // ③
            case UserCreatedEvent(var name) -> STR."the new user with name \{name} has been created";
            default -> null;
        };
    }

}