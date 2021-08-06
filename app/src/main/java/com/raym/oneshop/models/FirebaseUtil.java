package com.raym.oneshop.models;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class FirebaseUtil {
    public static FirebaseDatabase mFirebaseDatabase;
    public static DatabaseReference mDatabaseReference;
    public static ArrayList<StockInfo> sStockInfos;
    private static FirebaseUtil mFirebaseUtil;

    private FirebaseUtil(){}

    public static void firebaseRef (String reference){
        if (mFirebaseUtil == null){
            mFirebaseUtil = new FirebaseUtil();
            sStockInfos = new ArrayList<StockInfo>();
            mFirebaseDatabase = FirebaseDatabase.getInstance();
        }
        mDatabaseReference = mFirebaseDatabase.getReference().child(reference);
    }
}
