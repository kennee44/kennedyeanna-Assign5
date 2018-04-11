package com.example.eanna.roomlocatorapp;

/**
 * Created by eanna on 15/03/2018.
 */

public class SingleCardItem {
    private String mAmount;
    private String mName;

    public SingleCardItem( String roomName, String roomSummaryDetails)
    {
        mAmount = roomName;
        mName = roomSummaryDetails;
    }

    public String getmName()
    {
        return mName;
    }

    public String getGetmAmount() {
        return mAmount;
    }
}
