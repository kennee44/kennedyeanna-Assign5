package com.example.eanna.roomlocator;

/**
 * Created by eanna on 15/03/2018.
 */

public class SingleCardItem {
    private int mImageBuildingNumber;
    private String mRoomName;
    private String mRoomSummaryDetails;

    public SingleCardItem(int imageBuildingNumber, String roomName, String roomSummaryDetails)
    {
        mImageBuildingNumber = imageBuildingNumber;
        mRoomName = roomName;
        mRoomSummaryDetails = roomSummaryDetails;
    }

    public  int getmImageBuildingNumber()
    {
        return  mImageBuildingNumber;
    }

    public String getmRoomName()
    {
        return mRoomName;
    }

    public String getGetmRoomSummaryDetails() {
        return mRoomSummaryDetails;
    }
}
