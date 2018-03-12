package com.example.eanna.roomlocatorapp;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by eanna on 11/03/2018.
 */

public class GroceryAdapter extends RecyclerView.Adapter <GroceryAdapter.GroceryViewHolder>
{

    private Context mContext;
    private Cursor mCursor;

    public GroceryAdapter (Context context, Cursor cursor)
        {
        mContext = context;
        mCursor = cursor;
        }

    public class GroceryViewHolder extends RecyclerView.ViewHolder
    {
        public TextView nameText;
        public TextView countText;

        public GroceryViewHolder(View itemView)
        {
            super(itemView);

            nameText = (TextView) itemView.findViewById(R.id.textview_name_item);
            countText = (TextView) itemView.findViewById(R.id.textview_amount);

        }
    }

    @Override
    //This is one of three methods called with the Recycler view.
    //
    public GroceryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating grocery_item (xml)
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.grocery_item, parent,false);
        return new GroceryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GroceryViewHolder holder, int position) {
        // Data is displayed in our item

        if (!mCursor.moveToPosition(position))
        {
            // does the position exist =>  return to this method
            return;
        }
        // Use the cursor variable to get this values out of db
        String name = mCursor.getString(mCursor.getColumnIndex(GroceryContract.GroceryEntry.COLUMN_NAME));
        int amount = mCursor.getInt(mCursor.getColumnIndex(GroceryContract.GroceryEntry.COLUMN_AMOUNT));

        // holder is the name of the text which is the name of the TextView that displays the name of the item
        holder.nameText.setText(name);
        // our countText is an int so we have to use valueOf
        holder.countText.setText(String.valueOf(amount));
    }

    @Override
    public int getItemCount() {
        // return as many items as we have in our database.  We get a count.
        return mCursor.getCount();
    }

    // When we create this adapter (the whole adapter) we will pass a cursor to the adapter.
    // Every time we want to update our db we need to pass a new cursor
    // We need another method



    public void swapCursor(Cursor newCursor)
            // it will take a Cursor called newCursor
    {
        // if mCursor the one we currently have is not null
        if (mCursor != null)
        {
            //close cursor (i.e get rid of it)
            mCursor.close();
        }

        mCursor = newCursor;
        if (newCursor != null)
        {
            // need to update our Recycler view so we call.  ADapter is now ready
            notifyDataSetChanged();
        }

    }
}
