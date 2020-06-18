package com.example.tempapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Locale;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private Context context;
    private List<ListItem> listItems;




    public Adapter(List<ListItem> listItems, Context applicationContext) {
        this.context = applicationContext;
        this.listItems = listItems;
    }

    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ListItem listItem = listItems.get(position);

        holder.tvdist.setText(listItem.getDistrict());
        holder.tvrecover.setText(listItem.getRecover());
        holder.tvdeaths.setText(listItem.getDeaths());
        holder.tvcases.setText(listItem.getCases());
        holder.tvactive.setText(listItem.getActive());
        holder.tvstate.setText(listItem.getState());

    }

    @Override
    public int getItemCount() {
        return listItems.size();
    }



    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView tvstate,tvdist,tvactive,tvcases,tvdeaths,tvrecover;

        public ViewHolder(View itemView) {
            super(itemView);

            tvstate = itemView.findViewById(R.id.tvstate);
            tvdist = itemView.findViewById(R.id.tvdist);
            tvactive = itemView.findViewById(R.id.tvactive);
            tvcases = itemView.findViewById(R.id.tvcases);
            tvdeaths = itemView.findViewById(R.id.tvdeaths);
            tvrecover = itemView.findViewById(R.id.tvrecovered);
        }
    }

    public void filterList(List<ListItem> filteredList){
        this.listItems = filteredList;
        notifyDataSetChanged();
    }

    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        listItems.clear();
        if(charText.length() == 0){

        }else{

        }
    }
}
