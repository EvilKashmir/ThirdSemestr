package ru.itis;

public class Main {
    public static void main(String[] args) {
        StudentChanel.write(new Student("Ural", 902, 19, true));
        Student student = StudentChanel.read();
        System.out.println(student.toString());
    }
}
