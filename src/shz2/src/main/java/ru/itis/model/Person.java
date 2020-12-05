package ru.itis.model;

public class Person {
    private Long id;
    private String name;
    private boolean gender;

    public Person(Long id, String name, boolean gender) {
        this.id = id;
        this.name = name;
        this.gender = gender;
    }

    public Person(String name, boolean gender) {
        this(null, name, gender);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                '}';
    }
}
