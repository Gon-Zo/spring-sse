package com.example.demo.repository.support.boxbuilder;

public interface BoxFactory {
    BoxBuilderFactory getBoxAction(BoxType boxType, Long userId);
}
