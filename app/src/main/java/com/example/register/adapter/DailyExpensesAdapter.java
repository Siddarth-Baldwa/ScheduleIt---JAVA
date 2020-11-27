package com.example.register.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.register.Notif_Main;
import com.example.register.R;
import com.example.register.model.DailyExpensesModel;

import java.util.List;

public class DailyExpensesAdapter extends RecyclerView.Adapter<DailyExpensesAdapter.CategoryViewHolder> {

    Context context;
    List<DailyExpensesModel> dailyExpensesModelList;

    public DailyExpensesAdapter(Context context, List<DailyExpensesModel> dailyExpensesModelList) {
        this.context = context;
        this.dailyExpensesModelList = dailyExpensesModelList;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.dailyexpenses_items, parent, false);

        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        holder.expenses_name.setText(dailyExpensesModelList.get(position).getExpenses_name());
        holder.categoryImage.setImageResource(dailyExpensesModelList.get(position).getImageurl());
        holder.categoryImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                switch (position){
                    case 0:
                        intent=new Intent(context, Notif_Main.class);
                        context.startActivity(intent);
                        break;
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return dailyExpensesModelList.size();
    }

    public  static class CategoryViewHolder extends RecyclerView.ViewHolder{

        ImageView categoryImage;
        TextView expenses_name;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);

            categoryImage = itemView.findViewById(R.id.expenses_image);
            expenses_name = itemView.findViewById(R.id.expenses_text);

        }
    }

}