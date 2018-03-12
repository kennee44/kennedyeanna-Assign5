package com.example.eanna.roomlocatorapp;

import android.content.ContentValues;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText mEditTextName;
    private TextView mTextViewAmount;
    private int mAmount = 0;
    private SQLiteDatabase mDatabase;

    //member variable for our adpater
    private GroceryAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create database so we can add values to it
        GroceryDBHelper dbHelper = new GroceryDBHelper(this);
        mDatabase = dbHelper.getWritableDatabase();

        //assign our RV and adapter below wirting of db
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        //choose layout as Linear
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        // pass context and a cursor. We get this cursor by calling getAllItems
        //which queries all our Grocery Table
        mAdapter = new GroceryAdapter(this, getAllItems());

        recyclerView.setAdapter(mAdapter);



        mEditTextName = (EditText) findViewById(R.id.edittext_name);
        mTextViewAmount = (TextView) findViewById(R.id.textview_amount);

        //create button variables
        Button buttonIncrease = (Button) findViewById(R.id.button_increase);
        Button buttonDecrease = (Button) findViewById(R.id.button_decrease);
        Button buttonAdd = (Button) findViewById(R.id.button_add);

        //set onClickListeners for the buttons

        buttonIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                increase();
            }
        });

        buttonDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrease();
            }
        });

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addItem();
            }
        });



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

    private void increase() {
        mAmount++;
        mTextViewAmount.setText((String.valueOf(mAmount)));
    }

    private void decrease() {
        if(mAmount >0) {
        mAmount--;
        mTextViewAmount.setText((String.valueOf(mAmount)));}
    }

    private void addItem () {

        // does are edittext have any value and is amount greater than zero
        if(mEditTextName.getText().toString().trim().length() == 0 || mAmount == 0)
        {
            return;
        }

        String name = mEditTextName.getText().toString();
        //create content values to add records to the database
        //This is the connection between EditText and Column in database (Table name: groceryList)
        ContentValues cv = new ContentValues();
        //cv.put(String key, String value)

        cv.put(GroceryContract.GroceryEntry.COLUMN_NAME, name);
        cv.put(GroceryContract.GroceryEntry.COLUMN_AMOUNT, mAmount);

        mDatabase.insert(GroceryContract.GroceryEntry.TABLE_NAME, null,cv);
        // last thing to do. Take our adapter and swap the cursor
        mAdapter.swapCursor(getAllItems());

        mEditTextName.getText().clear();
    }

    // Need a method to return a cursor because we have to pass a cursor to our Adapter
    // We will assign our Recycler View and Adapter in the Main method

    //We have a private method of type Cursor
    //THis will get all the items our of our Grocery Table ordered by TImestamp
    //Newest item is at the top
    private Cursor getAllItems()
    {
        return mDatabase.query
        (  // Table, columns, selection, selectionARgs, groupBy, having, orderBy
           GroceryContract.GroceryEntry.TABLE_NAME,
                //these are all filters we don't need
                null, null, null, null, null,
            GroceryContract.GroceryEntry.COLUMN_TIMESTAMP + " DESC"
            //Close the method with a semicolon
        );
    }


}
