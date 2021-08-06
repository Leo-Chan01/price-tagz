package com.raym.oneshop;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class EditAddActivity extends AppCompatActivity {

    public List<StockInfo> mStockInfos = new ArrayList<>();
    public final String PREVIOUS_STOCK_INFOS = "com.example.oneshop.PREVIOUS_STOCK_INFOS";
    public List<StockInfo> mFreshStockInfoList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        TextView editUserHint = findViewById(R.id.text_editdealuser_note);
        CardView cardViewUserNote = findViewById(R.id.cardview_user_note);
        CardView cardFeedback = findViewById(R.id.card_feedback);
        final EditText editTextDealName = findViewById(R.id.editText_deal_name);
        final EditText editTextDealPrice = findViewById(R.id.editText_deal_price);
        final EditText editTextDealDescription = findViewById(R.id.editText_deal_description);
        ImageButton imageButtonAddImage = findViewById(R.id.imageButton_addimage);
        FloatingActionButton fabAddIt = findViewById(R.id.fab_addit);
        FloatingActionButton fabDone = findViewById(R.id.fab_done);
        final TextView textFeedback = findViewById(R.id.text_feedback);

        fabAddIt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //get the string values on click

                String nameData = editTextDealName.getText().toString();
                String priceData = editTextDealPrice.getText().toString();
                String descriptionData = editTextDealDescription.getText().toString();

                editTextDealName.setText(null);
                editTextDealPrice.setText(null);
                editTextDealDescription.setText(null);

                editTextDealName.setFocusable(true);

                //get a new instance of the stockInfo Class
                StockInfo stockInfo = new StockInfo(nameData, descriptionData, priceData);

                mStockInfos.add(stockInfo);

                int currentNumber = mStockInfos.size();
                int lastIndex = mStockInfos.size() - 1;

                if (editTextDealName == null) {
                    mStockInfos.remove(lastIndex);
                }

                String feedBackText = Integer.toString(currentNumber);
                if (currentNumber == 1) {
                    textFeedback.setText(new StringBuilder().append(feedBackText).append(" ").append(getString(R.string.added_deal_feedback)).toString());
                } else if (currentNumber > 1) {
                    textFeedback.setText(new StringBuilder().append(feedBackText).append(" ").append(getString(R.string.added_deals_feedback)).toString());
                }
            }
        });


        fabDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //then start the intent call
                AllDataStore.getInstance().setStockInfos(mStockInfos);
                mFreshStockInfoList = AllDataStore.getInstance().getStockInfos();
                Intent submitDealIntent = new Intent(EditAddActivity.this, DealDisplayActivity.class);
                submitDealIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                submitDealIntent.putParcelableArrayListExtra(Intent.EXTRA_TEXT, (ArrayList<? extends Parcelable>) mFreshStockInfoList);
                //start the activity
                startActivity(submitDealIntent);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelableArrayList(PREVIOUS_STOCK_INFOS, (ArrayList<? extends Parcelable>) mStockInfos);
    }
}