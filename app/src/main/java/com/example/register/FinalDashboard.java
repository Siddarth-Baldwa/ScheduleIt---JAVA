package com.example.register;

import android.os.Bundle;
import android.view.WindowManager;
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

import static com.example.register.R.drawable.icons8_accuracy_96;
import static com.example.register.R.drawable.icons8_add_96;
import static com.example.register.R.drawable.icons8_budget_96;
import static com.example.register.R.drawable.icons8_buying_96;
import static com.example.register.R.drawable.icons8_coins_96;
import static com.example.register.R.drawable.icons8_document_48;
import static com.example.register.R.drawable.icons8_feedback_96;
import static com.example.register.R.drawable.icons8_inspection_96;
import static com.example.register.R.drawable.icons8_kitchenwares_96;
import static com.example.register.R.drawable.icons8_meeting_room_96;
import static com.example.register.R.drawable.icons8_paid_96;
import static com.example.register.R.drawable.icons8_tasklist_48;
import static com.example.register.R.drawable.icons8_thursday_48;

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
        try
        {
            this.getSupportActionBar().hide();
        }
        catch (NullPointerException e){}
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        discountRecyclerView = findViewById(R.id.discountedRecycler);
        categoryRecyclerView = findViewById(R.id.categoryRecycler);
        /*allCategory = findViewById(R.id.allCategoryImage);*/
        recentlyViewedRecycler = findViewById(R.id.recently_item);


        /*allCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(FinalDashboard.this, AllCategory.class);
                startActivity(i);
            }*/
        /*});*/

        // adding data to model
        workModelList = new ArrayList<>();
        workModelList.add(new WorkModel("Meetings",0 ,icons8_meeting_room_96));
        workModelList.add(new WorkModel("Mails",1, icons8_feedback_96));
        workModelList.add(new WorkModel("Project Goals",2, icons8_accuracy_96));
        workModelList.add(new WorkModel("Points to Remember",3, icons8_inspection_96));
        workModelList.add(new WorkModel("Others",4, icons8_add_96));

        // adding data to model
        dailyExpensesModelList = new ArrayList<>();
        dailyExpensesModelList.add(new DailyExpensesModel("Daily Expenses",0, icons8_coins_96));
        dailyExpensesModelList.add(new DailyExpensesModel("Purchases",1, icons8_buying_96));
        dailyExpensesModelList.add(new DailyExpensesModel("Maintenance",2, icons8_kitchenwares_96));
        dailyExpensesModelList.add(new DailyExpensesModel("Payments Due",3, icons8_budget_96));
        dailyExpensesModelList.add(new DailyExpensesModel("Others",4, icons8_paid_96));

        // adding data to model
      /*  recentlyViewedList = new ArrayList<>();
        recentlyViewedList.add(new RecentlyViewed("Watermelon", "Watermelon has high water content and also provides some fiber.", "₹ 80", "1", "KG", card4, b4));
        recentlyViewedList.add(new RecentlyViewed("Papaya", "Papayas are spherical or pear-shaped fruits that can be as long as 20 inches.", "₹ 85", "1", "KG", card3, b3));
        recentlyViewedList.add(new RecentlyViewed("Strawberry", "The strawberry is a highly nutritious fruit, loaded with vitamin C.", "₹ 30", "1", "KG", card2, b1));
        recentlyViewedList.add(new RecentlyViewed("Kiwi", "Full of nutrients like vitamin C, vitamin K, vitamin E, folate, and potassium.", "₹ 30", "1", "PC", card1, b2));
   */
        recentlyViewedList = new ArrayList<>();
        recentlyViewedList.add(new RecentlyViewed("Tasks",icons8_tasklist_48,0));
        recentlyViewedList.add(new RecentlyViewed("Documents",icons8_document_48,1));
        recentlyViewedList.add(new RecentlyViewed("Calender",icons8_thursday_48,2));

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
