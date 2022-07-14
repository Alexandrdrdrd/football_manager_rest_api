package com.example.taskrestapi.entity;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "player")
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Size(min = 2, max = 15)
    @NotBlank()
    @Column(name="name")
    private String name;

    @Size(min = 2, max = 25)
    @NotBlank()
    @Column(name="surname")
    private String surname;

    @Min(16)
    @Column(name="age")
    private int age;

    @Min(0)
    @Column(name="experience")
    private int experience;

    public Player(String name, String surname, int age, int experience) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.experience = experience;
    }


    public Player() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }


    @Override
    public String toString() {
        return "Player{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", experience=" + experience +
                '}';
    }
}