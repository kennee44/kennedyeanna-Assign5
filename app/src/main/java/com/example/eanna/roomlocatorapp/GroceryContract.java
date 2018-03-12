package com.example.eanna.roomlocatorapp;

import android.provider.BaseColumns;
import android.provider.SyncStateContract;

/**
 * Created by eanna on 09/03/2018.
 */

public class GroceryContract {

    private GroceryContract () {}

    public static final class GroceryEntry implements BaseColumns{
        public static final String TABLE_NAME = "groceryList";
        //name of first column
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_AMOUNT = "amount";
        public static final String COLUMN_TIMESTAMP = "timestamp";
    }



}
