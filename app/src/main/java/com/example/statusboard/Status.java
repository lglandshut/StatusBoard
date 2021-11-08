package com.example.statusboard;

import androidx.annotation.NonNull;

public class Status {

    @NonNull
    private final String name;
    @NonNull
    private final String color;

    public Status(@NonNull String name, @NonNull String color) {
        this.name = name;
        this.color = color;
    }

    @NonNull
    public String getColor() {
        return color;
    }

    @NonNull
    public String getName() {
        return name;
    }
}
