package ru.itis.request;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client {
    public static void main(String[] args) throws IOException {
        System.out.println("Starting client...");
        String host = "youtube.com";
        Socket socket = new Socket(InetAddress.getByName(host), 80);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8));
        System.out.println("GET request");
        bufferedWriter.write("GET / HTTP/1.0");
        bufferedWriter.write("Host: " + host);
        bufferedWriter.flush();
        System.out.println("Getting answer");
        System.out.println("---------------------------------------------------------------------------------\n");
        String resp;
        while ((resp = bufferedReader.readLine()) != null) {
            System.out.println(resp);
        }
        System.out.println("---------------------------------------------------------------------------------\n");
        bufferedWriter.close();
        bufferedReader.close();
    }
}
