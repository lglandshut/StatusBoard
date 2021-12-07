package com.example.statusboard;

import android.view.View;

/** Interface um herauszufinden welches Board aus der RecyclerView ausgewählt wurde
 * um es an die neue BoardActivity (über die putExtra-Methode im BoardAdapter) zu passen (siehe Parcelable in Board)
 * https://stackoverflow.com/questions/28296708/get-clicked-item-and-its-position-in-recyclerview
 */
public interface BoardSelectedCallback {
    void boardClicked(Board board);
}
