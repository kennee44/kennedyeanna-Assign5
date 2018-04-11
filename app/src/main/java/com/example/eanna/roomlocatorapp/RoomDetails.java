package com.example.eanna.roomlocatorapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import java.util.ArrayList;

/**
 * Created by eanna on 19/03/2018.
 */

public class RoomDetails extends AppCompatActivity {
    private SQLiteDatabase mDatabase;
    VideoView mVideoView = null;
    String mVideoURI = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_details);

        // int data = getIntent().getExtras().getInt("mykey2");

        TextView name = (TextView) findViewById(R.id.name);
        TextView city = (TextView) findViewById(R.id.city);
        TextView building = (TextView) findViewById(R.id.building);
        TextView floor = (TextView) findViewById(R.id.floor);
        TextView floorlocation = (TextView) findViewById(R.id.floorlocation);
        TextView directions = (TextView) findViewById(R.id.directions);
        TextView imageURL = (TextView) findViewById(R.id.imageURL);
        TextView capacity = (TextView) findViewById(R.id.capacity);
        TextView amenities = (TextView) findViewById(R.id.amenities);
        TextView icon = (TextView) findViewById(R.id.iconURL);


       final DBHelperClass dbHelper = new DBHelperClass(this);


        mDatabase = dbHelper.getWritableDatabase();

        name.setText(myArray().get(0));
        city.setText(myArray().get(1));
        building.setText(myArray().get(2));
        floor.setText(myArray().get(3));
        floorlocation.setText(myArray().get(4));
        directions.setText(myArray().get(5));
        imageURL.setText(myArray().get(6));
        capacity.setText(myArray().get(7));
        amenities.setText(myArray().get(8));
        icon.setText(myArray().get(8));


        mVideoView = (VideoView) findViewById(R.id.videoViewer);
        mVideoURI = "android.resource://com.example.eanna.roomlocatorapp/raw/" + myArray().get(6);

        //The method Uri.parse creates a new Uri object from a properly formated String.
        mVideoView.setVideoURI(Uri.parse(mVideoURI));

        mVideoView.setKeepScreenOn(true);
        mVideoView.start();

        final MediaController mediaController = new MediaController(this,true);
        mediaController.setEnabled(false);

        mVideoView.setMediaController(mediaController);
        mVideoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaController.setEnabled(true);
            }
        });


        CharSequence text = "My Adapter position using indexOf is";
        Context context = getApplicationContext();


        // myArray().indexOf("Apple");


        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }

    // Clean up and release resources
    @Override
    protected void onPause() {

        if (mVideoView != null && mVideoView.isPlaying()) {
            mVideoView.stopPlayback();
            mVideoView = null;
        }
        super.onPause();
    }



    public ArrayList<String> myArray() {

        //mykey2 is the position on the adapter

        int data = getIntent().getExtras().getInt("mykey2") + 1;

      //  Cursor cursor = mDatabase.rawQuery("SELECT * FROM " + SQLTableStructure.TableColumns.TABLE_NAME + " WHERE id =" + data + " ORDER BY name ASC", null);
        //+ SQLTableStructure.TableColumns.COLUMN_NAME  + "\""

      //  ORIGINAL QUERY Cursor cursor = mDatabase.rawQuery("SELECT * FROM grocerylist ORDER BY name ASC ", null);
        Cursor cursor = mDatabase.query
                (  // Table, columns, selection, selectionARgs, groupBy, having, orderBy
                        SQLTableStructure.TableColumns.TABLE_NAME,
                //these are all filters we don't need
                null, null, null, null, null,
                SQLTableStructure.TableColumns.COLUMN_NAME + " ASC"
                //Close the method with a semicolon
        );



        // Move the cursor to position on SQLite database and put the row into an array

        cursor.moveToPosition(data -1);

        ArrayList<String> room = new ArrayList<String>();

        while (!cursor.isAfterLast()) {
            room.add(cursor.getString(cursor.getColumnIndex("name")));
            room.add(cursor.getString(cursor.getColumnIndex("city")));
            room.add(cursor.getString(cursor.getColumnIndex("building")));
            room.add(cursor.getString(cursor.getColumnIndex("floor")));
            room.add(cursor.getString(cursor.getColumnIndex("floorlocation")));
            room.add(cursor.getString(cursor.getColumnIndex("directions")));
            room.add(cursor.getString(cursor.getColumnIndex("imageURL")));
            room.add(cursor.getString(cursor.getColumnIndex("capacity")));
            room.add(cursor.getString(cursor.getColumnIndex("amenities")));
            room.add(cursor.getString(cursor.getColumnIndex("icon")));
            room.add(cursor.getString(cursor.getColumnIndex("spare1")));
            room.add(cursor.getString(cursor.getColumnIndex("spare2")));

            cursor.moveToNext();
        }
        cursor.close();

        return room;

    }

}


