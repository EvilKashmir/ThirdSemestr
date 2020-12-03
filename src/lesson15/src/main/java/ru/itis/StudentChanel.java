package ru.itis;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;

public class StudentChanel {

    public static Student read() {
        Student student = new Student();
        try {
            RandomAccessFile file = new RandomAccessFile(Constants.PATH, "rw");
            FileChannel channel = file.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int count = channel.read(buffer);
            System.out.println("Number of bytes has red : " + count);
            buffer.flip();
            student.setGroup(buffer.getInt());
            student.setAge(buffer.getInt());
            student.setGender(buffer.get() == 1);
            byte[] nameBytes = new byte[buffer.remaining()];
            buffer.get(nameBytes);
            student.setName(new String(nameBytes, StandardCharsets.UTF_8));
            channel.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return student;
    }

    public static boolean write(Student student) {
        try {
            RandomAccessFile file = new RandomAccessFile(Constants.PATH, "rw");
            FileChannel channel = file.getChannel();
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.putInt(student.getGroup());
            buffer.putInt(student.getAge());
            buffer.put((byte)(student.getGender().equals("male") ? 1 : 0));
            buffer.put(student.getName().getBytes(StandardCharsets.UTF_8));
            buffer.flip();
            channel.write(buffer);
            channel.close();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}