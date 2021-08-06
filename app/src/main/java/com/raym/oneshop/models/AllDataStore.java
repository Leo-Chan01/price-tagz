package com.raym.oneshop.models;

import java.util.ArrayList;
import java.util.List;

class AllDataStore {

    private static AllDataStore ourInstance = null;


    public static AllDataStore getInstance() {
        if(ourInstance == null) {
            ourInstance = new AllDataStore();
        }
        return ourInstance;
    }


    private List<StockInfo> mStockInfos = new ArrayList<>();
    public StockInfo mStockInfo;


    private AllDataStore(){
    }

    public int getCurrentStockPosition(List<StockInfo> stockInfos){
        return stockInfos.size()-1;
    }

    public List<StockInfo> getStockInfos() {
        return mStockInfos;
    }

    public void setStockInfos(List<StockInfo> stockInfos) {
        mStockInfos.addAll(stockInfos);
    }
}
