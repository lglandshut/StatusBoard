package com.example.statusboard;

import android.security.identity.IdentityCredentialStore;

import java.util.ArrayList;
import java.util.List;

public class Board {

    //Diese Liste von Status durchgehen und anzeigen
    List<Status> list = new ArrayList<Status>();
    String name, description;

    public Board (String name, String description){
        this.name = name;
        this.description = description;
        this.list = list;
    }
}
