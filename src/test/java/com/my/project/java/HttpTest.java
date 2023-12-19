package com.my.project.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

class HttpTest {

    @Test
    void http () throws Exception {

        // ①
        try (var http = HttpClient
                .newHttpClient()) {
            var request = HttpRequest.newBuilder(URI.create("https://httpbin.org"))
                    .GET()
                    .build() ;
            var response = http.send( request, HttpResponse.BodyHandlers.ofString());
            Assertions.assertEquals( response.statusCode() , 200);
            System.out.println(response.body());
        }
    }

}