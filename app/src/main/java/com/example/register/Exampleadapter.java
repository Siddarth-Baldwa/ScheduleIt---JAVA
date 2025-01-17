package com.example.register;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class Exampleadapter extends RecyclerView.Adapter<com.example.register.Exampleadapter.Exampleviewholder> implements Filterable {
    private ArrayList<Exampleitem> mexamplelist;
    private ArrayList<Exampleitem> allexamplelist;
    private OnItemClickListener mlistener;


    public interface OnItemClickListener {
        void onitemclick(int position);

        void ondelete(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listner) {
        mlistener = listner;
    }

    public static class Exampleviewholder extends RecyclerView.ViewHolder {
        public TextView mtitle, mtime, mdate, mrep;
        public ImageView marker;

        public Exampleviewholder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            mtitle = itemView.findViewById(R.id.reltitle);
            mtime = itemView.findViewById(R.id.reltime);
            mdate = itemView.findViewById(R.id.reldate);
            mrep = itemView.findViewById(R.id.relrep);
            marker = itemView.findViewById(R.id.relmarker);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onitemclick(position);
                        }
                    }
                }
            });
        }
    }

    public Exampleadapter(ArrayList<Exampleitem> examplelist) {
        mexamplelist = examplelist;
        allexamplelist = examplelist;

    }

    @NonNull
    @Override
    public Exampleviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item, parent, false);
        Exampleviewholder holder = new Exampleviewholder(v, mlistener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull Exampleviewholder holder, int position) {
        Exampleitem curitem = mexamplelist.get(holder.getAdapterPosition());
        holder.mtitle.setText(curitem.getTitle());
        Process p = new Process();
        holder.mdate.setText(parsedate(p.incrementmonth(curitem.getDate())));
        holder.mtime.setText(parsetime(curitem.getTime()));
        holder.mrep.setText(curitem.getRepeat());
        String color = "Black";
        if (curitem.getMarker() != null) {
            color = curitem.getMarker();
        }
        switch (color) {
            case "Black":
                holder.marker.setImageResource(R.drawable.mblack);
                break;
            case "Red":
                holder.marker.setImageResource(R.drawable.mred);
                break;
            case "Green":
                holder.marker.setImageResource(R.drawable.mgreen);
                break;
            case "Yellow":
                holder.marker.setImageResource(R.drawable.myellow);
                break;
            case "Purple":
                holder.marker.setImageResource(R.drawable.mpurple);
                break;
        }
        if (position % 3 == 0) {
            holder.itemView.setBackgroundColor(Color.parseColor("#26b3de"));
        } else if (position % 3 == 1) {
            holder.itemView.setBackgroundColor(Color.parseColor("#8aedeb"));
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#77d1ed"));
        }
    }

    public String parsedate(String d) {
        if (d.equals("---")) {
            return d;
        }
        String year = d.substring(0, 4), m = d.substring(4, 6), day = d.substring(6, 8);
        int month = Integer.parseInt(m);
        return day + "-" + month + "-" + year;
    }

    public String parsetime(String d) {
        if (d.equals("---")) {
            return d;
        }
        String h = d.substring(0, 2), m = d.substring(2, 4);
        int hr = Integer.parseInt(h);
        Boolean pm = false;
        if (hr >= 12) {
            pm = true;
            hr %= 12;
        }
        if (hr == 0)
            hr = 12;
        h = String.valueOf(hr);
        return h + ":" + m + (pm ? " PM" : " AM");
    }

    @Override
    public int getItemCount() {
        return mexamplelist.size();
    }


    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults filterResults = new FilterResults();

                if (charSequence == null || charSequence.length() == 0) {
                    filterResults.count = allexamplelist.size();
                    filterResults.values = allexamplelist;
                } else {
                    String searchChr = charSequence.toString().toLowerCase();
                    List<Exampleitem> resultData = new ArrayList<>();

                    for (Exampleitem exampleitem: allexamplelist) {
                        if (exampleitem.getTitle().toLowerCase().contains(searchChr) || exampleitem.getDes().toLowerCase().contains(searchChr)){
                            resultData.add(exampleitem);
                        }
                    }
                    filterResults.count = resultData.size();
                    filterResults.values = resultData;
                }
                return filterResults;

            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mexamplelist = (ArrayList<Exampleitem>) filterResults.values;
                notifyDataSetChanged();

            }
        };
        return filter ;
    }

}
