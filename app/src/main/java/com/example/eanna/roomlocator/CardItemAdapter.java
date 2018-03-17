package com.example.eanna.roomlocator;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by eanna on 15/03/2018.
 */

public class CardItemAdapter extends RecyclerView.Adapter<CardItemAdapter.CardItemViewHolder> {
    // Create a variable which contains the cardList
    private ArrayList<SingleCardItem> mCardList;
    //This activity mListener will be our main activity
    private OnItemClickListener mListener;
    //Used for onClick event for each card
    //Create an interface
    //Unlike a listView we don't have any inbuilt onCLickListener


    //It takes one arguement postion which will tell us which card was clicked
    public interface OnItemClickListener
    {
        void onItemCLick(int position);
    }

        //Creat a mehtod for the SetON.  We will call this method in the Main Activity
    public void setOnItemClickListener(OnItemClickListener listener)
    {
        ///save it in the member variable
        mListener = listener;
    }




    public static class CardItemViewHolder extends RecyclerView.ViewHolder
     {
        // Need to assign values to the image and textViews in the cardview_item.xml
        public ImageView mImageView;
        public TextView mTextViewRoomName;
        public TextView mTextViewRoomSummaryDetails;


         // This is the constructor for our CardItemViewHolder
         //  THis is how to deal with the click on our cards
         // Set an onClickListener on the itemView
        public CardItemViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            // Need to provide references to our views which we have done here
            mImageView = (ImageView) itemView.findViewById(R.id.imageViewBuildingNumber);
            mTextViewRoomName = (TextView) itemView.findViewById(R.id.textViewRoomName);
            mTextViewRoomSummaryDetails = (TextView) itemView.findViewById(R.id.textViewRoomSummaryDetails);

            // we click on this itemView (card) we get our RecyclerView Adapter position
            // and pass it to the interface method OnItemClickListener
            // then we get the position from our Adapter to our Main Activity
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                //onClick method.  When we click the itemView which is our single card
                //we want to call the onItemClick (int position) method on our mListener
                //we pass this click to our mainActivity
                // CardItemViewHolder is a static class so we can't access our mListener
                //CardItemViewHolder is an inner class so it's best to keep it static
                //We therefore pass this listener in the constructor of he CardItemViewHolder
                // Once we put the mListener into the constructor we can pass this listener to our ViewHolder
                // and we can now access it in our onClick method
                public void onClick(View v) {
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


    //  Need the data out of arr list inot the adapter.  Pass it in the consturctor of the Adapter
    // THis is the constructor of the adapter CardItemAdapter
    // taking single item and creating a list

    public CardItemAdapter (ArrayList<SingleCardItem> cardList)
    {
        mCardList = cardList;
    }
    @Override
    public CardItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Pass the layout of our cardview_item.xml to the Adapter
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_item, parent,false);
        //Create a viewHolder with the information from the variable v created above. vh stands for vh
       //When creating the View Holder we added a second arguement from above called mListener
        CardItemViewHolder vh = new CardItemViewHolder(v, mListener);
        return vh;
        //now we have our ViewHolder vh
    }

    @Override
    public void onBindViewHolder(CardItemViewHolder holder, int position) {
        // Need to pass values to the views (image and TextViews) in the Card
        // Get the data from the ArrayList.  This is our Model
        // pass the information from the cardList into the viewHolder

        // Create an isntance of our SingleCardItem
        //In the get we put in the position
        SingleCardItem currentCard = mCardList.get(position);

        // on this item we can get the getter methods fof the position
        //the image is saved in our CurrentCard

        holder.mImageView.setImageResource(currentCard.getmImageBuildingNumber());
        holder.mTextViewRoomName.setText(currentCard.getmRoomName());
        holder.mTextViewRoomSummaryDetails.setText(currentCard.getGetmRoomSummaryDetails());
    }

    @Override
    public int getItemCount() {
        return mCardList.size();
    }
}
