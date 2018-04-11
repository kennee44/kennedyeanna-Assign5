package com.example.eanna.roomlocatorapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//Import class
import com.example.eanna.roomlocatorapp.SQLTableStructure.*;



/**
 * This Helper Class DBHelperClass is used to create the roomlist.db which contains all the information
 *
 * <p>
 * Since we have only one table it contains only one inner Class.
 * If you would like to add more tables create other inner Classes here.
 * </p>
 *
 * Citation: Class contains code adapted from URL:
 * <a href="https://www.youtube.com/watch?v=5ISNPFmuOU8&list=PLrnPJCHvNZuBMJmll0xy2L2McYInT3aiu">
 *     YouTube: SQLite + RecyclerView Tutorial - Android Programming</a> (2018-03-12)
 *
 * table name "roomlist.db")
 *
 */

public class DBHelperClass extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "roomlist.db";
    public static final int DATABASE_VERSION = 111;

    public DBHelperClass(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_ROOMLIST_TABLE = "CREATE TABLE " +
                SQLTableStructure.TableColumns.TABLE_NAME + " (" +
                TableColumns._ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                TableColumns.COLUMN_NAME + " TEXT NOT NULL, " +
                TableColumns.COLUMN_CITY + " TEXT, " +
                TableColumns.COLUMN_BUILDING + " TEXT, " +
                TableColumns.COLUMN_FLOOR + " INTEGER, " +
                TableColumns.COLUMN_FLOORLOCATION + " TEXT, " +
                TableColumns.COLUMN_DIRECTIONS + " TEXT, " +
                TableColumns.COLUMN_IMAGEURL + " TEXT, " +
                TableColumns.COLUMN_CAPACITY + " INTEGER, " +
                TableColumns.COLUMN_AMENITIES + " TEXT, " +
                TableColumns.COLUMN_ICON + " TEXT, " +
                TableColumns.COLUMN_SPARE1 + " TEXT, " +
                TableColumns.COLUMN_SPARE2 + " TEXT, " +
                TableColumns.COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" +
                ");";

        // Create database using SQL_CREATE_ROOMLIST_TABLE string
        db.execSQL(SQL_CREATE_ROOMLIST_TABLE);

        // Insert data into SQLite database.  This information is inputted into an Excel Sheet and copied and pasted over.

        String startInsert = "INSERT INTO groceryList(name,city,building,floor,floorlocation,directions,imageURL,capacity,amenities,icon,spare1,spare2) ";

        db.execSQL(startInsert + "VALUES ('Tuan Mac Carrell','DUB','01',0,'Middle Finger','Turn Right, 15 meters, Meeting Room on Left','room',8,'Phone,Video Confrencing,Whiteboard','iconURL','spare1','spare2') ");
        db.execSQL(startInsert + "VALUES ('Muirne','GLY','01',0,'Left Finger','Turn Left at Watercooler','muirne',4,'Phone,Satellite Link,Whiteboard','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Bran agus Sieolang','DUB','01',0,'Right Finger','Straight Ahead','branagussieolang',2,'Phone,Windows Conf,Whiteboard','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Aonghus Óg','GLY','01',0,'Middle Finger','Beside Canteen','aonghusóg',3,'Phone,Video Confrencing,Whiteboard','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Nas Na Rioch','DUB','01',0,'Left Finger','Beside Entrance','nasnarioch',3,'Phone,Satellite Link,Whiteboard','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Na Fianna','GLY','01',0,'Right Finger','Turn Right, 15 meters, Meeting Room on Left','nafianna',3,'Phone,Windows Conf,Whiteboard','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Gaoth Dobhair 1','DUB','01',1,'Middle Finger','Turn Left at Watercooler','gaothdobhair1',2,'Phone,Video Confrencing,Whiteboard','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Gaoth Dobhair 2','GLY','01',1,'Left Finger','Straight Ahead','gaothdobhair2',2,'Phone,Satellite Link,Whiteboard','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Gaoth Dobhair 3','DUB','01',1,'Right Finger','Beside Canteen','gaothdobhair3',2,'Phone,Windows Conf,Whiteboard','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Mullach Íde 1','DUB','01',1,'Middle Finger','Beside Entrance','mullachíde1',2,'Phone,Video Confrencing,Whiteboard','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Mullach Íde 2','DUB','01',1,'Left Finger','Turn Right, 15 meters, Meeting Room on Left','mullachíde2',2,'Phone,Satellite Link,Flip Charts','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Mullach Íde 3','DUB','01',1,'Right Finger','Turn Left at Watercooler','mullachíde3',2,'Phone,Windows Conf,Flip Charts','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Mullach Íde 4','DUB','01',1,'Middle Finger','Straight Ahead','mullachíde4',2,'Phone,Video Confrencing,Flip Charts','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Mullach Íde 5','DUB','01',1,'Left Finger','Beside Canteen','mullachíde5',2,'Phone,Satellite Link,Flip Charts','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Fir Blog','GLY','01',1,'Right Finger','Beside Entrance','firblog',2,'Phone,Windows Conf,Flip Charts','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('An Tain','DUB','01',1,'Middle Finger','Turn Right, 15 meters, Meeting Room on Left','antain',4,'Phone,Video Confrencing,Flip Charts','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Clann Choilte 1','DUB','01',1,'Left Finger','Turn Left at Watercooler','clannchoilte1',2,'Phone,Satellite Link,Flip Charts','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Clann Choilte 22','DUB','01',1,'Right Finger','Straight Ahead','clannchoilte2',2,'Phone,Windows Conf,Flip Charts','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Clann Choilte 3','DUB','01',1,'Middle Finger','Beside Canteen','clannchoilte3',2,'Phone,Video Confrencing,Flip Charts','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('AN Breadan Freasa','DUB','01',1,'Left Finger','Turn Right, 15 meters, Meeting Room on Left','anbreadanfreasa',2,'Phone,Satellite Link,Flip Charts','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Teamhair','DUB','01',1,'Right Finger','Turn Left at Watercooler','teamhair',3,'Phone,Windows Conf,Flip Charts','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Children of Lir','GLY','01',1,'Middle Finger','Straight Ahead','childrenoflir',14,'Phone,Video Confrencing,Flip Charts','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Bann','DUB','02',1,'Left Finger','Beside Canteen','bann',3,'Phone,Satellite Link,Flip Charts','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Barrow','DUB','02',1,'Right Finger','Beside Entrance','barrow',3,'Phone,Windows Conf,Flip Charts','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('leinster','DUB','02',1,'Middle Finger','Turn Right, 15 meters, Meeting Room on Left','leinster',10,'Phone,Video Confrencing,Flip Charts','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Blackwater','DUB','02',2,'Left Finger','Turn Left at Watercooler','blackwater',4,'Phone,Satellite Link,Flip Charts','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Dodder','DUB','02',2,'Right Finger','Straight Ahead','dodder',4,'Phone,Windows Conf,Flip Charts','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Lagan','DUB','02',3,'Middle Finger','Beside Canteen','lagan',3,'Phone,Video Confrencing,Flip Charts','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Boyne','DUB','02',3,'Left Finger','Turn Right, 15 meters, Meeting Room on Left','boyne',4,'Phone,Satellite Link,Whiteboard','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Connacht','GLY','02',3,'Right Finger','Turn Left at Watercooler','connacht',4,'Phone,Windows Conf,Whiteboard','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Nore','DUB','02',4,'Middle Finger','Straight Ahead','nore',3,'Phone,Video Confrencing,Whiteboard','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Lee','DUB','02',4,'Left Finger','Beside Canteen','lee',10,'Phone,Satellite Link,Flip Charts','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Moy','DUB','02',6,'Right Finger','Beside Entrance','moy',3,'Phone,Windows Conf,Flip Charts','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Suir','GLY','02',6,'Middle Finger','Turn Right, 15 meters, Meeting Room on Left','suir',4,'Phone,Video Confrencing,Flip Charts','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Poddle','DUB','02',7,'Left Finger','Turn Left at Watercooler','poddle',4,'Phone,Satellite Link,Flip Charts','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Swilly','DUB','02',8,'Right Finger','Straight Ahead','swilly',3,'Phone,Windows Conf,Flip Charts','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('The Bandon','DUB','02',9,'Middle Finger','Beside Canteen','thebandon',5,'Phone,Video Confrencing,Flip Charts','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Galway-Apphaus','DUB','02',1,'Left Finger','Turn Right, 15 meters, Meeting Room on Left','galway-apphaus',4,'Phone,Satellite Link,Flip Charts','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Wexford-Apphaus','DUB','02',1,'Right Finger','Turn Left at Watercooler','wexford-apphaus',3,'Phone,Windows Conf,Flip Charts','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Kilkenny-Apphaus','GLY','02',1,'Middle Finger','Straight Ahead','kilkenny-apphaus',3,'Phone,Video Confrencing,Whiteboard','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Limerick-Apphaus','DUB','02',1,'Left Finger','Beside Canteen','limerick-apphaus',3,'Phone,Satellite Link,Whiteboard','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Cork-Apphaus','DUB','02',2,'Right Finger','Beside Entrance','cork-apphaus',10,'Phone,Windows Conf,Whiteboard','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Dublin-Apphaus','GLY','02',2,'Middle Finger','Turn Right, 15 meters, Meeting Room on Left','dublin-apphaus',10,'Phone,Windows Conf,Flip Charts','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Phoenix Park','DUB','03',1,'Left Finger','Turn Left at Watercooler','phoenixpark',12,'Phone,Video Confrencing,Flip Charts','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Croke Park','DUB','03',1,'Right Finger','Straight Ahead','crokepark',12,'Phone,Satellite Link,Flip Charts','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Montpelier Hill ','DUB','05',0,'Middle Finger','Beside Canteen','montpelierhill',3,'Phone,Windows Conf,Flip Charts','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Killiney Hill','DUB','05',1,'Left Finger','Turn Right, 15 meters, Meeting Room on Left','killineyhill',4,'Phone,Video Confrencing,Flip Charts','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Sugar loaf','DUB','05',1,'Right Finger','Turn Left at Watercooler','sugarloaf',18,'Phone,Satellite Link,Flip Charts','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Kippure Hill','DUB','05',1,'Middle Finger','Straight Ahead','kippurehill',3,'Phone,Windows Conf,Flip Charts','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Slieve Bloom','DUB','05',1,'Left Finger','Beside Canteen','slievebloom',10,'Phone,Video Confrencing,Flip Charts','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Hill of tara','DUB','05',1,'Right Finger','Beside Entrance','hilloftara',3,'Phone,Satellite Link,Whiteboard','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Hill of Allen','GLY','05',1,'Middle Finger','Turn Right, 15 meters, Meeting Room on Left','hillofallen',3,'Phone,Windows Conf,Whiteboard','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Cupidstown Hill ','DUB','05',1,'Left Finger','Turn Left at Watercooler','cupidstownhill',3,'Phone,Video Confrencing,Whiteboard','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Kildare St','DUB','05',2,'Right Finger','Straight Ahead','kildarest',4,'Phone,Satellite Link,Flip Charts','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('leeson St','DUB','05',2,'Middle Finger','Beside Canteen','leesonst',3,'Phone,Windows Conf,Flip Charts','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Merrion Sq','DUB','05',2,'Left Finger','Turn Right, 15 meters, Meeting Room on Left','merrionsq',10,'Phone,Video Confrencing,Flip Charts','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Thomas St','DUB','05',2,'Right Finger','Turn Left at Watercooler','thomasst',8,'Phone,Satellite Link,Flip Charts','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Pearse St','GLY','05',2,'Middle Finger','Straight Ahead','pearsest',3,'Phone,Windows Conf,Flip Charts','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Hardcourt St','DUB','05',2,'Left Finger','Beside Canteen','hardcourtst',4,'Phone,Video Confrencing,Flip Charts','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Temple Bar','GLY','05',2,'Right Finger','Beside Entrance','templebar',8,'Phone,Satellite Link,Flip Charts','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Dawson St','DUB','05',2,'Middle Finger','Turn Right, 15 meters, Meeting Room on Left','dawsonst',3,'Phone,Windows Conf,Flip Charts','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Moore St','DUB','05',2,'Left Finger','Turn Left at Watercooler','moorest',5,'Phone, VideoConfrencing,Whiteboard','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Dame St','DUB','05',3,'Right Finger','Straight Ahead','damest',4,'Phone,Windows Conf,Whiteboard','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('College green','GLY','05',3,'Middle Finger','Beside Canteen','collegegreen',3,'Phone,Video Confrencing,Whiteboard','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Stephens green','DUB','05',3,'Left Finger','Turn left at watercooler','stephensgreen',10,'Phone,Satellite Link, Audip','iconURL','Spare1','Spare2') ");
        db.execSQL(startInsert + "VALUES ('Grafton St','DUB','05',3,'Right Finger','Turn left at watercooler','graftonst',18,'Phone, VideoConfrencing, Audip','iconURL','Spare1','Spare2') ");

    }


    // Recreate database if for newer versions
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    db.execSQL("DROP TABLE IF EXISTS " + TableColumns.TABLE_NAME);
        onCreate(db);

    }

}
