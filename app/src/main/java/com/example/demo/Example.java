package com.example.demo;

import java.io.Serializable;

public class Example implements Serializable {
    private String num;

    public Example(String num) {
        this.num = num;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
