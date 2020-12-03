package ru.itis;

import java.io.Serializable;
import java.util.*;

public class Student implements Serializable {

    private String name;
    private int group;
    private int age;
    private boolean gender;

    public Student(String name, int group, int age, boolean gender) {
        if (!name.isEmpty() && name.length() < 10 && group > 0 && age > 17 && age < 100) {
            this.name = name;
            this.gender = gender;
            this.age = age;
            this.group = group;
        } else throw new InputMismatchException();
    }

    public Student() {

    }

    public String getName() {
        return name;
    }

    public int getGroup() {
        return group;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender ? "male" : "female";
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGroup(int group) {
        this.group = group;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return gender == student.gender &&
                age == student.age &&
                group == student.group &&
                Objects.equals(name, student.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, gender, age, group);
    }

    @Override
    public String toString() {
        return "(Name: " + getName() +
                ", Group: " + getGroup() +
                ", Age: " + getAge() +
                ", Sex: " + getGender() + ")";
    }
}

