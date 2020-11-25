package com.example.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.register.adapter.CategoryAdapter;
import com.example.register.adapter.DiscountedProductAdapter;
import com.example.register.adapter.RecentlyViewedAdapter;
import com.example.register.model.Category;
import com.example.register.model.DiscountedProducts;
import com.example.register.model.RecentlyViewed;

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
import static com.example.register.R.drawable.icons8_address_book_96;
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

public class MainActivity extends AppCompatActivity {

    RecyclerView discountRecyclerView, categoryRecyclerView, recentlyViewedRecycler;
    DiscountedProductAdapter discountedProductAdapter;
    List<DiscountedProducts> discountedProductsList;

    CategoryAdapter categoryAdapter;
    List<Category> categoryList;

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
                Intent i = new Intent(MainActivity.this, AllCategory.class);
                startActivity(i);
            }
        });

        // adding data to model
        discountedProductsList = new ArrayList<>();
        discountedProductsList.add(new DiscountedProducts(1, icons8_collaboration_96));
        discountedProductsList.add(new DiscountedProducts(2, icons8_contact_us_96));
        discountedProductsList.add(new DiscountedProducts(3, icons8_feedback_96));
        discountedProductsList.add(new DiscountedProducts(4, icons8_meeting_room_96));
        discountedProductsList.add(new DiscountedProducts(5, icons8_inspection_96));
        discountedProductsList.add(new DiscountedProducts(6, icons8_address_book_96));

        // adding data to model
        categoryList = new ArrayList<>();
        categoryList.add(new Category(1, icons8_coins_96));
        categoryList.add(new Category(2, icons8_buying_96));
        categoryList.add(new Category(3, icons8_kitchenwares_96));
        categoryList.add(new Category(4, icons8_discount_96));
        categoryList.add(new Category(5, icons8_paid_96));
        categoryList.add(new Category(6, icons8_delivery_96));
        categoryList.add(new Category(7, icons8_image_96));
        categoryList.add(new Category(8, icons8_add_96));

        // adding data to model
        recentlyViewedList = new ArrayList<>();
        recentlyViewedList.add(new RecentlyViewed("Watermelon", "Watermelon has high water content and also provides some fiber.", "₹ 80", "1", "KG", card4, b4));
        recentlyViewedList.add(new RecentlyViewed("Papaya", "Papayas are spherical or pear-shaped fruits that can be as long as 20 inches.", "₹ 85", "1", "KG", card3, b3));
        recentlyViewedList.add(new RecentlyViewed("Strawberry", "The strawberry is a highly nutritious fruit, loaded with vitamin C.", "₹ 30", "1", "KG", card2, b1));
        recentlyViewedList.add(new RecentlyViewed("Kiwi", "Full of nutrients like vitamin C, vitamin K, vitamin E, folate, and potassium.", "₹ 30", "1", "PC", card1, b2));

        setDiscountedRecycler(discountedProductsList);
        setCategoryRecycler(categoryList);
        setRecentlyViewedRecycler(recentlyViewedList);

    }

    private void setDiscountedRecycler(List<DiscountedProducts> dataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        discountRecyclerView.setLayoutManager(layoutManager);
        discountedProductAdapter = new DiscountedProductAdapter(this,dataList);
        discountRecyclerView.setAdapter(discountedProductAdapter);
    }


    private void setCategoryRecycler(List<Category> categoryDataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        categoryRecyclerView.setLayoutManager(layoutManager);
        categoryAdapter = new CategoryAdapter(this,categoryDataList);
        categoryRecyclerView.setAdapter(categoryAdapter);
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
