package com.akentech.crudreldemo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "review")
public class Review {

    // define fields
    // annotate fields

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "comment")
    private String comment;

    // define constructors

    public Review(String comment) {
        this.comment = comment;
    }

    public Review(){

    }

    // define getter/setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }


    // define toString

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", comment='" + comment + '\'' +
                '}';
    }

}
