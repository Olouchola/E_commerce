package com.example.demo;

import java.io.Serializable;

public class Publicite implements Serializable {
    private int images;

    public Publicite(int images) {
        this.images = images;
    }

    public int getImages() {
        return images;
    }

    public void setImages(int images) {
        this.images = images;
    }
}
