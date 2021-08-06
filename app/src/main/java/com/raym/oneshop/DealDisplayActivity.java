package com.raym.oneshop;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class DealDisplayActivity extends AppCompatActivity {

    private static final  List<StockInfo> PREVIOUS_STOCK_DATASET = new ArrayList<>();
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mDatabaseReference;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView.Adapter mAdapter;
    private StockInfo mStock;
    private List<StockInfo> mStocks = new ArrayList<>();
    private List<StockInfo> mOriginalStockInfoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal);

        final FloatingActionButton addDeal = findViewById(R.id.add_deal);
        final FloatingActionButton fabChat = findViewById(R.id.fab_chat);
        final TextView textNotice = findViewById(R.id.deal_text_notice);
        final ImageView nothingHereIcon = findViewById(R.id.image_nothing_here);

        mRecyclerView = findViewById(R.id.rv_deal_display);
        mRecyclerView.setHasFixedSize(true);

        if(mAdapter == null){
            mOriginalStockInfoList = AllDataStore.getInstance().getStockInfos();
            textNotice.setVisibility(View.VISIBLE);
            nothingHereIcon.setVisibility(View.VISIBLE);
        }

        Intent submitDealIntent = getIntent();

        //to check if this intent has the extra data needed
        if (submitDealIntent.hasExtra(Intent.EXTRA_TEXT)){
            mStocks = submitDealIntent.getParcelableArrayListExtra(Intent.EXTRA_TEXT);

//            mOriginalStockInfoList.addAll(mStocks);

            mAdapter = new DealDisplayAdapter(mStocks);
            if(mAdapter == null){
                textNotice.setVisibility(View.VISIBLE);
                nothingHereIcon.setVisibility(View.VISIBLE);
            }else{
                textNotice.setVisibility(View.INVISIBLE);
                nothingHereIcon.setVisibility(View.INVISIBLE);
                Toast.makeText(this , "Operation Success", Toast.LENGTH_LONG).show();
            }

            mRecyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
        }
        mLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //intent to move to the edit and add activity
        addDeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editActivityIntent = new Intent(DealDisplayActivity.this, EditAddActivity.class);
                startActivity(editActivityIntent);
            }
        });

        //this is to get the reference to the firebase database
        FirebaseUtil.firebaseRef("stock");
        mFirebaseDatabase = FirebaseUtil.mFirebaseDatabase;
        mDatabaseReference = FirebaseUtil.mDatabaseReference;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.onebig_shop_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_item_dashboard:
                Intent menuDashBoardIntent = new Intent(this, DashBoardActivity.class);
                startActivity(menuDashBoardIntent);
                return true;
            case R.id.shop_settings:
//                Toast.makeText(this, R.string.dummy_message_settings, Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}