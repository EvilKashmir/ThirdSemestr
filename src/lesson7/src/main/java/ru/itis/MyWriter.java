package ru.itis;

import au.com.bytecode.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;

public class MyWriter {
    private User user;

    public MyWriter(User user) {
        this.user = user;
    }

    public void write() throws IOException {

        String path = "D:\\JavaProjects\\ThirdSem\\src\\lesson7\\src\\main\\java\\ru\\itis\\data.csv";
        CSVWriter writer = new au.com.bytecode.opencsv.CSVWriter(new FileWriter(path, true));
        String[] record = (user.getName() + "," + user.getEmail() + "," + user.getPassword()).split(",");
        writer.writeNext(record);
        writer.close();
    }
}
