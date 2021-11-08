package com.example.statusboard;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Board {

//    Kannst dir merken Variablen die du private oder final setzen kannst sollst du auch so deklarieren.
//    Außerdem alle Variablen @NonNull setzen, wenn Sie nicht null werden können bzw. @Nullable wenn schon
//    Vor allem Marvin legt da Wert drauf in der WFC, ist nervig aber man vermeidet blöde Bugs.
//    Und hat Vorteile wenn man Java Code von Kotlin aufruft. Wenn du kein Bock drauf hast wechsel
//    zu Kotlin lol ʕノ•ᴥ•ʔノ ︵ ┻━┻
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
