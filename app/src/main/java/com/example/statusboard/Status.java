package com.example.statusboard;

import android.graphics.Color;

import androidx.annotation.NonNull;

public class Status {

    @NonNull
    private String name;
    @NonNull
    private int color;

    public Status(@NonNull String name, @NonNull int color) {
        this.name = name;
        this.color = color;
    }

    @NonNull
    public int getColor() {
        return color;
    }

    @NonNull
    public String getName() {
        return name;
    }

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public void setColor(@NonNull int color) {
        this.color = color;
    }
}
