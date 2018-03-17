package com.example.eanna.roomlocator;

import android.content.Context;
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
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    // Declare three variables that we need to make the Recycler View Work
    // RecyclerView + RecyclerView Adapter + Layout Manager
    // In our example it will create a ListView

    //Create a member variable so we can access it ouside our oncreate method
    private ArrayList<SingleCardItem> mRoomListArray;
    private RecyclerView mRecyclerView;
    // Need to change to be more specific.
    // Only way to access our custom methods
    private CardItemAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManger;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Create two methods and move the Array and build of the RecyclerView outside the main method
        createRoomListArray();
        buildRecyclerView();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void createRoomListArray()
    {
        mRoomListArray = new ArrayList<>();
        mRoomListArray.add(new SingleCardItem(R.drawable.ic_android, "Line 1", "Line2"));
        mRoomListArray.add(new SingleCardItem(R.drawable.ic_audio, "Line 3", "Line4"));
        mRoomListArray.add(new SingleCardItem(R.drawable.ic_sun, "Line 5", "Line6"));

    }

    public void  buildRecyclerView()
    {
        //create a mthod to build the RecyclerView
        //initialize RecyclerView
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //THe number of items in our RecyclerView xml will not change in size no - increase performance
        mRecyclerView.setHasFixedSize(true);
        mLayoutManger = new LinearLayoutManager(this);
        // pass an array list of roomList items because we set this in our constructor for the CardItemAdapter

        //This array list (roomlistArray gets passed to the Adapter,
        mAdapter = new CardItemAdapter(mRoomListArray);

        mRecyclerView.setLayoutManager(mLayoutManger);
        mRecyclerView.setAdapter(mAdapter);

        //  THis is how we handle the clicks on our items
        mAdapter.setOnItemClickListener(new CardItemAdapter.OnItemClickListener() {
            @Override
            public void onItemCLick(int position)
            {
                mRoomListArray.get(position);
                Context context = getApplicationContext();
                CharSequence text = "Success At last";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
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
}
