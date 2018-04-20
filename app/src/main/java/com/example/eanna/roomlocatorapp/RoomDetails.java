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
 * This class RoomDetails will give more details about the Office Room chosen .
 *
 *
 * <p>
 * It will show extra details on how to find the room, amenities offered and a Video
 * showing the inside of the room.  Currently we have mp4's of hardcourt st, pearse st,
 * templebar and thomas st.
 * </p>
 *
 * <p>
 * Note: The code is custom and not taken from any particular source but adaptations of
 * my understanding of cursors and ArrayLists in general.
 *
 * * Class that contains
 *</p>
 *
 */

public class RoomDetails extends AppCompatActivity {
    private SQLiteDatabase mDatabase;
    VideoView mVideoView = null;
    String mVideoURI = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        // get reference to the TextViews. Greyed out not used. Saved in case we need later
        TextView name = (TextView) findViewById(R.id.name);
        TextView city = (TextView) findViewById(R.id.city);
        TextView building = (TextView) findViewById(R.id.building);
        TextView floor = (TextView) findViewById(R.id.floor);
        TextView floorlocation = (TextView) findViewById(R.id.floorlocation);
        TextView directions = (TextView) findViewById(R.id.directions);
      //  TextView imageURL = (TextView) findViewById(R.id.imageURL);
        TextView capacity = (TextView) findViewById(R.id.capacity);
        TextView amenities = (TextView) findViewById(R.id.amenities);
      //  TextView icon = (TextView) findViewById(R.id.iconURL);


       final DBHelperClass dbHelper = new DBHelperClass(this);
       mDatabase = dbHelper.getWritableDatabase();

        name.setText(myArrayRoomDetails().get(0));
        city.setText(myArrayRoomDetails().get(1));
        building.setText(myArrayRoomDetails().get(2));
        floor.setText(myArrayRoomDetails().get(3));
        floorlocation.setText(myArrayRoomDetails().get(4));
        directions.setText(myArrayRoomDetails().get(5));
        //imageURL.setText(myArrayRoomDetails().get(6));
        capacity.setText(myArrayRoomDetails().get(7));
        amenities.setText(myArrayRoomDetails().get(8));
        //icon.setText(myArrayRoomDetails().get(8));

        // Get reference to videoViewer
        mVideoView = (VideoView) findViewById(R.id.videoViewer);
        mVideoURI = "android.resource://com.example.eanna.roomlocatorapp/raw/" + myArrayRoomDetails().get(6);

        //The method Uri.parse creates a new Uri object from a properly formatted String.
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



    public ArrayList<String> myArrayRoomDetails() {

        //keySendToRoomDetails is the position on the adapter
        int data = getIntent().getExtras().getInt("keySendToRoomDetails") + 1;

      /* Alternative Query not used
      Cursor cursor = mDatabase.rawQuery("SELECT * FROM " + SQLTableStructure.TableColumns.TABLE_NAME + " WHERE id =" + data + " ORDER BY name ASC", null);
      */
        // Cursor to retrieve all data from SQLite database and move the cursor to particular row
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

        cursor.close();

        return room;

    }

}


