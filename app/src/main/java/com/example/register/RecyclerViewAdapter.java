package com.example.register;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    Context context;
    List<ImageUploadInfo> MainImageUploadInfoList;
    public RecyclerViewAdapter(Context context, List<ImageUploadInfo> TempList) {
        this.MainImageUploadInfoList = TempList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ImageUploadInfo UploadInfo = MainImageUploadInfoList.get(position);
        holder.imageNameTextView.setText(UploadInfo.getImageName());
        Glide.with(context).load(UploadInfo.getImageURL()).asBitmap()
                .listener(new RequestListener() {
                    public boolean onException(Exception e,Object o,Target t,boolean b)
                    {return false;}
                    public boolean onResourceReady(Object o,Object p,Target t,boolean b,boolean c)
                    {return false;}}).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return MainImageUploadInfoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView imageNameTextView;
        public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageView);

            imageNameTextView = itemView.findViewById(R.id.ImageNameTextView);
        }
    }
}