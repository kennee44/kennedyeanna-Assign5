package com.example.eanna.roomlocatorapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

/**
 * This app is a Room Locator app.  It's primary function is to give the location of a room.
 *
 *
 * <p>
 * The app is to help employees find the location of a room using it's name.
 * It offers information about the room, amenities it offers and it's capacity.
 * A short video will give you a feel for it's size and dimensions.
 * </p>
 *
 * Citation: Class contains code adapted from URL:
 * <a href="https://www.youtube.com/watch?v=5ISNPFmuOU8&list=PLrnPJCHvNZuBMJmll0xy2L2McYInT3aiu">
 *     YouTube: SQLite + RecyclerView Tutorial - Android Programming</a> (2018-03-12)
 *
 * * Class that contains
 *<p> Intent openCamera
 * @author Eanna Kennedy
 * @version 256
 * @param MediaStore ACTION_IMAGE_CAPTURE
 * @param File Environment.getExternalStorageDirectory(), filename
 * @param MediaStore EXTRA_OUTPUT</p>
 *
 */

public class MainActivity extends AppCompatActivity {

    private SQLiteDatabase mDatabase;

    //member variable for our adpater
    private RVAdapter mRVAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        // Create database using the DBHelperClass to create database
        final DBHelperClass dbHelper = new DBHelperClass(this);
        mDatabase = dbHelper.getWritableDatabase();

        //Assign our RecyclerView using Linear format
        RecyclerView recyclerViewRooms = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerViewRooms.setLayoutManager(new LinearLayoutManager(this));
        

        // Set adapter to recyclerViewRooms.  The content is from a cursor by calling getAllItems method
         
        mRVAdapter = new RVAdapter(this, getAllItems());
        recyclerViewRooms.setAdapter(mRVAdapter);


        //Set OnClickListener to the adapter. Takes position as parameter.

        mRVAdapter.setOnItemClickListener(new RVAdapter.OnItemClickListener() {
            @Override
            public void onItemCLick(int position) {
                // Intent to send position of Adapter to Room Details Activity using mykey2
                Intent intent = new Intent(MainActivity.this, RoomDetails.class);
                // Cursor cursor = (Cursor) adapter.getItem(position);
                // intent.putExtra("mykey", SQLTableStructure.TableColumns.COLUMN_FLOOR);
                intent.putExtra("mykey2", position);
                startActivity(intent);
            }
        });

        //
       // mRVAdapter.swapCursor(getAllItems());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }




    /**
     * This getAllItems() method of type Cursor returns an SQLiteCursor for an Android SQLite database query.
     * <p> Returns all the entries from our roomList Table in the DBHelperClass.
     * We will pass this to our RecyclerView Adapter
     * We will assign our Recycler View and Adapter in the Main method</p>
     *  <p>
     *  @return mDatabase.query
     *
     * </p>
     *
     **/

    private Cursor getAllItems()
    {
        return mDatabase.query
                (  // Table, columns, selection, selectionARgs, groupBy, having, orderBy
                        SQLTableStructure.TableColumns.TABLE_NAME,
                        //these are all filters we don't need
                        null, null, null, null, null,
                        SQLTableStructure.TableColumns.COLUMN_NAME + " ASC"
                        //Close the method with a semicolon
                );
    }

}
