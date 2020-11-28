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

import com.example.register.HomeActivity;
import com.example.register.Notif_Main;
import com.example.register.R;
import com.example.register.model.WorkModel;

import java.util.List;

public class WorkAdapter extends RecyclerView.Adapter<WorkAdapter.DiscountedProductViewHolder> {

    Context context;
    List<WorkModel> workModelList;

    public WorkAdapter(Context context, List<WorkModel> workModelList) {
        this.context = context;
        this.workModelList = workModelList;
    }

    @NonNull
    @Override
    public DiscountedProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.work_items, parent, false);
        return new DiscountedProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DiscountedProductViewHolder holder, int position) {
        holder.work_name.setText(workModelList.get(position).getWork_name());
        holder.WorkImageView.setImageResource(workModelList.get(position).getImageurl());
        int identity = workModelList.get(position).getId();
        holder.WorkImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                switch (position){
                    case 0:
                        if(identity == 0) {
                            intent = new Intent(context, Notif_Main.class);
                            intent.putExtra("work", "Meetings");
                            context.startActivity(intent);
                            break;
                        }
                        else{
                            intent=new Intent(context, Notif_Main.class);
                            intent.putExtra("work","Assignments");
                            context.startActivity(intent);
                            break;
                        }

                    case 1:
                        if(identity == 1) {
                            intent = new Intent(context, Notif_Main.class);
                            intent.putExtra("work", "Mails");
                            context.startActivity(intent);
                            break;
                        }
                        else{
                            intent=new Intent(context, Notif_Main.class);
                            intent.putExtra("work","Test Schedule");
                            context.startActivity(intent);
                            break;
                        }
                    case 2:
                        if(identity == 2) {
                            intent = new Intent(context, HomeActivity.class);
                            intent.putExtra("meetings", "Project Goals");
                            context.startActivity(intent);
                            break;
                        }
                        else{
                            intent=new Intent(context, HomeActivity.class);
                            intent.putExtra("meetings","Daily Goals");
                            context.startActivity(intent);
                            break;
                        }

                    case 3:
                        if(identity==3) {
                            intent = new Intent(context, HomeActivity.class);
                            intent.putExtra("meetings", "Points To Remember");
                            context.startActivity(intent);
                            break;
                        }
                        else{
                            intent=new Intent(context, Notif_Main.class);
                            intent.putExtra("work","Submissions");
                            context.startActivity(intent);
                            break;
                        }
                    case 4:
                        if(identity == 4) {
                            intent = new Intent(context, Notif_Main.class);
                            intent.putExtra("work", "Others");
                            context.startActivity(intent);
                            break;
                        }
                        else{
                            intent=new Intent(context, Notif_Main.class);
                            intent.putExtra("work","Submissions");
                            context.startActivity(intent);
                            break;
                        }
                    case 5:
                        if(identity == 5) {
                            intent = new Intent(context, Notif_Main.class);
                            intent.putExtra("work", "Assignments");
                            context.startActivity(intent);
                            break;
                        }
                        else{
                            intent=new Intent(context, Notif_Main.class);
                            intent.putExtra("work","OtherBachelor");
                            context.startActivity(intent);
                            break;
                        }
                   /* case 6:
                        intent=new Intent(context, Notif_Main.class);
                        intent.putExtra("work","Test Schedule");
                        context.startActivity(intent);
                        break;
                    case 7:
                        intent=new Intent(context, HomeActivity.class);
                        intent.putExtra("meetings","Daily Goals");
                        context.startActivity(intent);
                        break;
                    case 8:
                        intent=new Intent(context, Notif_Main.class);
                        intent.putExtra("work","Submissions");
                        context.startActivity(intent);
                        break;
                    case 9:
                        intent=new Intent(context, Notif_Main.class);
                        intent.putExtra("work","OtherBachelor");
                        context.startActivity(intent);
                        break;
*/
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return workModelList.size();
    }

    public static class DiscountedProductViewHolder extends  RecyclerView.ViewHolder{

        ImageView WorkImageView;
        TextView work_name;

        public DiscountedProductViewHolder(@NonNull View itemView) {
            super(itemView);

            WorkImageView = itemView.findViewById(R.id.work_image);
            work_name = itemView.findViewById(R.id.work_text);


        }
    }
}
