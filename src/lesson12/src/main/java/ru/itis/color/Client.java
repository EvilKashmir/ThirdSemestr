package ru.itis.color;

import ru.itis.Protocol;

import java.awt.*;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws Throwable {
        System.out.println("Starting client...");
        Socket s = new Socket(InetAddress.getLocalHost(), Protocol.PORT);
        OutputStream out = s.getOutputStream();
        System.out.println("Starting sending random colors");
        Scanner scanner = new Scanner(System.in);
        while(true){
            Thread.sleep(1000);
            System.out.println("Enter red color value from 0 to 255");
            int r = scanner.nextInt();
            System.out.println("Enter green color value from 0 to 255");
            int g = scanner.nextInt();
            System.out.println("Enter blue color value from 0 to 255");
            int b = scanner.nextInt();
            Color c = new Color(r, g, b);
            ByteBuffer buf = ByteBuffer.allocate(12);
            buf.putInt(r).putInt(g).putInt(b);
            System.out.println(">> " + c);
            System.out.println(Arrays.toString(buf.array()));
            out.write(buf.array());
            out.flush();
        }
    }
}