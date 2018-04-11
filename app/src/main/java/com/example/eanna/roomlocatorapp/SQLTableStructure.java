package com.example.eanna.roomlocatorapp;

import android.provider.BaseColumns;
import android.provider.SyncStateContract;

/**
 * This class is used to define all the tables we use in our SQLite database
 * <p>
 * Since we have only one table it contains only one inner Class.
 * If you would like to add more tables create other inner Classes here.
 * </p>
 *
 * Citation: Class contains code adapted from URL:
 * <a href="https://www.youtube.com/watch?v=5ISNPFmuOU8&list=PLrnPJCHvNZuBMJmll0xy2L2McYInT3aiu">
 *     YouTube: SQLite + RecyclerView Tutorial - Android Programming</a> (2018-03-12)
 *
 * @param  url  an absolute URL giving the base location of the image
 * @param  name the location of the image, relative to the url argument
 * @return      the image at the specified URL
 * @see         Image
 */


public class SQLTableStructure {

    // Constructor for class. Empty not used.
  // private SQLTableStructure() {}

    // Used BaseColumns to inlclude _id automatically

    public static final class TableColumns implements BaseColumns


    {
        //Table Name
        public static final String TABLE_NAME = "groceryList";

        //Columns
        public static final String _ID = "id";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_CITY = "city";
        public static final String COLUMN_BUILDING = "building";
        public static final String COLUMN_FLOOR = "floor";
        public static final String COLUMN_FLOORLOCATION = "floorlocation";
        public static final String COLUMN_DIRECTIONS = "directions";
        public static final String COLUMN_IMAGEURL = "imageURL";
        public static final String COLUMN_CAPACITY = "capacity";
        public static final String COLUMN_AMENITIES = "amenities";
        public static final String COLUMN_ICON = "icon";
        public static final String COLUMN_SPARE1 = "spare1";
        public static final String COLUMN_SPARE2 = "spare2";
        public static final String COLUMN_TIMESTAMP = "timestamp";

    }

}
