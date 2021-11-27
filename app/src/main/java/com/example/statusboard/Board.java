package com.example.statusboard;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Board {

    @NonNull
    private final List<Status> statusList = new ArrayList<>();
    @NonNull
    private final String name, description;

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public String getDescription() {
        return description;
    }

    public Board (@NonNull String name, @NonNull String description) {
        this.name = name;
        this.description = description;
    }

    @NonNull
    @Override
    public String toString() {
        return "Board{" +
                "statusList=" + statusList +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
