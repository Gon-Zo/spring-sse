package com.example.sse.doamin;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "DATA_SET")
public class DataSet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

}
