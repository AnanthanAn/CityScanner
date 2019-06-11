package com.example.cityscanner.POJO;

public class NearbyItem {

    //private  int mImageResource;
    private  String mShopName;
    private  String mShopAddress;
    private  String mShopphone;


    public NearbyItem(String ShopName, String ShopAdress,String PhoneNo) {
        //mImageResource = mImageResource;
        mShopName = ShopName;
        mShopAddress = ShopAdress;
        mShopphone = PhoneNo;
    }

    public String getmShopAddress() {
        return mShopAddress;
    }

    public String getmShopphone() {
        return mShopphone;
    }

    public String getmShopName() {
        return mShopName;
    }


}
