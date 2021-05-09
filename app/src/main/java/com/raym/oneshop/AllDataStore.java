package com.raym.oneshop;

import java.util.ArrayList;
import java.util.List;

class AllDataStore {

    private static AllDataStore ourInstance = null;
    private final List<StockInfo> mStockInfos = new ArrayList<>();
    public StockInfo mStockInfo;

    private AllDataStore() {
    }

    public static AllDataStore getInstance() {
        if (ourInstance == null) {
            ourInstance = new AllDataStore();
        }
        return ourInstance;
    }

    public int getCurrentStockPosition(List<StockInfo> stockInfos) {
        return stockInfos.size() - 1;
    }

    public List<StockInfo> getStockInfos() {
        return mStockInfos;
    }

    public void setStockInfos(List<StockInfo> stockInfos) {
        mStockInfos.addAll(stockInfos);
    }
}
