package com.example.eanna.roomlocatorapp;

import android.content.Context;
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
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

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
 * </p>
 *
 */

public class MainActivity extends AppCompatActivity {


    private SQLiteDatabase mDatabase;

    //member variable for our adpater
    private RVAdapter mRVAdapter;
    RecyclerView recyclerViewRooms;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Create database using the DBHelperClass to create database
        final DBHelperClass dbHelper = new DBHelperClass(this);
        mDatabase = dbHelper.getWritableDatabase();

        //Create a refernce to our RecyclerView. Assign our RecyclerView using Linear format
        recyclerViewRooms = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerViewRooms.setLayoutManager(new LinearLayoutManager(this));

        // Set adapter to recyclerViewRooms.  The content is from a cursor by calling getAllItems method
        mRVAdapter = new RVAdapter(this, getAllItems());
        recyclerViewRooms.setAdapter(mRVAdapter);


       //Set OnClickListener to the adapter. Takes position as parameter.

        mRVAdapter.setOnItemClickListener(new RVAdapter.OnItemClickListener() {

            @Override
            public void onItemCLick(int position) {
                /* Intent to send position of Adapter to myArrayRoomDetails()
                 in Room Details Activity using key keySendToRoomDetails */
                Intent intent = new Intent(MainActivity.this, RoomDetails.class);
                intent.putExtra("keySendToRoomDetails", position);
                startActivity(intent);
            }
        });

        //Citation: https://www.youtube.com/watch?v=OWwOSLfWboY

        //Create a reference to our EditText for search
        EditText searchEditText = (EditText)(findViewById(R.id.editTextSearch));
        /* Add addTextChangedListener to searchEditText with a Text Watcher
        TextWatcher has three methods. We will use afterTextChanged (listen for changes) */

        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }

            //This textWatcher afterTextChanged uses the first Capital Letter entered to scroll RecyclerView
            @Override
            public void afterTextChanged(Editable searchEntry) {
                String stringSearchEntry = searchEntry.toString();
                int index =  firstLetterRoomArrayList().indexOf(stringSearchEntry);
                recyclerViewRooms.scrollToPosition(index);
           }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


    }


    /**
     * This getAllItems() method of type Cursor returns an SQLiteCursor for an Android SQLite database query.
     * <p> Returns all the entries from our roomList Table in the DBHelperClass.
     * We will pass this to the TextWatcher afterTextChanged() method.
     * We will assign our Recycler View and Adapter in the Main method</p>
     *  <p>
     *  @return mDatabase.query
     * </p>
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

    /**
     * This firstLetterRoomArrayList() method of type Array returns an Array of the First Letter of each Room
     * <p> We will pass this to TextWatcher.afterTextChanged() method </p>
     * <p> @return ArrayList roomNameArray.</p>
     **/
    public ArrayList<String> firstLetterRoomArrayList()
    {
       Cursor cursor = mDatabase.query
                (  // Table, columns, selection, selectionARgs, groupBy, having, orderBy
                        SQLTableStructure.TableColumns.TABLE_NAME,
                        //these are all filters we don't need
                        null, null, null, null, null,
                        SQLTableStructure.TableColumns.COLUMN_NAME + " ASC"
                        //Close the method with a semicolon
                );

        // Move the cursor to position on SQLite database and put the row into an array
        cursor.moveToPosition(0);


        ArrayList<String> roomNameArray = new ArrayList<String>();
        while (!cursor.isAfterLast()) {
           // get the First Letter from each entry in the roomNameArray
            roomNameArray.add(String.valueOf(cursor.getString(cursor.getColumnIndex("name")).charAt(0)));
            cursor.moveToNext();
        }
        cursor.close();
        return roomNameArray;
    }


}
