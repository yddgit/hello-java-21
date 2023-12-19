package com.my.project.java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class EchoClient {
    public static void main(String[] args) {
        String hostName = "localhost";
        int portNumber = 8765;
        try (
                Socket echoSocket = new Socket(hostName, portNumber);
                PrintWriter out =
                new PrintWriter(echoSocket.getOutputStream(), true);
                BufferedReader in =
                new BufferedReader(
                    new InputStreamReader(echoSocket.getInputStream()))
        ) {
            BufferedReader stdIn =
                new BufferedReader(
                    new InputStreamReader(System.in));
            String userInput;
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                System.out.println(STR."echo: \{in.readLine()}");
                if ("bye".equals(userInput)) {
                    break;
                }
            }
        } catch (UnknownHostException e) {
            System.err.println(STR."Don't know about host \{hostName}");
            System.exit(1);
        } catch (IOException e) {
            System.err.println(STR."Couldn't get I/O for the connection to \{hostName}");
            System.exit(1);
        }
    }
}