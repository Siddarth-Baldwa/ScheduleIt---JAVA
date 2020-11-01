package com.example.register;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;

import java.io.File;
import java.io.FileOutputStream;
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
                    public boolean onException(Exception e, Object o, Target t, boolean b) {
                        return false;
                    }

                    public boolean onResourceReady(Object o, Object p, Target t, boolean b, boolean c) {
                        return false;
                    }
                }).into(holder.imageView);

        holder.btnshare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Drawable drawable=holder.imageView.getDrawable();
                Bitmap bitmap=((BitmapDrawable)drawable).getBitmap();;
                Log.i("SHAREIMAGE","CACHE ENABLED");
                /*content.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED),View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));*/
                try {
                    File file = new File(context.getApplicationContext().getExternalCacheDir(), File.separator +"office.JPG");
                    FileOutputStream fOut = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fOut);
                    fOut.flush();
                    fOut.close();
                    file.setReadable(true, false);
                    final Intent intent = new Intent(android.content.Intent.ACTION_SEND);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                    Uri photoURI = FileProvider.getUriForFile(context.getApplicationContext(), BuildConfig.APPLICATION_ID +".provider", file);
                    intent.putExtra(Intent.EXTRA_STREAM, photoURI);
                    intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                    intent.setType("image/jpg");
                    //context.startActivity(Intent.createChooser(intent, "Share image via"));
                    context.startActivity(intent);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            /*    File root = Environment.getExternalStorageDirectory();
                File cachePath = new File(root.getAbsolutePath() + "/DCIM/Camera/image.jpg");
                Log.i("SHAREIMAGE", String.valueOf(cachePath));
               try {
                    cachePath.createNewFile();
                    Log.i("SHAREIMAGE","AFTER CREATE NEW FILE");
                    FileOutputStream ostream = new FileOutputStream(cachePath);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, ostream);
                    ostream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    content.setDrawingCacheEnabled(false);
                }
                Log.i("SHAREIMAGE","BEFORE CALLING INTENT");
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                share.setType("image/*");
                share.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(cachePath));
                context.startActivity(Intent.createChooser(share, "Share via")); */
            }
        });
    }

    @Override
    public int getItemCount() {
        return MainImageUploadInfoList.size();
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageView;
        public TextView imageNameTextView;
        public Button btnshare;
        public ViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageViewNew);
            btnshare = itemView.findViewById(R.id.share);
            imageNameTextView = itemView.findViewById(R.id.ImageNameTextView);
        }
    }
}