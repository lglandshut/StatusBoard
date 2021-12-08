package com.example.statusboard;

import android.graphics.Color;

import androidx.annotation.NonNull;

public class Status {

    @NonNull
    private String name;
    private String description;
    @NonNull
    private int color;

    public Status(@NonNull String name, String description, @NonNull int color) {
        this.name = name;
        this.description = description;
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
