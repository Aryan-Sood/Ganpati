package com.abhijeet.ganpatiapp.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.abhijeet.ganpatiapp.R;
import com.abhijeet.ganpatiapp.modelclass.MainPageModelClass;
import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class MainPageAdapter extends RecyclerView.Adapter<MainPageAdapter.ViewHolder> {

    List<MainPageModelClass> list;

    public MainPageAdapter(List<MainPageModelClass> list) {
        this.list = list;
    }


    @NonNull
    @Override
    public MainPageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.main_page_item_design,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainPageAdapter.ViewHolder holder, int position) {

        int color = list.get(position).getColor();

        holder.setData(color);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        MaterialCardView imageView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
        }

        public void setData(int color){
            imageView.setCardBackgroundColor(color);
        }
    }


}
