package com.example.statusboard;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class Board implements Parcelable {

    @NonNull
    private List<Status> statusList = new ArrayList<>();
    @NonNull
    private String name, description;

    public Board (@NonNull String name, @NonNull String description) {
        this.name = name;
        this.description = description;
    }

    @NonNull
    public String getName() {
        return name;
    }

    @NonNull
    public String getDescription() {
        return description;
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

    public void setName(@NonNull String name) {
        this.name = name;
    }

    public void setDescription(@NonNull String description) {
        this.description = description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    // write your object's data to the passed-in Parcel
    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(statusList);
        dest.writeString(name);
        dest.writeString(description);
    }

    // this is used to regenerate your object. All Parcelables must have a CREATOR that implements these two methods
    public static final Parcelable.Creator<Board> CREATOR = new Parcelable.Creator<Board>() {
        public Board createFromParcel(Parcel in) {
            return new Board(in);
        }

        public Board[] newArray(int size) {
            return new Board[size];
        }
    };

    // example constructor that takes a Parcel and gives you an object populated with it's values
    private Board(Parcel in) {
        statusList = new ArrayList<Status>();
        in.readList(statusList, Status.class.getClassLoader());
        name = in.readString();
        description = in.readString();

    }
}
