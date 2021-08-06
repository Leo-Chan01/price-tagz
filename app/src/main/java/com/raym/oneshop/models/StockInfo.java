package com.raym.oneshop.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class StockInfo implements Parcelable {
    
    private String name;
    private String description;
    private String price;

    public StockInfo(String name, String description, String price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    protected StockInfo(Parcel in) {
        name = in.readString();
        description = in.readString();
        price = in.readString();
    }

    public static final Creator<StockInfo> CREATOR = new Creator<StockInfo>() {
        @Override
        public StockInfo createFromParcel(Parcel in) {
            return new StockInfo(in);
        }

        @Override
        public StockInfo[] newArray(int size) {
            return new StockInfo[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int newNotePosition(List<StockInfo> stockInfos){
        return stockInfos.size();
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(description);
        parcel.writeString(price);
    }
}
