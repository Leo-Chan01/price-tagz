package com.raym.oneshop;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//This class contains the code to use an adapter with a recycler view to show the
//info added
public class DealDisplayAdapter  extends RecyclerView.Adapter<DealDisplayAdapter.DealDisplayViewHolder> {

//    private final StockInfo mStockInfo;
    private List<StockInfo> mStockInfoList;
    private int mCurrentPosition;

    public DealDisplayAdapter(List<StockInfo> stockInfoList) {
        mStockInfoList = stockInfoList;
//        mCurrentPosition = position;
    }
    @NonNull
    @Override
    public DealDisplayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View itemView = LayoutInflater.from(context).inflate(R.layout.deal_list_item, parent,false);
        return new DealDisplayViewHolder(itemView);
    }

    // TODO: 11/25/2020 the latest changes made
    @Override
    public void onBindViewHolder(@NonNull DealDisplayViewHolder holder, int position) {
        Log.d("err", "an error occurred");
        holder.bind(mStockInfoList, position);
//        getItemPosition();
    }


    @Override
    public int getItemCount() {
        return mStockInfoList.size();
    }

    //this is the viewHolder class, it works with the recycler view to hold the views and cache them
    public static class DealDisplayViewHolder extends RecyclerView.ViewHolder{
        //we use this variable to find the view in our code that was created in the layout file
        //in this case, the view in our list item is a TextView so we would find the view first,
        //then we would  bind the data to the text view from the list Item which is deal_item
        TextView dealNameDisplay;
        TextView dealPriceDisplay;
        TextView dealDescription;
        ImageView dealImage;
        ImageView dealSold;
        ImageView dealShare;

        public DealDisplayViewHolder(@NonNull View itemView) {
            super(itemView);
            dealNameDisplay = itemView.findViewById(R.id.deal_item_name);
            dealPriceDisplay = itemView.findViewById(R.id.deal_item_price);
            dealDescription = itemView.findViewById(R.id.deal_item_description);
            dealImage = itemView.findViewById(R.id.deal_item_image);
            dealSold = itemView.findViewById(R.id.deal_item_sold_button);
            dealShare = itemView.findViewById(R.id.deal_item_share_button);
        }

        //the method to bind the data
        public void bind(List<StockInfo> stockInfos, int currentPosition) {
            //TODO: the latest changes made: used get(currentPosition) to get the current stock to
            // display
            StockInfo stock = stockInfos.get(currentPosition);
            dealNameDisplay.setText(stock.getName());
            dealPriceDisplay.setText(stock.getPrice());
            dealDescription.setText(stock.getDescription());
        }
    }
}
