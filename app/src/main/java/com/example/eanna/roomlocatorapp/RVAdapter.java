package com.example.eanna.roomlocatorapp;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * This class RVAdapter is the Recycler Adapter .
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



public class RVAdapter extends RecyclerView.Adapter <RVAdapter.ViewHolder>
{
    private OnItemClickListener mListener;


    //It takes one arguement postion which will tell us which card was clicked
    public interface OnItemClickListener
    {
        void onItemCLick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener)
    {
        mListener = listener;
    }
//CardItemAdapter extends RecyclerView.Adapter<CardItemAdapter.CardItemViewHolder>
    private Context mContext;
    private Cursor mCursor;

    //
    public RVAdapter(Context context, Cursor cursor)
        {
        mContext = context;
        mCursor = cursor;
        }

    public class ViewHolder extends RecyclerView.ViewHolder

        //Create variables for our views within the ViewHolder
    {

        public TextView roomnameText;
        public TextView floorText;
        public TextView buildingText;
        public TextView cityText;

        public ViewHolder(View itemView, final OnItemClickListener listener)
        {
            super(itemView);
            //How do we find the views for our ViewHolder
            buildingText = (TextView) itemView.findViewById(R.id.textview_building);
            roomnameText = (TextView) itemView.findViewById(R.id.textview_roomname_item);
            floorText = (TextView) itemView.findViewById(R.id.textview_floor);
            cityText = (TextView) itemView.findViewById(R.id.textView_city);
            //set an onClickListener on the itemView in (View itemView)
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    //it makes this listener variable final so we can access it here
                    if(listener != null )
                    // we need our position
                    {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION)
                        {
                            listener.onItemCLick(position);
                        }
                    }
                }
            });
        }

    }

    @Override
    //This is one of three methods called with the Recycler view.
    //
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // inflating recycler_item (xml). Pass the layout of our cards to the onCreateViewHolder
        //mContext same as parent.getContext()
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.recycler_item, parent,false);
        //Now we can create a Viewholder with the above settings
        // We pass the listener to the ViewHolder
        return new ViewHolder(view, mListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Data is displayed in our item

        if (!mCursor.moveToPosition(position))
        {
            // does the position exist =>  return to this method
            return;
        }
        // Use the cursor variable to get this values out of db
        String name = mCursor.getString(mCursor.getColumnIndex(SQLTableStructure.TableColumns.COLUMN_NAME));
        int floor = mCursor.getInt(mCursor.getColumnIndex(SQLTableStructure.TableColumns.COLUMN_FLOOR));
        String city = mCursor.getString(mCursor.getColumnIndex(SQLTableStructure.TableColumns.COLUMN_CITY));
        String building = mCursor.getString(mCursor.getColumnIndex(SQLTableStructure.TableColumns.COLUMN_BUILDING));

        // holder is the name of the text which is the name of the TextView that displays the name of the item
        holder.roomnameText.setText(name);
        // our floorText is an int so we have to use valueOf
        holder.floorText.setText(String.valueOf(floor));
        // our Building is an int so we have to use valueOf
        holder.buildingText.setText(building);
        // our Building is an int so we have to use valueOf
        holder.cityText.setText(city);
    }

    @Override
    public int getItemCount() {
        // return as many items as we have in our database.  We get a count.
        return mCursor.getCount();
    }


    // When we create this adapter (the whole adapter) we will pass a cursor to the adapter.
    // Every time we want to update our db we need to pass a new cursor
    // We need another method

 /*   public void swapCursor(Cursor newCursor)
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
*/

}
