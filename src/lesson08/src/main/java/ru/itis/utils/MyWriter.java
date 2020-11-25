package ru.itis.utils;

import ru.itis.models.User;

import java.io.FileWriter;
import java.io.IOException;

public class MyWriter {
    private User user;

    public MyWriter(User user) {
        this.user = user;
    }

    public void write() throws IOException {

        String path = "D:\\JavaProjects\\ThirdSem\\src\\lesson8\\src\\main\\resources\\data.csv";
        au.com.bytecode.opencsv.CSVWriter writer = new au.com.bytecode.opencsv.CSVWriter(new FileWriter(path, true));
        String[] record = (user.getName() + "," + user.getEmail() + "," + user.getPassword()).split(",");
        writer.writeNext(record);
        writer.close();
    }
}
