package com.example.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.register.adapter.DailyExpensesAdapter;
import com.example.register.adapter.RecentlyViewedAdapter;
import com.example.register.adapter.WorkAdapter;
import com.example.register.model.DailyExpensesModel;
import com.example.register.model.RecentlyViewed;
import com.example.register.model.WorkModel;

import java.util.ArrayList;
import java.util.List;

import static com.example.register.R.drawable.b1;
import static com.example.register.R.drawable.b2;
import static com.example.register.R.drawable.b3;
import static com.example.register.R.drawable.b4;
import static com.example.register.R.drawable.card1;
import static com.example.register.R.drawable.card2;
import static com.example.register.R.drawable.card3;
import static com.example.register.R.drawable.card4;
import static com.example.register.R.drawable.icons8_add_96;
import static com.example.register.R.drawable.icons8_buying_96;
import static com.example.register.R.drawable.icons8_coins_96;
import static com.example.register.R.drawable.icons8_collaboration_96;
import static com.example.register.R.drawable.icons8_contact_us_96;
import static com.example.register.R.drawable.icons8_delivery_96;
import static com.example.register.R.drawable.icons8_discount_96;
import static com.example.register.R.drawable.icons8_feedback_96;
import static com.example.register.R.drawable.icons8_image_96;
import static com.example.register.R.drawable.icons8_inspection_96;
import static com.example.register.R.drawable.icons8_kitchenwares_96;
import static com.example.register.R.drawable.icons8_meeting_room_96;
import static com.example.register.R.drawable.icons8_paid_96;

public class FinalDashboard extends AppCompatActivity {

    RecyclerView discountRecyclerView, categoryRecyclerView, recentlyViewedRecycler;
    WorkAdapter workAdapter;
    List<WorkModel> workModelList;

    DailyExpensesAdapter dailyExpensesAdapter;
    List<DailyExpensesModel> dailyExpensesModelList;

    RecentlyViewedAdapter recentlyViewedAdapter;
    List<RecentlyViewed> recentlyViewedList;

    TextView allCategory;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        discountRecyclerView = findViewById(R.id.discountedRecycler);
        categoryRecyclerView = findViewById(R.id.categoryRecycler);
        allCategory = findViewById(R.id.allCategoryImage);
        recentlyViewedRecycler = findViewById(R.id.recently_item);


        allCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FinalDashboard.this, AllCategory.class);
                startActivity(i);
            }
        });

        // adding data to model
        workModelList = new ArrayList<>();
        workModelList.add(new WorkModel(1, icons8_collaboration_96));
        workModelList.add(new WorkModel(2, icons8_contact_us_96));
        workModelList.add(new WorkModel(3, icons8_feedback_96));
        workModelList.add(new WorkModel(4, icons8_meeting_room_96));
        workModelList.add(new WorkModel(5, icons8_inspection_96));
        workModelList.add(new WorkModel(6, icons8_add_96));

        // adding data to model
        dailyExpensesModelList = new ArrayList<>();
        dailyExpensesModelList.add(new DailyExpensesModel(1, icons8_coins_96));
        dailyExpensesModelList.add(new DailyExpensesModel(2, icons8_buying_96));
        dailyExpensesModelList.add(new DailyExpensesModel(3, icons8_kitchenwares_96));
        dailyExpensesModelList.add(new DailyExpensesModel(4, icons8_discount_96));
        dailyExpensesModelList.add(new DailyExpensesModel(5, icons8_paid_96));
        dailyExpensesModelList.add(new DailyExpensesModel(6, icons8_delivery_96));
        dailyExpensesModelList.add(new DailyExpensesModel(7, icons8_image_96));
        dailyExpensesModelList.add(new DailyExpensesModel(8, icons8_add_96));

        // adding data to model
        recentlyViewedList = new ArrayList<>();
        recentlyViewedList.add(new RecentlyViewed("Watermelon", "Watermelon has high water content and also provides some fiber.", "₹ 80", "1", "KG", card4, b4));
        recentlyViewedList.add(new RecentlyViewed("Papaya", "Papayas are spherical or pear-shaped fruits that can be as long as 20 inches.", "₹ 85", "1", "KG", card3, b3));
        recentlyViewedList.add(new RecentlyViewed("Strawberry", "The strawberry is a highly nutritious fruit, loaded with vitamin C.", "₹ 30", "1", "KG", card2, b1));
        recentlyViewedList.add(new RecentlyViewed("Kiwi", "Full of nutrients like vitamin C, vitamin K, vitamin E, folate, and potassium.", "₹ 30", "1", "PC", card1, b2));

        setDiscountedRecycler(workModelList);
        setCategoryRecycler(dailyExpensesModelList);
        setRecentlyViewedRecycler(recentlyViewedList);

    }

    private void setDiscountedRecycler(List<WorkModel> dataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        discountRecyclerView.setLayoutManager(layoutManager);
        workAdapter = new WorkAdapter(this,dataList);
        discountRecyclerView.setAdapter(workAdapter);
    }


    private void setCategoryRecycler(List<DailyExpensesModel> dailyExpensesModelDataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        categoryRecyclerView.setLayoutManager(layoutManager);
        dailyExpensesAdapter = new DailyExpensesAdapter(this, dailyExpensesModelDataList);
        categoryRecyclerView.setAdapter(dailyExpensesAdapter);
    }

    private void setRecentlyViewedRecycler(List<RecentlyViewed> recentlyViewedDataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        recentlyViewedRecycler.setLayoutManager(layoutManager);
        recentlyViewedAdapter = new RecentlyViewedAdapter(this,recentlyViewedDataList);
        recentlyViewedRecycler.setAdapter(recentlyViewedAdapter);
    }
    //Now again we need to create a adapter and model class for recently viewed items.
    // lets do it fast.
}
