package com.example.springweb.domain.db2;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Item2 {

    @Id
    private Long id;

    private String name;
}
