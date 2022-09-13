package com.example.springweb.domain.db1;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Table
@Getter
public class Item1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Long item2Id;

    @Transient
    public void updateName(String name){
        this.name = name;
    }
}
