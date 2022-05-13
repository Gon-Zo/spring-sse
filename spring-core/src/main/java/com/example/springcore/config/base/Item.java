package com.example.springcore.config.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

public class Item extends AbstractItem{

    @Autowired @Lazy
    private Item2 item2;

    @Override
    void show() {

    }
}
